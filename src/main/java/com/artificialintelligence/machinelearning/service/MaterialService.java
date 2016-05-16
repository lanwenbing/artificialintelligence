package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.machinelearning.model.MaterialModel;

public interface MaterialService {

	public List<MaterialModel> queryMaterialsbyAlgorithmId(int id);
	public MaterialModel queryMaterialbyMaterialId(int id);

}
