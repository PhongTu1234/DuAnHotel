package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
    public String index() {
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
