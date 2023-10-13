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
public class HotelController {
	
	@Autowired
	HotelService hService;

	@RequestMapping("/hotel/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Hotels item = hService.findById(id);
		model.addAttribute("item", item);
		return "product/	";
	}
	
	@RequestMapping("/hotel/all")
	public String Level(Model model) {
		List<Hotels> item = hService.findAll();
		model.addAttribute("item", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/0to1")
	public String Level1(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel0to1();
		model.addAttribute("item", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/1to2")
	public String Level2(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel1to2();
		model.addAttribute("item", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/2to3")
	public String Level3(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel2to3();
		model.addAttribute("item", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/3to4")
	public String Level4(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel3to4();
		model.addAttribute("item", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/4to5")
	public String Level5(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel4to5();
		model.addAttribute("item", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/5")
	public String LevelMax(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel5();
		model.addAttribute("item", item);
		return "shop";
	}
//	@RequestMapping("/hotel/0to1")
//	public String Level1(Model model) {
//		String startLV = "1";
//		String endLV = "2";
//		List<Hotels> item = hService.findByHotelTypeHotelLevelBetween(startLV, endLV);
//		model.addAttribute("item", item);
//		return "shop";
//	}
}
