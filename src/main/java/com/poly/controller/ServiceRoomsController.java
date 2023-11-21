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

import com.poly.Service.PlacesService;
import com.poly.Service.ServiceRoomsService;
import com.poly.entity.Places;
import com.poly.entity.ServiceRooms;

@Controller
@RequestMapping("/serviceRooms")
public class ServiceRoomsController {
	
	@Autowired
    private ServiceRoomsService sv_rService;
	
	//xu ly admin
	@GetMapping("/form")
 	public String formServiceRoom(Model model) {
 		model.addAttribute("serviceRooms", new ServiceRooms());
 		return "admin/ServiceRoom/form";
 	}

 	@GetMapping("/index")
    public String showServiceRoomsIndex(Model model) {
 		model.addAttribute("serviceRooms", sv_rService.findAll());
        return "admin/ServiceRoom/index";
    }
 
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/{id}")
    public String viewServiceRoom(@PathVariable("id") Integer id, Model model) {
    	ServiceRooms serviceRooms = sv_rService.findById(id);
        model.addAttribute("serviceRooms", serviceRooms);
        return "admin/ServiceRoom/form";
    }

    @PostMapping("/create")
    public String createServiceRoom(@ModelAttribute ServiceRooms serviceRooms) {
    	sv_rService.create(serviceRooms);
        return "redirect:/serviceRooms/form";
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
    public ModelAndView updateServiceRoom(@ModelAttribute ServiceRooms serviceRooms) {
        if (serviceRooms.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
        	sv_rService.update(serviceRooms);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	sv_rService.create(serviceRooms);
        }
        return new ModelAndView("redirect:/serviceRooms/index");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteServiceRoom(@PathVariable("id") Integer id) {
    	sv_rService.delete(id);
        return new ModelAndView("redirect:/serviceRooms/index");
    }
}
