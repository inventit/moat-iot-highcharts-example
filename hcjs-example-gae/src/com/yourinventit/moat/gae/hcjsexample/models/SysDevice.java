/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moat-iot-highcharts-example
 */
package com.yourinventit.moat.gae.hcjsexample.models;

import java.util.List;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class SysDevice extends MoatModel {

	private String uid;

	private String deviceId;

	private String name;

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.moat.gae.hcjsexample.models.MoatModel#asJson()
	 */
	@Override
	public String asJson() {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.moat.gae.hcjsexample.models.MoatModel#updateFrom(org.json.JSONObject)
	 */
	@Override
	public void updateFrom(JSONObject jsonObject) {
		try {
			setUid(jsonObject.getString("uid"));
			setName(jsonObject.getString("name"));
			setDeviceId(jsonObject.getString("deviceId"));
		} catch (JSONException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	public static List<SysDevice> find(int offset, int limit) {
		return find(SysDevice.class, offset, limit);
	}

	/**
	 * 
	 * @param deviceUid
	 */
	public static void delete(String deviceUid) {
		delete(SysDevice.class, deviceUid, null);
	}
}
