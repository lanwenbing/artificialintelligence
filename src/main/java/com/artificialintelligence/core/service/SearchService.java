package com.artificialintelligence.core.service;

import java.util.List;

import com.artificialintelligence.model.core.SearchContentModel;


public interface SearchService {
	public List<SearchContentModel> searchType(String value, int type, String lang);
}
