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

import com.poly.Service.ServiceService;
import com.poly.Service.UserService;
import com.poly.entity.Places;
import com.poly.entity.Services;
import com.poly.entity.Users;

@Controller
public class ServicesController {
	@Autowired
    private ServiceService serviceService;
 	
	
	//xu ly admin
 	@GetMapping("/services/form")
 	public String formSevice(Model model) {
 		model.addAttribute("services", new Services());
 		return "admin/Services/form";
 	}

 	@GetMapping("/services/index")
    public String showServicesIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
		Pageable page = PageRequest.of(p, 10);
		Page<Services> services = serviceService.findAlla(page);
		model.addAttribute("services", services);
        return "admin/Services/index";
    }
 
 	@RequestMapping("/services/lpage={last}")
	public String serviceAdminLast(Model model, @PathVariable("last") String plast) {
		List<Services> services = serviceService.findAll();
		int SOLuongTrongTrang = 10;
//		 model.addAttribute("users", userService.findAll());
		 int count = services.size();
//			int last = start - 1;
//			int next = start + 1;
		// int SOLuongTrongTrang = 10;
		 int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
			int endRounded = endRound;
			if((endRound * SOLuongTrongTrang) < count ) {
				endRounded = endRound + 1;
			}
//				List<Users> users = userService.findAll();
//				// model.addAttribute("roomtype", roomtype);
//				// int counta = roomtype.size();
//				model.addAttribute("users", users);

		// model.addAttribute("count", count);

		int start = Integer.parseInt(plast);
		// int last = start - 1;
		if (start == 1) {
			List<Services> items = serviceService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("services", items);
			model.addAttribute("last", null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<Services> items = serviceService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("services", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		model.addAttribute("endRounded", endRounded);
		return "admin/Services/index";
	}

	@RequestMapping("/services/npage={next}")
	public String serviceAdminNext(Model model, @PathVariable("next") String pnext) {

		List<Services> services = serviceService.findAll();
		int SOLuongTrongTrang = 10;
		int count = services.size();
		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		
		
		int start = Integer.parseInt(pnext);
		if (start == endRounded) {
			List<Services> items = serviceService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("services", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", null);
		} else {
			List<Services> items = serviceService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("services", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			
		}
		model.addAttribute("endRounded", (int)endRounded);
		return "admin/Services/index";
	}
 	
 	
//    @GetMapping
//    public String listServices(Model model) {
//        model.addAttribute("sv", serviceService.findAll());
//        return "Roles/index";
//    }

    @GetMapping("/services/{id}")
    public String viewServices(@PathVariable("id") Integer id, Model model) {
    	Services services = serviceService.findById(id);
        model.addAttribute("services", services);
        return "admin/Services/form";
    }

    @PostMapping("/services/create")
    public String createServices(@ModelAttribute Services services) {
    	serviceService.create(services);
        return "redirect:/Services/form";
    }

//    @PostMapping("/update")
//    public ModelAndView updateUser(@Validated @ModelAttribute("user") Users user) {
//        userService.update(user);
//        return new ModelAndView("redirect:/users/index");
//    }
//
//    @GetMapping("/delete/{cmt}")
//    public ModelAndView deleteUser(@PathVariable String cmt) {
//        userService.delete(cmt);
//        return new ModelAndView("redirect:/users/index");
//    }
    
    @PostMapping("/services/update")
    public ModelAndView updateService(@ModelAttribute Services services) {
        if (services.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
        	serviceService.update(services);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	serviceService.create(services);
        }
        return new ModelAndView("redirect:/services/index");
    }

    @GetMapping("/services/delete/{id}")
    public ModelAndView deleteService(@PathVariable("id") Integer id) {
    	serviceService.delete(id);
        return new ModelAndView("redirect:/services/index");
    }
}
