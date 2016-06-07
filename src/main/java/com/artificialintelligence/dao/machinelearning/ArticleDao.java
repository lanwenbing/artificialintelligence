package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.model.machinelearning.ArticleModel;

public interface ArticleDao extends BaseDao{

	public List<ArticleModel> queryArticlebyAlgorithmId(int id);
	
	public ArticleModel queryArticlebyArticleId(int id);
}
