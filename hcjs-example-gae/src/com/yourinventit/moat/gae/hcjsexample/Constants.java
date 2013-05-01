/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moat-iot-highcharts-example
 */
package com.yourinventit.moat.gae.hcjsexample;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class Constants {

	/**
	 * Singleton instance
	 */
	private static final Constants INSTANCE = new Constants();

	/**
	 * Singleton accessor
	 * 
	 * @return
	 */
	public static Constants getInstance() {
		return INSTANCE;
	}

	/**
	 * Loaded properties
	 */
	private final Properties properties = new Properties();

	/**
	 * Protected constructor
	 */
	protected Constants() {
		try {
			properties.load(Constants.class
					.getResourceAsStream("/constants.properties"));
			filter(properties);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

	}

	/**
	 * Applying substitution filter
	 * 
	 * @param properties
	 * @return
	 */
	private static Properties filter(Properties properties) {
		for (Object keyObject : properties.keySet()) {
			final String key = keyObject.toString();
			final String value = properties.getProperty(key);
			for (Entry<Object, Object> entry : properties.entrySet()) {
				entry.setValue(entry.getValue().toString()
						.replaceAll("#\\{" + key + "\\}", value));
			}
		}
		return properties;
	}

	public String getApplicationId() {
		return properties.getProperty("APPLICATION_ID", "");
	}

	public String getClientId() {
		return properties.getProperty("API_CLIENT_ID", "");
	}

	public String getClientSecret() {
		return properties.getProperty("API_CLIENT_SECRET", "");
	}

	public String getRestUri() {
		return properties.getProperty("REST_URI", "");
	}

	public String getRestPath() {
		return properties.getProperty("REST_PATH", "");
	}

	public String getPackageId() {
		return properties.getProperty("PACKAGE_ID", "");
	}

	public String getGoogleUserId() {
		return properties.getProperty("GOOGLE_USER_ID", "");
	}
}
