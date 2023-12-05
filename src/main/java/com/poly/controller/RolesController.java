package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.ImagesService;
import com.poly.Service.RoleService;
import com.poly.entity.Blogs;
import com.poly.entity.Images;
import com.poly.entity.Places;
import com.poly.entity.Role;

@Controller
public class RolesController {
	
	@Autowired
    private RoleService rolesService;
	
	//xu ly admin
	 	@GetMapping("/roles/form")
	 	public String formRole(Model model) {
	 		model.addAttribute("roles", new Role());
	 		return "admin/Roles/form";
	 	}

	 	@GetMapping("/roles/index")
	    public String showRolesIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
			Pageable page = PageRequest.of(p, 10);
			Page<Role> roles = rolesService.findAll(page);
			model.addAttribute("roles", roles);
	 		return "admin/Roles/index";
	    }
	 
//	    @GetMapping
//	    public String listPlaces(Model model) {
//	        model.addAttribute("places", placeService.findAll());
//	        return "";
//	    }

	    @GetMapping("/roles/{id}")
	    public String viewRole(@PathVariable("id") String id, Model model) {
	    	Role roles = rolesService.findById(id);
	        model.addAttribute("roles", roles);
	        return "admin/Roles/form";
	    }

	    @PostMapping("/roles/create")
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
	    
	    @PostMapping("/roles/update")
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

	    @GetMapping("/roles/delete/{id}")
	    public ModelAndView deleteRole(@PathVariable("id") String id) {
	    	rolesService.delete(id);
	        return new ModelAndView("redirect:/roles/index");
	    }
}
