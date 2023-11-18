package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.UserService;
import com.poly.entity.Users;

@Controller
@RequestMapping("/users")
public class UsersController {
	 	@Autowired
	    private UserService userService;
	 	
	 	@GetMapping("/form")
	 	public String formUser(Model model) {
	 		model.addAttribute("user", new Users());
	 		return "admin/Users/form";
	 	}

	 	@GetMapping("/index")
	    public String showUsersIndex(Model model) {
	 		model.addAttribute("users", userService.findAll());
	        return "admin/Users/index";
	    }
	 
	    @GetMapping
	    public String listUsers(Model model) {
	        model.addAttribute("users", userService.findAll());
	        return "Roles/index";
	    }

	    @GetMapping("/{cmt}")
	    public String viewUser(@PathVariable("cmt") String cmt, Model model) {
	        Users user = userService.findById(cmt);
	        model.addAttribute("user", user);
	        return "admin/Users/form";
	    }

	    @PostMapping("/create")
	    public String createUser(@ModelAttribute Users user) {
	        userService.create(user);
	        return "redirect:/users/form";
	    }

//	    @PostMapping("/update")
//	    public ModelAndView updateUser(@Validated @ModelAttribute("user") Users user) {
//	        userService.update(user);
//	        return new ModelAndView("redirect:/users/index");
//	    }
//
//	    @GetMapping("/delete/{cmt}")
//	    public ModelAndView deleteUser(@PathVariable String cmt) {
//	        userService.delete(cmt);
//	        return new ModelAndView("redirect:/users/index");
//	    }
	    
	    @PostMapping("/update")
	    public ModelAndView updateUser(@ModelAttribute Users user) {
	        if (user.getCmt() != null) {
	            // Nếu có ID, thực hiện cập nhật
	            userService.update(user);
	        } else {
	            // Nếu không có ID, thực hiện thêm mới
	            userService.create(user);
	        }
	        return new ModelAndView("redirect:/users/index");
	    }

	    @GetMapping("/delete/{cmt}")
	    public ModelAndView deleteUser(@PathVariable("cmt") String cmt) {
	        userService.delete(cmt);
	        return new ModelAndView("redirect:/users/index");
	    }
}