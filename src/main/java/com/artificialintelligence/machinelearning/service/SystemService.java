package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.model.machinelearning.SystemModel;

public interface SystemService {

	public List<SystemModel> querySystemsByAlgorithmId(int id, String lang);
	public SystemModel querySystemBySystemId(int id, String lang);

}
