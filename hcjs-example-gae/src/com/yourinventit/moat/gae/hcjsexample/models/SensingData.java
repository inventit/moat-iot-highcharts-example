/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moat-iot-highcharts-example
 */
package com.yourinventit.moat.gae.hcjsexample.models;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * 
 * @author dbaba@yourinventit.com
 * 
 */
@PersistenceCapable
public class SensingData extends MoatModel {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.UUIDSTRING)
	private String uid;

	@Persistent
	private String deviceName;

	@Persistent
	private String da;

	@Persistent
	private float value;

	@Persistent
	private String unit;

	@Persistent
	private long timestamp;

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
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName
	 *            the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the da
	 */
	public String getDa() {
		return da;
	}

	/**
	 * @param da
	 *            the da to set
	 */
	public void setDa(String da) {
		this.da = da;
	}

	/**
	 * @return the value
	 */
	public float getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(float value) {
		this.value = value;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the time
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTimestamp(long time) {
		this.timestamp = time;
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
	 * @see com.yourinventit.moat.gae.hcjsexample.models.MoatModel#updateFrom(com.google.appengine.labs.repackaged.org.json.JSONObject)
	 */
	@Override
	public void updateFrom(JSONObject jsonObject) {
		setUid(jsonObject.optString("uid"));
		setTimestamp(jsonObject.optLong("timestamp"));
		setDa(jsonObject.optString("da"));
		setUnit(jsonObject.optString("unit"));
		setValue((float) jsonObject.optDouble("value"));
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensingData> find(String deviceName, long timestamp) {
		final PersistenceManager persistenceManager = PMF.get()
				.getPersistenceManager();
		try {
			final Query query = persistenceManager.newQuery(SensingData.class);
			query.setOrdering("timestamp descending");
			query.setFilter("deviceName == deviceNameParam && timestamp >= timestampParam");
			query.declareParameters("String deviceNameParam, Long timestampParam");
			return (List<SensingData>) query.execute(deviceName, timestamp);
		} finally {
			persistenceManager.close();
		}
	}

	/**
	 * 
	 */
	public static long deleteAll() {
		final PersistenceManager persistenceManager = PMF.get()
				.getPersistenceManager();
		try {
			// remove all entities
			return persistenceManager.newQuery(SensingData.class)
					.deletePersistentAll();
		} finally {
			persistenceManager.close();
		}
	}

	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	public static List<SensingData> save(InputStream inputStream) {
		final PersistenceManager persistenceManager = PMF.get()
				.getPersistenceManager();
		final JSONObject jsonObject = fromInputStream(inputStream);
		final JSONObject notifyAsync = jsonObject.optJSONObject("notifyAsync");
		final JSONArray dataArray = notifyAsync.optJSONArray("data");
		try {
			final List<SensingData> result = new ArrayList<SensingData>(
					dataArray.length());
			for (int i = 0; i < dataArray.length(); i++) {
				final SensingData sensingData = new SensingData();
				sensingData.updateFrom(dataArray.optJSONObject(i));
				result.add(persistenceManager.makePersistent(sensingData));
			}
			return result;
		} finally {
			persistenceManager.close();
		}
	}

}
