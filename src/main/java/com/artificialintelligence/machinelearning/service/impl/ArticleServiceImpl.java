package com.artificialintelligence.machinelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.ArticleDao;
import com.artificialintelligence.machinelearning.model.ArticleModel;
import com.artificialintelligence.machinelearning.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;


	public List<ArticleModel> queryArticlebyAlgorithmId(int id) {
		
		List<ArticleModel> articleList = articleDao.queryArticlebyAlgorithmId(id);
		
		return articleList;
	}


	public ArticleModel queryArticlebyArticleId(int id) {
		
		ArticleModel article = articleDao.queryArticlebyArticleId(id);
		
		return article;
	}

	
}
