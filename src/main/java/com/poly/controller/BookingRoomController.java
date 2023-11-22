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

import com.poly.Service.Booking_RoomService;
import com.poly.Service.BookingsService;
import com.poly.entity.Booking_Room;
import com.poly.entity.Bookings;

@Controller
public class BookingRoomController {
	
	@Autowired
    private Booking_RoomService brService;
	
	//xu ly admin
 	@GetMapping("/bookingrooms/form")
 	public String formBookingRoom(Model model) {
 		model.addAttribute("bookingrooms", new Booking_Room());
 		return "admin/BookingRoom/form";
 	}

 	@GetMapping("/bookingrooms/index")
    public String showBookingRoomsIndex(Model model) {
// 		model.addAttribute("bookingrooms", brService.findAll());
 		List<Booking_Room> bookingroom = brService.findAll();

// 		model.addAttribute("hotels", hService.findAll());
 		int SOLuongTrongTrang = 10;
 		int count = bookingroom.size();
 		int start = 1;
 		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		 
		List<Booking_Room> bookingrooms = brService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
		model.addAttribute("bookingrooms", bookingrooms);
		model.addAttribute("last", null);
		model.addAttribute("start", start);
		model.addAttribute("next", start + 1);
		model.addAttribute("endRounded",endRounded);
        return "admin/BookingRoom/index";
    }
 
 	@RequestMapping("/bookingrooms/lpage={last}")
	public String bookingroomAdminLast(Model model, @PathVariable("last") String plast) {
		List<Booking_Room> bookingrooms = brService.findAll();
		int SOLuongTrongTrang = 10;
//		 model.addAttribute("users", userService.findAll());
		 int count = bookingrooms.size();
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
			List<Booking_Room> items = brService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("bookingrooms", items);
			model.addAttribute("last", null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<Booking_Room> items = brService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("bookingrooms", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		model.addAttribute("endRounded", endRounded);
		return "admin/BookingRoom/index";
	}

	@RequestMapping("/bookingrooms/npage={next}")
	public String bookingroomAdminNext(Model model, @PathVariable("next") String pnext) {

		List<Booking_Room> bookingrooms = brService.findAll();
		int SOLuongTrongTrang = 10;
		int count = bookingrooms.size();
		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		
		
		int start = Integer.parseInt(pnext);
		if (start == endRounded) {
			List<Booking_Room> items = brService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("bookingrooms", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", null);
		} else {
			List<Booking_Room> items = brService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("bookingrooms", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			
		}
		model.addAttribute("endRounded", (int)endRounded);
		return "admin/BookingRoom/index";
	}
 	
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/bookingrooms/{id}")
    public String viewBookingRoom(@PathVariable("id") Integer id, Model model) {
    	Booking_Room bookingrooms = brService.findById(id);
        model.addAttribute("bookingrooms", bookingrooms);
        return "admin/BookingRoom/form";
    }

    @PostMapping("/bookingrooms/create")
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
    
    @PostMapping("/bookingrooms/update")
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

    @GetMapping("/bookingrooms/delete/{id}")
    public ModelAndView deleteBookingRoom(@PathVariable("id") Integer id) {
    	brService.delete(id);
        return new ModelAndView("redirect:/bookingrooms/index");
    }

}
