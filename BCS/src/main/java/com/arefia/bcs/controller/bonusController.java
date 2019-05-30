package com.arefia.bcs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arefia.bcs.dao.agentDao;
import com.arefia.bcs.dao.userDao;
import com.arefia.bcs.entity.userEntity;

@Controller
public class bonusController {	
	@Autowired
	userDao userdao;
	
	@Autowired
	agentDao agentdao;
	
	@RequestMapping(value = "/bonusoperation", method = RequestMethod.GET)
	public String createMainPage(Model model, HttpSession session, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userEntity nameInfo = userdao.findUserNameById(auth.getName());
		JSONArray dateArr = new JSONArray();
		
		model.addAttribute("username", nameInfo.getUsername());
		
		if (request.isUserInRole("ROLE_ADMIN")) {
			String accid = "";
			List<userEntity> allUsers = userdao.findEnabledUser();
			JSONArray usersArr = new JSONArray();
			
			for (userEntity user: allUsers) {
				JSONObject userObj = new JSONObject();
				
				userObj.put("ACCOUNTID", user.getAccountid());
				userObj.put("USERNAME", user.getUsername());
				
				usersArr.put(userObj);
				
				if (accid.equals("")) {
					accid = user.getAccountid();
				}
			}
			
			List<Object[]> dateList = agentdao.getDateList(accid);
			
			for (Object[] dateItem: dateList) {
				JSONObject dateObj = new JSONObject();
				
				dateObj.put("DATESTR", dateItem[0]);
				
				dateArr.put(dateObj);
			}
			
			model.addAttribute("isadmin", "1");
			model.addAttribute("userslist", usersArr.toString());
		} else {
            List<Object[]> udateList = agentdao.getDateList(auth.getName());
			
			for (Object[] udateItem: udateList) {
				JSONObject udateObj = new JSONObject();
				
				udateObj.put("DATESTR", udateItem[0]);
				
				dateArr.put(udateObj);
			}
			
			model.addAttribute("isadmin", "0");
		}
				
		model.addAttribute("datelist", dateArr.toString());
		model.addAttribute("runout", false);
		model.addAttribute("pageid", "213b6eae-cc33-4d60-8536-f8afd07889ac");
		
		return "/function/bonusoperation";
	}
	
	@RequestMapping(value = "/bonusdatedata", method = RequestMethod.GET)
	@ResponseBody
	public String getUserBonusDate(HttpServletRequest request) {
		String accountid = request.getParameter("accountid");
		JSONArray dateArr = new JSONArray();
		List<Object[]> dateList = agentdao.getDateList(accountid);
		
		for (Object[] dateItem: dateList) {
			JSONObject dateObj = new JSONObject();
			
			dateObj.put("DATESTR", dateItem[0]);

			dateArr.put(dateObj);
		}
		
		return dateArr.toString();
	}
}
