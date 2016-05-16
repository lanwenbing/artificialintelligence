package com.artificialintelligence.machinelearning.model;

import java.util.Date;

public class AlgorithmModel extends BaseModel {

	private Integer id;
	private String algorithmName;
	private String algorithmDescription;
	private String algorithmPro;
	private String algorithmCon;
	private Integer materialNum;
	private Integer articleNum;
	private Integer systemNum;
	private Integer categoryId;
	private Date createTm;
	private Date modifyTm;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAlgorithmName() {
		return algorithmName;
	}
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}
	public String getAlgorithmDescription() {
		return algorithmDescription;
	}
	public void setAlgorithmDescription(String algorithmDescription) {
		this.algorithmDescription = algorithmDescription;
	}
	public String getAlgorithmPro() {
		return algorithmPro;
	}
	public void setAlgorithmPro(String algorithmPro) {
		this.algorithmPro = algorithmPro;
	}
	public String getAlgorithmCon() {
		return algorithmCon;
	}
	public void setAlgorithmCon(String algorithmCon) {
		this.algorithmCon = algorithmCon;
	}
	public Integer getMaterialNum() {
		return materialNum;
	}
	public void setMaterialNum(Integer materialNum) {
		this.materialNum = materialNum;
	}
	public Integer getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(Integer articleNum) {
		this.articleNum = articleNum;
	}
	public Integer getSystemNum() {
		return systemNum;
	}
	public void setSystemNum(Integer systemNum) {
		this.systemNum = systemNum;
	}
	public Date getCreateTm() {
		return createTm;
	}
	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}
	public Date getModifyTm() {
		return modifyTm;
	}
	public void setModifyTm(Date modifyTm) {
		this.modifyTm = modifyTm;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	


}
