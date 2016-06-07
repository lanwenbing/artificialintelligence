package com.artificialintelligence.core.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artificialintelligence.core.service.RegisterService;
import com.artificialintelligence.model.core.UserModel;

@Controller  
@RequestMapping("/register")
public class RegisterController{

	@Autowired
	private RegisterService registerService;
	
	private static Logger logger = Logger.getLogger(RegisterController.class);
	
	@RequestMapping("/register")
	public String register(Map<String, Object> map) {
		
		return "register";
	}
	 	
	@RequestMapping("/adduser")
	public String addUser(UserModel user) {
		if(user!=null)
			registerService.addUser(user);
		
		return "registersuccess";
	}
	
}
