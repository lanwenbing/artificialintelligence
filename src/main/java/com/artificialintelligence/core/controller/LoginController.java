package com.artificialintelligence.core.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artificialintelligence.core.service.LoginService;

@Controller  
@RequestMapping("/login")
public class LoginController{

	@Autowired
	private LoginService loginService;
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping("/userlogin")
	public String userLogin(Map<String, Object> map, @PathVariable("id") Integer id, HttpServletRequest request,HttpServletResponse response) {
		
		return "login";
	}
	
}
