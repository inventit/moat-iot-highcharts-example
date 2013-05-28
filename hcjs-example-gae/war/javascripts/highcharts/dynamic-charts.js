/*
 * Copyright (C) 2013 InventIt Inc.
 *
 * See https://github.com/inventit/moat-iot-highcharts-example
 */

function print_added_item(str) {
    var title = str;
    var count = 0;
    return function(unixt, val) {
        var tt = new Date(unixt);
        console.log(title + ' added. time: ' + tt.toLocaleString() +
                    ' value: ' + val + ' total: ' + ++count);
    }
}

(function($) { // encapsulate jQuery

$(function() {
    $(document).ready(function() {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
        var chart;
        $('#container').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                //marginRight: 10,
                events: {
                    load: function() {
                        var series_ampere = this.series[0];
                        var series_voltage = this.series[1];
                        var this_chart = this;
                        setInterval(function() {
                            var now = new Date();
                            var tz_offset_msec = now.getTimezoneOffset() * 60 * 1000;
                            console.log('[' + now.toLocaleString() +
                                        '] local timezone offset(min): ' +
                                        now.getTimezoneOffset());
                            $.get('/sensing_data/query?n=20', function(getdata) {
                                var addam = print_added_item('ampere');
                                var addvol = print_added_item('voltage');
                                var json_str = JSON.stringify(getdata);
                                console.log('json:' + json_str);
                                getdata.sort(function(a, b) {
                                    if (a.timestamp > b.timestamp) {
                                        return 1;
                                    } else if (a.timestamp < b.timestamp) {
                                        return -1;
                                    } else {
                                        return 0;
                                    }
                                });
                                console.log('get item(s): ' + getdata.length);
                                for (var i = 0; i < getdata.length; i++) {
                                    var is_date_exist = function(seriesarray, timestamp) {
                                        for (j = 0; j < seriesarray.length; j++) {
                                            if (seriesarray[j].x == timestamp) {
                                                return true;
                                            }
                                        }
                                        return false;
                                    };
                                    with (getdata[i]) {
                                        if (unit == 'mA' || unit == 'A') {
                                            var get_ampere_value = function(type, val) {
                                                 return (type == 'mA') ? val : val * 1000;
                                            };

                                            if (!is_date_exist(series_ampere.data, timestamp)) {
                                                addam(timestamp, value);
                                                series_ampere.addPoint([timestamp, get_ampere_value(unit, value)], false, true);
                                            }
                                        } else if (unit == 'V' || unit == 'mV') {
                                            var get_voltage_value = function(type, val) {
                                                 return (type == 'V') ? val : val * 0.001;
                                            };

                                            if (!is_date_exist(series_voltage.data, timestamp)) {
                                                addvol(timestamp, value);
                                                series_voltage.addPoint([timestamp, get_voltage_value(unit, value)], false, true);
                                            }
                                        } else {
                                            console.log('unknown unit: ' + unit);
                                        }
                                    }
                                }
                                this_chart.redraw();
                                console.log('@@ ampere item(s) : ' + series_ampere.data.length);
                                for (j = 0; j < series_ampere.data.length; ++j) {
                                    var tt = new Date(series_ampere.data[j].x);
                                    console.log('  time: ' + tt.toLocaleString() +
                                                ' value: ' + series_ampere.data[j].y);
                                }
                                console.log('@@ voltage item(s) : ' + series_voltage.data.length);
                                for (j = 0; j < series_voltage.data.length; ++j) {
                                    var tt = new Date(series_voltage.data[j].x);
                                    console.log('  time: ' + tt.toLocaleString() +
                                                ' value: ' + series_voltage.data[j].y);
                                }
                            });
                        }, 30000);
                    }
                }
            },
            title: {
                text: 'Live solar power data'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: [{
                title: {
                    text: 'mA'
                },
                labels: {
                    formatter: function() {
                        return this.value + ' mA';
                    },
                    style: {
                        color: '#808080'
                    }
                }
            }, {
                title: {
                    text: 'V'
                },
                labels: {
                    formatter: function() {
                        return this.value + ' V';
                    },
                    style: {
                        color: '#808080'
                    }
                },
                opposite: true
            }],
            tooltip: {
                formatter: function() {
                        return 'Category: ' + '<b>' + this.series.name + '</b><br/>' +
                        'Date: ' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        'Value: ' + Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Ampere',
                tooltip: {
                    valueSuffix: 'mA'
                },
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime() - 100000,
                        i;
                    for (i = -9; i <= 0; i++) {
                        data.push({
                            x: time + i * 10000,
                            y: 0
                        });
                    }
                    return data;
                })()
            }, {
                name: 'Voltage',
                yAxis: 1,
                tooltip: {
                    valueSuffix: 'V'
                },
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime() - 100000,
                        i;
                    for (i = -9; i <= 0; i++) {
                        data.push({
                            x: time + i * 10000,
                            y: 0
                        });
                    }
                    return data;
                })()
            }]
        });
    });
});
})(jQuery);
