package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Users;

@Controller
public class UsersController {

	@RequestMapping("/auth/myaccount")
	public String Myaccount(Model model) {
		return "auth/my-account";
	}
	
	
	
}