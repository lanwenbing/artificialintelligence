package com.artificialintelligence.core.service;

import com.artificialintelligence.model.core.UserModel;

public interface LoginService {
	public UserModel login(String username, String password);
}
