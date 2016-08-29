package com.artificialintelligence.machinelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.ArticleDao;
import com.artificialintelligence.machinelearning.service.ArticleService;
import com.artificialintelligence.model.machinelearning.ArticleModel;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;


	public List<ArticleModel> queryArticlebyAlgorithmId(int id, String lang) {
		
		List<ArticleModel> articleList = articleDao.queryArticlebyAlgorithmId(id, lang);
		
		return articleList;
	}


	public ArticleModel queryArticlebyArticleId(int id, String lang) {
		
		ArticleModel article = articleDao.queryArticlebyArticleId(id, lang);
		
		return article;
	}

	
}
