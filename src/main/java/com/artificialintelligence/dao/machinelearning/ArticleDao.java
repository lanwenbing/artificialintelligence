package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.machinelearning.model.ArticleModel;

public interface ArticleDao extends BaseDao{

	public List<ArticleModel> queryArticlebyAlgorithmId(int id);
	
	public ArticleModel queryArticlebyArticleId(int id);
}
