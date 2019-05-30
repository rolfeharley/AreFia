package com.arefia.bcs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arefia.bcs.dao.userDao;
import com.arefia.bcs.entity.userEntity;

@Controller
public class agentController {
	@Autowired
	userDao userdao;
	
	@RequestMapping(value = "/agentoperation", method = RequestMethod.GET)
	public String createMainPage(Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userEntity nameInfo = userdao.findUserNameById(auth.getName());
		
		model.addAttribute("username", nameInfo.getUsername());
		model.addAttribute("runout", false);
		model.addAttribute("pageid", "213b6eae-cc33-4d60-8536-f8afd07889ac");
		
		return "/function/agentoperation";
	}
}
