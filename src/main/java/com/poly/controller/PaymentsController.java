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

import com.poly.Service.PaymentService;
import com.poly.Service.RoleService;
import com.poly.entity.Images;
import com.poly.entity.Payment;
import com.poly.entity.Role;

@Controller
public class PaymentsController {
	@Autowired
    private PaymentService paymentsService;
	
	//xu ly admin
	 	@GetMapping("/payments/form")
	 	public String formPayment(Model model) {
	 		model.addAttribute("payments", new Payment());
	 		return "admin/Payment/form";
	 	}

	 	@GetMapping("/payments/index")
	    public String showPaymentsIndex(Model model) {
//	 		model.addAttribute("payments", paymentsService.findAll());
	 		List<Payment> payment = paymentsService.findAll();

//	 		model.addAttribute("hotels", hService.findAll());
	 		int SOLuongTrongTrang = 10;
	 		int count = payment.size();
	 		int start = 1;
	 		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
			int endRounded = endRound;
			if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = endRound + 1;
			}
			 
			List<Payment> payments = paymentsService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("payments", payments);
			model.addAttribute("last",  null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			model.addAttribute("endRounded",endRounded);
	        return "admin/Payment/index";
	    }
	 
	 	@RequestMapping("/payments/lpage={last}")
		public String paymentAdminLast(Model model, @PathVariable("last") String plast) {
			List<Payment> payments = paymentsService.findAll();
			int SOLuongTrongTrang = 10;
//			 model.addAttribute("users", userService.findAll());
			 int count = payments.size();
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
				List<Payment> items = paymentsService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("payments", items);
				model.addAttribute("last", null);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			} else {
				List<Payment> items = paymentsService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("payments", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
			}
			model.addAttribute("endRounded", endRounded);
			return "admin/Payment/index";
		}

		@RequestMapping("/payments/npage={next}")
		public String paymentAdminNext(Model model, @PathVariable("next") String pnext) {

			List<Payment> payments = paymentsService.findAll();
			int SOLuongTrongTrang = 10;
			int count = payments.size();
			int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
			int endRounded = endRound;
			if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = endRound + 1;
			}
			
			
			int start = Integer.parseInt(pnext);
			if (start == endRounded) {
				List<Payment> items = paymentsService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("payments", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", null);
			} else {
				List<Payment> items = paymentsService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
				model.addAttribute("payments", items);
				model.addAttribute("last", start - 1);
				model.addAttribute("start", start);
				model.addAttribute("next", start + 1);
				
			}
			model.addAttribute("endRounded", (int)endRounded);
			return "admin/Payment/index";
		}
	 	
//	    @GetMapping
//	    public String listPlaces(Model model) {
//	        model.addAttribute("places", placeService.findAll());
//	        return "";
//	    }

	    @GetMapping("/payments/{id}")
	    public String viewPayment(@PathVariable("id") Integer id, Model model) {
	    	Payment payments = paymentsService.findById(id);
	        model.addAttribute("payments", payments);
	        return "admin/Payment/form";
	    }

	    @PostMapping("/payments/create")
	    public String createPayment(@ModelAttribute Payment roles) {
	    	paymentsService.create(roles);
	        return "redirect:/payments/form";
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
	    
	    @PostMapping("/payments/update")
	    public ModelAndView updatePayment(@ModelAttribute Payment payments) {
	        if (payments.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
	        	paymentsService.update(payments);
	        } else {
	            // Nếu không có ID, thực hiện thêm mới
	        	paymentsService.create(payments);
	        }
	        return new ModelAndView("redirect:/payments/index");
	    }

	    @GetMapping("/payments/delete/{id}")
	    public ModelAndView deletePayment(@PathVariable("id") Integer id) {
	    	paymentsService.delete(id);
	        return new ModelAndView("redirect:/payments/index");
	    }
}
