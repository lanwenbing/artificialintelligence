/*
 * @source ReportUtil.java
 * @version 1.0, 11/03/2010
 * @author Uma.K
 * Modification History
 * ---------------------------------------------------------------------------
 * Name                   Date         Modification description
 * ----                   ----         ------------------------
 * Uma.K				  03/03/2010	Created	
 * Uma.K				  11/03/2010	Modified
 * ---------------------------------------------------------------------------
 */
package com.artificialintelligence.report.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utilities class used in reports.
 *
 * @author Uma.K
 * @version 1.0
 */
public class ReportUtil {
	private static final Log log = LogFactory.getLog(ReportUtil.class);
	
	public static String formatRawDate(String value)
	{
		if (value.trim().length() == 0)
		{
			return "";
		}
		String returnValue = value;
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(true);
			Date date = dateFormat.parse(value);
			DateFormat expectedFormat = new SimpleDateFormat("yyyy-MM-dd");
			expectedFormat.setLenient(true);
			returnValue = expectedFormat.format(date);
		}
		catch (Exception e)
		{
			if(log.isErrorEnabled()){
				log.error(e.getMessage(),e);
			}
		}
		return returnValue;
	}
	
	/**
	 * 
	 * @param pLog
	 */
	public static void isDebugEnabled(Object pLog) {
		if (log.isDebugEnabled()) {
			log.debug(pLog);
		}
	}
	
	/**
	 * 
	 * @param pLog
	 */
	public static void isErrorEnabled(String pLog) {
		if (log.isErrorEnabled()) {
			log.error(pLog);
		}
	}
}
