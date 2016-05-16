/*
 * @source JRCSVDataSource.java
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Interface to implement JRCSVDataSource functionalities in the application.
 * 
 * @author Uma.K
 * @version 1.0
 */
public class JRCSVDataSource implements JRDataSource {
	String row = "";
	int fieldPosition = 0;
	BufferedReader reader;
	private NumberFormat numberFormat = new DecimalFormat();
	private DateFormat inputdateFormat = new SimpleDateFormat(("yyyy-MM-dd"));
	private DateFormat reqddateFormat = new SimpleDateFormat(("dd/MM/yyyy"));
	static final Log log = LogFactory.getLog(JRCSVDataSource.class);

	/** Creates a new instance of JRCVSDataSource */
	public JRCSVDataSource(String csvFile) {
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (log.isErrorEnabled()) {
				log.error(ex.getMessage(), ex);
			}
		}
	}

	public Object getFieldValue(JRField jRField) {// throws JRException {
		Object returnValue = null;
		String[] rowArr = row.split("\\^");
		try{
		String token = rowArr[fieldPosition];
		fieldPosition++;
		returnValue = getValue(jRField, token);
		}
		catch (Exception e) {
		//	e.printStackTrace();
			log.error("Error in JRCSVDataSource.getFieldValue"+e.getMessage());
		}
		//System.out.println(returnValue);
		return returnValue;
	}

	private Object getValue(JRField jRField, String token) throws ParseException {
		//System.out.println("into getValue.........."+jRField.getName() + "%%%%%%%%%%%%"+token);
		Object retVal=null;
		
		Class valueClass = jRField.getValueClass();
		if (valueClass.equals(String.class)) {
			return token;
		}

		token = token.trim();

		if (token.length() == 0) {
			return null;
		}
			if (valueClass.equals(Boolean.class)) {
				retVal= token.equalsIgnoreCase("true") ? Boolean.TRUE: Boolean.FALSE;
			} else if (valueClass.equals(Byte.class)) {
				retVal= new Byte((numberFormat.parse(token)).byteValue());
			} else if (valueClass.equals(Integer.class)) {
				retVal= new Integer((numberFormat.parse(token)).intValue());
			} else if (valueClass.equals(Long.class)) {
				retVal= new Long((numberFormat.parse(token)).longValue());
			} else if (valueClass.equals(Short.class)) {
				retVal= new Short((numberFormat.parse(token)).shortValue());
			} else if (valueClass.equals(Double.class)) {
				retVal= new Double((numberFormat.parse(token)).doubleValue());
			} else if (valueClass.equals(Float.class)) {
				retVal= new Float((numberFormat.parse(token)).floatValue());
			} else if (valueClass.equals(BigDecimal.class)) {
				retVal= new BigDecimal((numberFormat.parse(token)).toString());
			} else if (valueClass.equals(BigInteger.class)) {
				retVal= new BigInteger(String.valueOf(numberFormat.parse(token).longValue()));
			} else if (valueClass.equals(java.lang.Number.class)) {
				retVal= numberFormat.parse(token);
			} else if (valueClass.equals(java.util.Date.class)) {
				Date inputDate = inputdateFormat.parse(token);
				String format = reqddateFormat.format(inputDate);
				retVal= (Date) reqddateFormat.parse(format);
			} else if (valueClass.equals(java.sql.Timestamp.class)) {
				retVal= new java.sql.Timestamp(inputdateFormat.parse(token).getTime());
			} else if (valueClass.equals(java.sql.Time.class)) {
				retVal= new java.sql.Time(inputdateFormat.parse(token).getTime());
			}else if (valueClass.equals(java.sql.Date.class)) {
				Date inputDate = inputdateFormat.parse(token);
				String format = reqddateFormat.format(inputDate);
				retVal= (Date) reqddateFormat.parse(format);
			}
		return retVal;
	}

	public boolean next() // throws JRException
	{
//		System.out.println("into next...");
		try {
			row = reader.readLine();
			fieldPosition = 0;
			if (row.length() > 0) {
				return true;
			}
		} catch (Exception ex) {
		//	ex.printStackTrace();
			log.error("Error in JRCSVDataSource.next"+ex.getMessage());
		}
		return false;
	}
}