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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artificialintelligence.core.service.CommentService;
import com.artificialintelligence.dao.machinelearning.result.Result;
import com.artificialintelligence.dao.machinelearning.util.BaseConstants;
import com.artificialintelligence.machinelearning.service.SystemService;
import com.artificialintelligence.model.core.CommentModel;
import com.artificialintelligence.model.machinelearning.SystemModel;

@Controller  
@RequestMapping("/system")
public class SystemController extends BaseController{

	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private CommentService commentService;
	
	private static Logger logger = Logger.getLogger(SystemController.class);
	
	@RequestMapping("/querysystem/{id}")
	public String getSystem(Map<String, Object> map, @PathVariable("id") Integer id, HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(); 
		String lang = (String) session.getAttribute("lang");
		SystemModel system= systemService.querySystemBySystemId(id, lang);
		request.setAttribute("system", system);
		
		List<CommentModel> comments= commentService.queryComments(BaseConstants.commentType.System.getnCode(), id);
		request.setAttribute("comments", comments);
		
		return "system";
		
	}
	
	@RequestMapping(value="/insertcomment/{id}", method=RequestMethod.POST)
	@ResponseBody
	public Result<List<CommentModel>> insertComment(Map<String, Object> map, @PathVariable("id") Integer id, HttpServletRequest request,HttpServletResponse response) {
		
		Result<List<CommentModel>> result = new Result<List<CommentModel>>();
		String comment = request.getParameter("comment");
		boolean value= commentService.insertComment(comment, BaseConstants.commentType.System.getnCode(), id);
		List<CommentModel> comments= commentService.queryComments(BaseConstants.commentType.System.getnCode(), id);
		result.setStatus(value);
		result.setData(comments);
		
		return result;
		
	}
	
}
