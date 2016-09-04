package com.artificialintelligence.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.core.service.SearchService;
import com.artificialintelligence.dao.core.SearchDao;
import com.artificialintelligence.model.core.SearchContentModel;

@Service
public class SearchServiceImpl implements SearchService{

	
	@Autowired
	private SearchDao searchDao;
	
	public List<SearchContentModel> searchType(String value, int type, String lang) {

		List<SearchContentModel> modelList = new ArrayList<SearchContentModel>();
		switch(type){
			case 1:
				modelList = searchDao.searchArticle(value, lang);
				break;
			case 2:
				modelList = searchDao.searchMaterial(value, lang);
				break;
			case 3:
				modelList = searchDao.searchSystem(value, lang);
				break;
			default:
				break;
		}
		
		return modelList;
	}

}
