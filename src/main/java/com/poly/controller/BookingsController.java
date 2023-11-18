package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookingsController {

	@RequestMapping("/hotel/room/order/cart")
	public String cart() {
		return "/order/cart";
	}
	
	@RequestMapping("/hotel/room/order/cart/checkout")
	public String checkout() {
		return "/order/checkout";
	}

}
