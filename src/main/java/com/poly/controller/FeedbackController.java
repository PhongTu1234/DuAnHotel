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
			Page<Feedback> feedback = fbService.findAlla(page);
			model.addAttribute("feedback", feedback);
	        return "admin/Feedback/index";
	    }
	 
	 	@RequestMapping("/feedback/lpage={last}")
		public String feedbackAdminLast(Model model, @PathVariable("last") String plast) {
			List<Feedback> feedback = fbService.findAll();
			int SOLuongTrongTrang = 10;
//			 model.addAttribute("users", userService.findAll());
			 int count = feedback.size();
//				int last = start - 1;
//				int next = start + 1;
			// int SOLuongTrongTrang = 10;
			 int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
				int endRounded = endRound;
				if((endRound * SOLuongTrongTrang) < count ) {
					endRounded = endRound + 1;
				}
//					List<Users> users = userService.findAll();
//					// model.addAttribute("roomtype", roomtype);
//					// int counta = roomtype.size();
//					model.addAttribute("users", users);

			// model.addAttribute("count", count);

			int start = Integer.parseInt(plast);
			// int last = start - 1;
			if (start == 1) {
				List<Feedback> items = fbService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("feedback", items);
				model.addAttribute("last", null);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			} else {
				List<Feedback> items = fbService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("feedback", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			}
			model.addAttribute("endRounded", endRounded);
			return "admin/Feedback/index";
		}

		@RequestMapping("/feedback/npage={next}")
		public String feedbackAdminNext(Model model, @PathVariable("next") String pnext) {

			List<Feedback> feedback = fbService.findAll();
			int SOLuongTrongTrang = 10;
			int count = feedback.size();
			int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
			int endRounded = endRound;
			if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = endRound + 1;
			}
			
			
			int start = Integer.parseInt(pnext);
			if (start == endRounded) {
				List<Feedback> items = fbService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("feedback", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", null);
			} else {
				List<Feedback> items = fbService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("feedback", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
				
			}
			model.addAttribute("endRounded", (int)endRounded);
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
