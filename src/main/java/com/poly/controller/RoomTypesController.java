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

import com.poly.Service.PlacesService;
import com.poly.Service.RoomTypesService;
import com.poly.entity.Hotels;
import com.poly.entity.Places;
import com.poly.entity.RoomTypes;

@Controller
public class RoomTypesController {
	@Autowired
    private RoomTypesService rtService;
	
	private int SOLuongTrongTrang = 10;
	
	//xu ly admin
 	@GetMapping("/roomtypes/form")
 	public String formRoomType(Model model) {
 		model.addAttribute("roomtypes", new RoomTypes());
 		return "admin/RoomType/form";
 	}

 	@GetMapping("/roomtypes/index")
    public String showRoomTypesIndex(Model model) {
// 		model.addAttribute("roomtypes", rtService.findAll());
 		List<RoomTypes> roomtype = rtService.findAll();

// 		model.addAttribute("hotels", hService.findAll());
		 int count = roomtype.size();
		int start = 1;
//			int last = start - 1;
//			int next = start + 1;
		// int SOLuongTrongTrang = 10;
		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		
		
		List<RoomTypes> roomtypes = rtService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
		model.addAttribute("roomtypes", roomtypes);
		model.addAttribute("last", null);
		model.addAttribute("start", start);
		if( endRounded > 1 ) {
			model.addAttribute("next", start + 1);
		}else {
			model.addAttribute("next", null);
		}
		
		model.addAttribute("endRounded",endRounded);
        return "admin/RoomType/index";
    }
 
 	@RequestMapping("/roomtypes/lpage={last}")
	public String roomtypeAdminLast(Model model, @PathVariable("last") String plast) {
		List<RoomTypes> roomtypes = rtService.findAll();
		int SOLuongTrongTrang = 10;
//		 model.addAttribute("users", userService.findAll());
		 int count = roomtypes.size();
//			int last = start - 1;
//			int next = start + 1;
		// int SOLuongTrongTrang = 10;
		 double end = count / SOLuongTrongTrang;
		 double endRound = Math.ceil(end);
		 int endRounded = (int) Math.round(endRound);
		 if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = (int) (endRound + 1);
			}
//				List<Users> users = userService.findAll();
//				// model.addAttribute("roomtype", roomtype);
//				// int counta = roomtype.size();
//				model.addAttribute("users", users);

		// model.addAttribute("count", count);

		int start = Integer.parseInt(plast);
		// int last = start - 1;
		if (start == 1) {
			List<RoomTypes> items = rtService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("roomtypes", items);
			model.addAttribute("last", null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<RoomTypes> items = rtService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("roomtypes", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		model.addAttribute("endRounded", endRounded);
		return "admin/RoomType/index";
	}

	@RequestMapping("/roomtypes/npage={next}")
	public String roomtypeAdminNext(Model model, @PathVariable("next") String pnext) {

		List<RoomTypes> roomtypes = rtService.findAll();
		int SOLuongTrongTrang = 10;
		int count = roomtypes.size();
		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		
		int start = Integer.parseInt(pnext);
		if (start == endRounded) {
			List<RoomTypes> items = rtService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("roomtypes", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", null);
		} else {
			List<RoomTypes> items = rtService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("roomtypes", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			
		}
		model.addAttribute("endRounded", (int)endRounded);
		return "admin/RoomType/index";
	}
 	
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/roomtypes/{id}")
    public String viewRoomType(@PathVariable("id") Integer id, Model model) {
        RoomTypes roomtypes = rtService.findById(id);
        model.addAttribute("roomtypes", roomtypes);
        return "admin/RoomType/form";
    }

    @PostMapping("/roomtypes/create")
    public String createRoomType(@ModelAttribute RoomTypes roomtypes) {
    	rtService.create(roomtypes);
        return "redirect:/roomtypes/form";
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
    
    @PostMapping("/roomtypes/update")
    public ModelAndView updateRoomType(@ModelAttribute RoomTypes roomtypes) {
        if (roomtypes.getId() != null) {
// Nếu có ID, thực hiện cập nhật
        	rtService.update(roomtypes);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	rtService.create(roomtypes);
        }
        return new ModelAndView("redirect:/roomtypes/index");
    }

    @GetMapping("/roomtypes/delete/{id}")
    public ModelAndView deleteRoomType(@PathVariable("id") Integer id) {
    	rtService.delete(id);
        return new ModelAndView("redirect:/roomtypes/index");
    }
}
		