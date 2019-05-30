package com.arefia.lamm.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arefia.lamm.utility.getClientInformation;

@Controller
public class environmentController {
	private static final Logger log = LogManager.getLogger(environmentController.class);
	
	@RequestMapping(value = "/envcheck", method = RequestMethod.GET)
	@ResponseBody
	public Boolean chcekClientEnvironment(HttpServletRequest request) {
		try {
			JSONObject browserObj = new getClientInformation().getClientBasicInformation(request);
			Boolean isSupported = true;
			double browserVersion = 0;
			
			try {
				browserVersion = Double.parseDouble(browserObj.getString("Browser_Version"));
			} catch(Exception e) {
				browserVersion = -1;
			}
			
			switch (browserObj.getString("Browser")) {
			    case "IE":
				    if (browserVersion < 10) {
				    	isSupported = false;
				    }
				    break;
			    case "Chrome":
			    	if (browserVersion < 16) {
				    	isSupported = false;
				    }
			    	break;
			    case "Safari":
			    	if (browserVersion < 7) {
				    	isSupported = false;
				    }
			    	break;
			    case "Opera":
			    	if (browserVersion < 12.1) {
				    	isSupported = false;
				    }
			    	break;
			    case "Firefox":
			    	if (browserVersion < 11) {
				    	isSupported = false;
				    }
			    	break;
			    default:
			    	isSupported = false;
			    	break;
			}
			
			return isSupported;
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return false;
		}
	}
}