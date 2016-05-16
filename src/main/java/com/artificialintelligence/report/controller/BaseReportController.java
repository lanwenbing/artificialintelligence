/*
 * @source BaseReportControler.java
 * @version 1.0, 4/01/2010
 * @author Uma.K
 * Modification History
 * ---------------------------------------------------------------------------
 * Name                   Date         Modification description
 * ----                   ----         ------------------------
 *Uma.K									created 11/03/2010
 * ---------------------------------------------------------------------------
 */

package com.artificialintelligence.report.controller;

//Java
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.artificialintelligence.report.manager.ReportManager;
import com.artificialintelligence.report.manager.ReportObject;
import com.artificialintelligence.report.util.ReportConstants;
import com.artificialintelligence.report.util.ReportNodeNameContainer;
import com.artificialintelligence.report.util.ThreadPoolExcecutorManager;

/**
 * The ReportBaseController class.
 *
 * @author Uma.K
 */
public class BaseReportController {
	private Log log = LogFactory.getLog(BaseReportController.class);


	private ReportManager reportManager=null;

	/**
	 *
	 * @return
	 */
	public ReportManager getReportManager() {
		return reportManager;
	}

	/**
	 *
	 * @param reportManager
	 */
	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}

	/**
	 * Method is called when the button to generate the report is selected. Calls the required PDF/Excel
	 * methods to generate the report.
	 *
	 * @param pRequest
	 * @param pResponse
	 * @throws IOException
	 */
	public void generateReport(final HttpServletRequest pRequest, final HttpServletResponse pResponse, ReportObject reportObj) throws IOException {
		try {
			long mainThreadId = Thread.currentThread().getId();
			log.debug("REPORTS::::::Into generateReport ...START..From Main Thread Id : " + mainThreadId);
			System.out.println("reportObj.getMReportType() +++++++++++++++++++"+reportObj.getMReportType());
			if (reportObj.getMReportType().equalsIgnoreCase(ReportConstants.PDF_REFCD)) {
				viewPdfReport(pResponse, reportObj);
			} else if (reportObj.getMReportType().equalsIgnoreCase(ReportConstants.EXCEL_REFCD)) {
				ThreadPoolExcecutorManager threadPool = ThreadPoolExcecutorManager.getInstance();
				final ReportObject report = reportObj;
				threadPool.runTask(new Runnable() {
					public void run() {
						try {
							viewExcelReport(pResponse, report);
						} catch (Exception e) {
							report.setExportFinished(true);
							e.printStackTrace();
						}
					}
				});
				log.debug("Waiting child thread export finish by Main Thread Id : " + mainThreadId);
				final long TWO_HOUR = 7200000l;
				final long end = System.currentTimeMillis() + TWO_HOUR;
				while (!reportObj.isExportFinished()) {
					Thread.currentThread().join(1000);
					if (System.currentTimeMillis() >= end) {
						log.debug("=== Time out Occure Main Thread Thread Id: " + mainThreadId);
						break;
					}
				}
				log.debug("Chid finished Continue run Main Thread Id : " + mainThreadId);
			} else if (reportObj.getMReportType().equalsIgnoreCase(ReportConstants.TXT_REFCD)) {
			    viewTextReport(pResponse, reportObj);
			}else if (reportObj.getMReportType().equalsIgnoreCase(ReportConstants.CSV_REFCD)) {
			    viewCsvReport(pResponse, reportObj);
			}
		} catch (JRException e) {
			// display stack trace in the browser
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			pResponse.setContentType(ReportConstants.CONTENT_TYPE_TEXT);
			pResponse.getOutputStream().print(stringWriter.toString());
		} catch (Exception sqle) {
			log.error("Error Generating The Report");
			sqle.printStackTrace();
			log.error(sqle.getMessage());
		} finally {
		    reportObj.setJasperPrint(null);
		    reportObj = null;
			pResponse.getOutputStream().flush();
			pResponse.getOutputStream().close();
		}
		log.debug("REPORTS::::::Into generateReport ...END...");
	}

	/**
	 * Method to view the report in the PDF format.
	 *
	 * @param pResponse
	 * @param pReportObject
	 * @throws ServletException
	 * @throws IOException
	 * @throws JRException
	 */
	protected void viewPdfReport(HttpServletResponse pResponse, ReportObject pReportObject) throws ServletException, IOException, JRException {
		log.debug("REPORTS::::::Into viewPdfReport ...START...");
		JasperPrint runReport = pReportObject.getJasperPrint();
		if(runReport!=null){
		ServletOutputStream lServletOutputStream = pResponse.getOutputStream();
		pResponse.reset();
		pResponse.setContentType(ReportConstants.CONTENT_TYPE_PDF);
		pResponse.setHeader( "Content-Disposition", "attachment; filename=" + pReportObject.getMReqName() + ".pdf;" );
		JasperExportManager.exportReportToPdfStream(runReport, lServletOutputStream);
		 if (pReportObject.getVirtualizer() != null)
		    {
			 pReportObject.getVirtualizer().cleanup();
		    }

		lServletOutputStream.flush();
		lServletOutputStream.close();
		}
		else{

			pResponse.setContentType("text/html");
		}
		log.debug("REPORTS::::::Into viewPdfReport ...end...");
	}

	/**
	 * Method to view the report in the Excel format.
	 *
	 * @param pResponse
	 * @param pReportObject
	 * @throws ServletException
	 * @throws IOException
	 * @throws JRException
	 */
	protected void viewExcelReport(HttpServletResponse pResponse, ReportObject pReportObject) throws ServletException, IOException, JRException {
		log.debug("REPORTS::::::Into viewExcelReport ...START...");
		log.debug("viewExcelReport..start.."+" Time::"+new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").format(new Date()));
		ServletOutputStream lServletOutputStream = pResponse.getOutputStream();
		pResponse.reset();
		pResponse.setContentType(ReportConstants.CONTENT_TYPE_EXCEL_2007);
		pResponse.setHeader( "Content-Disposition", "attachment; filename=" + pReportObject.getMReqName() + ".xlsx;" );
		
		JRXlsxExporter jRXlsxExporter = ReportManager.getCommonXlsxExporter(pReportObject.getJasperPrint());
		jRXlsxExporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, ReportConstants.TEMP);
		jRXlsxExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, lServletOutputStream);
		
		jRXlsxExporter.setParameter(JRXlsExporterParameter.START_PAGE_INDEX, 0);
		int endPageIndex = getEndPageIndex(pReportObject);
		log.debug("end page === > " + endPageIndex);
		jRXlsxExporter.setParameter(JRXlsExporterParameter.END_PAGE_INDEX, endPageIndex);
		
		try {
			JasperPrint jasperPrint = pReportObject.getJasperPrint();
			List pages = jasperPrint.getPages();
			int allPage = pages != null ? pages.size() -1 : 0;
			if(allPage > endPageIndex){
				log.debug("all page === > " + allPage + " incomplete data file "+pReportObject.getMReqName()+".xls limit 1200 pages.");
				pResponse.setHeader( "Content-Disposition", "attachment; filename=incompleteData_" + pReportObject.getMReqName() + ".xls;" );
			}
			jRXlsxExporter.exportReport();
		} catch (Exception e) {
			log.debug(e.getMessage());
		} finally {
			pReportObject.setExportFinished(true);
			lServletOutputStream.flush();
			lServletOutputStream.close();
			
		}
		log.debug("REPORTS::::::Into viewExcelReport ...end...");
		log.debug("viewExcelReport..end.."+" Time::"+new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").format(new Date()));
	}
	
	/**
	 * Method to view the report in the CSV format.
	 *
	 * @param pResponse
	 * @param pReportObject
	 * @throws ServletException
	 * @throws IOException
	 * @throws JRException
	 */
	protected void viewCsvReport(HttpServletResponse pResponse, ReportObject pReportObject) throws ServletException, IOException, JRException {
		log.debug("REPORTS::::::Into viewCsvReport ...START...");
		log.debug("viewCsvReport..start.."+" Time::"+new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").format(new Date()));
		ServletOutputStream lServletOutputStream = pResponse.getOutputStream();
		pResponse.reset();
		pResponse.setContentType(ReportConstants.CONTENT_TYPE_CSV);
		pResponse.setHeader( "Content-Disposition", "attachment; filename=" + pReportObject.getMReqName() + ".csv" );
		
		byte b[] = {(byte)0xEF,(byte)0xBB,(byte)0xBF};
		lServletOutputStream.write(b);
		
		JRCsvExporter csvApiProperties = ReportManager.getCsvApiProperties(pReportObject.getJasperPrint());
		csvApiProperties.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, ReportConstants.TEMP_CSV);
		csvApiProperties.setParameter(JRExporterParameter.OUTPUT_STREAM, lServletOutputStream);
		
		try {
			csvApiProperties.exportReport();
		} catch (Exception e) {
			log.debug(e.getMessage());
		} finally {
			lServletOutputStream.flush();
			lServletOutputStream.close();
		}
		
		log.debug("REPORTS::::::Into viewCsvReport ...end...");
		log.debug("viewCsvReport..end.."+" Time::"+new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").format(new Date()));
	}
	
	private int getEndPageIndex(ReportObject pReportObject) {
		JasperPrint jasperPrint = pReportObject.getJasperPrint();
		List pages = jasperPrint.getPages();
		int endPageIndex = (pages != null) ? (pages.size() -1) : 0;
		endPageIndex = (endPageIndex < 1200) ? (endPageIndex) : 1200;
		if (endPageIndex < 0) {
			endPageIndex = 0;
		}
		return endPageIndex;
	}

	 /*
	 * Construct the Report Object and the Criteria Map from the Request.
	 *
	 * @param pRequest
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ReportObject buildReportObject(HttpServletRequest pRequest) {
		// create the ReportObject and set the criteriaMap to it.
		ReportObject lReportObject = new ReportObject();
		Map<String, Object> lCriteriaMap = new HashMap<String, Object>();

		// iterate thru the parameters obtained from the request and set them in
		// the Criteria Map
		Map<String, String[]> lParameterMap = (HashMap<String, String[]>) pRequest.getParameterMap();

		Charset charset = Charset.forName("ISO8859-1");
		CharsetEncoder encoder = charset.newEncoder();
		CharBuffer chBuffer = null;
		
		if (!lParameterMap.isEmpty() && lParameterMap.size() > 0) {
			for (Map.Entry lCriteria : lParameterMap.entrySet()) {
				String paramKey = (String) lCriteria.getKey();
				String paramValue = (((String[]) lCriteria.getValue())[0]);
				
				chBuffer = CharBuffer.wrap(paramValue);		
				if (encoder.canEncode(chBuffer)) {
					paramValue = new String(paramValue.getBytes(charset), Charset.forName("UTF-8"));
				}
				
				if (paramKey != null && !paramKey.equals("")) {
					lCriteriaMap.put(paramKey, paramValue);
				} else {
					lCriteriaMap.put(paramKey, "");
				}
			}
		}

		lReportObject.setMReportCriterias(lCriteriaMap);

		if(pRequest.getParameter("reqname").endsWith("_web")){
			lReportObject.setMReqName(pRequest.getParameter("reqname").replace("_web", ""));
		}
		else{
			lReportObject.setMReqName(pRequest.getParameter("reqname"));
		}
		
		return lReportObject;
	}

	protected void viewTextReport(HttpServletResponse pResponse, ReportObject pReportObject) throws ServletException, IOException, JRException {
		log.debug("REPORTS::::::Into viewTextReport ...START...");
		log.debug("viewTextReport..start.."+" Time::"+new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").format(new Date()));
		ServletOutputStream lServletOutputStream = pResponse.getOutputStream();
		pResponse.reset();
		pResponse.setContentType("text/plain");
		pResponse.setHeader( "Content-Disposition", "attachment; filename=" + pReportObject.getMReqName() + ".txt;" );
		
		JRTextExporter textApiProperties = ReportManager.getTextApiProperties(pReportObject.getJasperPrint());
		textApiProperties.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "/jasperfiles/temp.txt");
		textApiProperties.setParameter(JRExporterParameter.OUTPUT_STREAM, lServletOutputStream);

		try {
			textApiProperties.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lServletOutputStream.flush();
			lServletOutputStream.close();
			
		}
		log.debug("REPORTS::::::Into viewExcelReport ...end...");
		log.debug("viewExcelReport..end.."+" Time::"+new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").format(new Date()));
	}


}
