package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.machinelearning.model.SystemModel;

public interface SystemDao extends BaseDao{

	public List<SystemModel> querySystemsByAlgorithmId(int id);
	public SystemModel querySystemBySystemId(int id);
}
