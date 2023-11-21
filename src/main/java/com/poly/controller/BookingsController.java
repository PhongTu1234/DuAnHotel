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

import com.poly.Service.BookingsService;
import com.poly.Service.FeedbackService;
import com.poly.entity.Bookings;
import com.poly.entity.Feedback;

@Controller
@RequestMapping("/bookings")
public class BookingsController {

	@Autowired
    private BookingsService bookingService;
	
	@RequestMapping("/hotel/room/order/cart")
	public String cart() {
		return "/order/cart";
	}
	
	@RequestMapping("/hotel/room/order/cart/checkout")
	public String checkout() {
		return "/order/checkout";
	}

	
	
	//xu ly admin
	 	@GetMapping("/form")
	 	public String formBooking(Model model) {
	 		model.addAttribute("bookings", new Bookings());
	 		return "admin/Booking/form";
	 	}

	 	@GetMapping("/index")
	    public String showBookingsIndex(Model model) {
	 		model.addAttribute("bookings", bookingService.findAll());
	        return "admin/Booking/index";
	    }
	 
//	    @GetMapping
//	    public String listPlaces(Model model) {
//	        model.addAttribute("places", placeService.findAll());
//	        return "";
//	    }

	    @GetMapping("/{id}")
	    public String viewBooking(@PathVariable("id") Integer id, Model model) {
	    	Bookings bookings = bookingService.findById(id);
	        model.addAttribute("bookings", bookings);
	        return "admin/Booking/form";
	    }

	    @PostMapping("/create")
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
	    
	    @PostMapping("/update")
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

	    @GetMapping("/delete/{id}")
	    public ModelAndView deleteBooking(@PathVariable("id") Integer id) {
	    	bookingService.delete(id);
	        return new ModelAndView("redirect:/bookings/index");
	    }
	
}
