package com.arefia.bcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class denyController {
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public String denyPage() {
		return "/deny/deny";
	}
}
