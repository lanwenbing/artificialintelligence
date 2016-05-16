/*
 * @source ReportConstants.java
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

/**
 * The ReportConstants is contains all the report specific constant and their definitions.
 * 
 * @author Uma.K
 * @version 1.0
 */

public class ReportConstants { 
	

	
	public static final String ENGINETYPE_JASPER = "jasper";

	public static final String REQJRXMLPROPSFILE="resource.reports.reqjrxml";
	
	public static final String PRINTER_NAME = "PRINTER_NAME";
	public static final String RESOURCE_PCKG=".report.";
	public static final String REPORT_CONFIG="printerConfig.properties";
	
	
	
	public static final String PDF_REFCD="2";
	public static final String EXCEL_REFCD="1";
	public static final String PDF_EXTN=".pdf";
	public static final String XLS_EXTN=".xls";
	public static final String JASPER_EXTN=".jasper";
	public static final String CSV_FILE="csv";
	public static final String TXT_REFCD="3";
	public static final String CSV_REFCD="4";
	
	public static final String TEMP = "/jasperfiles/temp.xls";
	public static final String TEMP_CSV = "/jasperfiles/temp.csv";
	public static final String CONTENT_TYPE_TEXT="text/plain";
	public static final String CONTENT_TYPE_PDF="application/pdf";
	public static final String CONTENT_TYPE_CSV="text/csv";
	
	public static final String CONTENT_TYPE_EXCEL_2007="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	//this is the variable for the path specified in the cmplus-env properites
	public static final String JASPERFILESPATH="JASPERFILESPATH";
	public static final String VITUALFILESSTOREPATH="VITUALFILESSTOREPATH";
	public static final String JASPERFILESSTOREPATH="JASPERFILESSTOREPATH";
	public static final String JASPERCSVPATH1="JASPERCSVPATH1";
	public static final String CMPLUS_ENV="cmplus_env";

	
	public static final String CHINESE="zh_TW";//Refcode for local and english
	public static final String CHINESE_CHINESE = "zh_CN";
	public static final String ENGFLG="en";

	

	public static final String LANGUAGE= "en";
	public static final String CHINESELANGUAGE= "zh";
	
	
	
	//properties file for the jrxml files. To enable multilingual support
	public static final String JASPER_LABELS_EN ="resource.reports.jasperLabels_en_EN";
	public static final String JASPER_LABELS_CHINESE ="resource.reports.jasperLabels_zh_TW";
	public static final String JASPER_LABELS_CHINESE_CHINESE ="resource.reports.jasperLabels_zh_CN";
	
	public static final String JRXML="jrxml";
	public static final String CSV="csv";
	public static final String BEAN="bean";
	
	public static final String ERROR_TRANSMITTING = "error.transmiting";
	public static final String ERROR_GENERATING_FILE = "errror.generating.file";
	

	
}  
