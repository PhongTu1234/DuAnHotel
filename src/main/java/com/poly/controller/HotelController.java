package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Service.HotelService;
import com.poly.Service.RoomService;
import com.poly.Service.RoomTypesService;
import com.poly.Service.ServiceService;
import com.poly.entity.Hotels;
import com.poly.entity.RoomTypes;
import com.poly.entity.Rooms;
import com.poly.entity.Services;

@Controller
public class HotelController {

	@Autowired
	HotelService hService;

	@Autowired
	RoomTypesService rtService;

	@Autowired
	ServiceService svService;
	
	@Autowired
	RoomService roomService;
	
	private void shop(Model model) {
        List<RoomTypes> roomtype = rtService.findAll();
        List<Hotels> hoteltype = hService.findAll();
        List<Services> services = svService.findAll();
        model.addAttribute("roomtype", roomtype);
        model.addAttribute("hoteltype", hoteltype);
        model.addAttribute("services", services);

        List<Hotels> item = hService.findAll();
        int count = item.size();
        model.addAttribute("counthotel", count);

        for (int i = 0; i <= 5; i++) {
            List<Hotels> hotelLevel = hService.findHotelByHotelLevel(i);
            model.addAttribute("countHotelLevel" + i, hotelLevel.size());
        }
    }

	@RequestMapping("/hotel/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Hotels item = hService.findById(id);
		model.addAttribute("item", item);
		
		List<Rooms> room = roomService.findByHotelId(id);
		model.addAttribute("room", room);
		return "hotel-detail";
	}

	// Hotel
	@RequestMapping("/hotel/all")
	public String Hotel(Model model) {
		shop(model);
		int start = 1;
		List<Hotels> hotel = hService.findAll();
		int count = hotel.size();
		int next = start + 1;
		int SOLuongTrongTrang = 15;
		double end = count / SOLuongTrongTrang;
		// double endRounded = Math.ceil(end);
		// model.addAttribute("count", endRounded);
		List<Hotels> items = hService.findPage((start - 1) * SOLuongTrongTrang);
		model.addAttribute("items", items);
		model.addAttribute("start", start);
		model.addAttribute("next", next);
		return "shop";
	}

	@RequestMapping("/hotel/lpage={last}")
	public String HotelLast(Model model, @PathVariable("last") String plast) {

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

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = Integer.parseInt(plast);
		int last = start - 1;
		if (start == 1) {
			List<Hotels> items = hService.findPage((start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<Hotels> items = hService.findPage((start) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}

	@RequestMapping("/hotel/npage={next}")
	public String HotelNext(Model model, @PathVariable("next") String next) {

		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findAll();
		int count = item.size();
		model.addAttribute("counthotel", count);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = Integer.parseInt(next);
		double end = count / 15;
		double endRounded = Math.ceil(end);

		if (endRounded < start) {
			List<Hotels> items = hService.findPage((start - 2) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
		} else {

			List<Hotels> items = hService.findPage((start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}

	// Hotel By start
	@RequestMapping("/hotel/HotelType={level}")
	public String hotellevel(Model model, @PathVariable("level") String hotellevel) {
		int level = Integer.parseInt(hotellevel);
		model.addAttribute("level", level);
		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		// model.addAttribute("roomtype", roomtype);
		// int counta = roomtype.size();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findAll();
		int countHotel = item.size();
		model.addAttribute("counthotel", countHotel);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = 1;
		// int last = start - 1;
		// int next = start + 1;
		// int SOLuongTrongTrang = 15;
		// List<Hotels> hotel = hService.findHotelByHotelLevel(level);
		// int count = hotel.size();
		// double end = count / SOLuongTrongTrang;
		// double endRounded = Math.ceil(end);
		// model.addAttribute("count", endRounded);
		List<Hotels> items = hService.findHotelByLevel(level, (start - 1) * 15);
		model.addAttribute("items", items);
		// model.addAttribute("last", start - 1);
		model.addAttribute("start", start);
		model.addAttribute("next", start + 1);
		return "shop";
	}

	@RequestMapping("/hotel/HotelType={level}/lpage={last}")
	public String hotellevelLast(Model model, @PathVariable("level") String hotellevel,
			@PathVariable("last") String last) {
		int level = Integer.parseInt(hotellevel);
		model.addAttribute("level", level);
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

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = Integer.parseInt(last);
		if (start == 1) {
			List<Hotels> items = hService.findHotelByLevel(level, (start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<Hotels> items = hService.findHotelByLevel(level, (start) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}

	@RequestMapping("/hotel/HotelType={level}/npage={next}")
	public String hotellevelNext(Model model, @PathVariable("level") String hotellevel,
			@PathVariable("next") String next) {
		int level = Integer.parseInt(hotellevel);
		model.addAttribute("level", level);
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

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int countHotel = 0;
		if (level == 0) {
			countHotel = HotelLevel0.size();
		} else {

			if (level == 1) {
				countHotel = HotelLevel1.size();
			} else {
				if (level == 2) {
					countHotel = HotelLevel2.size();
				} else {
					if (level == 3) {
						countHotel = HotelLevel3.size();
					} else {
						if (level == 4) {
							countHotel = HotelLevel4.size();
						} else {
							countHotel = HotelLevel5.size();
						}

					}
				}
			}
		}
		int start = Integer.parseInt(next);
		double end = countHotel / 15;
		double endRounded = Math.ceil(end);

		if (endRounded < start) {
			List<Hotels> items = hService.findHotelByLevel(level, (start - 2) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
		} else {
			List<Hotels> items = hService.findHotelByLevel(level, (start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}

	// Hotel By Place
	@RequestMapping("/hotel/place={id}")
	public String HotelByPlace(Model model, @PathVariable("id") Integer id) {

		model.addAttribute("place_id", id);

		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		// model.addAttribute("roomtype", roomtype);
		// int counta = roomtype.size();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findByPlaceId(id);
		int count = item.size();
		model.addAttribute("counthotel", count);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int SOLuongTrongTrang = 15;
		double end = count / SOLuongTrongTrang;
		double endRounded = Math.ceil(end);
		// model.addAttribute("count", endRounded);
//		List<Hotels> items = hService.findByPlaceId(id);
//		model.addAttribute("items", items);
		return "shop";
	}

	@RequestMapping("/hotel/place={place_id}/lpage={last}")
	public String HotelByPlaceLast(Model model, @PathVariable("last") String last,
			@PathVariable("place_id") String pid) {
		int id = Integer.parseInt(pid);
		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		// model.addAttribute("roomtype", roomtype);
		// int counta = roomtype.size();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findByPlaceId(id);
		int count = item.size();
		model.addAttribute("counthotel", count);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
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
	public String HotelByPlaceNext(Model model, @PathVariable("next") String next,
			@PathVariable("place_id") String pid) {
		int id = Integer.parseInt(pid);
		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		// model.addAttribute("roomtype", roomtype);
		// int counta = roomtype.size();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findByPlaceId(id);
		int count = item.size();
		model.addAttribute("counthotel", count);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
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

	// Hotel By start and Place
	@RequestMapping("/hotel/HotelType={level}/place={place_id}")
	public String hotellevelAndPlace(Model model, @PathVariable("level") String hotellevel,
			@PathVariable("place_id") String Place) {
		int level = Integer.parseInt(hotellevel);
		model.addAttribute("level", level);

		int place_id = Integer.parseInt(Place);
		model.addAttribute("place_id", place_id);

		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		// model.addAttribute("roomtype", roomtype);
		// int counta = roomtype.size();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findAll();
		int countHotel = item.size();
		model.addAttribute("counthotel", countHotel);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = 1;
		// int last = start - 1;
		// int next = start + 1;
		// int SOLuongTrongTrang = 15;
		// List<Hotels> hotel = hService.findHotelByHotelLevel(level);
		// int count = hotel.size();
		// double end = count / SOLuongTrongTrang;
		// double endRounded = Math.ceil(end);
		// model.addAttribute("count", endRounded);
		List<Hotels> items = hService.findHotelByPlaceidAndStart(level, place_id, (start - 1) * 15);
		model.addAttribute("items", items);
		// model.addAttribute("last", start - 1);
		model.addAttribute("start", start);
		model.addAttribute("next", start + 1);
		return "shop";
	}

	@RequestMapping("/hotel/HotelType={level}/place={place_id}/lpage={last}")
	public String hotellevelAndPlaceLast(Model model, @PathVariable("level") String hotellevel,
			@PathVariable("place_id") String place, @PathVariable("last") String last) {
		int level = Integer.parseInt(hotellevel);
		model.addAttribute("level", level);

		int place_id = Integer.parseInt(place);
		model.addAttribute("place_id", place_id);

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

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = Integer.parseInt(last);
		if (start == 1) {
			List<Hotels> items = hService.findHotelByLevel(level, (start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<Hotels> items = hService.findHotelByLevel(level, (start) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}

	@RequestMapping("/hotel/HotelType={level}/place={place_id}/npage={next}")
	public String hotellevelAndPlaceNext(Model model, @PathVariable("level") String hotellevel
													, @PathVariable("place_id") String place
													, @PathVariable("next") String next) {
		int level = Integer.parseInt(hotellevel);
		model.addAttribute("level", level);

		int place_id = Integer.parseInt(place);
		model.addAttribute("place_id", place_id);
		
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

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int countHotel = 0;
		if (level == 0) {
			countHotel = HotelLevel0.size();
		} else {

			if (level == 1) {
				countHotel = HotelLevel1.size();
			} else {
				if (level == 2) {
					countHotel = HotelLevel2.size();
				} else {
					if (level == 3) {
						countHotel = HotelLevel3.size();
					} else {
						if (level == 4) {
							countHotel = HotelLevel4.size();
						} else {
							countHotel = HotelLevel5.size();
						}

					}
				}
			}
		}
		int start = Integer.parseInt(next);
		double end = countHotel / 15;
		double endRounded = Math.ceil(end);

		if (endRounded < start) {
			List<Hotels> items = hService.findHotelByPlaceidAndStart(level, place_id, (start - 2) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
		} else {
			List<Hotels> items = hService.findHotelByPlaceidAndStart(level, place_id, (start - 1) * 15);
			model.addAttribute("items", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		return "shop";
	}

	// Hotel and service
	@RequestMapping("/hotel/Service={id}")
	public String LevelAndRoomType(Model model	, @PathVariable("id") String service_id) {
		int service = Integer.parseInt(service_id);
		model.addAttribute("service", service);
		List<RoomTypes> roomtype = rtService.findAll();
		List<Hotels> hoteltype = hService.findAll();
		List<Services> services = svService.findAll();
		// model.addAttribute("roomtype", roomtype);
		// int counta = roomtype.size();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);
		List<Hotels> item = hService.findAll();
		int countHotel = item.size();
		model.addAttribute("counthotel", countHotel);

		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
		model.addAttribute("countHotelLevel0", HotelLevel0.size());

		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
		model.addAttribute("countHotelLevel1", HotelLevel1.size());

		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
		model.addAttribute("countHotelLevel2", HotelLevel2.size());

		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
		model.addAttribute("countHotelLevel3", HotelLevel3.size());

		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
		model.addAttribute("countHotelLevel4", HotelLevel4.size());

		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
		model.addAttribute("countHotelLevel5", HotelLevel5.size());

		int start = 1;
		int last = start - 1;
		int next = start + 1;
		int SOLuongTrongTrang = 15;
		List<Hotels> hotel = hService.findHotelByHotelLevel(service);
		int count = hotel.size();
		double end = count / SOLuongTrongTrang;
		double endRounded = Math.ceil(end);
		// model.addAttribute("count", endRounded);
		List<Hotels> items = hService.findHotelByLevel(service, (start - 1) * SOLuongTrongTrang);
		model.addAttribute("items", items);
		model.addAttribute("last", last);
		model.addAttribute("start", start);
		model.addAttribute("next", next);
		return "shop";
	}

	
}
