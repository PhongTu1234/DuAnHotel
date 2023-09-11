package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProductController {

	@RequestMapping("/product/detail")
	public String detail() {
		return "product/single-product-variable";
	}
}
