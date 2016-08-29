package com.artificialintelligence.machinelearning.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.AlgorithmDao;
import com.artificialintelligence.machinelearning.service.AlgorithmService;
import com.artificialintelligence.model.machinelearning.AlgorithmModel;

@Service
public class AlgorithmServiceImpl implements AlgorithmService{

	@Autowired
	private AlgorithmDao algorithmDao;


	public List<AlgorithmModel> queryAlgorithmbyCategoryId(int id, String lang) {
		
		List<AlgorithmModel> algorithmList = algorithmDao.queryAlgorithmbyCategoryId(id, lang);
		return algorithmList;
	}

	public AlgorithmModel queryAlgorithmbyAlgorithmId(int id, String lang) {
		
		AlgorithmModel algorithm = algorithmDao.queryAlgorithmbyAlgorithmId(id, lang);
		
		return algorithm;
	}
	
}
