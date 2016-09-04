package com.artificialintelligence.machinelearning.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artificialintelligence.machinelearning.service.AlgorithmService;
import com.artificialintelligence.machinelearning.service.CategoryService;
import com.artificialintelligence.model.machinelearning.AlgorithmModel;
import com.artificialintelligence.model.machinelearning.CategoryModel;

@Controller  
@RequestMapping("/artificialintelligent")
public class ArtificialIntelligentController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AlgorithmService algorithmService;
	
	private static Logger logger = Logger.getLogger(ArtificialIntelligentController.class);
	
	@RequestMapping("/getcategory")
	public String getCategory(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		
		List<CategoryModel>  categoryList =  new ArrayList<CategoryModel>();
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		categoryList = categoryService.getCategory(lang);
		request.setAttribute("categoryList", categoryList);
		return "artificialintelligent";
		
	}
	
	@RequestMapping("/querycategory/{id}")
	public String artificialIntelligent(@PathVariable("id") Integer id, Map<String, Object> map, HttpServletRequest request,HttpServletResponse response) {
		CategoryModel  category =  new CategoryModel();
		HttpSession session = request.getSession(); 
		String lang = (String) session.getAttribute("lang");
		category = categoryService.queryCategory(id,lang);
		request.setAttribute("category", category);
		
		List<AlgorithmModel> algorithmList = new ArrayList<AlgorithmModel>();
		algorithmList = algorithmService.queryAlgorithmbyCategoryId(id, lang);
		logger.info(algorithmList.size());
		request.setAttribute("algorithmList", algorithmList);
		
		return "category";
		
	}
}

