package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.model.machinelearning.SystemModel;

public interface SystemDao extends BaseDao{

	public List<SystemModel> querySystemsByAlgorithmId(int id, String lang);
	public SystemModel querySystemBySystemId(int id, String lang);
}
