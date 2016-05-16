package com.artificialintelligence.report.manager;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface IReportManager {

	public JasperPrint compileReport(ReportObject pReportObject) throws JRException;
	public void compileReportToStore(ReportObject pReportObject, String fileName,String path)throws JRException,IOException;
	public void compileReportToStore(ReportObject pReportObject, String fileName)throws JRException,IOException;
	public Map<String, String> compileReportListForPrint(List<ReportObject> pReportObjectList,String str);
	public void process(ReportObject reportObject) throws IOException, JRException;
	public void  compileReportToPrint(ReportObject repObj)throws IOException, JRException,PrinterException;
}
