package com.artificialintelligence.machinelearning.controller;

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
import com.artificialintelligence.machinelearning.service.ArticleService;
import com.artificialintelligence.machinelearning.service.MaterialService;
import com.artificialintelligence.machinelearning.service.SystemService;
import com.artificialintelligence.model.machinelearning.AlgorithmModel;
import com.artificialintelligence.model.machinelearning.ArticleModel;
import com.artificialintelligence.model.machinelearning.MaterialModel;
import com.artificialintelligence.model.machinelearning.SystemModel;

@Controller  
@RequestMapping("/algorithm")
public class AlgorithmController extends BaseController{

	@Autowired
	private AlgorithmService algorithmService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private SystemService systemService;
	
	private static Logger logger = Logger.getLogger(AlgorithmController.class);
	
	@RequestMapping("/queryalgorithm/{id}")
	public String getSystems(Map<String, Object> map, @PathVariable("id") Integer id, HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession(); 
		String lang = (String) session.getAttribute("lang"); 
		
		AlgorithmModel algorithm= algorithmService.queryAlgorithmbyAlgorithmId(id, lang);
		request.setAttribute("algorithm", algorithm);
		
		List<ArticleModel> articles= articleService.queryArticlebyAlgorithmId(id, lang);
		request.setAttribute("articles", articles);
		
		List<MaterialModel> materials= materialService.queryMaterialsbyAlgorithmId(id, lang);
		request.setAttribute("materials", materials);
		
		List<SystemModel> systems= systemService.querySystemsByAlgorithmId(id, lang);
		request.setAttribute("systems", systems);
		
		return "algorithm";
		
	}
	
	@RequestMapping("/message")
	public String message(Map<String, Object> map) {
		
		return "summarypage";
	}
}
