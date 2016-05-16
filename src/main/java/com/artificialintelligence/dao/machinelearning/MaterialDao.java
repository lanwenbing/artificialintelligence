package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.machinelearning.model.MaterialModel;

public interface MaterialDao extends BaseDao{

	public List<MaterialModel> queryMaterialsbyAlgorithmId(int id);
	public MaterialModel queryMaterialbyMaterialId(int id);
}
