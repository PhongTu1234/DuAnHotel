package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Service.BlogsService;
import com.poly.Service.HotelService;
import com.poly.Service.PlacesService;
import com.poly.Service.RoleService;
import com.poly.Service.RoomService;
import com.poly.Service.RoomTypesService;
import com.poly.Service.UserService;
import com.poly.entity.Blogs;
import com.poly.entity.Hotels;
import com.poly.entity.Places;
import com.poly.entity.Role;
import com.poly.entity.RoomTypes;
import com.poly.entity.Rooms;
import com.poly.entity.Users;

@Controller
public class HomeController {

	@Autowired
	UserService uservice;
	
	@Autowired
	HotelService htservice;

	@Autowired
	RoomService rtservice;
	
	@Autowired
	RoomTypesService rservice;

	@Autowired
	PlacesService placeservice;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	BlogsService blogService;

	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		List<Rooms> rooms = rtservice.findTop8ByOrderByRatingDesc();
		model.addAttribute("items", rooms);
		
		List<Rooms> roomBestseller = rtservice.findTop8RoomsByTotalBookingsAndPaymentStatus();
		model.addAttribute("roomBestseller", roomBestseller);
		
		Places top1 = placeservice.findPlaceWithMostHotels();
		model.addAttribute("top1", top1);
		int top1_id = top1.getId();
		List<Hotels> HotelTop1 = htservice.findByPlaceId(top1_id);
		int countTop1 = HotelTop1.size();
		model.addAttribute("countTop1", countTop1);

		Places top2 = placeservice.findPlaceWithMostHotelsTop2();
		model.addAttribute("top2", top2);
		int top2_id = top2.getId();
		List<Hotels> HotelTop2 = htservice.findByPlaceId(top2_id);
		int countTop2 = HotelTop2.size();
		model.addAttribute("countTop2", countTop2);

		Places top3 = placeservice.findPlaceWithMostHotelsTop3();
		model.addAttribute("top3", top3);
		int top3_id = top3.getId();
		List<Hotels> HotelTop3 = htservice.findByPlaceId(top3_id);
		int countTop3 = HotelTop3.size();
		model.addAttribute("countTop3", countTop3);

		Places top4 = placeservice.findPlaceWithMostHotelsTop4();
		model.addAttribute("top4", top4);
		int top4_id = top4.getId();
		List<Hotels> HotelTop4 = htservice.findByPlaceId(top4_id);
		int countTop4 = HotelTop4.size();
		model.addAttribute("countTop4", countTop4);

		List<Hotels> HotelLevel5 = htservice.findHotelByHotelLevel(5);
		model.addAttribute("HotelLevel5", HotelLevel5);
		
		
		List<RoomTypes> roomtype = rservice.findShop();
		model.addAttribute("rt", roomtype);
		return "index";
	}

	@RequestMapping({ "/admin", "/admin/index" })
	public String admin(Model model) {
		List<Hotels> hotel = htservice.countHotel();
		model.addAttribute("countHotel", hotel.size());
		
		List<RoomTypes> roomtype = rservice.findShop();
		model.addAttribute("countRoomType", roomtype.size());
		
		List<Users> user = uservice.findShop();
		model.addAttribute("countUser", user.size());
		
		List<Role> role = roleService.findShop();
		model.addAttribute("countRole", role.size());
		
		List<Blogs> blog = blogService.findShop();
		model.addAttribute("countBlog", blog.size());
		
		List<Places> place = placeservice.findShop();
		model.addAttribute("countPlaces", place.size());
		
		
		
		return "admin/dashboard";
	}
	
	@RequestMapping("shop")
	public String shop() {
		return "shop";
	}

	@RequestMapping("about")
	public String about() {
		return "about";
	}

	@RequestMapping("404")
	public String error() {
		return "404";
	}

	@RequestMapping("contact")
	public String contact() {
		return "contact";
	}

	@RequestMapping("faq")
	public String faq() {
		return "faq";
	}

	@RequestMapping("wishlist")
	public String wishlist() {
		return "wishlist";
	}

}
