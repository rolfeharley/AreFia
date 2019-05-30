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
public class userInformationController {
	@Autowired
	getHeaderInformation hinf;
	
	@RequestMapping(value = "/usereditor", method = RequestMethod.GET)
	public String createMainPage(Model model, HttpSession session) {
        JSONObject hiobj = hinf.getinformation(session);
		
		if (hiobj.getJSONArray("menulist").length() > 0) {
			model.addAttribute("menuinfo", hiobj.getJSONArray("menulist").toString());
			model.addAttribute("username", hiobj.getString("username"));
			model.addAttribute("pageid", "1da26404-189b-4286-8a70-6fe8265d60a4");
			
		    return "/pages/userInfoEditor";
		} else {
			return "/deny/deny";
		}
	}
}