package com.arefia.lamm.utility;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.arefia.lamm.dao.menuDao;
import com.arefia.lamm.dao.userDao;
import com.arefia.lamm.entity.menuEntity;

@Component
public class getHeaderInformation {
	@Autowired
	userDao udo;
	
	@Autowired
	menuDao mdo;
	
    public JSONObject getinformation(HttpSession session) {
    	JSONObject headerInfo = new JSONObject();
    	
    	if (session.getAttribute("arefiamenulist") == null || session.getAttribute("arefiausername") == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			List<menuEntity> menuList = mdo.findMenuAuthed(auth.getName());
			String userName = udo.findUserNameById(auth.getName());
			
			JSONArray menuArray = new JSONArray();
			
			for (menuEntity mit: menuList) {
				JSONObject menuObject = new JSONObject();
				
				menuObject.put("MenuID", mit.getAppid());
				menuObject.put("MenuName", mit.getAppname());
				menuObject.put("MenuURL", mit.getAppurl());
				menuArray.put(menuObject);
			}
			
			headerInfo.put("menulist", menuArray);
			headerInfo.put("username", userName);
			
			session.setAttribute("arefiamenulist", menuArray.toString());
			session.setAttribute("arefiausername", userName);
		} else {
			headerInfo.put("menulist", new JSONArray(session.getAttribute("arefiamenulist").toString()));
			headerInfo.put("username", session.getAttribute("arefiausername").toString());
		}
    	
    	return headerInfo;
    }
}
