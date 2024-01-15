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

import com.poly.Service.ServiceService;
import com.poly.Service.SlugService;
import com.poly.Service.UserService;
import com.poly.entity.Places;
import com.poly.entity.Services;
import com.poly.entity.Users;

@Controller
public class ServicesController {
	@Autowired
    private ServiceService serviceService;
 	
	@Autowired
	SlugService slugService;
	
	//xu ly admin
 	@GetMapping("/quan-ly-dich-vu/them-moi")
 	public String formSevice(Model model) {
 		model.addAttribute("services", new Services());
 		return "admin/Services/form";
 	}

 	@GetMapping("/quan-ly-dich-vu/danh-sach")
    public String showServicesIndex(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		Pageable page = PageRequest.of(p-1, 10);
		Page<Services> services = serviceService.findAll(page);
		model.addAttribute("services", services);
		
		model.addAttribute("slugService", slugService);
		
        return "admin/Services/index";
    }

    @GetMapping("/quan-ly-dich-vu/{slug}/{id}")
    public String viewServices(@PathVariable("id") Integer id, Model model) {
    	Services services = serviceService.findById(id);
        model.addAttribute("services", services);
        return "admin/Services/form";
    }

    @PostMapping("/services/create")
    public String createServices(@ModelAttribute Services services) {
    	serviceService.create(services);
        return "redirect:/quan-ly-dich-vu/danh-sach";
    }
    
    @PostMapping("/services/update")
    public ModelAndView updateService(@ModelAttribute Services services) {
        if (services.getId() != null) {
        	serviceService.update(services);
        } else {
        	serviceService.create(services);
        }
        return new ModelAndView("redirect:/quan-ly-dich-vu/danh-sach");
    }

    @GetMapping("/services/delete/{id}")
    public ModelAndView deleteService(@PathVariable("id") Integer id) {
    	serviceService.delete(id);
        return new ModelAndView("redirect:/quan-ly-dich-vu/danh-sach");
    }
}
