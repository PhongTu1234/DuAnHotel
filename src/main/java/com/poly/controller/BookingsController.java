package com.poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Service.Booking_RoomService;

@Controller
public class BookingsController {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	Booking_RoomService brService;
	
	@RequestMapping("/hotel/room/order/cart")
	public String cart() {
		return "/order/cart";
	}
	
//	@RequestMapping("/hotel/room/order/cart/checkout")
//	public String checkout() {
//		return "/order/checkout";
//	}

	@RequestMapping("/cart/checkout")
	public String checkout() {
		if (!(request.isUserInRole("DIRE") || request.isUserInRole("STAF") || request.isUserInRole("CUST"))) {
			return "redirect:/auth/login/form";
		}
		return "cart/checkout";
	}

	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", brService.getBookingDetailsForUser(username));
		return "order/cart";
	}
}
