var nodeUnit = require('nodeunit');
var sinon = require('sinon');
var script = require('path').resolve('./upload-sensing-data!1.0.js');
var moat = require('moat');

module.exports = nodeUnit.testCase({
  setUp: function(callback) {
	require.cache[script] = null;
	callback();
  },
  tearDown: function(callback) {
  	callback();
  },
  'Data Upload, successful case.' : function(assert) {
	// record state
    var context = moat.init(sinon);
    var arguments = {
    };
    context.setDevice('uid', 'deviceId', 'name', 'status', 'clientVersion', 0);
    context.setDmjob('uid', 'deviceId', 'name', 'status', 'jobServiceId',
			'sessionId', arguments, 'createdAt', 'activatedAt', 'startedAt',
			'expiredAt', 'http', 'http://localhost');
	var results = [
		{
			uid: 'uid',
			timestamp: new Date().getTime(),
			da: 'DC',
			value: 5.5,
			unit: 'V'
		}
	];
	context.setObjects(results);
    var session = context.session;

    // Run the script (replay state)
    require(script);

    // Assertion
    assert.equal(false, session.commit.called);
    assert.equal(false, session.setWaitingForResultNotification.withArgs(true).called);
	assert.equal(true, session.notifyAsync.withArgs({
		success: true,
		data: results
	}).calledOnce);
    assert.done();
  },

  'Data Upload, error case.' : function(assert) {
	// record state
    var context = moat.init(sinon);
    var arguments = {
    };
    context.setDevice('uid', 'deviceId', 'name', 'status', 'clientVersion', 0);
    context.setDmjob('uid', 'deviceId', 'name', 'status', 'jobServiceId',
			'sessionId', arguments, 'createdAt', 'activatedAt', 'startedAt',
			'expiredAt', 'http', 'http://localhost');
	context.setObjects([]);
    var session = context.session;

    // Run the script (replay state)
	try {
    	require(script);
		assert.fail();
	} catch (e) {
	    assert.equal("No Result object!", e);
	}
    // Assertion
    assert.equal(false, session.commit.called);
    assert.equal(false, session.setWaitingForResultNotification.withArgs(true).called);
    assert.done();
  }
});

