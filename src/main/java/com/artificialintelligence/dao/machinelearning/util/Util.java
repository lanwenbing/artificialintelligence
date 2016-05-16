package com.artificialintelligence.dao.machinelearning.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

/**
 * A Library/Collection of static methods to help in frequently occurring
 * situations
 * 
 * @author pzf0w5
 * 
 */
public class Util
{

	
	/**
	 * Logger for this class
	 */
	private static final Log LOG = LogFactory.getLog(Util.class);
	
	/**
	 * To parse a given String into its integer value.
	 * 
	 * @param value
	 * @return
	 */
	public static int parseInt(String value)
	{
		if (value == null)
		{
			return 0;
		}
		int iValue = 0;
		try
		{
			iValue = Integer.parseInt(value);
		}
		catch (Exception e)
		{
			iValue = 0;
		}
		return iValue;
	}

	public static String smoothForBlank(String str)
	{
		if (str == null)
		{
			return "&nbsp;";
		}
		return str.trim();
	}

	public static String smoothNull(String str)
	{
		if (str == null)
		{
			return "";
		}
		return str.trim();
	}
	public static String getCSV(Set<String> set)
	{
		if (set == null)
		{
			return "";
		}
		StringBuilder builder = new StringBuilder();
		int index = 1;
		for (String str : set)
		{
			builder.append(str);
			if (index != set.size())
			{
				builder.append(",");
			}
			index++;
		}
		return builder.toString();
	}

	/**
	 * Validate the given field to number
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNumber(String value)
	{
		if (value == null)
		{
			return true;
		}

		if (value.trim().length() == 0)
		{
			return true;
		}

		boolean flag = false;
		try
		{
			Double.parseDouble(value);
			flag = true;
		}
		catch (Exception ex)
		{
			flag = false;
		}
		return flag;
	}

	public static String formatTime(String value)
	{
		if (value == null)
		{
			return "";
		}
		if (value.trim().length() == 0)
		{
			return "";
		}

		String returnValue = value;
		
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			dateFormat.setLenient(true);
			Date date = dateFormat.parse(value);
			DateFormat expectedFormat = new SimpleDateFormat("HH:mm");
			expectedFormat.setLenient(true);
			returnValue = expectedFormat.format(date);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return returnValue;
	}
	
	public static String formatRawDate(String value)
	{
		if (isNull(value))
		{
			return "";
		}
		if (value.trim().length() == 0)
		{
			return "";
		}

		String returnValue = value;

		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(true);
			Date date = dateFormat.parse(value);
			DateFormat expectedFormat = new SimpleDateFormat("dd/MM/yyyy");
			expectedFormat.setLenient(true);
			returnValue = expectedFormat.format(date);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return returnValue;
	}


	/**
	 * Checking mandatory field
	 * 
	 */
	public static boolean isEmpty(String value)
	{
		boolean flag = false;

		try
		{
			if ((value == null) || (value.trim().length() == 0) || ("null".equalsIgnoreCase(value)))
			{
				flag = true;
			}
		}
		catch (Exception ex)
		{
			flag = false;
		}
		return flag;
	}

	/**
	 * Checking whether given values is in the specified range;
	 * 
	 */

	public static boolean isWithinRange(String value, String matPattern)
	{
		boolean flag = false;

		if (value == null)
		{
			return true;
		}
		if (value.trim().length() == 0)
		{
			return true;
		}
		try
		{

			Pattern pattern = Pattern.compile(matPattern);
			Matcher matcher = pattern.matcher(value);
			if (matcher.matches())
			{
				flag = true;

			}
			else
			{
				flag = false;
			}
		}

		catch (Exception ex)
		{
			flag = false;
		}
		return flag;
	}

	/**
	 * Check for valid time
	 * 
	 */

	public static boolean isTime(String value)
	{

		if (value == null)
		{
			return true;
		}
		if (value.trim().length() == 0)
		{
			return true;
		}
		boolean flag = true;
		try
		{
			String[] str = value.split("[:]");
			if (str.length != 2)
			{
				flag = false;
			}
			else if (str[0].length() != 2 || str[1].length() != 2)
			{
				flag = false;

			}
			else
			{
				int hours = Integer.parseInt(str[0]);

				if (hours < 00 || hours > 23)
					flag = false;
				int minutes = Integer.parseInt(str[1]);

				if (minutes < 00 || minutes > 59)
					flag = false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
		return flag;

	}
	/**
	 * Check for valid Dojo time
	 * 
	 */

	public static boolean isDojoTime(String value)
	{

		if (value == null)
		{
			return true;
		}
		if (value.trim().length() == 0)
		{
			return true;
		}
		boolean flag = true;
		try
		{
			    String[] str = value.split("[:]");
			
			//	int hours = Integer.parseInt(str[0]);

				
				int minutes = Integer.parseInt(str[1]);
				if((minutes%15!=0)&&(str[1].length()==2))
				//if (minutes < 00 || minutes > 59)
					flag = false;
			
		}
		catch (Exception e)
		{
			return false;
		}
		return flag;

	}
	/**
	 * Checking Range 1.00 to 999.99
	 * 
	 */

	public static boolean isOrderPeriodRange(String value)
	{
		boolean flag = false;

		if (value == null)
		{
			return true;
		}
		if (value.trim().length() == 0)
		{
			return true;
		}

		try
		{
			double valueDouble = Double.parseDouble(value);

			if (valueDouble > 0.00 && valueDouble <= 1000)
			{
				return true;
			}
		}

		catch (Exception ex)
		{
			return false;
		}
		return flag;

	}

	/**
	 * Checking whether the digit repeats in a given number
	 * 
	 */

	public static boolean isDigitRepeats(String value)
	{
		boolean flag = false;

		if (value == null)
		{
			return true;
		}
		if (value.trim().length() == 0)
		{
			return true;
		}

		try
		{

			String strNum[] = value.split("");
			int counter = 0;
			for (int i = 1; i < strNum.length; i++)
			{
				String strName = strNum[i];
				if (!"%".equals(strName))
				{
					for (int j = i + 1; j < strNum.length; j++)
					{
						int res = strNum[i].compareTo(strNum[j]);
						if (res == 0)
						{
							counter++;
						}
					}
				}
			}
			if (counter > 0)
			{
				flag = false;
			}
			else
			{
				flag = true;
			}

		}

		catch (Exception ex)
		{
			return false;
		}
		return flag;
	}

	/**
	 * validate the Department Code field.allows only digits and comma
	 * 
	 * @param value
	 * @param matPattern
	 * @return
	 */
	public static boolean isDepartmentCode(String value, String matPattern)
	{
		boolean flag = false;

		if (value == null)
		{
			return true;
		}
		if (value.trim().length() == 0)
		{
			return true;
		}
		try
		{

			Pattern pattern = Pattern.compile(matPattern);
			Matcher matcher = pattern.matcher(value);
			if (matcher.matches())
			{
				flag = true;

			}
			else
			{
				flag = false;
			}
		}

		catch (Exception ex)
		{
			flag = false;
		}
		return flag;
	}

	/**
	 * To get the user displayed timestamp format
	 * 
	 * @param TimeStampToConvert
	 * @param reqFormat
	 *            should be valid date format ex: dd/MM/yyyy, dd-MM-yyyy,
	 *            MM-dd-yyyy
	 * @param inputDateFormat
	 *            should be valid for converting the date ex: yyyy-MM-dd
	 *            HH:mm:ss
	 * @return User displayed timestamp format
	 * @throws Exception
	 */

	public static String timeStampFormat(String timestampToConvert, String reqFormat, String inputDateFormat) throws Exception
	{
		String formatedTS = "";
		SimpleDateFormat inputDatef = new SimpleDateFormat(inputDateFormat);
		SimpleDateFormat reqSdf = new SimpleDateFormat(reqFormat);
			
			if (!isEmpty(timestampToConvert))
			{
				Date tempDate = inputDatef.parse(timestampToConvert);
				formatedTS = reqSdf.format(tempDate);
			}		
		return formatedTS;
	}
	
	
	/**
	 * To get the user displayed time format
	 * 
	 * @param dateToConvert
	 * @param reqFormat
	 *            should be valid date format ex: hh:mm,hh:mm:ss
	 * @param inputDateFormat
	 *            should be valid for converting the date ex: hh:mm:ss
	 * @return User displayed date format
	 * @throws Exception
	 */
	public static String timeFormat(String timeToConvert, String reqFormat) throws Exception
	{
		String formatedTime = "";
		if(!isEmpty(timeToConvert)) {
			Time inputTime = Time.valueOf(timeToConvert);
			SimpleDateFormat reqSdf = new SimpleDateFormat(reqFormat);
			formatedTime=reqSdf.format(inputTime);
		}
		return formatedTime;
	}

	/**
	 * To compare two timestamp string :: before comparing it will get the date
	 * by using the format "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param compareThis
	 * @param compareWith
	 * @return Result(Bolean) of two timestamp values comparison
	 * @throws Exception
	 */

	/*public static boolean timeStampIsGreater(String compareThis, String compareWith) throws Exception
	{
		SimpleDateFormat compareFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ((compareThis == null) || (compareThis.isEmpty()) || (compareWith == null) || (compareWith.isEmpty()))
		{
			return true;
		}
		else
		{
			Date thisDate = compareFormat.parse(compareThis);
			Date withDate = compareFormat.parse(compareWith);
			return (thisDate.getTime() > withDate.getTime());
		}
	}*/
	
	
	/**
	 * To compare two date string :: before comparing it will get the date
	 * by using the format "yyyy-MM-dd"
	 * 
	 * @param compareThis
	 * @param compareWith
	 * @return Result(Bolean) of two date values comparison
	 * @throws Exception
	 */

	public static boolean dateIsGreater(String compareThis, String compareWith) throws Exception
	{
		SimpleDateFormat compareFormat = new SimpleDateFormat("yyyy-MM-dd");
		if ((isEmpty(compareThis))||(isEmpty(compareWith)))
		{
			return true;
		}
		else
		{
			Date thisDate = compareFormat.parse(compareThis);
			Date withDate = compareFormat.parse(compareWith);
			return (thisDate.getTime() > withDate.getTime());
		}
	}

	/**
	 * To get the DB supported timestamp
	 * 
	 * @param inputDate
	 * @param inputFormat
	 *            should be valid date format ex: dd/MM/yyyy, dd-MM-yyyy,
	 *            MM-dd-yyyy
	 * @return DB Supported TimeStamp
	 * @throws Exception
	 */
	public static Timestamp getDBTimeStampFormat(String inputTimeStamp, String inputFormat) throws Exception
	{
		SimpleDateFormat simple = new SimpleDateFormat(inputFormat);
		if (!isEmpty(inputTimeStamp))
		{
			Date inputDateVal = simple.parse(inputTimeStamp);
			return new Timestamp(inputDateVal.getTime());
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * To get the DB supported date
	 * 
	 * @param inputDate
	 * @param inputFormat
	 *            should be valid date format ex: dd/MM/yyyy, dd-MM-yyyy,
	 *            MM-dd-yyyy
	 * @return DB Supported date
	 * @throws Exception
	 */
	public static java.sql.Date getDBDateFormat(String inputDate, String inputFormat) throws Exception {
		java.sql.Date outDate = null;
		//from bridge some times date value if null in DB it returns string  "null" 
		if(inputDate.equals("null")){
			return outDate;
		}
		SimpleDateFormat simple = new SimpleDateFormat(inputFormat);
		if (!isEmpty(inputDate)) {
			outDate = new java.sql.Date(simple.parse(inputDate).getTime());
		}
		return outDate;
	}
	
	/**
	 * To get the DB supported time
	 * 
	 * @param inputTime
	 * @param inputFormat
	 *            should be valid time format ex: hh:mm,hh:mm:ss
	 * @return DB Supported time
	 * @throws Exception
	 */	
	public static Time getDBTimeFormat(String inputTime, String inputTimeFormat) throws Exception {
		Time outTime = null;
		if(!isEmpty(inputTime)) {
			if(inputTime.trim().length() == 5){
				inputTime = inputTime + ":00";
			}
			try{
			outTime = Time.valueOf(inputTime);
			} catch (IllegalArgumentException iarg){
				throw new Exception("Invalid Time Format::"+iarg.getMessage());
			}
		}
		return outTime;
	}


	/**
	 * To get the assocColumn by merging association and column with "."
	 * 
	 * @param association
	 * @param column
	 * 
	 * @return assocColumn
	 */
	public static String getAssocColumn(String association, String column)
	{
		String assocColumn = null;
		if ((association == null) || (association.isEmpty()))
		{
			assocColumn = column;
		}
		else
		{
			assocColumn = association + "." + column;
		}
		return assocColumn;
	}

	/**
	 * Checking whether given value is with in a specified Range.
	 * 
	 * 
	 */

	public static boolean isNumericValueRange(String value, String startvalue, String endValue)
	{
		boolean flag = false;

		if (value == null)
		{
			return true;
		}
		if (value.trim().length() == 0)
		{
			return true;
		}

		try
		{
			double startVal = Double.parseDouble(startvalue);
			double endVal = Double.parseDouble(endValue);
			double inputValue = Double.parseDouble(value);

			if (inputValue >= startVal && inputValue <= endVal)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}

		catch (Exception ex)
		{
			return false;
		}
		return flag;

	}

	/**
	 * Compare the decimal numbers.
	 * 
	 * @param firstNum
	 * @param secondNum
	 * @return
	 */
	public static double compareNumbers(String firstNum, String secondNum) {
		double res = 0;

		try {
			double firstVal = Double.parseDouble(firstNum);
			double secondVal = Double.parseDouble(secondNum);
			res = firstVal - secondVal;
		} catch (Exception ex) {
			LOG.error("Error in Util.compareNumbers "+ex.getMessage());
			//return 0;
			//Return 0
		}

		return res;
	}

	/**
	 * Checking whether given value is with in a specified Range.
	 * 
	 * 
	 */

	public static boolean isMaximumLength(String value, String maxlength)
	{
		boolean flag = false;

		if (value == null)
		{
			return true;
		}
		if (value.trim().length() == 0)
		{
			return true;
		}

		try
		{
			int maxLength = Util.parseInt(maxlength);
			int inputValueLength = value.length();

			if (inputValueLength <= maxLength)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}

		catch (Exception ex)
		{
			return false;
		}
		return flag;

	}

	/**
	 * If the value of a key has its value as "D,W,D" the value should be
	 * transformed as "D,W".
	 */
	public static void removeDuplicateValues(Map<String, String> aMap, String delimiter)
	{
		if (aMap == null)
		{
			return;
		}
		Set<String> keys = aMap.keySet();
		for (String key : keys)
		{
			String value = aMap.get(key);
			if (value == null)
			{
				continue;
			}
			value = getUniqueString(value, delimiter);
			aMap.put(key, value);
		}
	}

	/**
	 * @see removeDeuplicateValues Use a set to capture the unique values and
	 *      return that as a string
	 * @param value
	 * @return
	 */
	public static String getUniqueString(String value, String delimiter)
	{
		if (value == null)
		{
			return null;
		}
		String returnValue = null;
		String arr[] = value.split(delimiter);
		Set<String> aSet = new HashSet<String>();
		for (String str : arr)
		{
			aSet.add(str);
		}
		for (String str : aSet)
		{
			returnValue = (returnValue == null) ? str : returnValue + "," + str;
		}
		return returnValue;
	}

	/**
	 * Return the maximum date from the list of Dates
	 */
	public static void setMaxDate(Map<String, String> aMap, String delimiter)
	{
		if (aMap == null)
		{
			return;
		}

		String returnValue = null;
		Set<String> keys = aMap.keySet();
		for (String key : keys)
		{
			String value = aMap.get(key);
			if (value == null)
			{
				continue;
			}
			returnValue = getMaxDate(value, delimiter);
			returnValue=smoothForNullIntheString(returnValue);
			aMap.put(key, returnValue);
		}
	}

	/**
	 * @see Use a TreeSet and return the last value
	 * 
	 * @param value
	 * @return
	 */
	public static String getMaxDate(String value, String delimiter)
	{
		if (value == null)
		{
			return null;
		}
		String returnValue = "";
		String arr[] = value.split(delimiter);
		Set<String> aSet = new TreeSet<String>();
		for (String str : arr)
		{
			if (isEmpty(str))
			{
				return "";
			}
			aSet.add(str);
		}
		if (aSet.size() == 0)
		{
			return "";
		}
		for (String str : aSet)
		{
			returnValue = str;
		}

		return formatRawDate(returnValue);
	}
	
	public static long compareDates(String startdate,String enddate,String format)
	{
					
		if (startdate == null)
		{
			return 0;
		}
		if (startdate.trim().length() == 0)
		{
			return 0;
		}
		if (enddate == null)
		{
			return 0;
		}
		if (enddate.trim().length() == 0)
		{
			return 0;
		}
		Long diffrence=0L;
			
		SimpleDateFormat compareFormat = new SimpleDateFormat(format);
		try {
			Date startDate = compareFormat.parse(startdate);
			Date endDate = compareFormat.parse(enddate);
			diffrence = (startDate.getTime() - endDate.getTime());
					
			
		} catch (ParseException e) {
			System.out.println("Invalid Date:"+e.getMessage());
		}
		return diffrence;
	}
	
	public static long compareWithCurntDate(String inDate) {
		long diff = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date inDateObj = dateFormat.parse(inDate);
			Date curntDate = new Date(System.currentTimeMillis());
			diff = curntDate.getTime() - inDateObj.getTime(); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}

	/**
	 * To compare the passed date with current date.
	 * 
	 * @param inDate
	 * @param dateFormat
	 * @return
	 */
	public static long compareWithCurntDate(String inDate, String dateFormat) {
		LOG.debug("Inside Util.compareWithCurntDate method");
		long diff = 0;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			Date inDateObj = format.parse(inDate);
			Date curntDate = format.parse(getCurrentDate(dateFormat));
			diff = curntDate.getTime() - inDateObj.getTime(); 
		} catch (ParseException e) {
			LOG.error("Error in Util.compareWithCurntDate:" + e.getMessage(), e);
		}

		return diff;
	}

	public static long compareWithCurntDate(Date inDate) {
		return (System.currentTimeMillis() - inDate.getTime());
	}

	/**
	 * Formats the given string decimal with precision provided.
	 * 
	 * @param inputDecimal
	 * @param noofDecDgts
	 * @return
	 */
	public static String decimalFormatValue(String inputDecimal, int noofDecDgts) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inside Util.decimalFormatValue method");
		}
		if (isEmpty(inputDecimal) || !isNumber(inputDecimal)) {
			return "";
		}

		String noOfDecimals = "";
		for(int count = 0; count<noofDecDgts; count++){
			noOfDecimals  = noOfDecimals + "0";
		}
		Double inDecimal = Double.valueOf(inputDecimal);
		DecimalFormat formatter = new DecimalFormat("#0."+noOfDecimals);

		return formatter.format(inDecimal);
	}


	/**
	 * To generate number of zero's as per the given input
	 * 
	 * @param noOfZeroToForm
	 * @return
	 */

	public static String toFormZeroString(int noOfZeroToForm)
	{
		String finalZeroString = "";

		for (int i = 0; i < noOfZeroToForm; i++)
		{
			finalZeroString = finalZeroString + "0";
		}

		return finalZeroString;

	}

	public static void main(String[] arg)
	{
		System.out.println(ceil(20,10));
	}
	
	/**
	 * Checking whether given value is with in a specified Range.
	 * 
	 * 
	 */

	public static boolean isMinimumLength(String value, String minlength)
	{
		boolean flag = false;

		if (value == null)
		{
			return true;
		}
		if (value.trim().length() == 0)
		{
			return true;
		}

		try
		{
			int minLngt = Util.parseInt(minlength);
			int inputValueLength = value.length();

			if (inputValueLength < minLngt)
			{
				flag = false;
			}
			else
			{
				flag = true;
			}
		}

		catch (Exception ex)
		{
			return false;
		}
		return flag;

	}

	/*
	 * This method gives minutes between cutoff times+order date and current date
	 * it will give +Ve when current date will not exceed cuttoff times
	 * 
	 */
	public static double getTimeDiffInMin(String time) {

		try {
			if (time == null) {
				return 0;
			}
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			String finalTime = timeFormat.format(new Date(System
					.currentTimeMillis()));
			Date currentTm = timeFormat.parse(finalTime);
			Date cutOfftime = timeFormat.parse(time);
			
			double currTmLg = currentTm.getTime();
			double cutOffTmLg = cutOfftime.getTime();
			double diff = cutOffTmLg - currTmLg;
			double mins = diff / (1000 * 60);
			return mins;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
		
		
	public static String roundTwoDecimal(Double value) { 
	    DecimalFormat twoDForm = new DecimalFormat("#0.00");
		return twoDForm.format(value);
	}
	
	public static String getCurrentDate(String inputFormat) {
		//DateFormat expectedFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat expectedFormat = new SimpleDateFormat(inputFormat);
		return expectedFormat.format(new Date());
	}
	
	public static boolean validateTime(String timeInput) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		boolean isValid = true;
		try {
			timeFormat.parse(timeInput);
		} catch (ParseException e) {
			isValid = false;
		}
		return isValid;
	}
	
	public static String numGen() {
		String sID = "";
		int iNoOfDigits = 4;
		Random oRandom = new Random();
		double dDoubleVal = oRandom.nextDouble();
		long lLongVal = new Double(dDoubleVal * (getMaxDigits(iNoOfDigits)))
				.longValue();
		sID = "" + lLongVal;
		return sID;
	}
	
	/**
     * This method is used to get the max allowed value for the passed no of
     * digits.
     * 
     * @param noofDigits
     * @return max allowed value for given no of digits
     */
    public static long getMaxDigits(int noofDigits) {
          long maxVal = 1;
          for (int i = 0; i < noofDigits; i++) {
                maxVal = maxVal * 10;
          }
          return (maxVal - 1);
    }
    
    public static long compareDates(Date date1, Date date2) {
    	return (date1.getTime() - date2.getTime()); 
    }
    
    /**
	 * This method is used to get the comma seperated values for the passed string[] values 
	 * @param values
	 * @return comma sepertaed values in string formate
	 */
    public static String getCommaSeparatedValues(String[] values)
	{
		String comaSeperatedValues = "";
		if (values != null)
		{
			int length = values.length;
			if (length == 1)
			{
				return values[0];
			}
			else
			{
				for (int i = 0; i < length; i++)
				{
					comaSeperatedValues = comaSeperatedValues + "," + values[i];
				}
				comaSeperatedValues = comaSeperatedValues.substring(1, comaSeperatedValues.length());

			}
		}
		return comaSeperatedValues;
	}


	/**
	 * To increment the number if any reminder occurs.
	 * 
	 * @param dr
	 * @param nr
	 * @return
	 */
	public static int ceil(int dr, int nr)
	{
		int value = dr/nr;
		
		value = dr%nr>0?value+1:value;
		
		return value;
	}
	/**
	 * To return the empty string if we got the str value as the null IN THE STRING.
	 * @param str
	 * @return
	 */
	public static String smoothForNullIntheString(String str)
	{
		if (str == null)
		{
			return "";
		}
		if(str.equalsIgnoreCase("null"))
		{
			return "";
		}
		return str.trim();
	}
	
	/**
	 * To return the empty string if we got the str value as the null IN THE STRING.
	 * @param str
	 * @return
	 */
	public static String smoothForNullWithOutTrim(String str)
	{
		if (str == null)
		{
			return "";
		}
		if(str.equalsIgnoreCase("null"))
		{
			return "";
		}
		return str;
	}
	public static boolean isNull(String str)
	{
		if (str == null)
		{
			return true;
		}
		if(str.equalsIgnoreCase("null"))
		{
			return true;
		}
		return false;
	}
	
	public static String getAscOrder(String value, String delimiter)
	{
		if (value == null)
		{
			return null;
		}
		String returnValue = null;
		String arr[] = value.split(delimiter);
		Set<String> aSet = new TreeSet<String>();
		for (String str : arr)
		{
			if (isEmpty(str))
			{
				continue;
			}
			aSet.add(str);
		}
		if (aSet.size() == 0)
		{
			return "";
		}
		for (String str : aSet)
		{
			returnValue = (returnValue == null) ? str : returnValue + "," + str;
		}

		return returnValue;
	}
	
	public static String addDaysToDate(String inDate, String inDateFormat,
			int daysToAdd) {
		String finalDate = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(inDateFormat);
			Calendar dateCal = Calendar.getInstance();
			Date dateObj = dateFormat.parse(inDate);
			dateCal.setTime(dateObj);
			dateCal.add(Calendar.DATE, daysToAdd);
			DateFormat expectedFormat = new SimpleDateFormat("dd/MM/yyyy");
			finalDate = expectedFormat.format(dateCal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return finalDate;
	}
	
	public static String getTimeFromDate(String inDate, String inDateFormat) {
		String finalTime = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(inDateFormat);
			Date dateObj = dateFormat.parse(inDate);
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			finalTime = timeFormat.format(dateObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return finalTime;
	}
	
	public static String getTimeFromCurntDt() {
		String finalTime = "";
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		finalTime = timeFormat.format(new Date(System.currentTimeMillis()));
		return finalTime;
	}
	
	/**
	 * Convert the two dimensional array to the Map
	 * 
	 * @param inputArray
	 * @return
	 */
	public static Map<String, String> prepareMap(String[][] inputArray)
	{
		if (inputArray == null)
		{
			return null;
		}
		Map<String, String> preparedMap = new LinkedHashMap<String, String>();
		for (String[] detailedArray : inputArray)
		{
			if (detailedArray.length == 2)
			{
				preparedMap.put(detailedArray[0], detailedArray[1]);
			}
		}
		return preparedMap;
	}
	

	
	public static String getMaxTime(String time,String delimeter)
	{
		
		if (time == null)
		{
			return null;
		}
		String returnValue = "";
		String arr[] = time.split(delimeter);
		Set<String> aSet = new TreeSet<String>();
		for (String str : arr)
		{
			if (isEmpty(str))
			{
				continue;
			}
			aSet.add(str);
		}
		if (aSet.size() == 0)
		{
			return "";
		}
		for (String str : aSet)
		{
			returnValue = str;
		}
		return returnValue;
	}
	
	public static String formatDojoTime(String value)
	{
		if (value == null)
		{
			return "";
		}
		if (value.trim().length() == 0)
		{
			return "";
		}
		String[] values = value.split("[:]");
		if(values[0].charAt(0) == 'T')
		value = value.substring(1, (value.length()-1));
		
		String returnValue = value;
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			dateFormat.setLenient(true);
			Date date = dateFormat.parse(value);
			DateFormat expectedFormat = new SimpleDateFormat("HH:mm");
			expectedFormat.setLenient(true);
			returnValue = expectedFormat.format(date);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return returnValue;
	}


	/**
	 * To round the provided decimal value based on the precision and rounding method.
	 * 
	 * @param valToRound
	 * @param noOfDigitsToTrunc
	 * @return
	 */
	public static BigDecimal roundBigDecimal(BigDecimal valToRound,int noOfDigitsToTrunc) {
		BigDecimal roundedBigDecimal = null;
		if (null != valToRound) {
			roundedBigDecimal = valToRound.setScale(noOfDigitsToTrunc,
					BigDecimal.ROUND_HALF_UP);
		}
		return roundedBigDecimal;
	}

	/**
	 * To compare dates.
	 * 
	 * @param inDate
	 * @return
	 */
	public static long compareDate(String inDate)
	{
		long diff = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			Date curntDate = new Date(System.currentTimeMillis());
			if (inDate.equals(dateFormat.format(curntDate)))
			{
				return 0;
			}
			Date inDateObj = dateFormat.parse(inDate);
			diff = curntDate.getTime() - inDateObj.getTime();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return diff;
	}

	/**
	 * To check whether the given date lies between the start date and end date.
	 * 
	 * @param startDate
	 * @param endDate
	 * @param date
	 * @return
	 */
	public static boolean isDateWithin(String startDate, String endDate, String date, String inputFormat) {
		LOG.debug("Inside Util.isDateWithin method");
		boolean res = false;
		try {
			if (compareDates(startDate, date, inputFormat) <= 0 &&
					compareDates(endDate, date, inputFormat) >= 0) {
				res = true;
			}
		} catch (Exception e) {
			LOG.error("Error in Util.isDateWithin:" + e.getMessage(), e);
			//No need to propagate the exception.
		}

		return res;
	}
	/*
		 * This method gives HH:MM between Start time and end time
		 *
		 *
		 */
		public static String getDiffTimeInHHMMFormat(String startTime,String endTime)
		{
		   String hh_mm=null;
		   if(startTime!=null && endTime!=null)
		   {
		    try{
		      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		     //Convert the user input into a date object
		      Date time1 = sdf.parse(startTime);
		      Date time2 = sdf.parse(endTime);
		      // Get time values of the date objects
		      long l1 = time1.getTime();
		      long l2 = time2.getTime();
		      double diff=l2-l1;
		      double mins1=Math.abs(Math.floor(diff / (1000 * 60)));
		      double hhm=Math.floor(mins1/60);
		      int hh = (int)hhm;
		      double mmh=mins1-(hh*60);
		      int mm;
		      mm = (int)mmh;
		      if(mm<9){
		       hh_mm=hh+":0"+mm+" hrs";
		      }else{
		       hh_mm=hh+":"+mm+" hrs";
		      }
		   } catch (Exception e) {
		     e.printStackTrace();
		   }
		 }
		   return hh_mm;
 }


	
	public static String getMaxVal(String value,String delimeter)
	{
		
		if (value == null)
		{
			return null;
		}
		String returnValue = "";
		String arr[] = value.split(delimeter);
		Set<String> aSet = new TreeSet<String>();
		for (String str : arr)
		{
			if (isEmpty(str))
			{
				continue;
			}
			aSet.add(str);
		}
		if (aSet.size() == 0)
		{
			return "";
		}
		for (String str : aSet)
		{
			returnValue = str;
		}
		return returnValue;
	}


	
	/**
	 * input string with comma separated,replaces with , and returns double
	 * @param val
	 * @return
	 */
	public static Double getDoubleVal(String val){
		LOG.debug("Inside Util.getDoubleVal method");
		Double value = 0.0;
		if (!isEmpty(val) && isNumber(val.replace(",",""))) {
			value = Double.parseDouble(val.replace(",",""));
		}

		return value;
	}

	/**
	 * Removes the commas from the given string.
	 * 
	 * @param val
	 * @return
	 */
	public static String removeComma(String val) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inside Util.removeComma method");
		}
		String res = null;
		if (!isEmpty(val)) {
			res = val.replace(",", "");
		}
		return res;
	}
}
