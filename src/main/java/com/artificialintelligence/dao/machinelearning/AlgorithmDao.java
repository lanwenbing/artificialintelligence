package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.machinelearning.model.AlgorithmModel;

public interface AlgorithmDao extends BaseDao{

	public List<AlgorithmModel> queryAlgorithmbyCategoryId(int id);
	public AlgorithmModel queryAlgorithmbyAlgorithmId(int id);
}
