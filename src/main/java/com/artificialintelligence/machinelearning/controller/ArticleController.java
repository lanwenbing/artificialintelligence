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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artificialintelligence.dao.machinelearning.result.Result;
import com.artificialintelligence.dao.machinelearning.util.BaseConstants;
import com.artificialintelligence.machinelearning.service.ArticleService;
import com.artificialintelligence.machinelearning.service.CommentService;
import com.artificialintelligence.model.machinelearning.ArticleModel;
import com.artificialintelligence.model.machinelearning.CommentModel;

@Controller  
@RequestMapping("/article")
public class ArticleController extends BaseController{

	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	
	
	private static Logger logger = Logger.getLogger(ArticleController.class);
	
	@RequestMapping("/queryarticle/{id}")
	public String queryArticle(Map<String, Object> map, @PathVariable("id") Integer id, HttpServletRequest request,HttpServletResponse response) {
		
		ArticleModel article= articleService.queryArticlebyArticleId(id);
		request.setAttribute("article", article);

		List<CommentModel> comments= commentService.queryComments(BaseConstants.commentType.Article.getnCode(), id);
		request.setAttribute("comments", comments);
		
		return "article";
		
	}
	
	@RequestMapping(value="/insertComment/{id}", method=RequestMethod.POST)
	@ResponseBody
	public Result<List<CommentModel>> insertComment(Map<String, Object> map, @PathVariable("id") Integer id, HttpServletRequest request,HttpServletResponse response) {
		
		Result<List<CommentModel>> result = new Result<List<CommentModel>>();
		String comment = request.getParameter("comment");
		boolean value= commentService.insertComment(comment, BaseConstants.commentType.Article.getnCode(), id);
		List<CommentModel> comments= commentService.queryComments(BaseConstants.commentType.Article.getnCode(), id);
		result.setStatus(value);
		result.setData(comments);
		
		return result;
		
	}
}
