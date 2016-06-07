package com.artificialintelligence.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.core.service.LoginService;
import com.artificialintelligence.dao.machinelearning.AlgorithmDao;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private AlgorithmDao algorithmDao;
}
