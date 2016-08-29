package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.model.machinelearning.AlgorithmModel;

public interface AlgorithmDao extends BaseDao{

	public List<AlgorithmModel> queryAlgorithmbyCategoryId(int id, String lang);
	public AlgorithmModel queryAlgorithmbyAlgorithmId(int id, String lang);
}
