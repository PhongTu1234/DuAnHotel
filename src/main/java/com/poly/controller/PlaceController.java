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
import com.poly.Service.PlacesService;
import com.poly.Service.RoomTypesService;
import com.poly.Service.ServiceService;
import com.poly.Service.UserService;
import com.poly.entity.Hotels;
import com.poly.entity.Payment;
import com.poly.entity.Places;
import com.poly.entity.RoomTypes;
import com.poly.entity.Services;
import com.poly.entity.Users;

@Controller
public class PlaceController {

	@Autowired
	HotelService hService;

	@Autowired
	RoomTypesService rtService;

	@Autowired
	ServiceService svService;

	@Autowired
    private PlacesService placeService;
	
//	@RequestMapping("/hotel/place={id}")
//	public String findById(Model model, @PathVariable("id") Integer id) {
//
//		model.addAttribute("place_id", id);
//
//		List<RoomTypes> roomtype = rtService.findAll();
//		List<Hotels> hoteltype = hService.findAll();
//		List<Services> services = svService.findAll();
//		// model.addAttribute("roomtype", roomtype);
//		// int counta = roomtype.size();
//		model.addAttribute("roomtype", roomtype);
//		model.addAttribute("hoteltype", hoteltype);
//		model.addAttribute("services", services);
//		List<Hotels> item = hService.findAll();
//		int count = item.size();
//		model.addAttribute("counthotel", count);
//
//		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel0();
//		model.addAttribute("countHotelLevel0", HotelLevel0.size());
//
//		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel1();
//		model.addAttribute("countHotelLevel1", HotelLevel1.size());
//
//		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel2();
//		model.addAttribute("countHotelLevel2", HotelLevel2.size());
//
//		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel3();
//		model.addAttribute("countHotelLevel3", HotelLevel3.size());
//
//		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel4();
//		model.addAttribute("countHotelLevel4", HotelLevel4.size());
//
//		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel5();
//		model.addAttribute("countHotelLevel5", HotelLevel5.size());
//
//		int start = 1;
//		int last = start - 1;
//		int next = start + 1;
//		int SOLuongTrongTrang = 15;
//		double end = count / SOLuongTrongTrang;
//		double endRounded = Math.ceil(end);
//		// model.addAttribute("count", endRounded);
//		List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * SOLuongTrongTrang);
//		model.addAttribute("items", items);
//		model.addAttribute("last", last);
//		model.addAttribute("start", start);
//		model.addAttribute("next", next);
////		List<Hotels> items = hService.findByPlaceId(id);
////		model.addAttribute("items", items);
//		return "shop";
//	}
//
//	@RequestMapping("/hotel/place={place_id}/lpage={last}")
//	public String Last(Model model, @PathVariable("last") String last, @PathVariable("place_id") String pid) {
//		int id = Integer.parseInt(pid);
//		List<RoomTypes> roomtype = rtService.findAll();
//		List<Hotels> hoteltype = hService.findAll();
//		List<Services> services = svService.findAll();
//		// model.addAttribute("roomtype", roomtype);
//		// int counta = roomtype.size();
//		model.addAttribute("roomtype", roomtype);
//		model.addAttribute("hoteltype", hoteltype);
//		model.addAttribute("services", services);
//		List<Hotels> item = hService.findAll();
//		int count = item.size();
//		model.addAttribute("counthotel", count);
//
//		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel0();
//		model.addAttribute("countHotelLevel0", HotelLevel0.size());
//
//		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel1();
//		model.addAttribute("countHotelLevel1", HotelLevel1.size());
//
//		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel2();
//		model.addAttribute("countHotelLevel2", HotelLevel2.size());
//
//		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel3();
//		model.addAttribute("countHotelLevel3", HotelLevel3.size());
//
//		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel4();
//		model.addAttribute("countHotelLevel4", HotelLevel4.size());
//
//		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel5();
//		model.addAttribute("countHotelLevel5", HotelLevel5.size());
//
//		int start = Integer.parseInt(last);
//		if (start == 1) {
//			List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * 15);
//			model.addAttribute("items", items);
//			model.addAttribute("start", start);
//			model.addAttribute("next", start + 1);
//		} else {
//			List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * 15);
//			model.addAttribute("items", items);
//			model.addAttribute("last", start - 1);
//			model.addAttribute("start", start);
//			model.addAttribute("next", start + 1);
//		}
//		return "shop";
//	}
//
//	@RequestMapping("/hotel/place={place_id}/npage={next}")
//	public String Next(Model model, @PathVariable("next") String next, @PathVariable("place_id") String pid) {
//		int id = Integer.parseInt(pid);
//		List<RoomTypes> roomtype = rtService.findAll();
//		List<Hotels> hoteltype = hService.findAll();
//		List<Services> services = svService.findAll();
//		// model.addAttribute("roomtype", roomtype);
//		// int counta = roomtype.size();
//		model.addAttribute("roomtype", roomtype);
//		model.addAttribute("hoteltype", hoteltype);
//		model.addAttribute("services", services);
//		List<Hotels> item = hService.findAll();
//		int count = item.size();
//		model.addAttribute("counthotel", count);
//
//		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel0();
//		model.addAttribute("countHotelLevel0", HotelLevel0.size());
//
//		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel1();
//		model.addAttribute("countHotelLevel1", HotelLevel1.size());
//
//		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel2();
//		model.addAttribute("countHotelLevel2", HotelLevel2.size());
//
//		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel3();
//		model.addAttribute("countHotelLevel3", HotelLevel3.size());
//
//		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel4();
//		model.addAttribute("countHotelLevel4", HotelLevel4.size());
//
//		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel5();
//		model.addAttribute("countHotelLevel5", HotelLevel5.size());
//
//		int start = Integer.parseInt(next);
//		double end = count / 15;
//		double endRounded = Math.ceil(end);
//
//		if (endRounded < start) {
//			List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * 15);
//			model.addAttribute("items", items);
//			model.addAttribute("last", start - 1);
//			model.addAttribute("start", start);
//		} else {
//
//			List<Hotels> items = hService.findHotelByPlaceid(id, (start - 1) * 15);
//			model.addAttribute("items", items);
//			model.addAttribute("last", start - 1);
//			model.addAttribute("start", start);
//			model.addAttribute("next", start + 1);
//		}
//		return "shop";
//	}
//
//	@RequestMapping("/place={id}/hotel/start={Level}&&page={page}")
//	public String findHotelByPlaceidAndStart(Model model, @PathVariable("id") Integer id,
//			@PathVariable("Level") Integer Level, @PathVariable("page") Integer page) {
//		List<Hotels> items = hService.findHotelByPlaceidAndStart(Level, id, (page - 1) * 15);
//		model.addAttribute("items", items);
//		return "shop";
//	}

	
//	@RequestMapping("/hotel/place={id}")
//	public String findById(Model model,  @PathVariable("id") Integer id) {
//		
//		model.addAttribute("place_id", id);
//		
//		List<RoomTypes> roomtype = rtService.findAll();
//		List<Hotels> hoteltype = hService.findAll();
//		List<Services> services= svService.findAll();
//		//model.addAttribute("roomtype", roomtype);
//		//int counta = roomtype.size();
//		model.addAttribute("roomtype", roomtype);
//		model.addAttribute("hoteltype", hoteltype);
//		model.addAttribute("services", services);
//		List<Hotels> item = hService.findByPlaceId(id);
//		int count = item.size();
//		model.addAttribute("counthotel", count);
//		
//		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
//		model.addAttribute("countHotelLevel0", HotelLevel0.size());
//		
//		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
//		model.addAttribute("countHotelLevel1", HotelLevel1.size());
//		
//		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
//		model.addAttribute("countHotelLevel2", HotelLevel2.size());
//		
//		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
//		model.addAttribute("countHotelLevel3", HotelLevel3.size());
//		
//		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
//		model.addAttribute("countHotelLevel4", HotelLevel4.size());
//		
//		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
//		model.addAttribute("countHotelLevel5", HotelLevel5.size());
//		
//		int start = 1;
//		int last = start - 1;
//		int next = start + 1;
//		int SOLuongTrongTrang = 15;
//        double end = count / SOLuongTrongTrang;
//        double endRounded = Math.ceil(end);
//		//model.addAttribute("count", endRounded);
//		List<Hotels> items = hService.findHotelByPlaceid(id,(start-1) * SOLuongTrongTrang);
//		model.addAttribute("items", items);
//		model.addAttribute("last", last);
//		model.addAttribute("start", start);
//		model.addAttribute("next", next);
////		List<Hotels> items = hService.findByPlaceId(id);
////		model.addAttribute("items", items);
//		return "shop";
//	}
//	
//	@RequestMapping("/hotel/place={place_id}/lpage={last}")
//	public String Last(Model model, @PathVariable("last") String last, @PathVariable("place_id") String pid) {
//		int id = Integer.parseInt(pid);
//		List<RoomTypes> roomtype = rtService.findAll();
//		List<Hotels> hoteltype = hService.findAll();
//		List<Services> services= svService.findAll();
//		//model.addAttribute("roomtype", roomtype);
//		//int counta = roomtype.size();
//		model.addAttribute("roomtype", roomtype);
//		model.addAttribute("hoteltype", hoteltype);
//		model.addAttribute("services", services);
//		List<Hotels> item = hService.findByPlaceId(id);
//		int count = item.size();
//		model.addAttribute("counthotel", count);
//		
//		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
//		model.addAttribute("countHotelLevel0", HotelLevel0.size());
//		
//		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
//		model.addAttribute("countHotelLevel1", HotelLevel1.size());
//		
//		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
//		model.addAttribute("countHotelLevel2", HotelLevel2.size());
//		
//		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
//		model.addAttribute("countHotelLevel3", HotelLevel3.size());
//		
//		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
//		model.addAttribute("countHotelLevel4", HotelLevel4.size());
//		
//		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
//		model.addAttribute("countHotelLevel5", HotelLevel5.size());
//		
//		int start = Integer.parseInt(last);
//		if(start==1) {
//			List<Hotels> items= hService.findHotelByPlaceid(id,(start-1) * 15);
//			model.addAttribute("items", items);
//			model.addAttribute("start", start);
//			model.addAttribute("next", start + 1);
//		}else {
//			List<Hotels> items= hService.findHotelByPlaceid(id,(start-1) * 15);
//			model.addAttribute("items", items);
//			model.addAttribute("last", start - 1);
//			model.addAttribute("start", start );
//			model.addAttribute("next", start + 1);
//		}
//		return "shop";
//	}
//	
//	@RequestMapping("/hotel/place={place_id}/npage={next}")
//	public String Next(Model model, @PathVariable("next") String next, @PathVariable("place_id") String pid) {
//		int id = Integer.parseInt(pid);
//		List<RoomTypes> roomtype = rtService.findAll();
//		List<Hotels> hoteltype = hService.findAll();
//		List<Services> services= svService.findAll();
//		//model.addAttribute("roomtype", roomtype);
//		//int counta = roomtype.size();
//		model.addAttribute("roomtype", roomtype);
//		model.addAttribute("hoteltype", hoteltype);
//		model.addAttribute("services", services);
//		List<Hotels> item = hService.findByPlaceId(id);
//		int count = item.size();
//		model.addAttribute("counthotel", count);
//		
//		List<Hotels> HotelLevel0 = hService.findHotelByHotelLevel(0);
//		model.addAttribute("countHotelLevel0", HotelLevel0.size());
//		
//		List<Hotels> HotelLevel1 = hService.findHotelByHotelLevel(1);
//		model.addAttribute("countHotelLevel1", HotelLevel1.size());
//		
//		List<Hotels> HotelLevel2 = hService.findHotelByHotelLevel(2);
//		model.addAttribute("countHotelLevel2", HotelLevel2.size());
//		
//		List<Hotels> HotelLevel3 = hService.findHotelByHotelLevel(3);
//		model.addAttribute("countHotelLevel3", HotelLevel3.size());
//		
//		List<Hotels> HotelLevel4 = hService.findHotelByHotelLevel(4);
//		model.addAttribute("countHotelLevel4", HotelLevel4.size());
//		
//		List<Hotels> HotelLevel5 = hService.findHotelByHotelLevel(5);
//		model.addAttribute("countHotelLevel5", HotelLevel5.size());
//		
//		int start = Integer.parseInt(next);
//		double end = count / 15;
//		double endRounded = Math.ceil(end) ;
//		
//		if(endRounded < start) {
//			List<Hotels> items= hService.findHotelByPlaceid(id,(start-1) * 15);
//			model.addAttribute("items", items);
//			model.addAttribute("last", start - 1);
//			model.addAttribute("start", start);
//		}else {
//			
//			List<Hotels> items= hService.findHotelByPlaceid(id,(start-1) * 15);
//			model.addAttribute("items", items);
//			model.addAttribute("last", start - 1);
//			model.addAttribute("start", start );
//			model.addAttribute("next", start + 1);
//		}
//		return "shop";
//	}
//	
//	@RequestMapping("/place={id}/hotel/start={Level}&&page={page}")
//	public String findHotelByPlaceidAndStart(Model model,  @PathVariable("id") Integer id, @PathVariable("Level") Integer Level, @PathVariable("page") Integer page) {
//		List<Hotels> items = hService.findHotelByPlaceidAndStart(Level, id, (page-1)*15);
//		model.addAttribute("items", items);
//		return "shop";
//	}
	
	//xu ly admin
 	
 	@GetMapping("/places/form")
 	public String formPlace(Model model) {
 		model.addAttribute("places", new Places());
 		return "admin/Place/form";
 	}

 	@GetMapping("/places/index")
    public String showPlacesIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
		Pageable page = PageRequest.of(p, 10);
		Page<Places> places = placeService.findAlla(page);
		model.addAttribute("places", places);
        return "admin/Place/index";
    }
 
 	@RequestMapping("/places/lpage={last}")
	public String placeAdminLast(Model model, @PathVariable("last") String plast) {
		List<Places> places = placeService.findAll();
		int SOLuongTrongTrang = 10;
//		 model.addAttribute("users", userService.findAll());
		 int count = places.size();
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
			List<Places> items = placeService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("places", items);
			model.addAttribute("last", null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<Places> items = placeService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("places", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		model.addAttribute("endRounded", endRounded);
		return "admin/Place/index";
	}

	@RequestMapping("/places/npage={next}")
	public String placeAdminNext(Model model, @PathVariable("next") String pnext) {

		List<Places> places = placeService.findAll();
		int SOLuongTrongTrang = 10;
		int count = places.size();
		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		
		
		int start = Integer.parseInt(pnext);
		if (start == endRounded) {
			List<Places> items = placeService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("places", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", null);
		} else {
			List<Places> items = placeService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("places", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			
		}
		model.addAttribute("endRounded", (int)endRounded);
		return "admin/Place/index";
	}
 	
 	
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/places/{id}")
    public String viewPlace(@PathVariable("id") Integer id, Model model) {
        Places place = placeService.findById(id);
        model.addAttribute("places", place);
        return "admin/Place/form";
    }

    @PostMapping("/places/create")
    public String createPlace(@ModelAttribute Places places) {
    	placeService.create(places);
        return "redirect:/places/form";
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
    
    @PostMapping("/places/update")
    public ModelAndView updatePlace(@ModelAttribute Places place) {
        if (place.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
        	placeService.update(place);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	placeService.create(place);
        }
        return new ModelAndView("redirect:/places/index");
    }

    @GetMapping("/places/delete/{id}")
    public ModelAndView deletePlace(@PathVariable("id") Integer id) {
    	placeService.delete(id);
        return new ModelAndView("redirect:/places/index");
    }
}
