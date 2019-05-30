package com.arefia.lamm.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arefia.lamm.bean.webCommunication;
import com.arefia.lamm.dao.sysinfoDao;
import com.arefia.lamm.entity.sysinfoEntity;
import com.arefia.lamm.model.webCommunicationModel;

@Service
public class followerInfo {
	private static final Logger log = LogManager.getLogger(followerInfo.class);
	
	@Autowired
	sysinfoDao sido;
	
	@Autowired
	webCommunication wcc;
	
	public JSONObject getFollowerInfo(String followerid) {
		try {
			sysinfoEntity sysiey = sido.findAll().get(0);
			
			HashMap<String, String> repHead = new HashMap<String, String>();
		    repHead.put("Content-Type", "application/json");
		    repHead.put("Authorization", "Bearer  " + sysiey.getChannel_token());
		    
		    webCommunicationModel repObj = new webCommunicationModel();
		    
		    repObj.setConnURL("https://api.line.me/v2/bot/profile/" + followerid);
		    repObj.setHeaders(repHead);
		    
		    wcc.comWithGet(repObj);
		    
		    InputStream finfInp = wcc.getIns;
		    
		    String finfStr = new BufferedReader(new InputStreamReader(finfInp, "UTF-8")).lines().collect(Collectors.joining(System.lineSeparator()));
		    return new JSONObject(finfStr);
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
			return null;
		} finally {
			wcc.disconnectMethod();
		}
	}
}
