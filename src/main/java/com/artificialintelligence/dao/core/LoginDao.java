package com.artificialintelligence.dao.core;

import com.artificialintelligence.model.core.UserModel;

public interface LoginDao {
	public UserModel queryUser(String username, String password);
}
