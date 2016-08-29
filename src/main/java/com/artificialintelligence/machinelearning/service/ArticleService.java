package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.model.machinelearning.ArticleModel;

public interface ArticleService {

	public List<ArticleModel> queryArticlebyAlgorithmId(int id, String lang);
	public ArticleModel queryArticlebyArticleId(int id, String lang);

}
