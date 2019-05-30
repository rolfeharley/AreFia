package com.arefia.lamm.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
public class zohoAuthService {
	private static final Logger log = LogManager.getLogger(zohoAuthService.class);
	
	@Autowired
    sysinfoDao sido;
	
	@Autowired
	webCommunication wcc;
	
    public String getIniAuthCode() {
    	try {
    		sysinfoEntity zohoent = sido.findAll().get(0);
        	String clientid = zohoent.getZoho_clientid();
        	String secretid = zohoent.getZoho_secret();
        	String refreshid = zohoent.getZoho_refresh_code();
        	webCommunicationModel zohoWebMod = new webCommunicationModel();    	
        	StringBuilder parms = new StringBuilder();
        	
        	parms.append("refresh_token=");
        	parms.append(refreshid);
        	parms.append("&client_id=");
        	parms.append(clientid);
        	parms.append("&client_secret=");
        	parms.append(secretid);
        	parms.append("&grant_type=refresh_token");
        	
        	zohoWebMod.setConnURL("https://accounts.zoho.com/oauth/v2/token");
        	zohoWebMod.setUrlparms(parms.toString());
        	
        	wcc.getWithPost(zohoWebMod);
    	    
    	    InputStream authres = wcc.gpsIns;
    	    
    	    String authstr = new BufferedReader(new InputStreamReader(authres, "UTF-8")).lines().collect(Collectors.joining(System.lineSeparator()));
    	    
    	    JSONObject authObj = new JSONObject(authstr);
    	    
    	    wcc.disconnectMethod();
    	    
    	    return authObj.getString("access_token");
    	} catch (Exception e) {
    		log.error(e.getLocalizedMessage(), e);
    		return null;
    	}
    }
}
