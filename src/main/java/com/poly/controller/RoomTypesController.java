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

import com.poly.Service.PlacesService;
import com.poly.Service.RoomTypesService;
import com.poly.entity.Places;
import com.poly.entity.RoomTypes;

@Controller
@RequestMapping("/roomtypes")
public class RoomTypesController {
	@Autowired
    private RoomTypesService rtService;
	
	//xu ly admin
 	@GetMapping("/form")
 	public String formRoomType(Model model) {
 		model.addAttribute("roomtypes", new RoomTypes());
 		return "admin/RoomType/form";
 	}

 	@GetMapping("/index")
    public String showRoomTypesIndex(Model model) {
 		model.addAttribute("roomtypes", rtService.findAll());
        return "admin/RoomType/index";
    }
 
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/{id}")
    public String viewRoomType(@PathVariable("id") Integer id, Model model) {
        RoomTypes roomtypes = rtService.findById(id);
        model.addAttribute("roomtypes", roomtypes);
        return "admin/RoomType/form";
    }

    @PostMapping("/create")
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
    
    @PostMapping("/update")
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

    @GetMapping("/delete/{id}")
    public ModelAndView deleteRoomType(@PathVariable("id") Integer id) {
    	rtService.delete(id);
        return new ModelAndView("redirect:/roomtypes/index");
    }
}
