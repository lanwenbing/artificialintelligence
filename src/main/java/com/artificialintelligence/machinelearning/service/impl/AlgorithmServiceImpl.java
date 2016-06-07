package com.artificialintelligence.machinelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.AlgorithmDao;
import com.artificialintelligence.machinelearning.service.AlgorithmService;
import com.artificialintelligence.model.machinelearning.AlgorithmModel;

@Service
public class AlgorithmServiceImpl implements AlgorithmService{

	@Autowired
	private AlgorithmDao algorithmDao;


	public List<AlgorithmModel> queryAlgorithmbyCategoryId(int id) {
		
		List<AlgorithmModel> algorithmList = algorithmDao.queryAlgorithmbyCategoryId(id);
		
		return algorithmList;
	}

	public AlgorithmModel queryAlgorithmbyAlgorithmId(int id) {
		
		AlgorithmModel algorithm = algorithmDao.queryAlgorithmbyAlgorithmId(id);
		
		return algorithm;
	}
	
}
