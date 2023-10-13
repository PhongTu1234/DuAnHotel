package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.Service.HotelService;
import com.poly.Service.RoomService;
import com.poly.entity.Hotels;
import com.poly.entity.Rooms;
@Controller
public class RoomTypeController {
	
	@Autowired
	RoomService rtservice;
	
	@Autowired
	HotelService hservice;
	
	@RequestMapping("/roomTypes/{room_type_id}")
    public String roomTypes(Model model, @RequestParam("room_type_id") Optional<String> rtid) {
		if (rtid.isPresent()) {
			List<Rooms> listht = rtservice.findByRoomTypesId(rtid.get());
			model.addAttribute("items", listht);
		} else {
			List<Rooms> listht = rtservice.findAll();
			model.addAttribute("items", listht);
		}
		return "room-type";
	}
	
//	public String a(Model model, @RequestParam("room_type_id") String rtid) {
//		if (rtid != null) {
//			List<Hotels> listht = hservice.a(rtid);
//			model.addAttribute("items", listht);
//		} else {
//			List<Rooms> listht = rtservice.findAll();
//			model.addAttribute("items", listht);
//		}
//		return "room-type";
//	}
	
	@RequestMapping("/roomTypes")
    public String roomTypes(Model model) {
		List<Rooms> listht = rtservice.findAll();
		model.addAttribute("items", listht);
		return "room-type";
	}
}
