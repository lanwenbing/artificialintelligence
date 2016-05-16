package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.machinelearning.model.SystemModel;

public interface SystemService {

	public List<SystemModel> querySystemsByAlgorithmId(int id);
	public SystemModel querySystemBySystemId(int id);

}
