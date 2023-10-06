package com.poly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.HotelTypesService;

@Component
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	HotelTypesService hoteltypesService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("ht", hoteltypesService.findAll());
		//request.setAttribute("cates", categoryService.findAll());
	}

	
}
