package com.arefia.lamm.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arefia.lamm.utility.getHeaderInformation;

@Controller
public class flexMessageController {
	@Autowired
	getHeaderInformation hinf;
	
	@RequestMapping(value = "/flexmsg", method = RequestMethod.GET)
	public String createMainPage(Model model, HttpSession session) {
        JSONObject hiobj = hinf.getinformation(session);
		
		if (hiobj.getJSONArray("menulist").length() > 0) {
			model.addAttribute("menuinfo", hiobj.getJSONArray("menulist").toString());
			model.addAttribute("username", hiobj.getString("username"));
			model.addAttribute("pageid", "3d87ed11-be2c-4286-b2bc-f3d4c0621d23");

		    return "/pages/flexMessage";
		} else {
			return "/deny/deny";
		}
	}
}
