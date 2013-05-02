/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moat-iot-highcharts-example
 */
package com.yourinventit.moat.gae.hcjsexample.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yourinventit.moat.gae.hcjsexample.models.SensingData;

/**
 * 
 * @author dbaba@yourinventit.com
 * 
 */
@SuppressWarnings("serial")
public class SensingDataControllerServlet extends HttpServlet {

	/**
	 * {@link Logger}
	 */
	private static final Logger LOGGER = Logger
			.getLogger(SensingDataControllerServlet.class.getName());

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final String pathInfo = req.getPathInfo();
		if ("/create".equalsIgnoreCase(pathInfo)) {
			// create
			final List<SensingData> result = SensingData.save(req
					.getInputStream());
			LOGGER.info("Data Arrived." + result.size() + " records.");
		}
	}

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
		if ("/query".equalsIgnoreCase(pathInfo)) {
			final String deviceName = req.getParameter("deviceName");
			final String n = req.getParameter("n");
			final List<SensingData> list = SensingData.find(deviceName,
					n == null ? -1 : Integer.valueOf(n));
			final String result = SensingData.asJson(list);
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().print(result);
			resp.flushBuffer();
		}
	}
}
