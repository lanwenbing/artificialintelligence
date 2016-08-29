package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.model.machinelearning.MaterialModel;

public interface MaterialDao extends BaseDao{

	public List<MaterialModel> queryMaterialsbyAlgorithmId(int id, String lang);
	public MaterialModel queryMaterialbyMaterialId(int id, String lang);
}
