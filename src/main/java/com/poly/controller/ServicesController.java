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

import com.poly.Service.ServiceService;
import com.poly.Service.UserService;
import com.poly.entity.Services;
import com.poly.entity.Users;

@Controller
@RequestMapping("/services")
public class ServicesController {
	@Autowired
    private ServiceService serviceService;
 	
 	@GetMapping("/form")
 	public String formSevice(Model model) {
 		model.addAttribute("sv", new Services());
 		return "admin/Services/form";
 	}

 	@GetMapping("/index")
    public String showServicesIndex(Model model) {
 		model.addAttribute("id", serviceService.findAll());
        return "admin/Services/index";
    }
 
    @GetMapping
    public String listServices(Model model) {
        model.addAttribute("sv", serviceService.findAll());
        return "Roles/index";
    }

    @GetMapping("/{id}")
    public String viewServices(@PathVariable("id") Integer id, Model model) {
    	Services sv = serviceService.findById(id);
        model.addAttribute("sv", sv);
        return "admin/Services/form";
    }

    @PostMapping("/create")
    public String createServices(@ModelAttribute Services sv) {
    	serviceService.create(sv);
        return "redirect:/Services/form";
    }

//    @PostMapping("/update")
//    public ModelAndView updateUser(@Validated @ModelAttribute("user") Users user) {
//        userService.update(user);
//        return new ModelAndView("redirect:/users/index");
//    }
//
//    @GetMapping("/delete/{cmt}")
//    public ModelAndView deleteUser(@PathVariable String cmt) {
//        userService.delete(cmt);
//        return new ModelAndView("redirect:/users/index");
//    }
    
    @PostMapping("/update")
    public ModelAndView updateService(@ModelAttribute Services sv) {
        if (sv.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
        	serviceService.update(sv);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	serviceService.create(sv);
        }
        return new ModelAndView("redirect:/services/index");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteService(@PathVariable("id") Integer id) {
    	serviceService.delete(id);
        return new ModelAndView("redirect:/services/index");
    }
}
