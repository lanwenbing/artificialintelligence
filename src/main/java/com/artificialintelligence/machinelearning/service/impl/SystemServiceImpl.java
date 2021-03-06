package com.artificialintelligence.machinelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.SystemDao;
import com.artificialintelligence.machinelearning.service.SystemService;
import com.artificialintelligence.model.machinelearning.SystemModel;

@Service
public class SystemServiceImpl implements SystemService{

	@Autowired
	private SystemDao systemDao;
	
	public List<SystemModel> querySystemsByAlgorithmId(int id, String lang) {
		
		List<SystemModel> systemList = systemDao.querySystemsByAlgorithmId(id, lang);
		return systemList;
	}

	public SystemModel querySystemBySystemId(int id, String lang) {
		SystemModel system = systemDao.querySystemBySystemId(id, lang);
		return system;
	}

}
