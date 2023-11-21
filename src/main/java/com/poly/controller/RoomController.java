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

import com.poly.Service.RoomService;
import com.poly.entity.Hotels;
import com.poly.entity.Rooms;

@Controller
@RequestMapping("/rooms")
public class RoomController {
	@Autowired
	RoomService rservice;

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
 	@GetMapping("/form")
 	public String formRoom(Model model) {
 		model.addAttribute("rooms", new Rooms());
 		return "admin/Rooms/form";
 	}

 	@GetMapping("/index")
    public String showRoomsIndex(Model model) {
 		model.addAttribute("rooms", rservice.findAll());
        return "admin/Rooms/index";
    }
 
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/{id}")
    public String viewRooms(@PathVariable("id") Integer id, Model model) {
    	Rooms rooms = rservice.findById(id);
        model.addAttribute("rooms", rooms);
        return "admin/Rooms/form";
    }

    @PostMapping("/create")
    public String createRooms(@ModelAttribute Rooms rooms) {
    	rservice.create(rooms);
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
    
    @PostMapping("/update")
    public ModelAndView updateRooms(@ModelAttribute Rooms rooms) {
        if (rooms.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
        	rservice.update(rooms);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	rservice.create(rooms);
        }
        return new ModelAndView("redirect:/rooms/index");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteRooms(@PathVariable("id") Integer id) {
    	rservice.delete(id);
        return new ModelAndView("redirect:/rooms/index");
    }
}
