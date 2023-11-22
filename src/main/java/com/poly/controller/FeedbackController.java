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

import com.poly.Service.FeedbackService;
import com.poly.Service.ImagesService;
import com.poly.entity.Feedback;
import com.poly.entity.Images;
import com.poly.entity.Payment;

@Controller
public class FeedbackController {
	
	@Autowired
    private FeedbackService fbService;
	
	//xu ly admin
	 	@GetMapping("/feedbacks/form")
	 	public String formFeedback(Model model) {
	 		model.addAttribute("feedbacks", new Feedback());
	 		return "admin/Feedback/form";
	 	}

	 	@GetMapping("/feedbacks/index")
	    public String showFeedbacksIndex(Model model) {
//	 		model.addAttribute("feedbacks", fbService.findAll());
	 		List<Feedback> feedback = fbService.findAll();

//	 		model.addAttribute("hotels", hService.findAll());
	 		int SOLuongTrongTrang = 10;
	 		int count = feedback.size();
	 		int start = 1;
	 		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
			int endRounded = endRound;
			if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = endRound + 1;
			}
			 
			List<Feedback> feedbacks = fbService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("feedbacks", feedbacks);
			model.addAttribute("last",  null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			model.addAttribute("endRounded",endRounded);
	        return "admin/Feedback/index";
	    }
	 
	 	@RequestMapping("/feedbacks/lpage={last}")
		public String feedbackAdminLast(Model model, @PathVariable("last") String plast) {
			List<Feedback> feedbacks = fbService.findAll();
			int SOLuongTrongTrang = 10;
//			 model.addAttribute("users", userService.findAll());
			 int count = feedbacks.size();
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
				model.addAttribute("feedbacks", items);
				model.addAttribute("last", null);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			} else {
				List<Feedback> items = fbService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("feedbacks", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			}
			model.addAttribute("endRounded", endRounded);
			return "admin/Feedback/index";
		}

		@RequestMapping("/feedbacks/npage={next}")
		public String feedbackAdminNext(Model model, @PathVariable("next") String pnext) {

			List<Feedback> feedbacks = fbService.findAll();
			int SOLuongTrongTrang = 10;
			int count = feedbacks.size();
			int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
			int endRounded = endRound;
			if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = endRound + 1;
			}
			
			
			int start = Integer.parseInt(pnext);
			if (start == endRounded) {
				List<Feedback> items = fbService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("feedbacks", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", null);
			} else {
				List<Feedback> items = fbService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("feedbacks", items);
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

	    @GetMapping("/feedbacks/{id}")
	    public String viewFeedback(@PathVariable("id") Integer id, Model model) {
	    	Feedback feedbacks = fbService.findById(id);
	        model.addAttribute("feedbacks", feedbacks);
	        return "admin/Feedback/form";
	    }
	    
	    @PostMapping("/feedbacks/create")
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
	    
	    @PostMapping("/feedbacks/update")
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

	    @GetMapping("/feedbacks/delete/{id}")
	    public ModelAndView deleteFeedback(@PathVariable("id") Integer id) {
	    	fbService.delete(id);
	        return new ModelAndView("redirect:/feedbacks/index");
	    }
}
