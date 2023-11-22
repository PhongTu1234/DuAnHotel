package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.Booking_RoomService;
import com.poly.Service.BookingsService;
import com.poly.entity.Booking_Room;
import com.poly.entity.Bookings;

@Controller
public class BookingRoomController {
	
	@Autowired
    private Booking_RoomService brService;
	
	//xu ly admin
 	@GetMapping("/form")
 	public String formBookingRoom(Model model) {
 		model.addAttribute("bookingrooms", new Booking_Room());
 		return "admin/BookingRoom/form";
 	}

 	@GetMapping("/index")
    public String showBookingRoomsIndex(Model model) {
 		model.addAttribute("bookingrooms", brService.findAll());
        return "admin/BookingRoom/index";
    }
 
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/{id}")
    public String viewBookingRoom(@PathVariable("id") Integer id, Model model) {
    	Booking_Room bookingrooms = brService.findById(id);
        model.addAttribute("bookingrooms", bookingrooms);
        return "admin/BookingRoom/form";
    }

    @PostMapping("/create")
    public String createBookingRoom(@ModelAttribute Booking_Room bookingrooms) {
    	brService.create(bookingrooms);
        return "redirect:/bookingrooms/form";
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
    public ModelAndView updateBookingRoom(@ModelAttribute Booking_Room bookingrooms) {
        if (bookingrooms.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
        	brService.update(bookingrooms);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	brService.create(bookingrooms);
        }
        return new ModelAndView("redirect:/bookingrooms/index");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteBookingRoom(@PathVariable("id") Integer id) {
    	brService.delete(id);
        return new ModelAndView("redirect:/bookingrooms/index");
    }

}
