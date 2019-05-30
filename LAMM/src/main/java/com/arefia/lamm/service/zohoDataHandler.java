package com.arefia.lamm.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arefia.lamm.bean.webCommunication;
import com.arefia.lamm.controller.managementController;
import com.arefia.lamm.model.webCommunicationModel;

@Service
public class zohoDataHandler {
	private static final Logger log = LogManager.getLogger(zohoDataHandler.class);
	
    @Autowired
    zohoAuthService auts;
    
    @Autowired
	webCommunication wcc;
    
    public String getAllRecord(String scope) {
    	try {
    		String acctoken = auts.getIniAuthCode();
        	webCommunicationModel zohomod = new webCommunicationModel();
        	HashMap<String, String> zohohead = new HashMap<String, String>();
        	zohohead.put("Authorization", "Zoho-oauthtoken  " + acctoken);
        	
        	zohomod.setConnURL("https://www.zohoapis.com/crm/v2/" + scope);
        	zohomod.setHeaders(zohohead);
        	
        	wcc.comWithGet(zohomod);
        	
            InputStream recsres = wcc.getIns;
    	    
    	    String datastr = new BufferedReader(new InputStreamReader(recsres, "UTF-8")).lines().collect(Collectors.joining(System.lineSeparator()));
    	    
        	return datastr;
    	} catch(Exception e) {
    		log.error(e.getLocalizedMessage(), e);
    		return null;
    	}
    }
}
