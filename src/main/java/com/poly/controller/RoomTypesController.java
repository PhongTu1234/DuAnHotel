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

import com.poly.Service.RoomTypesService;
import com.poly.entity.RoomTypes;

@Controller
@RequestMapping("/roomtypes")
public class RoomTypesController {
	@Autowired
	RoomTypesService roomtypeService;
	
	@GetMapping("/form")
 	public String formUser(Model model) {
 		model.addAttribute("roomtypes", new RoomTypes());
 		return "admin/RoomTypes/form";
 	}

 	@GetMapping("/index")
    public String showRoomTypesIndex(Model model) {
 		model.addAttribute("roomtypes", roomtypeService.findAll());
        return "admin/RoomTypes/index";
    }
 
    @GetMapping
    public String listRoomTypes(Model model) {
        model.addAttribute("roomtypes", roomtypeService.findAll());
        return "Roles/index";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable("id") Integer id, Model model) {
        RoomTypes roomtypes = roomtypeService.findById(id);
        model.addAttribute("roomtypes", roomtypes);
        return "admin/RoomTypes/form";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute RoomTypes roomtypes) {
        roomtypeService.create(roomtypes);
        return "redirect:/roomtypes/form";
    }

//    @PostMapping("/update")
//    public ModelAndView updateUser(@Validated @ModelAttribute("user") RoomTypes user) {
//        roomtypeService.update(user);
//        return new ModelAndView("redirect:/RoomTypes/index");
//    }
//
//    @GetMapping("/delete/{id}")
//    public ModelAndView deleteUser(@PathVariable String id) {
//        roomtypeService.delete(id);
//        return new ModelAndView("redirect:/RoomTypes/index");
//    }
    
    @PostMapping("/update")
    public ModelAndView updateUser(@ModelAttribute RoomTypes roomtypes) {
        if (roomtypes.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
            roomtypeService.update(roomtypes);
        } else {
            // Nếu không có ID, thực hiện thêm mới
            roomtypeService.create(roomtypes);
        }
        return new ModelAndView("redirect:/roomtypes/index");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Integer id) {
        roomtypeService.delete(id);
        return new ModelAndView("redirect:/roomtypes/index");
    }
}
