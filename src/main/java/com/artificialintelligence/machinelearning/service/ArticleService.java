package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.machinelearning.model.ArticleModel;

public interface ArticleService {

	public List<ArticleModel> queryArticlebyAlgorithmId(int id);
	public ArticleModel queryArticlebyArticleId(int id);

}
