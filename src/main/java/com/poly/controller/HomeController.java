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
import com.poly.entity.Hotels;

@Controller
public class HomeController {
	@Autowired
	HotelService htservice;
	
	@GetMapping({"/", "/index"})
    public String index(Model model, @RequestParam("htid") Optional<String> htid) {
		if (htid.isPresent()) {
			List<Hotels> list = htservice.findByHotelTypesId(htid.get());
			model.addAttribute("items", list);
		} else {
			List<Hotels> list = htservice.findAll();
			model.addAttribute("items", list);
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
