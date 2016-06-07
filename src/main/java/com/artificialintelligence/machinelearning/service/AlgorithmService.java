package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.model.machinelearning.AlgorithmModel;

public interface AlgorithmService {

	public List<AlgorithmModel> queryAlgorithmbyCategoryId(int id);
	public AlgorithmModel queryAlgorithmbyAlgorithmId(int id);

}
