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
public class ProductController {
	@RequestMapping("/product/detail")
	public String detail() {
		return "product/single-product-variable";
	}
}
