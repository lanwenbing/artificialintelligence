/*
 * JobReportBean.java ver. 1.00 
 *
 * Bean class for Automatic data transfer log report. 
 * 
 * Modification History
 * ---------------------------------------------------------------------------
 * Name                   Date         Modification description
 * ----                   ----         ------------------------
 * Uma.K				  01/12/2010	Created	
 * ---------------------------------------------------------------------------
 */
package com.artificialintelligence.report.manager;

import java.sql.Timestamp;
import java.util.Date;

/**
 * This is the bean class,which will have the data to be loaded for the 
 * automatic data transfer log report.
 *
 * @author Uma.K
 * @version 1.0
 */
public class JobReportBean {
	
	private String job_group;
	private String batch_id;
	private String job_name;
	private String description;
	private String status;
	private String message;
	private Timestamp job_start_timstm;
	private Timestamp job_finish_timstm;
	private Date job_start_dt;
	private Date job_finish_dt;
	private String filename;
	
	/**
	 * getter for the job group attribute.
	 * @return
	 */
	public String getJob_group() {
		return job_group;
	}

	/**
	 * setter for the job group attribute.
	 * @param job_group
	 */
	public void setJob_group(String job_group) {
		this.job_group = job_group;
	}

	/**
	  getter for the batch id attribute. 
	 * @return
	 */
	public String getBatch_id() {
		return batch_id;
	}

	/**
	 * setter for the batch id attribute. 
	 * @param batch_id
	 */
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}

	/**
	 * getter for the job name attribute. 
	 * @return
	 */
	public String getJob_name() {
		return job_name;
	}

	/**
	 * setter for the job name attribute.
	 * @param job_name
	 */
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	/**
	 * getter for the job description attribute.
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * setter for the job description attribute.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getter for the job status attribute.
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * setter for the job status attribute.
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * getter for the job message attribute.
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * setter for the job message attribute.
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * getter for the job start time attribute.
	 * @return
	 */
	public Timestamp getJob_start_timstm() {
		return job_start_timstm;
	}

	/**
	 * setter for the job start time attribute.
	 * @param job_start_timstm
	 */
	public void setJob_start_timstm(Timestamp job_start_timstm) {
		this.job_start_timstm = job_start_timstm;
	}

	/**
	 * getter for the job end time attribute.
	 * @return
	 */
	public Timestamp getJob_finish_timstm() {
		return job_finish_timstm;
	}

	/**
	 * setter for the job end time attribute.
	 * @param job_finish_timstm
	 */
	public void setJob_finish_timstm(Timestamp job_finish_timstm) {
		this.job_finish_timstm = job_finish_timstm;
	}

	/**
	 * getter for the job start date attribute.
	 * @return
	 */
	public Date getJob_start_dt() {
		return job_start_dt;
	}

	/**
	 * setter for the job start date attribute.
	 * @param job_start_dt
	 */
	public void setJob_start_dt(Date job_start_dt) {
		this.job_start_dt = job_start_dt;
	}

	/**
	 * getter for the job end date attribute.
	 * @return
	 */
	public Date getJob_finish_dt() {
		return job_finish_dt;
	}

	/**
	 * setter for the job end date attribute.
	 * @param job_finish_dt
	 */
	public void setJob_finish_dt(Date job_finish_dt) {
		this.job_finish_dt = job_finish_dt;
	}

	/**
	 * getter for the interface file name attribute.
	 * @return
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * setter for the interface file name attribute.
	 * @param filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
