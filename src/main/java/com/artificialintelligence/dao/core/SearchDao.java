package com.artificialintelligence.dao.core;

import java.util.List;

import com.artificialintelligence.model.core.SearchContentModel;


public interface SearchDao {
	public List<SearchContentModel> searchArticle(String value, String lang);
	public List<SearchContentModel> searchMaterial(String value, String lang);
	public List<SearchContentModel> searchSystem(String value, String lang);
}
