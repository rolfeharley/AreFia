package com.arefia.lamm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arefia.lamm.dao.sysinfoDao;

@RestController
public class zohoCallbackController {
    @Autowired
	sysinfoDao sido;
	
    @RequestMapping(value = "/zohocallback", method = RequestMethod.GET)
    public void zohogetcallback(HttpServletRequest request) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();	
        
    	if (request.getParameter("code") == null || request.getParameter("code").toString().equals("")) {
    		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	} else {
    		response.setStatus(HttpServletResponse.SC_OK);
    	}
	}
}
