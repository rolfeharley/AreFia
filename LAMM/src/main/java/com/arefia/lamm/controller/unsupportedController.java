package com.arefia.lamm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class unsupportedController {
	@RequestMapping(value = "/unsupported", method = RequestMethod.GET)
	public String denyPage() {
		return "/unsupported/unsupported";
	}
}
