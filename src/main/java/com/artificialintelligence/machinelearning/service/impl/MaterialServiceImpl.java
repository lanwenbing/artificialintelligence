package com.artificialintelligence.machinelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.MaterialDao;
import com.artificialintelligence.machinelearning.model.MaterialModel;
import com.artificialintelligence.machinelearning.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	private MaterialDao materialDao;


	public List<MaterialModel> queryMaterialsbyAlgorithmId(int id) {
		
		List<MaterialModel> materialList = materialDao.queryMaterialsbyAlgorithmId(id);
		
		return materialList;
	}


	public MaterialModel queryMaterialbyMaterialId(int id) {
		
		MaterialModel material = materialDao.queryMaterialbyMaterialId(id);
		return material;
	}
	
}
