package com.artificialintelligence.machinelearning.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artificialintelligence.core.service.CommentService;
import com.artificialintelligence.dao.machinelearning.result.Result;
import com.artificialintelligence.dao.machinelearning.util.BaseConstants;
import com.artificialintelligence.machinelearning.service.MaterialService;
import com.artificialintelligence.model.core.CommentModel;
import com.artificialintelligence.model.machinelearning.MaterialModel;

@Controller  
@RequestMapping("/material")
public class MaterialController extends BaseController{

	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private CommentService commentService;
	
	private static Logger logger = Logger.getLogger(MaterialController.class);
	
	@RequestMapping("/querymaterial/{id}")
	public String getSystems(Map<String, Object> map, @PathVariable("id") Integer id, HttpServletRequest request,HttpServletResponse response) {
		
		MaterialModel material= materialService.queryMaterialbyMaterialId(id);
		request.setAttribute("material", material);
		
		List<CommentModel> comments= commentService.queryComments(BaseConstants.commentType.Material.getnCode(), id);
		request.setAttribute("comments", comments);
		
		return "material";
		
	}
	
	@RequestMapping(value="/insertComment/{id}", method=RequestMethod.POST)
	@ResponseBody
	public Result<List<CommentModel>> insertComment(Map<String, Object> map, @PathVariable("id") Integer id, HttpServletRequest request,HttpServletResponse response) {
		
		Result<List<CommentModel>> result = new Result<List<CommentModel>>();
		String comment = request.getParameter("comment");
		boolean value= commentService.insertComment(comment, BaseConstants.commentType.Material.getnCode(), id);
		List<CommentModel> comments= commentService.queryComments(BaseConstants.commentType.Material.getnCode(), id);
		result.setStatus(value);
		result.setData(comments);
		
		return result;
		
	}	
}
