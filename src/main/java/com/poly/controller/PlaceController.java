package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Service.HotelService;
import com.poly.Service.RoomTypesService;
import com.poly.Service.ServiceService;
import com.poly.entity.Hotels;
import com.poly.entity.RoomTypes;
import com.poly.entity.Services;

@Controller
public class PlaceController {

	@Autowired
	HotelService hService;

	@Autowired
	RoomTypesService rtService;

	@Autowired
	ServiceService svService;

	@RequestMapping("/hotel/place={id}")
	public String findById(Model model, @PathVariable("id") Integer id) {

		model.addAttribute("place_id", id);

		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		// model.addAttribute("roomtype", roomtype);
		// int counta = roomtype.size();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findAll();
		int count = item.size();
		model.addAttribute("counthotel", count);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel0();
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel1();
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel2();
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel3();
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel4();
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel5();
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = 1;
		int last = start - 1;
		int next = start + 1;
		int SOLuongTrongTrang = 15;
		double end = count / SOLuongTrongTrang;
		double endRounded = Math.ceil(end);
		// model.addAttribute("count", endRounded);
		List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * SOLuongTrongTrang);
		model.addAttribute("items", items);
		model.addAttribute("last", last);
		model.addAttribute("start", start);
		model.addAttribute("next", next);
//		List<Hotels> items = hService.findByPlaceId(id);
//		model.addAttribute("items", items);
		return "shop";
	}

	@RequestMapping("/hotel/place={place_id}/lpage={last}")
	public String Last(Model model, @PathVariable("last") String last, @PathVariable("place_id") String pid) {
		int id = Integer.parseInt(pid);
		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		// model.addAttribute("roomtype", roomtype);
		// int counta = roomtype.size();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findAll();
		int count = item.size();
		model.addAttribute("counthotel", count);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel0();
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel1();
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel2();
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel3();
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel4();
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel5();
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = Integer.parseInt(last);
		if (start == 1) {
			List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}

	@RequestMapping("/hotel/place={place_id}/npage={next}")
	public String Next(Model model, @PathVariable("next") String next, @PathVariable("place_id") String pid) {
		int id = Integer.parseInt(pid);
		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		// model.addAttribute("roomtype", roomtype);
		// int counta = roomtype.size();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findAll();
		int count = item.size();
		model.addAttribute("counthotel", count);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel0();
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel1();
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel2();
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel3();
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel4();
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel5();
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = Integer.parseInt(next);
		double end = count / 15;
		double endRounded = Math.ceil(end);

		if (endRounded < start) {
			List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
		} else {

			List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}

	@RequestMapping("/place={id}/hotel/start={Level}&&page={page}")
	public String findHotelByPlaceidAndStart(Model model, @PathVariable("id") Integer id,
			@PathVariable("Level") Integer Level, @PathVariable("page") Integer page) {
		List<Hotels> items = hService.findHotelByPlaceidAndStart(Level, id, (page - 1) * 15);
		model.addAttribute("items", items);
		return "shop";
	}

}
