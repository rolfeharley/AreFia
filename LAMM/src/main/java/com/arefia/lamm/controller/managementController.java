package com.arefia.lamm.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arefia.lamm.service.zohoDataHandler;
import com.arefia.lamm.utility.getHeaderInformation;

@Controller
public class managementController {
	@Autowired
	getHeaderInformation hinf;
	
	@Autowired
	zohoDataHandler zohodh;
	
	@Value("${web_cust_data.intzoho}")
    private String zohointe;

	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String createMainPage(Model model, HttpSession session) {
		JSONObject hiobj = hinf.getinformation(session);
		
		if (hiobj.getJSONArray("menulist").length() > 0) {
			model.addAttribute("menuinfo", hiobj.getJSONArray("menulist").toString());
			model.addAttribute("username", hiobj.getString("username"));
			model.addAttribute("pageid", "68da6714-c6c9-477b-8fa2-4374d1c80563");

		    return "/pages/management";
		} else {
			return "/deny/deny";
		}
	}
}
