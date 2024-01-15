package com.poly.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.HotelService;
import com.poly.Service.PlacesService;
import com.poly.Service.RoomService;
import com.poly.Service.RoomTypesService;
import com.poly.Service.ServiceService;
import com.poly.Service.SlugService;
import com.poly.dto.RoomSearchDTO;
import com.poly.entity.Booking_Room;
import com.poly.entity.Hotels;
import com.poly.entity.Places;
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

	@Autowired
	PlacesService placeService;

	@Autowired
	private SlugService slugService;

	private void shop(Model model) {
		List<RoomTypes> roomtype = rtService.findShop();
		List<Hotels> hoteltype = hService.countHotel();
		List<Services> services = svService.findShop();
		model.addAttribute("roomtype", roomtype);
		model.addAttribute("hoteltype", hoteltype);
		model.addAttribute("services", services);

		List<Hotels> item = hService.countHotel();
		int count = item.size();
		model.addAttribute("counthotel", count);

		for (int i = 0; i <= 5; i++) {
			List<Hotels> hotelLevel = hService.findHotelByHotelLevel(i);
			model.addAttribute("countHotelLevel" + i, hotelLevel.size());
		}
	}

	@RequestMapping("/search")
	public String searchRooms(@RequestParam(name = "services", required = false) List<Integer> serviceIds,
			@RequestParam(name = "roomTypes", required = false) List<Integer> roomTypeIds,
			@RequestParam(name = "priceRange", required = false) String priceRange, Model model,
			@RequestParam(name = "p", defaultValue = "1") Integer p) {
		shop(model);
		Pageable page = PageRequest.of(p - 1, 12);

		RoomSearchDTO searchDTO = new RoomSearchDTO();
		searchDTO.setServiceIds(serviceIds);
		searchDTO.setRoomTypeIds(roomTypeIds);

		if (priceRange != null && !priceRange.isEmpty()) {
			String[] priceValues = priceRange.split(";");
			if (priceValues.length == 2) {
				try {
					BigDecimal[] priceArray = new BigDecimal[2];
					priceArray[0] = new BigDecimal(priceValues[0].trim());
					priceArray[1] = new BigDecimal(priceValues[1].trim());

					searchDTO.setMinPrice(priceArray[0]);
					searchDTO.setMaxPrice(priceArray[1]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			} else {
				System.err.println("Incorrect number of elements in the priceRange array.");
			}
		}

		Page<Hotels> matchingHotels = hService.searchHotels(searchDTO, page);
		model.addAttribute("items", matchingHotels);

		model.addAttribute("serviceIds", serviceIds);
		model.addAttribute("roomTypeIds", roomTypeIds);
		return "shop";
	}

	@RequestMapping("/thong-tin-khach-san/{slug}/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Hotels item = hService.findById(id);
		model.addAttribute("item", item);

		List<Rooms> room = roomService.findByHotelId(id);
		model.addAttribute("room", room);
		
		return "hotel-detail";
	}

	// Hotel
	@RequestMapping("/danh-sach-khach-san")
	public String Hotel(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		shop(model);
		Pageable page = PageRequest.of(p - 1, 12);
		Page<Hotels> items = roomService.findHotelAndRoomType(page);
		model.addAttribute("items", items);
		
		model.addAttribute("slugService", slugService);
		return "shop";
	}

	// xu ly admin
	@GetMapping("/quan-ly-khach-san/them-moi")
	public String formHotel(Model model) {
		model.addAttribute("hotels", new Hotels());
		return "admin/Hotel/form";
	}

	@GetMapping("/quan-ly-khach-san/danh-sach")
	public String showHotelsIndex(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		Pageable page = PageRequest.of(p - 1, 10);
		Page<Hotels> hotels = hService.findAll(page);
		model.addAttribute("hotels", hotels);
		
		model.addAttribute("slugService", slugService);
		
		return "admin/Hotel/index";
	}

	@GetMapping("/quan-ly-khach-san/{slug}/{id}")
	public String viewHotel(@PathVariable("id") Integer id, Model model) {
		Hotels hotels = hService.findById(id);
		model.addAttribute("hotels", hotels);
		return "admin/Hotel/form";
	}

	@PostMapping("/hotels/create")
	public String createHotel(@ModelAttribute Hotels hotels) {
		return "redirect:/hotels/form";
	}

	@PostMapping("/hotels/update")
	public ModelAndView updateHotel(@ModelAttribute("hotels") Hotels hotels, Model model) {
		String Place = hotels.getPlace().getName();
		if (placeService.findByPlaceName(Place) != null) {
			if (hotels.getId() != null) {
				hotels.setPlace(placeService.findByPlaceName(Place));
				hService.update(hotels);
			} else {
				hotels.setPlace(placeService.findByPlaceName(Place));
				hService.create(hotels);
			}
			return new ModelAndView("redirect:/hotels/index");
		} else {
			model.addAttribute("message", "Địa điểm không tồn tại");
			return new ModelAndView("admin/Hotel/form");
		}
	}

//	 @RequestMapping("/places/{id}/EditHotelDetails")
//		public String RoomDetail(Model model, @PathVariable("id") Integer id, @RequestParam(name = "p", defaultValue = "1") Integer p) {
//			Pageable page = PageRequest.of(p-1, 10);
//			Page<Hotels> br = hService.adfindByPlaceId(id, page);
//			model.addAttribute("bookingrooms", br);
//			
//			
//			
//			return "admin/Hotel/index";
//		}
	
	@GetMapping("/hotels/delete/{id}")
	public ModelAndView deleteHotel(@PathVariable("id") Integer id) {
		hService.delete(id);
		return new ModelAndView("redirect:/hotels/index");
	}
	 
//	 @GetMapping("/places/deleteHotel={id}")
//	    public String deleteRooms(@PathVariable("id") Integer id) {
//		 	Hotels hotel = hService.findById(id);
//	    	Places place = hotel.getPlace();
//	    	
//	    	//String redirectUrl = "/hotels/EditRoomDetails=" + hotel.getId();
////	    	String slug = slugService.convertToSlug(hotel.getName());
//	    	hService.delete(id);
//	    	return "redirect:/places/" + place.getId() + "/EditHotelDetails";
//	    }
}
