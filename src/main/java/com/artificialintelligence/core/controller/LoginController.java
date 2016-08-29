package com.artificialintelligence.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artificialintelligence.core.service.LoginService;
import com.artificialintelligence.model.core.UserModel;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	private static Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping("/transfertologin")
	public String getLoginUser() {
		return "login";
	}

	@RequestMapping("/userlogin")
	public String getLoginUser(HttpServletRequest request,
			HttpServletResponse response) {
		UserModel user = null;
		HttpSession session = request.getSession();
		Boolean islogin = (Boolean) session.getAttribute("islogin");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		if (islogin == null || islogin.equals(false)) {
			user = loginService.login(username, password);
			if (user != null&&user.getCountry()!=null) {
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("islogin", true);
				request.getSession().setAttribute("country", user.getCountry());
			}
		}

		return "login";
	}

	@RequestMapping("/logout")
	public void logOut(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		Boolean islogin = (Boolean) session.getAttribute("islogin");
		request.getSession().setAttribute("username", null);
		request.getSession().setAttribute("islogin", false);
		try {
			request.getRequestDispatcher("/artificialintelligent/getcategory.do").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@RequestMapping("/changelanguage")
	@ResponseBody
	public void changeLanguage(HttpServletRequest request,
			HttpServletResponse response) {
		
		String language = (String) request.getParameter("language");
		if(language!=null)
			request.getSession().setAttribute("country", language);
			HttpSession session = request.getSession(); 
			session.setAttribute("lang", language); 
		
	}
}
