package com.poly.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.Booking_RoomService;
import com.poly.Service.BookingsService;
import com.poly.Service.HotelService;
import com.poly.Service.PaymentService;
import com.poly.Service.RoomService;
import com.poly.Service.UserService;
import com.poly.entity.Blogs;
import com.poly.entity.Booking_Room;
import com.poly.entity.Bookings;
import com.poly.entity.Hotels;
import com.poly.entity.Payment;
import com.poly.entity.Rooms;

@Controller
public class BookingRoomController {
	
	@Autowired
    private Booking_RoomService brService;
	
	@Autowired
    private BookingsService bookingsService;
	
	@Autowired
    private RoomService rService;
	
	@Autowired
    private HotelService hService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	UserService userService;
	
	//xu ly admin
 	@GetMapping("/bookingrooms/form")
 	public String formBookingRoom(Model model) {
 		model.addAttribute("bookingrooms", new Booking_Room());
 		return "admin/BookingRoom/form";
 	}

 	@GetMapping("/bookingrooms/index")
    public String showBookingRoomsIndex(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
 		Pageable page = PageRequest.of(p-1, 10);
		Page<Booking_Room> bookingrooms = brService.findAll(page);
		model.addAttribute("bookingrooms", bookingrooms);
		
        return "admin/BookingRoom/index";
    }

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
    public ModelAndView updateBookingRoom(@ModelAttribute("bookingrooms") Booking_Room bookingrooms, Model model) {
    	String Rooms = bookingrooms.getRooms().getName();
    	int Bookings= bookingrooms.getBookings().getId();
//    	String Hotels = bookingrooms.getHotels().getName();
    	
    	Rooms room= rService.findByRoomName(Rooms);
//    	Hotels hotel= hService.findByHotelName(Hotels);
    	Bookings booking= bookingsService.findByBookingID(Bookings);
        if( room != null  &&  booking != null) {
        	if (bookingrooms.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
        		bookingrooms.setRooms(rService.findByRoomName(Rooms));
        		bookingrooms.setBookings(bookingsService.findByBookingID(Bookings));
//        		bookingrooms.setHotels(hService.findByHotelName(Hotels));
        		brService.update(bookingrooms);
	        } else {
	        	bookingrooms.setRooms(rService.findByRoomName(Rooms));
        		bookingrooms.setBookings(bookingsService.findByBookingID(Bookings));
//        		bookingrooms.setHotels(hService.findByHotelName(Hotels));
	            // Nếu không có ID, thực hiện thêm mới
	        	brService.create(bookingrooms);
	        }
        	return new ModelAndView("redirect:/bookingrooms/index");
        }else {
        	model.addAttribute("message", "khách sạn hoặc phòng hoặc booking không tồn tại!");
        	return new ModelAndView("admin/BookingRoom/form");
        }
    }

    @RequestMapping("/hotels/{id}/EditBookingRoomDetails")
	public String RoomDetail(Model model, @PathVariable("id") Integer id, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		Pageable page = PageRequest.of(p-1, 10);
		Page<Booking_Room> br = brService.adfindByHotelId(id, page);
		model.addAttribute("bookingrooms", br);
		
		
		
		return "admin/BookingRoom/index";
	}
    
    @GetMapping("/bookingrooms/delete/{id}")
    public ModelAndView deleteBookingRoom(@PathVariable("id") Integer id) {
    	brService.delete(id);
        return new ModelAndView("redirect:/bookingrooms/index");
    }

//    @GetMapping("/hotels/deleteBookingRoom={id}")
//    public String deleteRooms(@PathVariable("id") Integer id) {
//    	Booking_Room br = brService.findById(id);
////    	Hotels hotel = br.getHotels();
//    	
//    	//String redirectUrl = "/hotels/EditRoomDetails=" + hotel.getId();
////    	String slug = slugService.convertToSlug(hotel.getName());
//    	brService.delete(id);
//    	return "redirect:/hotels/" + hotel.getId() + "/EditBookingRoomDetails";
//    }
    
    @RequestMapping("/BookingRoom/{id}")
    public String addBookingRoom(Model model, @PathVariable("id") Integer id) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Bookings booking = bookingsService.checkStatus(username);
//        Bookings bookings = new Bookings();
        if (booking != null) {
        	Booking_Room bookingrooms = new Booking_Room();
        	bookingrooms.setBookings(booking);
        	bookingrooms.setRooms(rService.findById(id));
        	bookingrooms.setCount(1);
        	brService.create(bookingrooms);
        } else {
        	Payment payment = new Payment();
        	payment.setDate(LocalDate.now());
        	payment.setTotal(BigDecimal.valueOf(0));
        	payment.setMethod("Tiền mặt");
        	paymentService.create(payment);

        	Bookings bookings = new Bookings();
        	bookings.setUsers(userService.findById(username));
        	bookings.setPayment(payment);
        	bookings.setBookingDate(LocalDate.now());
        	bookings.setCheckinDate(LocalDate.now());
        	bookings.setCheckoutDate(LocalDate.now());
        	bookings.setStatus(false);
        	bookingsService.create(bookings);
        	
        	Booking_Room bookingRoom = new Booking_Room();
        	bookingRoom.setBookings(bookings);
        	bookingRoom.setRooms(rService.findById(id));
        	bookingRoom.setCount(1);
        	brService.create(bookingRoom);
        	
        	Bookings status = bookingsService.checkStatus(username);
        	
        	return "redirect:/hotel/room/order/cart/" + status.getId();
        }
        return "redirect:/hotel/room/order/cart/" + booking.getId();
    }
    
    @RequestMapping("/checkout")
    public String Checkout() {
    	return "/order/checkout";
    }
    
}
