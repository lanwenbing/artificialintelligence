/**
 * COPYRIGHT NOTICE Copyright (c) 2016 by Lynn. All rights
 * reserved. These materials are confidential and proprietary to Lynn
 * No part of this code may be reproduced, published in any form by
 * any means (electronic or mechanical, including photocopy or any information
 * storage or retrieval system), nor may the materials be disclosed to third
 * parties, or used in derivative works without the express written
 * authorization of Lynn.
 */
package com.artificialintelligence.dao.machinelearning.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class to load the given properties files and it provide method 
 * the access the property values
 */
public final class PropertyUtil {

	//Logger
	private static final Log LOG = LogFactory.getLog(PropertyUtil.class);

	/** Holds the properties read from the Properties file */
	private static final Properties stateProps = new Properties();
	
	public static final String PROPERTIES_FILE_NAME = BaseConstants.AI_PROP_FILE_NOLANG;

	/**
	 * Instantiate the propertyUtil object at load time
	 */
	static {
		new PropertyUtil();
	}

	/**
	 * private Constructor
	 * Call the load method to load the property files
	 *
	 */
	private PropertyUtil() {

		load();

	}

	/**
	 * Gets the value for a given property key
	 *
	 * @param key        key for which the value needs to be fetched
	 * @param fileType	 specifies from which property file object
	 * 					 value has to return based on provided key
	 * @param language   language for which the value come form the user language   
	 * 
	 * @return value     value of the key
	 * @throws IOException if any exception occurs while fetching value
	 */
	public static String getProperty(String key,String language, String fileName)
			throws IOException {
		if (Util.isEmpty(key)) {
			return null;
		}

		String propertyValue = null;
		String countryCode = "US";
		
        if((language == null) || ("".equals(language))) {
        	language = "en";
        } else {
        	String[] codes = language.split("_");
        	language = codes[0];
        	if (codes.length == 2) {
        		countryCode = codes[1];
        	}
        }
        
        Locale locale = new Locale(language, countryCode);
        ResourceBundle rb = ResourceBundle.getBundle(fileName, locale);
        try
        {
        	propertyValue = rb.getString(key);
        }
        catch(Exception e)
        {
        	if (LOG.isErrorEnabled()) {
        		LOG.error("Error while fetching the property for the key:" + key);
        		LOG.error(e.getMessage(), e);
        	}
        	throw new IOException(e.getMessage(), e);
        }
		
		return propertyValue;
	}
	
	/**
	 * Gets the value for a given property key
	 *
	 * @param key        key for which the value needs to be fetched
	 * @param fileType	 specifies from which property file object
	 * 					 value has to return based on provided key
	 * @return value     value of the key
	 * @throws IOException if any exception occurs while fetching value
	 */
	public static String getProperty(String key, String fileName)
			throws IOException {
		String propertyValue = null;
		if(null != fileName) {
			propertyValue = ResourceBundle.getBundle(fileName).getString(key);
		}
		return propertyValue;
	}

	/**
	 * Gets the Resource bundle object given the language
	 *
	 * @param key        key for which the value needs to be fetched
	 * @param fileType	 specifies from which property file object
	 * 					 value has to return based on provided key
	 * @param language   language for which the value come form the user language   
	 * 
	 * @return value     value of the key
	 * @throws IOException if any exception occurs while fetching value
	 */
	public static Object getResourceBundleForLoc(String language, String fileName)
			throws IOException {
		
        if((language == null) || ("".equals(language)))
        {
        	language = "en";
        }
        
        Locale locale = new Locale(language);
        ResourceBundle rb = ResourceBundle.getBundle(fileName, locale);
        return rb;
	}
	
	
	/**
	 * Loads all the property files into corresponding
	 * Properties Objects
	 *
	 */
	private void load() {
		try {

			ClassLoader classLoader = this.getClass().getClassLoader();
			InputStream inputStream = classLoader
					.getResourceAsStream(PROPERTIES_FILE_NAME);
			stateProps.load(inputStream);

		} catch (IOException ioe) {
			LOG.error("Error in PropertyUtil.load "+ioe.getMessage());
			// TODO: handle exception
		} catch (Exception e) {
			LOG.error("Error in PropertyUtil.load "+e.getMessage());
			// TODO: handle exception
		}
	}
}