/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moat-iot-highcharts-example
 */
package com.yourinventit.moat.gae.hcjsexample.controllers;

import java.io.IOException;

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
			SensingData.save(req.getInputStream());
		}
	}
}
