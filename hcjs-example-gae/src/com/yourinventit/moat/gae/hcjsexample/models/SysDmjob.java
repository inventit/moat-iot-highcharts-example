/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moat-iot-highcharts-example
 */
package com.yourinventit.moat.gae.hcjsexample.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class SysDmjob extends MoatModel {

	private String uid;

	private String name;

	private String status;

	private String jobServiceId;

	private Date activatedAt;

	private Date startedAt;

	private Date expiredAt;

	private JSONObject arguments;

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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the jobServiceId
	 */
	public String getJobServiceId() {
		return jobServiceId;
	}

	/**
	 * @param jobServiceId
	 *            the jobServiceId to set
	 */
	public void setJobServiceId(String jobServiceId) {
		this.jobServiceId = jobServiceId;
	}

	/**
	 * @return the activatedAt
	 */
	public Date getActivatedAt() {
		return activatedAt;
	}

	/**
	 * @param activatedAt
	 *            the activatedAt to set
	 */
	public void setActivatedAt(Date activatedAt) {
		this.activatedAt = activatedAt;
	}

	/**
	 * @return the startedAt
	 */
	public Date getStartedAt() {
		return startedAt;
	}

	/**
	 * @param startedAt
	 *            the startedAt to set
	 */
	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	/**
	 * @return the expiredAt
	 */
	public Date getExpiredAt() {
		return expiredAt;
	}

	/**
	 * @param expiredAt
	 *            the expiredAt to set
	 */
	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

	/**
	 * @return the arguments
	 */
	public JSONObject getArguments() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(JSONObject arguments) {
		this.arguments = arguments;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.moat.gae.hcjsexample.models.MoatModel#asJson()
	 */
	@Override
	public String asJson() {
		final StringBuilder builder = new StringBuilder();
		final DateFormat dateFormat = getRfc2822Format();
		builder.append("{").append("\"jobServiceId\":\"").append(jobServiceId)
				.append("\",\"name\":\"").append(name)
				.append("\",\"activatedAt\":\"")
				.append(dateFormat.format(activatedAt))
				.append("\",\"expiredAt\":\"")
				.append(dateFormat.format(expiredAt))
				.append("\",\"arguments\":{");
		if (arguments != null && arguments.length() > 0) {
			try {
				for (Iterator<?> iterator = arguments.keys(); iterator
						.hasNext();) {
					final String key = iterator.next().toString();
					builder.append("\"").append(key).append("\":");
					final Object value = arguments.get(key);
					if (value instanceof Number || value instanceof Boolean) {
						builder.append(value);
					} else {
						builder.append("\"").append(value).append("\"");
					}
					builder.append(",");
				}
				builder.delete(builder.length() - 1, builder.length());
			} catch (JSONException e) {
				throw new IllegalStateException(e);
			}
		}
		builder.append("}}");
		return builder.toString();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.moat.gae.hcjsexample.models.MoatModel#updateFrom(com.google.appengine.labs.repackaged.org.json.JSONObject)
	 */
	@Override
	public void updateFrom(JSONObject jsonObject) {
		setUid(jsonObject.optString("uid"));
		setName(jsonObject.optString("name"));
		setJobServiceId(jsonObject.optString("jobServiceId"));
		setStatus(jsonObject.optString("status"));
		final DateFormat rfc2822 = getRfc2822Format();
		try {
			setActivatedAt(toDate(jsonObject.optString("activatedAt"), rfc2822));
			setStartedAt(toDate(jsonObject.optString("startedAt"), rfc2822));
			setExpiredAt(toDate(jsonObject.optString("expiredAt"), rfc2822));
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	public static List<SysDmjob> find(int offset, int limit) {
		return find(SysDmjob.class, offset, limit);
	}

	/**
	 * 
	 * @param uid
	 */
	public static void delete(String uid) {
		delete(SysDmjob.class, uid, null);
	}

}
