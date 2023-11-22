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

import com.poly.Service.FeedbackService;
import com.poly.Service.ImagesService;
import com.poly.entity.Feedback;
import com.poly.entity.Images;

@Controller
public class FeedbackController {
	
	@Autowired
    private FeedbackService fbService;
	
	//xu ly admin
	 	@GetMapping("/form")
	 	public String formFeedback(Model model) {
	 		model.addAttribute("feedbacks", new Feedback());
	 		return "admin/Feedback/form";
	 	}

	 	@GetMapping("/index")
	    public String showFeedbacksIndex(Model model) {
	 		model.addAttribute("feedbacks", fbService.findAll());
	        return "admin/Feedback/index";
	    }
	 
//	    @GetMapping
//	    public String listPlaces(Model model) {
//	        model.addAttribute("places", placeService.findAll());
//	        return "";
//	    }

	    @GetMapping("/{id}")
	    public String viewFeedback(@PathVariable("id") Integer id, Model model) {
	    	Feedback feedbacks = fbService.findById(id);
	        model.addAttribute("feedbacks", feedbacks);
	        return "admin/Feedback/form";
	    }
	    
	    @PostMapping("/create")
	    public String createFeedback(@ModelAttribute Feedback feedbacks) {
	    	if (feedbacks.getRooms() != null && feedbacks.getRooms().getId() == null) {
	            
	        }
	    	fbService.create(feedbacks);
	        return "redirect:/feedbacks/form";
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
	    
	    @PostMapping("/update")
	    public ModelAndView updateFeedback(@ModelAttribute Feedback feedbacks) {
	        if (feedbacks.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
	        	fbService.update(feedbacks);
	        } else {
	            // Nếu không có ID, thực hiện thêm mới
	        	fbService.create(feedbacks);
	        }
	        return new ModelAndView("redirect:/feedbacks/index");
	    }

	    @GetMapping("/delete/{id}")
	    public ModelAndView deleteFeedback(@PathVariable("id") Integer id) {
	    	fbService.delete(id);
	        return new ModelAndView("redirect:/feedbacks/index");
	    }
}
