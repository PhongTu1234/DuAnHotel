package com.poly.controller;

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
import com.poly.Service.RoomService;
import com.poly.Service.RoomTypesService;
import com.poly.Service.ServiceService;
import com.poly.Service.SlugService;
import com.poly.entity.Hotels;
import com.poly.entity.Places;
import com.poly.entity.Rooms;

@Controller
public class RoomController {
	@Autowired
	RoomService rservice;

	@Autowired
	HotelService hService;

	@Autowired
	RoomTypesService rtService;
	
	@Autowired
	ServiceService serviceService;
	
	@Autowired
	private SlugService slugService;


	@RequestMapping("/hotel/room/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Rooms item = rservice.findById(id);
		model.addAttribute("room", item);
		return "product/single-product-variable";
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
 	@GetMapping("/quan-ly-phong/them-moi")
 	public String formRoom(Model model) {
 		model.addAttribute("rooms", new Rooms());
 		return "admin/Rooms/form";
 	}

 	@GetMapping("/quan-ly-phong/danh-sach")
    public String showRoomsIndex(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		Pageable page = PageRequest.of(p-1, 10);
		Page<Rooms> rooms = rservice.findAll(page);
		model.addAttribute("rooms", rooms);
		
		model.addAttribute("slugService", slugService);
		
        return "admin/Rooms/index";
    }

    @GetMapping("/quan-ly-phong/{slug}/{id}")
    public String viewRooms(@PathVariable("id") Integer id, Model model) {
    	Rooms rooms = rservice.findById(id);
        model.addAttribute("rooms", rooms);
        return "admin/Rooms/form";
    }

    @PostMapping("/rooms/create")
    public String createRooms(@ModelAttribute Rooms rooms) {
//    	rservice.create(rooms);
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
    
    @PostMapping("/rooms/update")
    public ModelAndView updateRooms(@ModelAttribute("rooms") Rooms rooms, Model model) {
    	String Hotels = rooms.getHotels().getName();
    	String Roomtypes= rooms.getRoomTypes().getName();
        if(hService.findByHotelName(Hotels) != null && rtService.findByRoomtypeName(Roomtypes) != null) {
        	if (rooms.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
        		rooms.setHotels(hService.findByHotelName(Hotels));
        		rooms.setRoomTypes(rtService.findByRoomtypeName(Roomtypes));
	        	rservice.update(rooms);
	        } else {
	        	rooms.setHotels(hService.findByHotelName(Hotels));
        		rooms.setRoomTypes(rtService.findByRoomtypeName(Roomtypes));
	            // Nếu không có ID, thực hiện thêm mới
        		rservice.create(rooms);
	        }
        	return new ModelAndView("redirect:/rooms/index");
        }else {
        	model.addAttribute("message", "Hotel hoặc loại phòng không có!");
        	return new ModelAndView("admin/Rooms/form");
        }
//    	
//    	if(rtService.findByRoomtypeName(Roomtypes) != null ) {
//        	if (rooms.getId() != null) {
//	            // Nếu có ID, thực hiện cập nhật
//        		rooms.setRoomTypes(rtService.findByRoomtypeName(Roomtypes));
//        		rservice.update(rooms);
//	        } else {
//	        	rooms.setRoomTypes(rtService.findByRoomtypeName(Roomtypes));
//	            // Nếu không có ID, thực hiện thêm mới
//	        	rservice.create(rooms);
//	        }
//        	return new ModelAndView("redirect:/hotels/index");
//        }else {
//        	model.addAttribute("message", "Khách sạn không tồn tại");
//        	return new ModelAndView("admin/Hotel/form");
//        }
    
    	
    	
    }
    
    @RequestMapping("/{slug}/quan-ly-phong/{id}/danh-sach")
	public String RoomList(Model model, @PathVariable("id") Integer id, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		Pageable page = PageRequest.of(p-1, 10);
		Page<Rooms> room = rservice.adfindByHotelId(id, page);
		model.addAttribute("rooms", room);
		
		model.addAttribute("slugService", slugService);
		
		return "admin/Rooms/index";
	}
    
    @RequestMapping("/{slugHotel}/quan-ly-phong/{slugRoom}/{id}/chinh-sua")
	public String RoomDetail(Model model, @PathVariable("id") Integer id) {
    	Rooms rooms = rservice.findById(id);
        model.addAttribute("rooms", rooms);
		
		model.addAttribute("serivice", serviceService.findShop());
		
		return "admin/Rooms/form";
	}    

    @GetMapping("/hotels/deleteRoom={id}")
    public String deleteRooms(@PathVariable("id") Integer id) {
    	Rooms room = rservice.findById(id);
    	Hotels hotel = room.getHotels();
    	
    	//String redirectUrl = "/hotels/EditRoomDetails=" + hotel.getId();
    	String slug = slugService.convertToSlug(hotel.getName());
    	rservice.delete(id);
    	return "redirect:/hotels/" + slug + "/" + hotel.getId() + "/EditRoomDetails";
    }
    

}
