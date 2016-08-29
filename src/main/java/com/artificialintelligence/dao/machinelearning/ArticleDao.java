package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.model.machinelearning.ArticleModel;

public interface ArticleDao extends BaseDao{

	public List<ArticleModel> queryArticlebyAlgorithmId(int id, String lang);
	
	public ArticleModel queryArticlebyArticleId(int id, String lang);
}
