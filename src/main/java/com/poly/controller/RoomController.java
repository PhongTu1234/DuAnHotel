package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Service.RoomService;
import com.poly.entity.Rooms;

@Controller
public class RoomController {
	@Autowired
	RoomService rservice;

	@RequestMapping("/hotel/room/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Rooms item = rservice.findById(id);
		model.addAttribute("room", item);
		return "product/single-product-variable";
	}
	
	@RequestMapping("/hotel/room/wishlist")
	public String  wishlist(Model model) {
		return "/wishlist";
	}
	
	@RequestMapping("/hotel/room/compare")
	public String  compare(Model model) {
		return "/compare";
	}
}
