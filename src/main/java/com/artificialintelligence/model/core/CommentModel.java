package com.artificialintelligence.model.core;

import java.util.Date;

import com.artificialintelligence.model.machinelearning.BaseModel;

public class CommentModel extends BaseModel {

	private Integer id;
	private String content;
	private String type;
	private String typeId;
	private Date tm;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public Date getTm() {
		return tm;
	}
	public void setTm(Date tm) {
		this.tm = tm;
	}

}
