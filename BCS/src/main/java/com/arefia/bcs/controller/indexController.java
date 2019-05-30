package com.arefia.bcs.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arefia.bcs.dao.menuDao;
import com.arefia.bcs.dao.userDao;
import com.arefia.bcs.entity.menuEntity;
import com.arefia.bcs.entity.userEntity;

@Controller
public class indexController {
	@Autowired
	menuDao menudao;
	
	@Autowired
	userDao userdao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userEntity nameInfo = userdao.findUserNameById(auth.getName());
		List<menuEntity> menuList = menudao.findMenuAuthed(auth.getName());
		Iterator<menuEntity> menuIt = menuList.iterator();
		StringBuilder menuBd = new StringBuilder();
		int menuCnt = 0;
		
		while (menuIt.hasNext()) {
			menuEntity menuItem = menuIt.next();
			
			if (menuCnt == 0 || menuCnt % 6 == 0) {
				if (menuCnt > 0) {
					menuBd.append("</div>");
				}
				menuBd.append("<div class=\"row\">");
			}
			menuBd.append("<div class=\"col-4 col-sm-4 col-md-4 col-lg-2 col-xl-2\">");
			menuBd.append("<img src=\"media/" + menuItem.getAppicon() + "\" onclick=\"redirectPage('" + menuItem.getAppurl() + "')\" />");
			menuBd.append("</div>");
			
			menuCnt++;
		}
		
		if (menuList.size() % 6 != 0) {
			for (int b = 0; b < 6 - (menuList.size() % 6); b++) {
				menuBd.append("<div class=\"col-4 col-sm-4 col-md-4 col-lg-2 col-xl-2\">");
				menuBd.append("</div>");
			}
			menuBd.append("</div>");
		}
		
		model.addAttribute("username", nameInfo.getUsername());
		model.addAttribute("menustring", menuBd.toString());
		model.addAttribute("runout", false);
		model.addAttribute("pageid", "1baf92e9-0a42-493e-8b62-afc5bae27014");
		
		return "/home/index";
    }
}
