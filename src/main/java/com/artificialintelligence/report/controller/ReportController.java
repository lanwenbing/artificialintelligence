/*
 * @source ReportController.java
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

package com.artificialintelligence.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artificialintelligence.report.manager.ReportObject;
import com.artificialintelligence.report.util.ReportConstants;

/**
 * The ReportController class is responsible for all the following operations.
 * 1. loads the respective search criteria page launched via the menu, based on
 * the request. 2. invokes the method to validate the criteria, if invalid then
 * displays suitable error message else calls the method to display the report.
 *
 * @author Lynn
 */

@Controller  
@RequestMapping("/report")
public class ReportController extends BaseReportController {

	private Log log = LogFactory.getLog(ReportController.class);

	/**
	 * To display report.
	 *
	 * @param pRequest
	 * @param pResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/displayreport")
	public void displayReport(final HttpServletRequest pRequest, final HttpServletResponse pResponse) throws ServletException, IOException 
	{

		ReportObject reportObj = buildReportObject(pRequest);
		String reqName = "";
		reqName = pRequest.getParameter("reqname");
		reportObj.setMReqName(reqName);
		
		String srcType = "";
		srcType = pRequest.getParameter("srctype");
		try {
			if(srcType.equalsIgnoreCase(ReportConstants.BEAN)){
				reportObj.setMInpSrc(ReportConstants.BEAN);
			}else if(srcType.equalsIgnoreCase(ReportConstants.CSV)){
				reportObj.setMInpSrc(ReportConstants.CSV);
			}
			getReportManager().process(reportObj);

		} catch (Exception e1) {
			throw new ServletException(e1);
		}
		generateReport(pRequest, pResponse, reportObj);
		
	}

}
