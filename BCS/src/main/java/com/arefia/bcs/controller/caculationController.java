package com.arefia.bcs.controller;

import java.time.YearMonth;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arefia.bcs.dao.agentDao;

@Controller
public class caculationController {
	@Autowired
	agentDao agentdao;
	
	@RequestMapping(value = "/calbonus", method = RequestMethod.GET)
	@ResponseBody
	public String getBonusAmount(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String accountid;
		String datestr = request.getParameter("datestr");
		String yearstr = datestr.substring(0, datestr.indexOf("/"));
		String monstr = datestr.substring(datestr.indexOf("/") + 1);
		String montmp, monsta, monend;
		
		if (request.getParameter("accountid") == null) {
			accountid = auth.getName();
		} else {
			accountid = request.getParameter("accountid");
		}
		
		if (monstr.startsWith("0")) {
			montmp = monstr.replace("0", "");
		} else {
			montmp = monstr;
		}

		YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(yearstr), Integer.parseInt(montmp));
		int daysInMonth = yearMonthObject.lengthOfMonth();
		
		monsta = yearstr + "/" + monstr + "/01";
		monend = yearstr + "/" + monstr + "/" + String.valueOf(daysInMonth);
		
		List<Object[]> bonusList = agentdao.calBonus(accountid, monsta, monend);
		
		if (bonusList.size() > 0) {
			JSONArray bonusArr = new JSONArray();
			
			for (Object[] bonus: bonusList) {
				JSONObject bonusObj = new JSONObject();
				
				bonusObj.put("ACCOUNTID", bonus[0]);
				bonusObj.put("USERNAME", bonus[1]);
				bonusObj.put("IMTYPE", bonus[2]);
				bonusObj.put("CUSTLEVEL", bonus[3]);
				bonusObj.put("NUMS", bonus[4]);
				bonusObj.put("BONUS", bonus[5]);
				
				bonusArr.put(bonusObj);
			}
			
			return bonusArr.toString();
		} else {
			return null;
		}
	}
}
