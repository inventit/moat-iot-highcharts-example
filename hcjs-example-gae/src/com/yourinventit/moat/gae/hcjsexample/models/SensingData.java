/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moat-iot-highcharts-example
 */
package com.yourinventit.moat.gae.hcjsexample.models;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * 
 * @author dbaba@yourinventit.com
 * 
 */
@PersistenceCapable
public class SensingData extends MoatModel {

	private static final Logger LOGGER = Logger.getLogger(SensingData.class
			.getName());

	private static final boolean DATA_STORE_ENABLED = false;

	/**
	 * 10 minutes
	 */
	private static final long CHECKING_INTERVAL_MS = 60 * 10 * 1000;

	/**
	 * Last removed timestamp
	 */
	private static long lastRemoved = 0;

	private static final List<SensingData> MEMORY_STORE = new ArrayList<SensingData>();

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.UUIDSTRING)
	private String uid;

	@Persistent
	private String deviceName;

	@Persistent
	private String da;

	@Persistent
	private float value = 0.0f;

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
		return toJSONObject().toString();
	}

	/**
	 * 
	 * @return
	 */
	protected JSONObject toJSONObject() {
		final JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("uid", getUid());
			jsonObject.put("timestamp", getTimestamp());
			jsonObject.put("da", getDa());
			jsonObject.put("unit", getUnit());
			if (!Float.isInfinite(getValue())) {
				jsonObject.put("value", getValue());
			}
			return jsonObject;
		} catch (JSONException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String asJson(List<SensingData> list) {
		final JSONArray array = new JSONArray();
		for (SensingData data : list) {
			array.put(data.toJSONObject());
		}
		return array.toString();
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
	 */
	public static long deleteOlderThan(long time) {
		final PersistenceManager persistenceManager = PMF.get()
				.getPersistenceManager();
		try {
			final Query query = persistenceManager.newQuery(SensingData.class);
			query.setFilter("timestamp < timestampParam");
			query.declareParameters("Long timestampParam");
			return query.deletePersistentAll(time);
		} finally {
			persistenceManager.close();
		}
	}

	static JSONArray extractDataJSONArray(JSONObject jsonObject) {
		final JSONObject arguments = jsonObject.optJSONObject("arguments");
		final JSONObject notifyAsync = arguments.optJSONObject("notifyAsync");
		final JSONArray dataResultArray = notifyAsync.optJSONArray("data");
		final JSONObject dataResult = dataResultArray.optJSONObject(0);
		return dataResult.optJSONArray("array");
	}

	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	public static List<SensingData> save(InputStream inputStream) {
		if (DATA_STORE_ENABLED) {
			final PersistenceManager persistenceManager = PMF.get()
					.getPersistenceManager();
			final JSONObject jsonObject = fromInputStream(inputStream);
			try {
				final JSONArray dataArray = extractDataJSONArray(jsonObject);
				final List<SensingData> result = new ArrayList<SensingData>(
						dataArray.length());
				for (int i = 0; i < dataArray.length(); i++) {
					final SensingData sensingData = new SensingData();
					sensingData.updateFrom(dataArray.optJSONObject(i));
					result.add(sensingData);
				}
				persistenceManager.makePersistentAll(result);
				final long now = System.currentTimeMillis();
				if (now - lastRemoved > CHECKING_INTERVAL_MS) {
					deleteOlderThan(now - CHECKING_INTERVAL_MS);
					lastRemoved = now;
				}
				return result;
			} catch (RuntimeException e) {
				LOGGER.severe("[ERROR:save] => " + jsonObject);
				throw e;
			} finally {
				persistenceManager.close();
			}
		} else {
			return saveOnMemory(inputStream);
		}

	}

	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	public static List<SensingData> saveOnMemory(InputStream inputStream) {
		final JSONObject jsonObject = fromInputStream(inputStream);
		try {
			final JSONArray dataArray = extractDataJSONArray(jsonObject);
			final List<SensingData> result = new ArrayList<SensingData>(
					dataArray.length());
			for (int i = 0; i < dataArray.length(); i++) {
				final SensingData sensingData = new SensingData();
				sensingData.updateFrom(dataArray.optJSONObject(i));
				result.add(sensingData);
			}
			synchronized (MEMORY_STORE) {
				final long maxDiff = MEMORY_STORE.size() - CHECKING_INTERVAL_MS
						/ 1000;
				if (maxDiff > 0) {
					for (int i = 0; i < maxDiff; i++) {
						MEMORY_STORE.remove(0);
					}
				}
				MEMORY_STORE.addAll(result);
			}
			return result;
		} catch (RuntimeException e) {
			LOGGER.severe("[ERROR:save] => " + jsonObject);
			throw e;
		}
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensingData> find(String deviceName, int n) {
		if (DATA_STORE_ENABLED) {
			final PersistenceManager persistenceManager = PMF.get()
					.getPersistenceManager();
			try {
				final Query query = persistenceManager
						.newQuery(SensingData.class);
				query.setOrdering("timestamp descending");
				query.setFilter("deviceName == deviceNameParam");
				query.declareParameters("String deviceNameParam");
				final List<SensingData> result = (List<SensingData>) query
						.execute(deviceName);
				if (n < 1 || n >= result.size()) {
					return result;
				}
				return result.subList(result.size() - n, result.size());
			} finally {
				persistenceManager.close();
			}
		} else {
			// ignore deviceName
			return findOnMemory(n);
		}
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public static List<SensingData> findOnMemory(int n) {
		synchronized (MEMORY_STORE) {
			if (n < 1 || n >= MEMORY_STORE.size()) {
				return new ArrayList<SensingData>(MEMORY_STORE);
			}
			return MEMORY_STORE.subList(MEMORY_STORE.size() - n,
					MEMORY_STORE.size());
		}
	}
}
