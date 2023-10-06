package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping({"/", "/index"})
    public String index(Model model, @RequestParam("htid") Optional<String> htid, @RequestParam("rtid") Optional<String> rtid) {
		if (htid.isPresent()) {
			List<Hotels> listht = htservice.findByHotelTypesId(htid.get());
			model.addAttribute("items", listht);
		} else {
			List<Hotels> listht = htservice.findAll();
			model.addAttribute("items", listht);
		}
		if (rtid.isPresent()) {
			List<Rooms> listrt = rtservice.findByRoomTypesId(rtid.get());
			model.addAttribute("items", listrt);
		} else {
			List<Rooms> listrt = rtservice.findAll();
			model.addAttribute("items", listrt);
		}
		return "index";
	}
	
	@RequestMapping("shop")
	public String shop() {
		return "shop";
	}
	
	@RequestMapping("blog")
	public String blog() {
		return "blog";
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
