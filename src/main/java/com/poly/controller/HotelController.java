package com.poly.controller;

import java.util.List;
import java.lang.Math;

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
//		int count = 109;
		int start = 1;
		int last = start - 1;
		int next = start + 1;
//		int page= 12;
//		float end = count/page;
//		double endPage = Math.ceil(end) * 10;
		double count = item.size();
        int SOLuongTrongTrang = 15;
        double end = count / SOLuongTrongTrang;
        double endRounded = Math.ceil(end) ;
//		model.addAttribute("items", item);
        //double a = count - SOLuongTrongTrang * start;
//        double b = (endRounded - 1) * 15;
		model.addAttribute("count", endRounded);
		
		List<Hotels> items = hService.findPage((start-1) * SOLuongTrongTrang);
		model.addAttribute("items", items);
		model.addAttribute("last", last);
		model.addAttribute("start", start);
		model.addAttribute("next", next);
		
		return "shop";
	}
	
	@RequestMapping("/hotel/last/{last}")
	public String Last(Model model, @PathVariable("last") String last) {
		int start = Integer.parseInt(last);
		if(start==1) {
			List<Hotels> items= hService.findPage((start-1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("start", start);
		}else {
			List<Hotels> items= hService.findPage((start) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start );
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}
	
	@RequestMapping("/hotel/next/{next}")
	public String Next(Model model, @PathVariable("next") String next) {
		int start = Integer.parseInt(next);
		List<Hotels> item = hService.findAll();
		double count = item.size();
		double end = count / 15;
		double endRounded = Math.ceil(end) ;
		
		if(endRounded < start) {
			List<Hotels> items= hService.findPage((start-2) * 15);
			model.addAttribute("items", items);
			model.addAttribute("start", start);
		}else {
			
			List<Hotels> items= hService.findPage((start-1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start );
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}
	
	
	
	@RequestMapping("/hotel/0to1")
	public String Level1(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel0to1();
		model.addAttribute("items", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/1to2")
	public String Level2(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel1to2();
		model.addAttribute("items", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/2to3")
	public String Level3(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel2to3();
		model.addAttribute("items", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/3to4")
	public String Level4(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel3to4();
		model.addAttribute("items", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/4to5")
	public String Level5(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel4to5();
		model.addAttribute("items", item);
		return "shop";
	}
	
	@RequestMapping("/hotel/5")
	public String LevelMax(Model model) {
		List<Hotels> item = hService.findHotelByHotelLevel5();
		model.addAttribute("items", item);
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
