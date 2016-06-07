package com.artificialintelligence.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.core.service.RegisterService;
import com.artificialintelligence.dao.core.RegisterDao;
import com.artificialintelligence.model.core.UserModel;

@Service
public class RegisterServiceImpl implements RegisterService{

	@Autowired
	private RegisterDao registerDao;
	
	public void addUser(UserModel user) {
		
		registerDao.addUser(user);

	}

}
