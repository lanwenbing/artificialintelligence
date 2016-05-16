/*
 * @source JasperReportEngine.java
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

import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Manager class is responsible for all the following operations.
 * 
 * 
 * @author Uma.K
 * @version 1.0
 */

public class JasperReportEngine implements IReportEngine {
	
	private Log log = LogFactory.getLog(JasperReportEngine.class);

	/**
	 * given the name of the JRXML File, complies the report by invoking the jasper compliler and returns the JasperReport Object
	 * @return Object - JasperReport Object.
	 */
	public Object compileReport(String pJrxml) throws JRException {
		JasperDesign jasperDesign = JRXmlLoader.load(pJrxml);
		return JasperCompileManager.compileReport(jasperDesign);
	}


	/**
	 * given two strings (path and the jasper file), invokes the method to compile and store the report in that path.
	 * @throws JRException 
	 */
	public void compileReport(String string, String string2) throws JRException {
			JasperCompileManager.compileReportToFile(string, string2);
	}
	/**
	 * @return Object- JasperReport
	 */
	public Object compileReport(InputStream pResStream) throws JRException {
		JasperDesign jasperDesign = JRXmlLoader.load(pResStream);
		return JasperCompileManager.compileReport(jasperDesign);
	}
	
	/**
	 * @return Object - JasperPrint Object
	 * @throws JRException 
	 */
	public Object fillData(JRSwapFileVirtualizer virtualizer,JasperReport jasperReport, Map<String, Object> params,	Connection conn) throws JRException {
		
		if (log.isDebugEnabled()) {
			log.debug("Filling Report in JAsperEngine..........."+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		}
		
		params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		JasperPrint fillReport = null;
		fillReport = JasperFillManager.fillReport((JasperReport)jasperReport, params, conn);
		
		if (virtualizer != null)
        {
            virtualizer.setReadOnly(true);            
        }

		
		if (log.isDebugEnabled()) {
			log.debug("End Filling Report..........."+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		}
		return fillReport;
	}


	public Object fillData(JRSwapFileVirtualizer virtualizer,Object pReport, Map<String, Object> pParams,JRCSVDataSource csvDataSource) throws JRException {
		if (log.isDebugEnabled()) {
			log.debug("Filling Report in JAsperEngine..........."+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		}
		pParams.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		JasperPrint fillReport = null;
			fillReport = JasperFillManager.fillReport((JasperReport)pReport, pParams, csvDataSource);
		
		if (virtualizer != null)
	        {
	            virtualizer.setReadOnly(true);            
	        }

		if (log.isDebugEnabled()) {
			log.debug("End Filling Report..........."+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		}
		return fillReport;
	}


	public Object fillData(Object report, Map<String, Object> params,JRBeanCollectionDataSource beanDataSource) throws JRException  {
		if (log.isDebugEnabled()) {
			log.debug("Filling Report in JAsperEngine..........."+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		}
		JasperPrint fillReport = null;
			fillReport = JasperFillManager.fillReport((JasperReport)report, params, beanDataSource);
		if (log.isDebugEnabled()) {
			log.debug("End Filling Report..........."+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		}
		return fillReport;
	}
}
