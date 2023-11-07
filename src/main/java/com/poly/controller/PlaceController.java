package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Service.HotelService;
import com.poly.entity.Hotels;

@Controller
public class PlaceController {

	@Autowired
	HotelService hService;
	
	@RequestMapping("/place/hotel/{id}")
	public String findById(Model model,  @PathVariable("id") Integer id) {
		List<Hotels> items = hService.findByPlaceId(id);
		model.addAttribute("items", items);
		return "shop";
	}
	
	
}
