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

import com.poly.Service.ImagesService;
import com.poly.Service.RoleService;
import com.poly.entity.Images;
import com.poly.entity.Role;

@Controller
@RequestMapping("/roles")
public class RolesController {
	
	@Autowired
    private RoleService rolesService;
	
	//xu ly admin
	 	@GetMapping("/form")
	 	public String formRole(Model model) {
	 		model.addAttribute("roles", new Role());
	 		return "admin/Roles/form";
	 	}

	 	@GetMapping("/index")
	    public String showRolesIndex(Model model) {
	 		model.addAttribute("roles", rolesService.findAll());
	        return "admin/Roles/index";
	    }
	 
//	    @GetMapping
//	    public String listPlaces(Model model) {
//	        model.addAttribute("places", placeService.findAll());
//	        return "";
//	    }

	    @GetMapping("/{id}")
	    public String viewRole(@PathVariable("id") String id, Model model) {
	    	Role roles = rolesService.findById(id);
	        model.addAttribute("roles", roles);
	        return "admin/Roles/form";
	    }

	    @PostMapping("/create")
	    public String createRole(@ModelAttribute Role roles) {
	    	rolesService.create(roles);
	        return "redirect:/roles/form";
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
	    public ModelAndView updateRole(@ModelAttribute Role roles) {
	        if (roles.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
	        	rolesService.update(roles);
	        } else {
	            // Nếu không có ID, thực hiện thêm mới
	        	rolesService.create(roles);
	        }
	        return new ModelAndView("redirect:/roles/index");
	    }

	    @GetMapping("/delete/{id}")
	    public ModelAndView deleteRole(@PathVariable("id") String id) {
	    	rolesService.delete(id);
	        return new ModelAndView("redirect:/roles/index");
	    }
}
