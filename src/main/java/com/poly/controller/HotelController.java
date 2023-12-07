package com.poly.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.HotelService;
import com.poly.Service.PlacesService;
import com.poly.Service.RoomService;
import com.poly.Service.RoomTypesService;
import com.poly.Service.ServiceService;
import com.poly.dto.RoomSearchDTO;
import com.poly.entity.Hotels;
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
	public String searchRooms(
	        @RequestParam(name = "services", required = false) List<Integer> serviceIds,
	        @RequestParam(name = "roomTypes", required = false) List<Integer> roomTypeIds,
	        @RequestParam(name = "pronia-range-slider", required = false) String priceRange,
	        Model model
	) {
		shop(model);
		
	    RoomSearchDTO searchDTO = new RoomSearchDTO();
	    searchDTO.setServiceIds(serviceIds);
	    searchDTO.setRoomTypeIds(roomTypeIds);

	    // Parse the priceRange string to a BigDecimal array
	    if (priceRange != null && !priceRange.isEmpty()) {
	        String[] priceValues = priceRange.split(",");
	        BigDecimal[] priceArray = new BigDecimal[2];

	        try {
	            priceArray[0] = new BigDecimal(priceValues[0].trim());
	            priceArray[1] = new BigDecimal(priceValues[1].trim());
	        } catch (NumberFormatException e) {
	            // Handle parsing error if needed
	            e.printStackTrace();
	        }

	        searchDTO.setMinPrice(priceArray[0]);
	        searchDTO.setMaxPrice(priceArray[1]);
	    }

	    List<Hotels> matchingHotels = hService.searchHotels(searchDTO);
	    model.addAttribute("items", matchingHotels);
	    return "shop";
	}
	
	@RequestMapping("/hotel/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Hotels item = hService.findById(id);
		model.addAttribute("item", item);
		
		List<Rooms> room = roomService.findByHotelId(id);
		model.addAttribute("room", room);
		return "hotel-detail";
	}

	// Hotel
	@RequestMapping("/hotel/all")
	public String Hotel(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		shop(model);
		Pageable page = PageRequest.of(p-1, 12);		
		Page<Hotels> items = hService.findAll(page);
		model.addAttribute("items", items);
		return "shop";
	}

	// Hotel By Place
	@RequestMapping("/hotel/place={id}")
	public String HotelByPlace(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("place_id", id);
		List<Hotels> items = hService.findByPlaceId(id);
		model.addAttribute("items", items);
		return "shop";
	}
	
	//xu ly admin
	 	@GetMapping("/hotels/form")
	 	public String formHotel(Model model) {
	 		model.addAttribute("hotels", new Hotels());
	 		return "admin/Hotel/form";
	 	}

	 	@GetMapping("/hotels/index")
	    public String showHotelsIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
	 		Pageable page = PageRequest.of(p, 10);
			Page<Hotels> hotels = hService.findAll(page);
			model.addAttribute("hotels", hotels);
			return "admin/Hotel/index";
		}
	 	
	    @GetMapping("/hotels/{id}")
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
	    public ModelAndView updateHotel(@ModelAttribute Hotels hotels, Model model) { 
	    	String Place = hotels.getPlace().getName();
	        if(placeService.findByPlaceName(Place) != null ) {
	        	if (hotels.getId() != null) {
	        		hotels.setPlace(placeService.findByPlaceName(Place));
		        	hService.update(hotels);
		        } else {
		        	hotels.setPlace(placeService.findByPlaceName(Place));
		        	hService.create(hotels);
		        }
	        	return new ModelAndView("redirect:/hotels/index");
	        }else {
	        	model.addAttribute("message", "Địa điểm không tồn tại");
	        	return new ModelAndView("admin/Hotel/form");
	        }
	    }

	    @GetMapping("/hotels/delete/{id}")
	    public ModelAndView deleteHotel(@PathVariable("id") Integer id) {
	    	hService.delete(id);
	        return new ModelAndView("redirect:/hotels/index");
	    }
}
