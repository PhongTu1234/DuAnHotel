package com.poly.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Provide the path to your custom error page, e.g., "error/404"
        return "/404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
