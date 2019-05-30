package com.arefia.lamm.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arefia.lamm.service.zohoAuthService;
import com.arefia.lamm.service.zohoDataHandler;
import com.arefia.lamm.utility.getHeaderInformation;

@Controller
public class managementController {
	private static final Logger log = LogManager.getLogger(managementController.class);
	
	@Autowired
	getHeaderInformation hinf;
	
	@Autowired
	zohoDataHandler zohodh;

	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String createMainPage(Model model, HttpSession session) {
		JSONObject dataobj = new JSONObject();
		JSONArray detsarr = new JSONArray();
		
		JSONObject itemobj = new JSONObject();
		
		itemobj.put("field9", "S");
		
		detsarr.put(itemobj);
		
		dataobj.put("data", detsarr);
		
		String tt = zohodh.updateRecord("Contacts", "3410123000001387008", dataobj);
		
        log.info("----------------------------------------------------------------" + tt);
		
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
