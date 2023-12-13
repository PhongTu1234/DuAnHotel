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

import com.poly.Service.HotelService;
import com.poly.Service.PlacesService;
import com.poly.Service.RoomTypesService;
import com.poly.Service.ServiceService;
import com.poly.Service.UserService;
import com.poly.entity.Hotels;
import com.poly.entity.Payment;
import com.poly.entity.Places;
import com.poly.entity.RoomTypes;
import com.poly.entity.Services;
import com.poly.entity.Users;

@Controller
public class PlaceController {

	@Autowired
	HotelService hService;

	@Autowired
	RoomTypesService rtService;

	@Autowired
	ServiceService svService;

	@Autowired
    private PlacesService placeService;
	
	//xu ly admin
 	
 	@GetMapping("/places/form")
 	public String formPlace(Model model) {
 		model.addAttribute("places", new Places());
 		return "admin/Place/form";
 	}

 	@GetMapping("/places/index")
    public String showPlacesIndex(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		Pageable page = PageRequest.of(p-1, 10);
		Page<Places> places = placeService.findAll(page);
		model.addAttribute("places", places);
        return "admin/Place/index";
    }
 	
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/places/{id}")
    public String viewPlace(@PathVariable("id") Integer id, Model model) {
        Places place = placeService.findById(id);
        model.addAttribute("places", place);
        return "admin/Place/form";
    }

    @PostMapping("/places/create")
    public String createPlace(@ModelAttribute Places places) {
    	placeService.create(places);
        return "redirect:/places/form";
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
    
    @PostMapping("/places/update")
    public ModelAndView updatePlace(@ModelAttribute Places place) {
        if (place.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
        	placeService.update(place);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	placeService.create(place);
        }
        return new ModelAndView("redirect:/places/index");
    }

    @GetMapping("/places/delete/{id}")
    public ModelAndView deletePlace(@PathVariable("id") Integer id) {
    	placeService.delete(id);
        return new ModelAndView("redirect:/places/index");
    }
}
