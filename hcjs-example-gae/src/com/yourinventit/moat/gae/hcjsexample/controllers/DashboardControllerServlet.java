/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moat-iot-highcharts-example
 */
package com.yourinventit.moat.gae.hcjsexample.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.datanucleus.util.StringUtils;

import com.yourinventit.moat.gae.hcjsexample.models.SensingData;
import com.yourinventit.moat.gae.hcjsexample.models.SysDevice;
import com.yourinventit.moat.gae.hcjsexample.models.SysDmjob;

/**
 * 
 * @author dbaba@yourinventit.com
 * 
 */
@SuppressWarnings("serial")
public class DashboardControllerServlet extends HttpServlet {

	static final String VIEW_PATH = "/dashboard.jsp";

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final String pathInfo = req.getPathInfo();
		if ("/delete_device".equalsIgnoreCase(pathInfo)) {
			// delete_device
			SysDevice.delete(req.getParameter("device_uid"));
		} else if ("/cancel".equalsIgnoreCase(pathInfo)) {
			// cancel
			SysDmjob.delete(req.getParameter("uid"));
		} else if ("/delete_all_sensing_data".equalsIgnoreCase(pathInfo)) {
			// delete_all_sensing_data
			SensingData.deleteAll();
		}
		// index
		final List<SysDevice> devices = SysDevice.find(0, -1);
		req.setAttribute("devices", devices);
		final Map<String, List<SensingData>> sensingDataList = new HashMap<String, List<SensingData>>();
		final String n = req.getParameter("n");
		final int nInt = StringUtils.isEmpty(n) ? -1 : Integer.valueOf(n);
		for (SysDevice device : devices) {
			sensingDataList.put(device.getName(),
					SensingData.find(device.getName(), nInt));
		}
		req.setAttribute("sensing_data_list", sensingDataList);
		req.setAttribute("job_list", SysDmjob.find(0, -1));

		req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
	}
}
