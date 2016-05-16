/*
 * ReportObject.java ver. 1.00 
 *
 * Class that has all the report attributes.
 * 
 * Modification History:
 * Date				Modified by			Description
 * 					Uma.K				  Created
 */

package com.artificialintelligence.report.manager;

import java.io.Serializable;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fill.JRAbstractLRUVirtualizer;

import com.artificialintelligence.report.util.ReportConstants;

/**
 * The Report Object class has all the required setter methods to set the attributes of the report object.
 * 
 * @author Uma.K
 * @version 1.0
 */

public class ReportObject implements Serializable {

	private static final long serialVersionUID = -8930748169321055361L;
	// if its pdf/excel doc
	private String mReportType;
	
	// if true then store else view.
	private boolean mReportStore = false; 
	
	//map which contains all the criterias required to compile and 
	//display the jasper report. These criterias are specified in 
	//the report at design time.
	private Map<String, Object> mReportCriterias;;
	private String mReqName;
	private String mLocale = ReportConstants.ENGFLG;
	private JasperPrint jasperPrint;
	private String transStatusCode;
	private String transStatusMsg;
	private boolean bilingualFlag=true;
	private String userLangPreference="E";
	private String mPrinterName=null;
	//private String mTrayNo;
	private int mNoOfCopies=1;
	private String mCSVFileName=null;
	private String mInpSrc=ReportConstants.JRXML;
	private JRAbstractLRUVirtualizer virtualizer;
	private String sessionID=null; // used to just save the session id so that we can use them as naming convention for th CSV generated files
	private String hhOption;
	//this field show status for exporting excel finished
	private boolean isExportFinished;
	
    public String getMInpSrc() {
		return mInpSrc;
	}

	public void setMInpSrc(String inpSrc) {
		mInpSrc = inpSrc;
	}

	/**
	 * @return the mReportType
	 */
	public String getMReportType() {
		return mReportType;
	}

	/**
	 * @param reportType
	 *            the mReportType to set
	 */
	public void setMReportType(String reportType) {
		mReportType = reportType;
	}

	/**
	 * @return the mReportStore
	 */
	public boolean isMReportStore() {
		return mReportStore;
	}

	/**
	 * @param reportStore
	 *            the mReportStore to set
	 */
	public void setMReportStore(boolean reportStore) {
		mReportStore = reportStore;
	}

	/**
	 * @return the mReportCriterias
	 */
	public Map<String, Object> getMReportCriterias() {
		return mReportCriterias;
	}

	/**
	 * @param reportCriterias
	 *            the mReportCriterias to set
	 */
	public void setMReportCriterias(Map<String, Object> reportCriterias) {
		mReportCriterias = reportCriterias;
	}

	/**
	 * @return the mReqName
	 */
	public String getMReqName() {
		return mReqName;
	}

	/**
	 * @param reqName
	 *            the mReqName to set
	 */
	public void setMReqName(String reqName) {
		mReqName = reqName;
	}

	/**
	 * @return the jasperPrint
	 */
	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	/**
	 * @param jasperPrint
	 *            the jasperPrint to set
	 */
	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	/**
	 * @return the transStatusCode
	 */
	public String getTransStatusCode() {
		return transStatusCode;
	}

	/**
	 * @param transStatusCode
	 *            the transStatusCode to set
	 */
	public void setTransStatusCode(String transStatusCode) {
		this.transStatusCode = transStatusCode;
	}

	/**
	 * @return the transStatusMsg
	 */
	public String getTransStatusMsg() {
		return transStatusMsg;
	}

	/**
	 * @param transStatusMsg
	 *            the transStatusMsg to set
	 */
	public void setTransStatusMsg(String transStatusMsg) {
		this.transStatusMsg = transStatusMsg;
	}

	/**
	 * 
	 * @return 
	 */
	public String getMLocale() {
		return mLocale;
	}
	
	/**
	 * 
	 * @param locale
	 */
	public void setMLocale(String locale) {
		mLocale = locale;
	}
	
	public boolean getBilingualFlag() {
		return bilingualFlag;
	}

	public void setBilingualFlag(boolean bilingualFlag) {
		this.bilingualFlag = bilingualFlag;
	}
	
	/*public void set(boolean bilingualFlag) {
		this.bilingualFlag = bilingualFlag;
	}*/
	
	public String getUserLangPreference() {
		return userLangPreference;
	}

	public void setUserLangPreference(String userLangPreference) {
		this.userLangPreference = userLangPreference;
	}

	public String getMPrinterName() {
		return mPrinterName;
	}

	public void setMPrinterName(String printerName) {
		mPrinterName = printerName;
	}

	public int getMNoOfCopies() {
		return mNoOfCopies;
	}

	public void setMNoOfCopies(int noOfCopies) {
		mNoOfCopies = noOfCopies;
	}

	public String getMCSVFileName() {
		return mCSVFileName;
	}

	public void setMCSVFileName(String fileName) {
		mCSVFileName = fileName;
	}

	public JRAbstractLRUVirtualizer getVirtualizer() {
		return virtualizer;
	}

	public void setVirtualizer(JRAbstractLRUVirtualizer virtualizer) {
		this.virtualizer = virtualizer;
	}
	
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getHhOption() {
		return hhOption;
	}

	public void setHhOption(String hhOption) {
		this.hhOption = hhOption;
	}

	public boolean isExportFinished() {
		return isExportFinished;
	}

	public void setExportFinished(boolean isExportFinished) {
		this.isExportFinished = isExportFinished;
	}
	
}
