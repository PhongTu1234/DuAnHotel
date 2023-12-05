package com.poly.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.format.annotation.DateTimeFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.poly.Service.BookingsService;
import com.poly.Service.PaymentService;
import com.poly.Service.UserService;
import com.poly.entity.Bookings;
import com.poly.entity.Payment;
import com.poly.entity.Users;

import com.poly.Service.Booking_RoomService;

@Controller
public class BookingsController {

	@Autowired
    private BookingsService bookingService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	Booking_RoomService brService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PaymentService paymentService;
	
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
	    public String showBookingsIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
	 		Pageable page = PageRequest.of(p, 10);
			Page<Bookings> bookings = bookingService.findAll(page);
			model.addAttribute("bookings", bookings);
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
	    public ModelAndView updateBooking(@ModelAttribute Bookings bookings, Model model) {
	    	int Payment = bookings.getPayment().getId();
	    	String Users= bookings.getUsers().getCmt();
//	    	try {
	            // Parse String dates from the form into Timestamp
	    		bookings.setBookingDate(LocalDate.parse(bookings.getBookingDateStr()));
	            bookings.setCheckinDate(LocalDate.parse(bookings.getCheckinDateStr()));
	            bookings.setCheckoutDate(LocalDate.parse(bookings.getCheckoutDateStr()));
	            
	            Payment payment  = paymentService.findByPaymentName(Payment);
	            Users user = userService.findByUserName(Users);
	            
	            if(payment != null &&  user != null) {
		        	if (bookings.getId() != null) {
			            // Nếu có ID, thực hiện cập nhật
		        		bookings.setPayment(paymentService.findByPaymentName(Payment));
		        		bookings.setUsers(userService.findByUserName(Users));
		        		bookingService.update(bookings);
			        } else {
			        	bookings.setPayment(paymentService.findByPaymentName(Payment));
			        	bookings.setUsers(userService.findByUserName(Users));
			            // Nếu không có ID, thực hiện thêm mới
			        	bookingService.create(bookings);
			        }
		        	return new ModelAndView ("redirect:/bookings/index");
		        }else {
		        	model.addAttribute("message", "thanh toan hoặc user không tồn tại!");
		        	return new ModelAndView ("admin/Booking/form");
		        }
//	        } catch (ParseException e) {
//	            e.printStackTrace();
//	            return null;
//	            // Handle the exception as needed
//	        }
	    	 
    		

	        
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
