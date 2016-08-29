package com.artificialintelligence.machinelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.MaterialDao;
import com.artificialintelligence.machinelearning.service.MaterialService;
import com.artificialintelligence.model.machinelearning.MaterialModel;

@Service
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	private MaterialDao materialDao;


	public List<MaterialModel> queryMaterialsbyAlgorithmId(int id, String lang) {
		
		List<MaterialModel> materialList = materialDao.queryMaterialsbyAlgorithmId(id, lang);
		
		return materialList;
	}


	public MaterialModel queryMaterialbyMaterialId(int id, String lang) {
		
		MaterialModel material = materialDao.queryMaterialbyMaterialId(id, lang);
		return material;
	}
	
}
