package com.artificialintelligence.core.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artificialintelligence.core.service.SearchService;
import com.artificialintelligence.model.core.SearchContentModel;

@Controller  
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	
	@RequestMapping("/searchtype")
	public String searchType(Map<String, Object> map, String value, int type, HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		String lang = (String) session.getAttribute("lang");
		
		List<SearchContentModel> list = searchService.searchType(value, type, lang);
		
		map.put("list", list);
		switch(type){
		case 1:
			map.put("type", "article");
			break;
		case 2:
			map.put("type", "material");
			break;
		case 3:
			map.put("type", "system");
			break;
		default:
			break;
	}
		
		return "searchtype";
	}
	 
}
