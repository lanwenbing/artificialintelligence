package com.artificialintelligence.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.artificialintelligence.core.controller.LoginController;
import com.artificialintelligence.core.service.LoginService;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String[] Login_URI = {"/insertComment/","/userlogin"};

	
	@Autowired
	private LoginController loginController;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = true;
		String url = request.getRequestURL().toString();
		System.out.println(">>>: " + url);
		for (String s : Login_URI) {
			if (url.contains(s)) {
				flag = false;
				break;
			}
		}
		if (!flag) {
			loginController.getLoginUser(request, response);
			request.getRequestDispatcher("/artificialintelligent/getcategory.do").forward(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
