package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.BookingsService;
import com.poly.entity.Bookings;

import com.poly.Service.Booking_RoomService;

@Controller
public class BookingsController {

	@Autowired
    private BookingsService bookingService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	Booking_RoomService brService;
	
	@RequestMapping("/hotel/room/order/cart")
	public String cart() {
		return "/order/cart";
	}
	
//	@RequestMapping("/hotel/room/order/cart/checkout")
//	public String checkout() {
//		return "/order/checkout";
//	}

	@RequestMapping("/cart/checkout")
	public String checkout() {
		if (!(request.isUserInRole("DIRE") || request.isUserInRole("STAF") || request.isUserInRole("CUST"))) {
			return "redirect:/auth/login/form";
		}
		return "cart/checkout";
	}	
	
	//xu ly admin
	 	@GetMapping("/bookings/form")
	 	public String formBooking(Model model) {
	 		model.addAttribute("bookings", new Bookings());
	 		return "admin/Booking/form";
	 	}

	 	@GetMapping("/bookings/index")
	    public String showBookingsIndex(Model model) {
//	 		model.addAttribute("bookings", bookingService.findAll());
	 		List<Bookings> booking = bookingService.findAll();

//	 		model.addAttribute("hotels", hService.findAll());
	 		int SOLuongTrongTrang = 10;
	 		int count = booking.size();
	 		int start = 1;
	 		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
			int endRounded = endRound;
			if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = endRound + 1;
			}
			 
			List<Bookings> bookings = bookingService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("bookings", bookings);
			model.addAttribute("last", null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			model.addAttribute("endRounded",endRounded);
	        return "admin/Booking/index";
	    }
	 
	 	@RequestMapping("/bookings/lpage={last}")
		public String bookingAdminLast(Model model, @PathVariable("last") String plast) {
			List<Bookings> bookings = bookingService.findAll();
			int SOLuongTrongTrang = 10;
//			 model.addAttribute("users", userService.findAll());
			 int count = bookings.size();
//				int last = start - 1;
//				int next = start + 1;
			// int SOLuongTrongTrang = 10;
			 int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
				int endRounded = endRound;
				if((endRound * SOLuongTrongTrang) < count ) {
					endRounded = endRound + 1;
				}
//					List<Users> users = userService.findAll();
//					// model.addAttribute("roomtype", roomtype);
//					// int counta = roomtype.size();
//					model.addAttribute("users", users);

			// model.addAttribute("count", count);

			int start = Integer.parseInt(plast);
			// int last = start - 1;
			if (start == 1) {
				List<Bookings> items = bookingService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("bookings", items);
				model.addAttribute("last", null);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			} else {
				List<Bookings> items = bookingService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("bookings", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			}
			model.addAttribute("endRounded", endRounded);
			return "admin/Booking/index";
		}

		@RequestMapping("/bookings/npage={next}")
		public String bookingAdminNext(Model model, @PathVariable("next") String pnext) {

			List<Bookings> bookings = bookingService.findAll();
			int SOLuongTrongTrang = 10;
			int count = bookings.size();
			int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
			int endRounded = endRound;
			if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = endRound + 1;
			}
			
			int start = Integer.parseInt(pnext);
			if (start == endRounded) {
				List<Bookings> items = bookingService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("bookings", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", null);
			} else {
				List<Bookings> items = bookingService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("bookings", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
				
			}
			model.addAttribute("endRounded", (int)endRounded);
			return "admin/Booking/index";
		}
	 	
	 	
//	    @GetMapping
//	    public String listPlaces(Model model) {
//	        model.addAttribute("places", placeService.findAll());
//	        return "";
//	    }

	    @GetMapping("/bookings/{id}")
	    public String viewBooking(@PathVariable("id") Integer id, Model model) {
	    	Bookings bookings = bookingService.findById(id);
	        model.addAttribute("bookings", bookings);
	        return "admin/Booking/form";
	    }

	    @PostMapping("/bookings/create")
	    public String createBooking(@ModelAttribute Bookings bookings) {
	    	bookingService.create(bookings);
	        return "redirect:/bookings/form";
	    }

//	    @PostMapping("/update")
//	    public ModelAndView updateUser(@Validated @ModelAttribute("user") Users user) {
//	        userService.update(user);
//	        return new ModelAndView("redirect:/users/index");
//	    }
	//
//	    @GetMapping("/delete/{cmt}")
//	    public ModelAndView deleteUser(@PathVariable String cmt) {
//	        userService.delete(cmt);
//	        return new ModelAndView("redirect:/users/index");
//	    }
	    
	    @PostMapping("/bookings/update")
	    public ModelAndView updateBooking(@ModelAttribute Bookings bookings) {
	        if (bookings.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
	        	bookingService.update(bookings);
	        } else {
	            // Nếu không có ID, thực hiện thêm mới
	        	bookingService.create(bookings);
	        }
	        return new ModelAndView("redirect:/bookings/index");
	    }

	    @GetMapping("/bookings/delete/{id}")
	    public ModelAndView deleteBooking(@PathVariable("id") Integer id) {
	    	bookingService.delete(id);
	        return new ModelAndView("redirect:/bookings/index");
	    }
	
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", brService.getBookingDetailsForUser(username));
		return "order/cart";
	}
}
