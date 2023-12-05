package com.poly.controller;

import java.time.LocalDate;
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

import com.poly.Service.PaymentService;
import com.poly.Service.RoleService;
import com.poly.entity.Feedback;
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
	    public String showPaymentsIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
			Pageable page = PageRequest.of(p, 10);
			Page<Payment> payments = paymentsService.findAll(page);
			model.addAttribute("payments", payments);

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
	    	payments.setDate(LocalDate.parse(payments.getPaymentDateStr()));
	    	
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
