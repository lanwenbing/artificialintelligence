/*
 * @source IReportEngine.java
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
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;

/**
 * Interface to implement Report Engine in the application.
 *
 * @author Uma.K
 * @version 1.0
 */
public interface IReportEngine {
	
	public Object compileReport(String pSource) throws JRException ;
	public Object fillData(JRSwapFileVirtualizer virtualizer, JasperReport jasperReport, Map<String, Object> params, Connection conn) throws JRException;
	public Object fillData(JRSwapFileVirtualizer virtualizer,Object report, Map<String, Object> params, JRCSVDataSource csvDataSource) throws JRException;
	public Object fillData(Object report, Map<String, Object> params, JRBeanCollectionDataSource beanDataSource) throws JRException;
	public void compileReport(String string, String string2) throws JRException;
	public Object compileReport(InputStream pResStream) throws JRException ;
	}
