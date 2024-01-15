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

import com.poly.Service.PlacesService;
import com.poly.Service.RoomTypesService;
import com.poly.Service.SlugService;
import com.poly.entity.Hotels;
import com.poly.entity.Places;
import com.poly.entity.RoomTypes;
import com.poly.entity.Rooms;

@Controller
public class RoomTypesController {
	@Autowired
    private RoomTypesService rtService;
	
	@Autowired
	private SlugService slugService;
		
	//xu ly admin
 	@GetMapping("/quan-ly-loai-phong/them-moi")
 	public String formRoomType(Model model) {
 		model.addAttribute("roomtypes", new RoomTypes());
 		return "admin/RoomTypes/form";
 	}

 	@GetMapping("/quan-ly-loai-phong/danh-sach")
    public String showRoomTypesIndex(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		Pageable page = PageRequest.of(p-1, 10);
		Page<RoomTypes> roomtypes = rtService.findAll(page);
		model.addAttribute("roomtypes", roomtypes);
		
		model.addAttribute("slugService", slugService);
		
        return "admin/RoomTypes/index";
    }

    @GetMapping("/quan-ly-loai-phong/{slug}/{id}")
    public String viewRoomType(@PathVariable("id") Integer id, Model model) {
        RoomTypes roomtypes = rtService.findById(id);
        model.addAttribute("roomtypes", roomtypes);
        return "admin/RoomTypes/form";
    }

    @PostMapping("/roomtypes/create")
    public String createRoomType(@ModelAttribute RoomTypes roomtypes) {
    	rtService.create(roomtypes);
        return "redirect:/quan-ly-loai-phong/them-moi";
    }
    
    @PostMapping("/roomtypes/update")
    public ModelAndView updateRoomType(@ModelAttribute RoomTypes roomtypes) {
        if (roomtypes.getId() != null) {
        	rtService.update(roomtypes);
        } else {
        	rtService.create(roomtypes);
        }
        return new ModelAndView("redirect:/quan-ly-loai-phong/danh-sach");
    }

    @GetMapping("/roomtypes/delete/{id}")
    public ModelAndView deleteRoomType(@PathVariable("id") Integer id) {
    	rtService.delete(id);
        return new ModelAndView("redirect:/quan-ly-loai-phong/danh-sach");
    }
}
		