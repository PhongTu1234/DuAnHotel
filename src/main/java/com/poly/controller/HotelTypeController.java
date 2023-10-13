package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.Service.HotelService;
import com.poly.Service.HotelTypesService;
import com.poly.entity.HotelTypes;
import com.poly.entity.Hotels;
@Controller
public class HotelTypeController {
	
	@Autowired
	HotelService htservice;
	
	@Autowired	
	HotelTypesService httypeservice;
	
	@RequestMapping("/hotelTypes/{hotel_type_id}")
    public String hotelTypes(Model model, @RequestParam("hotel_type_id") Optional<String> htid) {
		if (htid.isPresent()) {
			List<Hotels> listht = htservice.findByHotelTypesId(htid.get());
			model.addAttribute("items", listht);
		} else {
			List<Hotels> listht = htservice.findAll();
			model.addAttribute("items", listht);
		}
		return "hotel_type";
	}
	
	@RequestMapping("/hotelTypes")
    public String hotelTypes(Model model) {
		List<HotelTypes> listht = httypeservice.findAll();
		model.addAttribute("items", listht);
		return "hotel_type";
	}

}
