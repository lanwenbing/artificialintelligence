package com.artificialintelligence.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.core.service.LoginService;
import com.artificialintelligence.dao.core.LoginDao;
import com.artificialintelligence.model.core.UserModel;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDao loginDao;

	public UserModel login(String username, String password) {

		UserModel user = loginDao.queryUser(username, password);
		return user;
	}
}
