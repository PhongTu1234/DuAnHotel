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

import com.poly.Service.BlogsService;
import com.poly.Service.ImagesService;
import com.poly.entity.Blogs;
import com.poly.entity.Booking_Room;
import com.poly.entity.Images;

@Controller
public class BlogController {

	@Autowired
    private BlogsService blogsService;
	
	
	@RequestMapping("blog")
	public String blog() {
		return "blog-listview";
	}

	@RequestMapping("/blog/detail")
	public String detail() {
		return "blog-detail";
	}

	
	//xu ly admin
 	@GetMapping("/blogs/form")
 	public String formBlog(Model model) {
 		model.addAttribute("blogs", new Blogs());
 		return "admin/Blog/form";
 	}

 	@GetMapping("/blogs/index")
    public String showBlogsIndex(Model model) {
// 		model.addAttribute("blogs", blogsService.findAll());
 		List<Blogs> blog = blogsService.findAll();

// 		model.addAttribute("hotels", hService.findAll());
 		int SOLuongTrongTrang = 10;
 		int count = blog.size();
 		int start = 1;
 		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		 
		List<Blogs> blogs = blogsService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
		model.addAttribute("blogs", blogs);
		model.addAttribute("last", null);
		model.addAttribute("start", start);
		model.addAttribute("next", start + 1);
		model.addAttribute("endRounded",endRounded);
        return "admin/Blog/index";
    }
 
 	@RequestMapping("/blogs/lpage={last}")
	public String blogAdminLast(Model model, @PathVariable("last") String plast) {
		List<Blogs> blogs = blogsService.findAll();
		int SOLuongTrongTrang = 10;
//		 model.addAttribute("users", userService.findAll());
		 int count = blogs.size();
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
			List<Blogs> items = blogsService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("blogs", items);
			model.addAttribute("last", null);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		} else {
			List<Blogs> items = blogsService.findPageAdmin((start) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("blogs", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
		}
		model.addAttribute("endRounded", endRounded);
		return "admin/Blog/index";
	}

	@RequestMapping("/blogs/npage={next}")
	public String blogroomAdminNext(Model model, @PathVariable("next") String pnext) {

		List<Blogs> blogs = blogsService.findAll();
		int SOLuongTrongTrang = 10;
		int count = blogs.size();
		int endRound = (int) Math.ceil(count / SOLuongTrongTrang);
		int endRounded = endRound;
		if((endRound * SOLuongTrongTrang) < count ) {
			endRounded = endRound + 1;
		}
		
		
		int start = Integer.parseInt(pnext);
		if (start == endRounded) {
			List<Blogs> items = blogsService.findPageAdmin((start - 1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("blogs", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", null);
		} else {
			List<Blogs> items = blogsService.findPageAdmin((start-1) * SOLuongTrongTrang, SOLuongTrongTrang);
			model.addAttribute("blogs", items);
			model.addAttribute("last", start - 1);
			model.addAttribute("start", start);
			model.addAttribute("next", start + 1);
			
		}
		model.addAttribute("endRounded", (int)endRounded);
		return "admin/Blog/index";
	}
 	
 	
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/blogs/{id}")
    public String viewBlog(@PathVariable("id") Integer id, Model model) {
    	Blogs blogs = blogsService.findById(id);
        model.addAttribute("blogs", blogs);
        return "admin/Blog/form";
    }

    @PostMapping("/blogs/create")
    public String createBlog(@ModelAttribute Blogs blogs) {
    	blogsService.create(blogs);
        return "redirect:/blogs/form";
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
    
    @PostMapping("/blogs/update")
    public ModelAndView updateBlog(@ModelAttribute Blogs blogs) {
        if (blogs.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
        	blogsService.update(blogs);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	blogsService.create(blogs);
        }
        return new ModelAndView("redirect:/blogs/index");
    }

    @GetMapping("/blogs/delete/{id}")
    public ModelAndView deleteBlog(@PathVariable("id") Integer id) {
    	blogsService.delete(id);
        return new ModelAndView("redirect:/blogs/index");
    }
}
