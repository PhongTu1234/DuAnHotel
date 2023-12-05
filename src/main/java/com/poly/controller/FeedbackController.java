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

import com.poly.Service.FeedbackService;
import com.poly.Service.ImagesService;
import com.poly.Service.RoomService;
import com.poly.Service.UserService;
import com.poly.entity.Booking_Room;
import com.poly.entity.Feedback;
import com.poly.entity.Images;
import com.poly.entity.Payment;

@Controller
public class FeedbackController {
	
	@Autowired
    private FeedbackService fbService;
	
	@Autowired
    private RoomService rService;
	
	@Autowired
    private UserService userService;
	
	//xu ly admin
	 	@GetMapping("/feedback/form")
	 	public String formFeedback(Model model) {
	 		model.addAttribute("feedback", new Feedback());
	 		return "admin/Feedback/form";
	 	}

	 	@GetMapping("/feedback/index")
	    public String showfeedbackIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
	 		Pageable page = PageRequest.of(p, 10);
			Page<Feedback> feedback = fbService.findAll(page);
			model.addAttribute("feedback", feedback);
	        return "admin/Feedback/index";
	    }
	 	
//	    @GetMapping
//	    public String listPlaces(Model model) {
//	        model.addAttribute("places", placeService.findAll());
//	        return "";
//	    }

	    @GetMapping("/feedback/{id}")
	    public String viewFeedback(@PathVariable("id") Integer id, Model model) {
	    	Feedback feedback = fbService.findById(id);
	        model.addAttribute("feedback", feedback);
	        return "admin/Feedback/form";
	    }
	    
	    @PostMapping("/feedback/create")
	    public String createFeedback(@ModelAttribute Feedback feedback) {
//	    	if (feedback.getRooms() != null && feedback.getRooms().getId() == null) {
//	            
//	        }
	    	fbService.create(feedback);
	        return "redirect:/feedback/form";
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
	    
	    @PostMapping("/feedback/update")
	    public ModelAndView updateFeedback(@ModelAttribute Feedback feedback, Model model) {
	    	String Rooms = feedback.getRooms().getName();
	    	String Users= feedback.getUsers().getCmt();
	        if(rService.findByRoomName(Rooms) != null && userService.findByUserName(Users) != null) {
	        	if (feedback.getId() != null) {
		            // Nếu có ID, thực hiện cập nhật
	        		feedback.setRooms(rService.findByRoomName(Rooms));
	        		feedback.setUsers(userService.findByUserName(Users));
	        		fbService.update(feedback);
		        } else {
		        	feedback.setRooms(rService.findByRoomName(Rooms));
		        	feedback.setUsers(userService.findByUserName(Users));
		            // Nếu không có ID, thực hiện thêm mới
		        	fbService.create(feedback);
		        }
	        	return new ModelAndView("redirect:/feedback/index");
	        }else {
	        	model.addAttribute("message", "phòng hoặc user không tồn tại!");
	        	return new ModelAndView("admin/Feedback/form");
	        }
	    }

	    @GetMapping("/feedback/delete/{id}")
	    public ModelAndView deleteFeedback(@PathVariable("id") Integer id) {
	    	fbService.delete(id);
	        return new ModelAndView("redirect:/feedback/index");
	    }
}
