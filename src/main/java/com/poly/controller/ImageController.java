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

import com.poly.Service.ImagesService;
import com.poly.Service.PlacesService;
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
	    public String showImagesIndex(Model model) {
//	 		model.addAttribute("images", imagesService.findAll());
	 		List<Images> image = imagesService.findAll();

//	 		model.addAttribute("hotels", hService.findAll());
	 		int SOLuongTrongTrang = 10;
	 		int count = image.size();
	 		int start = 1;
	 		double end = count / SOLuongTrongTrang;
	 		double endRound = Math.ceil(end);
			 int endRounded = (int) Math.round(endRound) +1;
			 
			List<Images> images = imagesService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("images", images);
			model.addAttribute("last", null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			model.addAttribute("endRounded",endRounded);
	        return "admin/Images/index";
	    }
	 
	 	@RequestMapping("/images/lpage={last}")
		public String imageAdminLast(Model model, @PathVariable("last") String plast) {
			List<Images> images = imagesService.findAll();
			int SOLuongTrongTrang = 10;
//			 model.addAttribute("users", userService.findAll());
			 int count = images.size();
//				int last = start - 1;
//				int next = start + 1;
			// int SOLuongTrongTrang = 10;
			 double end = count / SOLuongTrongTrang;
			 double endRound = Math.ceil(end);
			 int endRounded = (int) Math.round(endRound) +1;
//					List<Users> users = userService.findAll();
//					// model.addAttribute("roomtype", roomtype);
//					// int counta = roomtype.size();
//					model.addAttribute("users", users);

			// model.addAttribute("count", count);

			int start = Integer.parseInt(plast);
			// int last = start - 1;
			if (start == 1) {
				List<Images> items = imagesService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("images", items);
				model.addAttribute("last", null);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			} else {
				List<Images> items = imagesService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("images", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			}
			model.addAttribute("endRounded", endRounded);
			return "admin/Images/index";
		}

		@RequestMapping("/images/npage={next}")
		public String imageAdminNext(Model model, @PathVariable("next") String pnext) {

			List<Images> images = imagesService.findAll();
			int SOLuongTrongTrang = 10;
			int count = images.size();
			double end = count / SOLuongTrongTrang;
			double endRound = Math.ceil(end);
			 int endRounded = (int) Math.round(endRound) +1;
			 
			
			int start = Integer.parseInt(pnext);
			if (start == endRounded) {
				List<Images> items = imagesService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("images", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", null);
			} else {
				List<Images> items = imagesService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("images", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
				
			}
			model.addAttribute("endRounded", (int)endRounded);
			return "admin/Images/index";
		}
//	    @GetMapping
//	    public String listPlaces(Model model) {
//	        model.addAttribute("places", placeService.findAll());
//	        return "";
//	    }

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
