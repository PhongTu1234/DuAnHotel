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

import com.poly.Service.PlacesService;
import com.poly.Service.RoomService;
import com.poly.Service.ServiceRoomsService;
import com.poly.Service.ServiceService;
import com.poly.entity.Places;
import com.poly.entity.RoomTypes;
import com.poly.entity.ServiceRooms;
import com.poly.entity.Services;

@Controller
public class ServiceRoomsController {
	
	@Autowired
    private ServiceRoomsService sv_rService;
	
	@Autowired
    private ServiceService svService;
	
	@Autowired
    private RoomService rService;
	
	//xu ly admin
	@GetMapping("/serviceRooms/form")
 	public String formServiceRoom(Model model) {
 		model.addAttribute("serviceRooms", new ServiceRooms());
 		return "admin/ServiceRoom/form";
 	}

 	@GetMapping("/serviceRooms/index")
    public String showServiceRoomsIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
		Pageable page = PageRequest.of(p, 10);
		Page<ServiceRooms> serviceRooms = sv_rService.findAll(page);
		model.addAttribute("serviceRooms", serviceRooms);
        return "admin/ServiceRoom/index";
    }
 
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/serviceRooms/{id}")
    public String viewServiceRoom(@PathVariable("id") Integer id, Model model) {
    	ServiceRooms serviceRooms = sv_rService.findById(id);
        model.addAttribute("serviceRooms", serviceRooms);
        return "admin/ServiceRoom/form";
    }

    @PostMapping("/serviceRooms/create")
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
    
    @PostMapping("/serviceRooms/update")
    public ModelAndView updateServiceRoom(@ModelAttribute ServiceRooms serviceRooms, Model model) {
//        if (serviceRooms.getId() != null) {
//            // Nếu có ID, thực hiện cập nhật
//        	sv_rService.update(serviceRooms);
//        } else {
//            // Nếu không có ID, thực hiện thêm mới
//        	sv_rService.create(serviceRooms);
//        }
    	
    	String Rooms = serviceRooms.getRooms().getName();
    	String Services= serviceRooms.getServices().getName();
        if(rService.findByRoomName(Rooms) != null && svService.findByServiceName(Services) != null) {
        	if (serviceRooms.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
        		serviceRooms.setRooms(rService.findByRoomName(Rooms));
        		serviceRooms.setServices(svService.findByServiceName(Services));
        		sv_rService.update(serviceRooms);
	        } else {
	        	serviceRooms.setRooms(rService.findByRoomName(Rooms));
	        	serviceRooms.setServices(svService.findByServiceName(Services));
	            // Nếu không có ID, thực hiện thêm mới
	        	sv_rService.create(serviceRooms);
	        }
        	return new ModelAndView("redirect:/serviceRooms/index");
        }else {
        	model.addAttribute("message", "phòng hoặc dịch vụ không có!");
        	return new ModelAndView("admin/ServiceRoom/form");
        }
        
    }

    @GetMapping("/serviceRooms/delete/{id}")
    public ModelAndView deleteServiceRoom(@PathVariable("id") Integer id) {
    	sv_rService.delete(id);
        return new ModelAndView("redirect:/serviceRooms/index");
    }
}
