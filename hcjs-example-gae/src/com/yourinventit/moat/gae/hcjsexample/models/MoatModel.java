/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moat-iot-highcharts-example
 */
package com.yourinventit.moat.gae.hcjsexample.models;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.yourinventit.moat.gae.hcjsexample.Constants;

/**
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public abstract class MoatModel {

	private static final Logger LOGGER = Logger.getLogger(MoatModel.class
			.getName());

	private static final byte[] LOCK = new byte[0];
	private static final String[] PARAMS = { "offset", "limit", "f", "r" };
	private static String authToken;
	private static long lastAccessed;

	/**
	 * @return the sysAuth
	 */
	protected static String getAuthToken() {
		synchronized (LOCK) {
			final long now = System.currentTimeMillis();
			if (authToken == null || now - lastAccessed > 60 * 25 * 1000) {
				lastAccessed = now;
				final Constants constants = Constants.getInstance();
				final StringBuilder builder = new StringBuilder(
						constants.getRestUri()).append(constants.getRestPath());
				if (builder.charAt(builder.length() - 1) != '/') {
					builder.append("/");
				}
				builder.append("sys/auth?a=")
						.append(constants.getApplicationId()).append("&u=")
						.append(constants.getClientId()).append("&c=")
						.append(constants.getClientSecret());
				final JSONObject result = issue(builder.toString(), "GET", null);
				authToken = result.optString("accessToken");
				if (authToken == null || authToken.length() == 0) {
					throw new IllegalStateException(
							"Credentials information is wrong. Check your constatns.properties.");
				}
			}
		}
		return authToken;
	}

	/**
	 * 
	 * @param c
	 * @param options
	 * @return
	 * @throws JSONException
	 */
	protected static StringBuilder buildUrl(Class<?> c, JSONObject options)
			throws JSONException {
		final Constants constants = Constants.getInstance();
		final StringBuilder builder = new StringBuilder(constants.getRestUri())
				.append(constants.getRestPath());
		if (builder.charAt(builder.length() - 1) != '/') {
			builder.append("/");
		}
		if (SysDevice.class.isAssignableFrom(c)
				|| SysDmjob.class.isAssignableFrom(c)) {
			builder.append("sys/").append(
					c.getSimpleName().substring(3).toLowerCase());
		} else {
			if (options != null && options.has("device")) {
				builder.append("sys/device/")
						.append(options.getString("device")).append("/");
			}
			builder.append(constants.getPackageId()).append("/")
					.append(c.getSimpleName().toLowerCase());
		}
		if (options != null && options.has("uid")) {
			builder.append("/").append(options.getString("uid"));
		}
		return builder;
	}

	/**
	 * 
	 * @param c
	 * @param options
	 * @return
	 */
	protected static <T> T find(Class<?> c, JSONObject options) {
		try {
			final StringBuilder builder = buildUrl(c, options);
			builder.append("?token=").append(getAuthToken());

			for (String param : PARAMS) {
				if (options.has(param)) {
					builder.append("&").append(param).append("=")
							.append(options.getString(param));
				}
			}
			return issue(builder.toString(), "GET", null);

		} catch (JSONException exception) {
			throw new IllegalArgumentException(exception);
		}
	}

	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected static <T> T fromInputStream(InputStream inputStream) {
		String json = "";
		try {
			final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			final byte[] buff = new byte[1024];
			int read = -1;
			while ((read = inputStream.read(buff)) >= 0) {
				outputStream.write(buff, 0, read);
			}
			final byte[] bodyArray = outputStream.toByteArray();
			if (bodyArray.length > 0) {
				json = new String(bodyArray, "utf-8");
				if (bodyArray[0] == '[') {
					return (T) new JSONArray(json);
				} else {
					return (T) new JSONObject(json);
				}
			} else {
				return (T) new JSONObject();
			}
		} catch (JSONException e) {
			LOGGER.warning("RESPONSE BODY => " + json);
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 
	 * @param dest
	 * @param method
	 * @param body
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected static <T> T issue(String dest, String method, Object body) {
		try {
			final URL url = new URL(dest);
			final HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(method);
			connection.setDoInput(true);
			if ("POST".equalsIgnoreCase(method)
					|| "PUT".equalsIgnoreCase(method)) {
				connection.setDoOutput(true);
				final String bodyString = body.toString();
				final byte[] output = bodyString.getBytes("utf-8");
				connection.setRequestProperty("Content-Length",
						String.valueOf(output.length));
				connection.setRequestProperty("Content-Type",
						"application/json;charset=utf-8");
				final OutputStream outputStream = connection.getOutputStream();
				outputStream.write(output);
				outputStream.flush();
				LOGGER.fine("bodyString==>" + bodyString);
			} else {
				connection.connect();
			}
			final int status = connection.getResponseCode();
			final InputStream inputStream = (status / 100 != 2) ? connection
					.getErrorStream() : connection.getInputStream();
			LOGGER.fine("dest:" + dest + ", method:" + method + ", STATUS:"
					+ status);
			final Object result = fromInputStream(inputStream);
			LOGGER.fine("==>" + result);
			return (T) result;

		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 
	 * @param string
	 * @param rfc2822
	 * @return
	 * @throws ParseException
	 */
	protected static Date toDate(String string, DateFormat rfc2822)
			throws ParseException {
		if (string == null || string.length() == 0) {
			return null;
		}
		return rfc2822.parse(string);
	}

	/**
	 * 
	 * @return
	 */
	protected static DateFormat getRfc2822Format() {
		final DateFormat rfc2822 = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss z", Locale.UK);
		rfc2822.setTimeZone(TimeZone.getTimeZone("GMT"));
		return rfc2822;
	}

	/**
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	protected static <T extends MoatModel> List<T> find(Class<T> c, int offset,
			int limit) {
		return find(c, null, offset, limit);
	}

	/**
	 * 
	 * @param c
	 * @param uid
	 * @param deviceUid
	 */
	protected static <T extends MoatModel> void delete(Class<T> c, String uid,
			String deviceUid) {
		try {
			final JSONObject options = new JSONObject();
			if (uid != null && uid.length() > 0) {
				options.put("uid", uid);
			}
			if (deviceUid != null && deviceUid.length() > 0) {
				options.put("device", deviceUid);
			}
			if (options.length() == 0) {
				return;
			}
			final StringBuilder builder = buildUrl(c, options);
			builder.append("?token=").append(getAuthToken());
			issue(builder.toString(), "DELETE", null);

		} catch (JSONException exception) {
			throw new IllegalArgumentException(exception);
		}

	}

	/**
	 * 
	 * @param entity
	 *            the object will be updated after posting dmjob is successful.
	 */
	public static <T extends MoatModel> T save(T entity) {
		if (entity == null) {
			return null;
		}
		try {
			final StringBuilder builder = buildUrl(entity.getClass(), null);
			builder.append("?token=").append(getAuthToken());
			final JSONObject jsonObject = issue(builder.toString(), "POST",
					entity.asJson());
			entity.updateFrom(jsonObject);
			return entity;

		} catch (JSONException exception) {
			throw new IllegalArgumentException(exception);
		}

	}

	/**
	 * @param c
	 * @param deviceUid
	 * @param offset
	 * @param limit
	 * @return
	 */
	protected static <T extends MoatModel> List<T> find(Class<T> c,
			String deviceUid, int offset, int limit) {

		final JSONObject options = new JSONObject();
		try {
			if (deviceUid != null && deviceUid.length() > 0) {
				options.put("device", deviceUid);
			}
			if (offset >= 0) {
				options.put("offset", offset);
			}
			if (limit >= 0) {
				options.put("limit", limit);
			}
			final Object result = find(c, options);
			JSONArray array = null;
			if (result instanceof JSONArray) {
				array = (JSONArray) result;
			} else if (result == null) {
				return Collections.<T> emptyList();
			} else if (((JSONObject) result).has("array")) {
				array = ((JSONObject) result).getJSONArray("array");
			}
			if (array == null || array.length() == 0) {
				return Collections.<T> emptyList();
			}
			final List<T> list = new ArrayList<T>(array.length());
			for (int i = 0; i < array.length(); i++) {
				final T model = c.newInstance();
				model.updateFrom(array.getJSONObject(i));
				list.add(model);
			}
			return list;

		} catch (JSONException e) {
			throw new IllegalArgumentException(e);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}

	}

	/**
	 * 
	 * @return
	 */
	public abstract String asJson();

	/**
	 * 
	 * @param jsonObject
	 */
	public abstract void updateFrom(JSONObject jsonObject);
}
