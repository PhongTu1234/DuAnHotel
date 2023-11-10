package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HotelTypeController {

	@RequestMapping("/HotelType_1")
	public String shoptype1() {
		return "shop";
	}

	@RequestMapping("/HotelType_2")
	public String shoptype2() {
		return "shop";
	}

}
