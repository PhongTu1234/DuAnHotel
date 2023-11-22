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

import com.poly.Service.PaymentService;
import com.poly.Service.RoleService;
import com.poly.entity.Payment;
import com.poly.entity.Role;

@Controller
public class PaymentsController {
	@Autowired
    private PaymentService paymentsService;
	
	//xu ly admin
	 	@GetMapping("/form")
	 	public String formPayment(Model model) {
	 		model.addAttribute("payments", new Payment());
	 		return "admin/Payment/form";
	 	}

	 	@GetMapping("/index")
	    public String showPaymentsIndex(Model model) {
	 		model.addAttribute("payments", paymentsService.findAll());
	        return "admin/Payment/index";
	    }
	 
//	    @GetMapping
//	    public String listPlaces(Model model) {
//	        model.addAttribute("places", placeService.findAll());
//	        return "";
//	    }

	    @GetMapping("/{id}")
	    public String viewPayment(@PathVariable("id") Integer id, Model model) {
	    	Payment payments = paymentsService.findById(id);
	        model.addAttribute("payments", payments);
	        return "admin/Payment/form";
	    }

	    @PostMapping("/create")
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
	    
	    @PostMapping("/update")
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

	    @GetMapping("/delete/{id}")
	    public ModelAndView deletePayment(@PathVariable("id") Integer id) {
	    	paymentsService.delete(id);
	        return new ModelAndView("redirect:/payments/index");
	    }
}
