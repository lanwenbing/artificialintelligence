/*
 * ReportManager.java ver. 1.00
 *
 * Class that acts as an mediator in handling all the report specific operations.
 *
 * Modification History
 * ---------------------------------------------------------------------------
 * Name                   Date         Modification description
 * ----                   ----         ------------------------
 * Uma.K				  03/03/2010	Created
 * Uma.K				  11/03/2010	Modified
 * ---------------------------------------------------------------------------
 */

package com.artificialintelligence.report.manager;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.artificialintelligence.core.util.BeanUtils;
import com.artificialintelligence.dao.machinelearning.util.DBUtil;
import com.artificialintelligence.dao.machinelearning.util.PropertyUtil;
import com.artificialintelligence.dao.machinelearning.util.Util;
import com.artificialintelligence.report.util.IReportEngine;
import com.artificialintelligence.report.util.JRCSVDataSource;
import com.artificialintelligence.report.util.JasperReportEngine;
import com.artificialintelligence.report.util.ReportConstants;
import com.artificialintelligence.report.util.ReportQueries;
import com.artificialintelligence.report.util.ReportUtil;

/**
 * The Manager class is responsible for all the following operations.
 *
 * 1. Get the JasperEngine object. 2. Fetch the Jasper file name for a given
 * request. 3. Process the Report object to build the JasperPrint Object. 4.
 * Setting the required properties for PDF ,Excel, Printer Service, Local
 * Settings. 5. Handling report printing operation. 6. Report storing operation.
 *
 * @author Uma.K
 * @version 1.0
 */

public class ReportManager implements IReportManager {
	
    
	private static final Log log = LogFactory.getLog(ReportManager.class);
	private static JasperReport mJasperReport;
	private String mSelPrinterName;
	private int mSelCopies;

	/**
	 * Takes the Report Object,invokes the method to build the JasperReport
	 * object and calls the method to compile this object using the JasperEngine
	 *
	 * @param reportObject
	 *            - Object that is to be processed
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	public void process(ReportObject reportObject) throws IOException, JRException {
        log.error("[" + Thread.currentThread().getId() + "] >>>>>>>>>>> START com.c4.component.report.manager.ReportManager.process" );
		isDebugEnabled("Processing Report Object,building,compiling.........." + reportObject.getMReqName());
		JasperPrint jasperPrint = null;
		buildJasperReportObj(reportObject);
		jasperPrint = compileReport(reportObject);
		reportObject.setJasperPrint(jasperPrint);
		reportObject.setMReportCriterias(null);
		isDebugEnabled("Done Processing Report Object...............");
        log.error("[" + Thread.currentThread().getId() + "] >>>>>>>>>>> FINISH com.c4.component.report.manager.ReportManager.process" );
	}



	/**
	 * Method the gets the requested jasperfile and loads the input stream to
	 * construct the JasperReport Object.
	 *
	 * @param pReportObject
	 * @throws IOException
	 * @throws JRException
	 */
	public void buildJasperReportObj(ReportObject pReportObject) throws IOException, JRException {
		isDebugEnabled("Build Report Object.Requestname....." + pReportObject.getMReqName());
		String jasperFilePath = getJasperFilePath();
		String lJrxmlName = null;
		lJrxmlName = PropertyUtil.getProperty(pReportObject.getMReqName(), ReportConstants.LANGUAGE, ReportConstants.REQJRXMLPROPSFILE);
		String lJasperFilePath = jasperFilePath + lJrxmlName + ".jasper";

		isDebugEnabled("File path:" + lJasperFilePath);
		InputStream lInputStream = new FileInputStream(lJasperFilePath);
		isDebugEnabled("InputStream is............." + lInputStream);

		// input the jasper file and show report.compilation not done here.
		mJasperReport = (JasperReport) JRLoader.loadObject(lInputStream);
		//pReportObject.setmJasperReport(mJasperReport);
		isDebugEnabled("OUT.....Build Report Object.Requestname.....");
	}

	/**
	 * given the report object, get the jrxml name for the request. uses the
	 * reqjrxml.properties file for the same.
	 *
	 * @param reportObj
	 * @return
	 * @throws IOException
	 */
	public String getJasperPath(ReportObject reportObj) throws IOException {
		String jasperFilePath = getJasperFilePath();
		String lJrxmlName = PropertyUtil.getProperty(reportObj.getMReqName(), ReportConstants.LANGUAGE, ReportConstants.REQJRXMLPROPSFILE);
		return jasperFilePath + lJrxmlName + ".jasper";
	}

	/**
	 * Method that invokes the jasper engine and passes the JasperReport
	 * object,connection object to get the jasper object(JasperPrint) which can
	 * be exported to the required file format. The method also checks for the
	 * data source that is required for the appropriate report(CSV/connection).
	 * If there is a need to invoke the storeproc then the method passes the
	 * connection object to the appropriate method and returns the jasperPrint
	 * object returned by it.
	 *
	 * @param pReportObject
	 * @return
	 * @throws JRException
	 */
	public JasperPrint compileReport(ReportObject pReportObject) throws JRException {
		isDebugEnabled("Into compileReport...............");
		JasperPrint lJasperPrint = null;
		Connection oConnection = null;
		log.debug("Start Virtualiser......" + System.currentTimeMillis());
		JRSwapFile swapFile = null;
		try {
			swapFile = new JRSwapFile(getVitualStorePath(), 1024, 1024);
		} catch (IOException e1) {
			if (log.isErrorEnabled()) {
				log.error("Error while getting virtual path.....");
			}
		}
		log.debug("Start Virtualiser End......" + swapFile.toString());
		JRSwapFileVirtualizer virtualizer = new JRSwapFileVirtualizer(2, swapFile, true);

		if (pReportObject.getMInpSrc().equalsIgnoreCase(ReportConstants.CSV)) {
			try {
				String fileName1 = pReportObject.getMCSVFileName();
				log.debug("File name........." + fileName1);
				// isDebugEnabled(" File Name Given to the datasource........"+
				// fileName);
				JRCSVDataSource csvDataSource = new JRCSVDataSource(fileName1);

		        //JasperReport mJasperReport = pReportObject.getmJasperReport();
				lJasperPrint = (JasperPrint) getEngineType(ReportConstants.ENGINETYPE_JASPER).fillData(virtualizer, mJasperReport, setLocaleProp(pReportObject), csvDataSource);
				deleteCSVFile(fileName1);
			} catch (Exception e) {
				if (log.isErrorEnabled()) {
					log.error("Error while creating jasperprint :: " + lJasperPrint);
				}
			}
		} else {
			
			try {
				oConnection = DBUtil.getConnection();
				isDebugEnabled("Begin Connection...." + oConnection + "........................" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				if (pReportObject.getMInpSrc().equalsIgnoreCase(ReportConstants.JRXML)) {

					pReportObject.setVirtualizer(virtualizer);

	                //JasperReport mJasperReport = pReportObject.getmJasperReport();
					lJasperPrint = (JasperPrint) getEngineType(ReportConstants.ENGINETYPE_JASPER).fillData(virtualizer, mJasperReport, setLocaleProp(pReportObject), oConnection);
					// System.out.println("------------------>>>"+lJasperPrint.getPages().size());

				} else if (pReportObject.getMInpSrc().equalsIgnoreCase(ReportConstants.BEAN)) {
	                //JasperReport mJasperReport = pReportObject.getmJasperReport();
					isDebugEnabled(" using bean datasource........");
					 Vector<JobReportBean> vec = new  Vector<JobReportBean>();
					JobReportBean bean = new JobReportBean();
					vec.add(bean);
					JRBeanCollectionDataSource beanDS = new JRBeanCollectionDataSource(vec);
					lJasperPrint = (JasperPrint) getEngineType(ReportConstants.ENGINETYPE_JASPER).fillData(mJasperReport, setLocaleProp(pReportObject), beanDS);
				}

			} catch (Exception excp) {
				if (log.isErrorEnabled()) {
					log.error("Error while getting the connection :: " + excp.getMessage(), excp);
				}
			} finally {
				try {
					isDebugEnabled("End Connection.." + oConnection);
					
				} catch (Exception excp) {
					if (log.isErrorEnabled()) {
						log.debug("Error while getting the connection :: " + excp.getMessage(), excp);
					}
				}
			}
		}
		isDebugEnabled("Connection Ended....Jasper print object" + lJasperPrint + "......" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
		return lJasperPrint;
	}

	
	private void deleteCSVFile(String fileName1) throws Exception {
		log.info("Inside deleteFile Start...");
		try {
			// Construct a File object for the file to be deleted.
			File target = new File(fileName1);

			if (!target.exists()) {
				log.error("File " + fileName1 + " not present to begin with!");
			}

			if (target.delete()) {
				log.error("** Deleted " + fileName1 + " **");
			} else {
				log.error("Failed to delete " + fileName1);
				throw new Exception("Error while deleting the local zip file");
			}
		} catch (SecurityException e) {
			log.error("Unable to delete " + fileName1 + "(" + e.getMessage() + ")");
			throw new Exception("Error while deleting the local zip file");
		}
		log.info("Inside deleteFile End...");
	}

	/**
	 * Compile the report if it has to be stored and invoke respective method
	 * either to store this as PDF or EXCEL.
	 *
	 * @param pReportObject
	 * @param fileName
	 * @param path
	 * @throws JRException
	 * @throws IOException
	 */
	public void compileReportToStore(ReportObject pReportObject, String fileName, String path) throws JRException, IOException {
		isDebugEnabled("Compling Report To Store" + path);
		String lDefaultFileName = getFileName(PropertyUtil.getProperty(pReportObject.getMReqName(), ReportConstants.REQJRXMLPROPSFILE));
		String lFileName = null == fileName ? lDefaultFileName : fileName;
		JasperPrint lJasperPrint = getJasperFileToStore(pReportObject);
		if (pReportObject.getMReportType().equalsIgnoreCase(ReportConstants.PDF_REFCD)) {
			storeAsPdf(lJasperPrint, lFileName, path);
		} else if (pReportObject.getMReportType().equalsIgnoreCase(ReportConstants.EXCEL_REFCD)) {
			storeAsExcel(lJasperPrint, lFileName, path);
		}
	}

	/**
	 * Compile the report if it has to be stored and invoke respective method
	 * either to store this as PDF or EXCEL.
	 *
	 * @param pReportObject
	 */
	public void compileReportToStore(ReportObject pReportObject, String fileName) throws JRException, IOException {
		isDebugEnabled("Compling Report To Store");
		String lDefaultFileName = getFileName(PropertyUtil.getProperty(pReportObject.getMReqName(), ReportConstants.REQJRXMLPROPSFILE));
		String lFileName = null == fileName ? lDefaultFileName : fileName;

		JasperPrint lJasperPrint = getJasperFileToStore(pReportObject);
		String storePath = PropertyUtil.getProperty(ReportConstants.JASPERFILESSTOREPATH, ReportConstants.CMPLUS_ENV);

		if (pReportObject.getMReportType().equalsIgnoreCase(ReportConstants.PDF_REFCD)) {
			storeAsPdf(lJasperPrint, lFileName, storePath);
		} else if (pReportObject.getMReportType().equalsIgnoreCase(ReportConstants.EXCEL_REFCD)) {
			storeAsExcel(lJasperPrint, lFileName, storePath);
		}
	}

	
	/**
	 * This method is used by the order service. The method accepts list of
	 * report objects, sends them for print and returns the print status of each
	 * order number.
	 *
	 * @param pReportObjectList
	 * @return
	 */
	public Map<String, String> compileReportListForPrint(List<ReportObject> pReportObjectList, String rptKey) {
		Map<String, String> resultMap = new HashMap<String, String>();
		for (ReportObject lReportObject : pReportObjectList) {
			String lOrderNo = (String) lReportObject.getMReportCriterias().get(rptKey);
			if (null != lOrderNo && lOrderNo.length() > 0) {
				try {
					compileReportToPrint(lReportObject);
				} catch (IOException e) {
					resultMap.put((String) lReportObject.getMReportCriterias().get(rptKey), ReportConstants.ERROR_GENERATING_FILE);
				} catch (JRException e) {
					resultMap.put((String) lReportObject.getMReportCriterias().get(rptKey), ReportConstants.ERROR_GENERATING_FILE);
				} catch (PrinterException e) {
					resultMap.put((String) lReportObject.getMReportCriterias().get(rptKey), ReportConstants.ERROR_TRANSMITTING);
				}
			}
		}
		return resultMap;

	}

	/**
	 * Compile Report in case of Printing, and invoke the method to set the
	 * print properties.
	 *
	 * @param pReportObject
	 * @return
	 * @throws IOException
	 * @throws JRException
	 * @throws PrinterException
	 */
	public void compileReportToPrint(ReportObject pReportObject) throws IOException, JRException, PrinterException {
		if (pReportObject.getMPrinterName() == null) {
			// System.out.println("compioling");
			pReportObject.setMPrinterName(getDefaultPinter());
		}
		// System.out.println("compioling");
		setMSelPrinterName(pReportObject.getMPrinterName());
		setMSelCopies(pReportObject.getMNoOfCopies());
		// System.out.println("compioling");
		isDebugEnabled("Compiling Report To Print....");
		String jasperFilePath = getJasperFilePath();
		String lJrxmlName = PropertyUtil.getProperty(pReportObject.getMReqName(), ReportConstants.REQJRXMLPROPSFILE);
		isDebugEnabled("File path:" + jasperFilePath + lJrxmlName + ".jasper");
		InputStream lInputStream = new FileInputStream(jasperFilePath + lJrxmlName + ".jasper");
		
		try
		{
            //JasperReport mJasperReport = pReportObject.getmJasperReport();
    		mJasperReport = (JasperReport) JRLoader.loadObject(lInputStream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		JasperPrint lJasperPrint = compileReport(pReportObject);

		// get the orientation, landscape or portrait from the jrxml.
        //JasperReport mJasperReport = pReportObject.getmJasperReport();
		lJasperPrint.setOrientation(mJasperReport.getOrientationValue());
		setPrinterPropsAndPrint(lJasperPrint);
	}

	/**
	 * Set the Excel Properties before exporting the data to excel doc.
	 *
	 * @param pJasperPrint
	 * @return
	 */
	public static JExcelApiExporter getExcelApiProperties(JasperPrint pJasperPrint) {
		JExcelApiExporter lExporterXls = new JExcelApiExporter();
//		pJasperPrint.setProperty(JRParameter.IS_IGNORE_PAGINATION, "true");
		lExporterXls.setParameter(JRExporterParameter.JASPER_PRINT, pJasperPrint);
		lExporterXls.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		lExporterXls.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.TRUE);
		lExporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		lExporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		lExporterXls.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.FALSE);
		lExporterXls.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
		return lExporterXls;
	}
	
	public static JRXlsxExporter getCommonXlsxExporter(JasperPrint pJasperPrint) {
	    JRXlsxExporter exporter = new JRXlsxExporter();
	    pJasperPrint.setProperty(JRParameter.IS_IGNORE_PAGINATION, "false");
	    exporter.setParameter(JRExporterParameter.JASPER_PRINT, pJasperPrint);
	    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	    exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.TRUE);
	    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
	    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	    exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.FALSE);
	    exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
//	    exporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET, 50000);
	    return exporter;
	}
	
	public static JRTextExporter getTextApiProperties(JasperPrint pJasperPrint) {
		JRTextExporter lExporterTxt = new JRTextExporter();
		pJasperPrint.setProperty(JRParameter.IS_IGNORE_PAGINATION, "true");
		lExporterTxt.setParameter(JRExporterParameter.JASPER_PRINT, pJasperPrint);
		lExporterTxt.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float(4.5));
		lExporterTxt.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float(12.0));
		lExporterTxt.setParameter(JRTextExporterParameter.BETWEEN_PAGES_TEXT, "\r\n");
		lExporterTxt.setParameter(JRTextExporterParameter.LINE_SEPARATOR, "\r\n");
//		lExporterTxt.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(1000));
//		lExporterTxt.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Integer(5000));

		return lExporterTxt;
	}
	
	public static JRCsvExporter getCsvApiProperties(JasperPrint pJasperPrint) {
		JRCsvExporter  lExporterCsv = new JRCsvExporter();
		pJasperPrint.setProperty(JRParameter.IS_IGNORE_PAGINATION, "true");
		lExporterCsv.setParameter(JRExporterParameter.JASPER_PRINT, pJasperPrint);
		lExporterCsv.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
		return lExporterCsv;
	}

	/**
	 * Check if there is a request for Local Specific report display, if so get
	 * the property file for that particular Local and set set it to the
	 * criteria Map in the Report Object for processing.
	 *
	 * @param pReportObject
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> setLocaleProp(ReportObject pReportObject) throws IOException {
		if (!pReportObject.getMReqName().equalsIgnoreCase("stockOutReport_csv")
				&& !pReportObject.getMReqName().equalsIgnoreCase("normalShelfCapacityReport_csv")) {
			readCriteriaParams(pReportObject.getMReportCriterias());
		}
		ResourceBundle lResBndl = null;
		isDebugEnabled("Bilingual Param" + pReportObject.getBilingualFlag());
		isDebugEnabled("User Prefernce" + pReportObject.getUserLangPreference());
		if (pReportObject.getBilingualFlag()) {
			if (pReportObject.getMLocale() != null) {
				if (pReportObject.getMLocale().equals(ReportConstants.CHINESE)) {
					lResBndl = (ResourceBundle) PropertyUtil.getResourceBundleForLoc(ReportConstants.CHINESELANGUAGE, ReportConstants.JASPER_LABELS_CHINESE);
				}else if(pReportObject.getMLocale().equals(ReportConstants.CHINESE_CHINESE)){
					lResBndl = (ResourceBundle) PropertyUtil.getResourceBundleForLoc(ReportConstants.CHINESELANGUAGE, ReportConstants.JASPER_LABELS_CHINESE_CHINESE);
				}else {
					lResBndl = (ResourceBundle) PropertyUtil.getResourceBundleForLoc(ReportConstants.LANGUAGE, ReportConstants.JASPER_LABELS_EN);
				}
			} else if (pReportObject.getUserLangPreference().equals(ReportConstants.CHINESE)) {
				lResBndl = (ResourceBundle) PropertyUtil.getResourceBundleForLoc(ReportConstants.CHINESELANGUAGE, ReportConstants.JASPER_LABELS_CHINESE);
			} else if(pReportObject.getUserLangPreference().equals(ReportConstants.CHINESE_CHINESE)){
				lResBndl = (ResourceBundle) PropertyUtil.getResourceBundleForLoc(ReportConstants.CHINESELANGUAGE, ReportConstants.JASPER_LABELS_CHINESE_CHINESE);
			}else {
				lResBndl = (ResourceBundle) PropertyUtil.getResourceBundleForLoc(ReportConstants.LANGUAGE, ReportConstants.JASPER_LABELS_EN);
			}
		} else {
			lResBndl = (ResourceBundle) PropertyUtil.getResourceBundleForLoc(ReportConstants.LANGUAGE, ReportConstants.JASPER_LABELS_EN);
		}
		Map<String, Object> reportCriterias = pReportObject.getMReportCriterias();
		reportCriterias.put(JRParameter.REPORT_RESOURCE_BUNDLE, lResBndl);
		reportCriterias.put("jasperfilepath", getJasperFilePath());
		isDebugEnabled(" sub report path::" + reportCriterias.get("jasperfilepath"));

		return reportCriterias;
	}

	/**
	 *
	 * @return
	 */
	public String getMSelPrinterName() {
		return mSelPrinterName;
	}

	/**
	 *
	 * @param selPrinterName
	 */
	public void setMSelPrinterName(String selPrinterName) {
		mSelPrinterName = selPrinterName;
	}

	/**
	 *
	 * @return
	 */
	public int getMSelCopies() {
		return mSelCopies;
	}

	/**
	 *
	 * @param selCopies
	 */
	public void setMSelCopies(int selCopies) {
		mSelCopies = selCopies;
	}

	/**
	 *
	 * @param pReportObject
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws JRException
	 */
	private JasperPrint getJasperFileToStore(ReportObject pReportObject) throws IOException, FileNotFoundException, JRException {
		String pJrxmlName = PropertyUtil.getProperty(pReportObject.getMReqName(), ReportConstants.REQJRXMLPROPSFILE);
		isDebugEnabled("File path:" + getJasperFilePath() + pJrxmlName + ".jasper");
		InputStream lInputStream = new FileInputStream(getJasperFilePath() + pJrxmlName + ".jasper");
        //JasperReport mJasperReport = pReportObject.getmJasperReport();
		mJasperReport = (JasperReport) JRLoader.loadObject(lInputStream);
		JasperPrint jasperReport = compileReport(pReportObject);
		return jasperReport;
	}

	/**
	 * Query required for getting the query to run the unknownbarcode report.
	 *
	 * @param reportCriterias
	 * @param reqName
	 * @return
	 */
	private StringBuilder getQuery(Map<String, Object> reportCriterias, String reqName) {
		ReportQueries reportQueries = new ReportQueries();

		//if (reqName.equalsIgnoreCase("dailySalesReport_csv")) {
		//	return reportQueries.getDailySalesReportQuery(reportCriterias, reqName);
		//}
		
		return null;
	}



	/**
	 * This method looks into all the available printer services that is
	 * configured to the system, initializes a job for that printer that is
	 * specified in the properties file and sets all the printer settings to
	 * print the report. Mphasis99-5109293
	 *
	 * @param jasperPrint
	 * @return
	 * @throws JRException
	 * @throws PrinterException
	 * @throws JRException
	 */
	private void setPrinterPropsAndPrint(JasperPrint jasperPrint) throws PrinterException, JRException {
		isDebugEnabled("Setting Printer Properties.....");
		PrinterJob job = PrinterJob.getPrinterJob();

		// Create an array of PrintServices
		PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		int selectedService = 0;
		boolean prtFnd = false;
		isDebugEnabled("Printer Services.....");
		// Scan found services to see if anyone suits our needs
		if(BeanUtils.isNotEmpty(services)){
			for (int i = 0; i < services.length; i++) {
				isDebugEnabled("Printer availiable ....." + services[i].getName() + "--->" + services.length);
				isDebugEnabled("Printer Configured is....." + getMSelPrinterName());
					if (services[i].getName().trim().equalsIgnoreCase(getMSelPrinterName().trim())) {
						isDebugEnabled(getMSelPrinterName() + ":: found");
						// If the service is named as what we are querying we select it
						selectedService = i;
						prtFnd = true;
						break;
					}
			}
		}else{
			String printerNotfound = "Y";
			throw new PrinterException(printerNotfound);
		}
		if (!prtFnd) {
			isDebugEnabled("Printer Not found....." + services[selectedService]);
			throw new PrinterException("Printer Not Found");
		}
		isDebugEnabled("sending data to printer....." + services[selectedService]);
		job.setPrintService(services[selectedService]);
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		JRPrintServiceExporter exporter = new JRPrintServiceExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		
		List<JasperPrint> jprintlist = new ArrayList<JasperPrint>();
		for(int i=0; i<getMSelCopies(); i++){
			jprintlist.add(jasperPrint);
		}

		// We set the selected service and pass it as a parameter
		exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]);
		exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes());
		exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);

		// SS's author: siritas's
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
		log.debug("turnoff dialog prompted");
		
		exporter.setParameter(JRPrintServiceExporterParameter.JASPER_PRINT_LIST, jprintlist);
		exporter.exportReport();
		isDebugEnabled("End Print");
	}

	/**
	 * @deprecated Note: Put deprecated to remove if sure that there're no use
	 *             of this method.
	 *
	 *             In case there is no printer name availiable then fetch the
	 *             same from the properties file.
	 *
	 * @return
	 * @throws IOException
	 */
	private String getDefaultPinter() throws IOException {
		ResourceBundle rb = ResourceBundle.getBundle(ReportConstants.CMPLUS_ENV);
		String propPath = rb.getString("PAGE_DEFINITION_PATH");
		String resourcePath = ReportConstants.RESOURCE_PCKG.replace(".", "/");
		resourcePath = propPath + resourcePath;
		File directory = new File(resourcePath);
		InputStream is = new FileInputStream(new File(directory, ReportConstants.REPORT_CONFIG));
		PropertyResourceBundle propertyResourceBundle = new PropertyResourceBundle(is);
		return propertyResourceBundle.getString(ReportConstants.PRINTER_NAME);
	}

	/**
	 * Method that returns the jasper file path.
	 *
	 * @return
	 * @throws IOException
	 */
	private String getJasperFilePath() throws IOException {
		String path = null;
		isDebugEnabled("Fetching the jasperfilepath........constant file entry:::" + ReportConstants.JASPERFILESPATH);
		path = PropertyUtil.getProperty(ReportConstants.JASPERFILESPATH, ReportConstants.CMPLUS_ENV);
		isDebugEnabled("The file path is..........." + path);
		return path;
	}

	private String getVitualStorePath() throws IOException {
		String path = null;
		log.debug("Fetching the virtualfilepath........constant file entry:::" + ReportConstants.VITUALFILESSTOREPATH);
		path = PropertyUtil.getProperty(ReportConstants.VITUALFILESSTOREPATH, ReportConstants.CMPLUS_ENV);
		log.debug("The file path is..........." + path);
		return path.trim();
	}

	/**
	 * Returns the path of the CSV file.
	 *
	 * @return
	 */
	private String getCSVFilePath() {
		String path = null;
		log.debug("Fetching the csvpath........constant file entry:::" + ReportConstants.JASPERCSVPATH1);
		try {
			path = PropertyUtil.getProperty(ReportConstants.JASPERCSVPATH1, ReportConstants.CMPLUS_ENV);
		} catch (IOException e) {
			log.error("Error getting the csv file :: " + e.getMessage(), e);
		}
		log.debug("The file path is..........." + path);
		return path;
	}

	/**
	 * returns Jasper engine object.
	 *
	 * @param pReptEngine
	 * @return
	 */
	private IReportEngine getEngineType(String pReptEngine) {
		IReportEngine lReptEngine = null;
		if (pReptEngine.equalsIgnoreCase(ReportConstants.ENGINETYPE_JASPER))
			lReptEngine = new JasperReportEngine();
		return lReptEngine;
	}

	/**
	 * get the path where the report has to be stored and export the report in
	 * PDF format to that location.
	 *
	 * @param pJasperPrint
	 * @param lJrxmlName
	 * @throws IOException
	 * @throws Exception
	 */
	private void storeAsPdf(JasperPrint pJasperPrint, String lJrxmlName, String storePath) throws JRException, IOException {
		isDebugEnabled("Storing Report as PDF at ::" + storePath);
		JasperExportManager.exportReportToPdfFile(pJasperPrint, storePath + lJrxmlName + ReportConstants.PDF_EXTN);
	}

	/**
	 * get the path where the report has to be stored and export the report in
	 * Excel format to that location.
	 *
	 * @param pJasperPrint
	 * @param lJrxmlName
	 * @throws Exception
	 */
	private void storeAsExcel(JasperPrint pJasperPrint, String lJrxmlName, String storePath) throws JRException, IOException {
		JExcelApiExporter excelApiProperties = getExcelApiProperties(pJasperPrint);
		excelApiProperties.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, storePath + lJrxmlName + ReportConstants.XLS_EXTN);
		excelApiProperties.exportReport();
	}

	/**
	 *
	 * @param jrxmlName
	 * @return
	 */
	private String getFileName(String jrxmlName) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd_MMM_yyyy");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH_mm_ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		String strTime = sdfTime.format(now);
		return jrxmlName + strDate + "_" + strTime;
	}

	/**
	 *
	 * @param pLog
	 */
	private void isDebugEnabled(Object pLog) {
		if (log.isDebugEnabled()) {
			log.debug(pLog);
		}
	}

	/**
	 *
	 * @param pLog
	 */
	private void isErrorEnabled(String pLog) {
		if (log.isErrorEnabled()) {
			log.error(pLog);
		}
	}

	/**
	 * Test method to read the key values from a given Map
	 *
	 * @param reportCriterias
	 */
	private void readCriteriaParams(Map<String, Object> reportCriterias) {
		isDebugEnabled("Report Criterias before conn........");
		for (Entry<String, Object> lCriteria : reportCriterias.entrySet()) {
			String paramKey = (String) lCriteria.getKey();
			String paramValue = (String) lCriteria.getValue();
			isDebugEnabled("Key....." + paramKey);
			isDebugEnabled("Value....." + paramValue);
		}
	}


}
