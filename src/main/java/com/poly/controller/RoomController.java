package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.HotelService;
import com.poly.Service.RoomService;
import com.poly.Service.RoomTypesService;
import com.poly.entity.Hotels;
import com.poly.entity.RoomTypes;
import com.poly.entity.Rooms;

@Controller
public class RoomController {
	@Autowired
	RoomService rservice;

	@Autowired
	HotelService hService;

	@Autowired
	RoomTypesService rtService;

	@RequestMapping("/hotel/room/{hotel_id}")
	public String detail(Model model, @PathVariable("hotel_id") String id) {
		List<Rooms> item = rservice.findByHotelId(Integer.parseInt(id));
		model.addAttribute("item", item);
		return "Room_shop";
	}
	
	@RequestMapping("/hotel/room/wishlist")
	public String  wishlist(Model model) {
		return "/wishlist";
	}
	
	@RequestMapping("/hotel/room/compare")
	public String  compare(Model model) {
		return "/compare";
	}
	
	
	//xu ly admin
 	@GetMapping("/rooms/form")
 	public String formRoom(Model model) {
 		model.addAttribute("rooms", new Rooms());
 		return "admin/Rooms/form";
 	}

 	@GetMapping("/rooms/index")
    public String showRoomsIndex(Model model) {
// 		model.addAttribute("rooms", rservice.findAll());
 		List<Rooms> room = rservice.findAll();

// 		model.addAttribute("hotels", hService.findAll());
 		int SOLuongTrongTrang = 10;
 		int count = room.size();
 		int start = 1;
 		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		 
		List<Rooms> rooms = rservice.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
		model.addAttribute("rooms", rooms);
		model.addAttribute("last", null);
		model.addAttribute("start", start);
		model.addAttribute("next", start + 1);
		model.addAttribute("endRounded",endRounded);
        return "admin/Rooms/index";
    }
 
 	@RequestMapping("/rooms/lpage={last}")
	public String roomAdminLast(Model model, @PathVariable("last") String plast) {
		List<Rooms> rooms = rservice.findAll();
		int SOLuongTrongTrang = 10;
//		 model.addAttribute("users", userService.findAll());
		 int count = rooms.size();
//			int last = start - 1;
//			int next = start + 1;
		// int SOLuongTrongTrang = 10;
		 int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
			int endRounded = endRound;
			if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = endRound + 1;
			}
//				List<Users> users = userService.findAll();
//				// model.addAttribute("roomtype", roomtype);
//				// int counta = roomtype.size();
//				model.addAttribute("users", users);

		// model.addAttribute("count", count);

		int start = Integer.parseInt(plast);
		// int last = start - 1;
		if (start == 1) {
			List<Rooms> items = rservice.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("rooms", items);
			model.addAttribute("last", null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<Rooms> items = rservice.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("rooms", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		model.addAttribute("endRounded", endRounded);
		return "admin/Rooms/index";
	}

	@RequestMapping("/rooms/npage={next}")
	public String roomAdminNext(Model model, @PathVariable("next") String pnext) {

		List<Rooms> rooms = rservice.findAll();
		int SOLuongTrongTrang = 10;
		int count = rooms.size();
		double end = count / SOLuongTrongTrang;
		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		
		
		int start = Integer.parseInt(pnext);
		if (start == endRounded) {
			List<Rooms> items = rservice.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("rooms", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", null);
		} else {
			List<Rooms> items = rservice.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("rooms", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			
		}
		model.addAttribute("endRounded", (int)endRounded);
		return "admin/Rooms/index";
	}
 	
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/rooms/{id}")
    public String viewRooms(@PathVariable("id") Integer id, Model model) {
    	Rooms rooms = rservice.findById(id);
        model.addAttribute("rooms", rooms);
        return "admin/Rooms/form";
    }

    @PostMapping("/rooms/create")
    public String createRooms(@ModelAttribute Rooms rooms) {
//    	rservice.create(rooms);
        return "redirect:/rooms/form";
    }

//    @PostMapping("/update")
//    public ModelAndView updateUser(@Validated @ModelAttribute("user") Users user) {
//        userService.update(user);
//        return new ModelAndView("redirect:/users/index");
//    }
//
//    @GetMapping("/delete/{cmt}")
//    public ModelAndView deleteUser(@PathVariable String cmt) {
//        userService.delete(cmt);
//        return new ModelAndView("redirect:/users/index");
//    }
    
    @PostMapping("/rooms/update")
    public ModelAndView updateRooms(@ModelAttribute Rooms rooms, Model model) {
    	String Hotels = rooms.getHotels().getName();
    	String Roomtypes= rooms.getRoomTypes().getName();
        if(hService.findByHotelName(Hotels) != null && rtService.findByRoomtypeName(Roomtypes) != null) {
        	if (rooms.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
        		rooms.setHotels(hService.findByHotelName(Hotels));
        		rooms.setRoomTypes(rtService.findByRoomtypeName(Roomtypes));
	        	rservice.update(rooms);
	        } else {
	        	rooms.setHotels(hService.findByHotelName(Hotels));
        		rooms.setRoomTypes(rtService.findByRoomtypeName(Roomtypes));
	            // Nếu không có ID, thực hiện thêm mới
        		rservice.create(rooms);
	        }
        	return new ModelAndView("redirect:/rooms/index");
        }else {
        	model.addAttribute("message", "Hotel không tồn tại");
        	return new ModelAndView("admin/Rooms/form");
        }
    }

    @GetMapping("/rooms/delete/{id}")
    public ModelAndView deleteRooms(@PathVariable("id") Integer id) {
    	rservice.delete(id);
        return new ModelAndView("redirect:/rooms/index");
    }
}
