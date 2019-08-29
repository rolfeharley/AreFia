package com.arefia.lamm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class newUserInfoController {
	@GetMapping("/memberinfo")
	public String createForm() {
		 return "/pages/newUserInfo";
	}
}
