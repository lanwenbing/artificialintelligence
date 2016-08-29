package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.model.machinelearning.MaterialModel;

public interface MaterialService {

	public List<MaterialModel> queryMaterialsbyAlgorithmId(int id, String lang);
	public MaterialModel queryMaterialbyMaterialId(int id, String lang);

}
