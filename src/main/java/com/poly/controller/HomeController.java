package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Service.HotelService;
import com.poly.Service.RoomService;
import com.poly.entity.Hotels;
import com.poly.entity.Rooms;

@Controller
public class HomeController {
	
	@Autowired
	HotelService htservice;
	
	@Autowired
	RoomService rtservice;
	
	@RequestMapping({"/", "/index"})
    public String index(Model model) {
//		List<Rooms> room = rtservice.findAll();
		List<Rooms> rooms = rtservice.findByRoom1to8();
//		model.addAttribute("items", room);
		model.addAttribute("items", rooms);
		return "index";
	}
	
//	@RequestMapping({"/roomtop8"})
//    public String roomTop8(Model model) {
//		List<Rooms> room = rtservice.findByRoom1to8();
//		model.addAttribute("items", room);
//		return "index";
//	}
	
	@RequestMapping({ "/admin", "/admin/index" })
	public String admin(Model model) {
		List<Hotels> hotels = htservice.findAll();
		int count = hotels.size();
		model.addAttribute("items", count);
		return "/admin/dashboard";
	}
	
	
	@RequestMapping("shop")
	public String shop() {
		return "shop";
	}

	
	@RequestMapping("cart")
	public String cart() {
		return "cart";
	}
	
	@RequestMapping("about")
	public String about() {
		return "about";
	}
	
	@RequestMapping("404")
	public String error() {
		return "404";
	}
	
	@RequestMapping("contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("faq")
	public String faq() {
		return "faq";
	}
	
	@RequestMapping("wishlist")
	public String wishlist() {
		return "wishlist";
	}
	
	
}
