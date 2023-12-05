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

import com.poly.Service.ImagesService;
import com.poly.Service.PlacesService;
import com.poly.entity.Bookings;
import com.poly.entity.Hotels;
import com.poly.entity.Images;
import com.poly.entity.Places;

@Controller
public class ImageController {
	
	@Autowired
    private ImagesService imagesService;
	
	//xu ly admin
	 	@GetMapping("/images/form")
	 	public String formImage(Model model) {
	 		model.addAttribute("images", new Images());
	 		return "admin/Images/form";
	 	}

	 	@GetMapping("/images/index")
	    public String showImagesIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
	 		Pageable page = PageRequest.of(p, 10);
			Page<Images> images = imagesService.findAll(page);
			model.addAttribute("images", images);
			return "admin/Images/index";
		}

	    @GetMapping("/images/{id}")
	    public String viewImage(@PathVariable("id") Integer id, Model model) {
	    	Images place = imagesService.findById(id);
	        model.addAttribute("images", place);
	        return "admin/Images/form";
	    }

	    @PostMapping("/images/create")
	    public String createImage(@ModelAttribute Images images) {
	    	imagesService.create(images);
	        return "redirect:/images/form";
	    }

//	    @PostMapping("/update")
//	    public ModelAndView updateUser(@Validated @ModelAttribute("user") Users user) {
//	        userService.update(user);
//	        return new ModelAndView("redirect:/users/index");
//	    }
	//
//	    @GetMapping("/delete/{cmt}")
//	    public ModelAndView deleteUser(@PathVariable String cmt) {
//	        userService.delete(cmt);
//	        return new ModelAndView("redirect:/users/index");
//	    }
	    
	    @PostMapping("/images/update")
	    public ModelAndView updateImage(@ModelAttribute Images images) {
	        if (images.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
	        	imagesService.update(images);
	        } else {
	            // Nếu không có ID, thực hiện thêm mới
	        	imagesService.create(images);
	        }
	        return new ModelAndView("redirect:/images/index");
	    }

	    @GetMapping("/images/delete/{id}")
	    public ModelAndView deleteImage(@PathVariable("id") Integer id) {
	    	imagesService.delete(id);
	        return new ModelAndView("redirect:/images/index");
	    }
}
