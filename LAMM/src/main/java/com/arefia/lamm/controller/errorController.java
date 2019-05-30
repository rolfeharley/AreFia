package com.arefia.lamm.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errorController implements ErrorController {
	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	model.addAttribute("errimg", "404icon");
	        } else {
	        	model.addAttribute("errimg", "erroricon");
	        }
	    } else {
	    	model.addAttribute("errimg", "erroricon");
	    }
	    
	    return "/error/error";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
}
