package com.arefia.lamm.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
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
    	    
    	    wcc.disconnectMethod();
    	    
        	return datastr;
    	} catch(Exception e) {
    		log.error(e.getLocalizedMessage(), e);
    		return null;
    	}
    }
    
    public String getSpecRecord(String scope, HashMap<String, String> parms, ArrayList<String> fields) {
    	try {
    		String acctoken = auts.getIniAuthCode();
        	webCommunicationModel zohomod = new webCommunicationModel();
        	HashMap<String, String> zohohead = new HashMap<String, String>();
        	zohohead.put("Authorization", "Zoho-oauthtoken  " + acctoken);
        	StringBuilder parbd = new StringBuilder();
        	
        	parbd.append("https://www.zohoapis.com/crm/v2/");
        	parbd.append(scope);
        	parbd.append("/search?criteria=(");
        	
        	Iterator<Map.Entry<String, String>> parpint = parms.entrySet().iterator();
			
			while (parpint.hasNext()) {
				Map.Entry<String, String> parmdata = parpint.next();
				
				parbd.append("(");
				parbd.append(parmdata.getKey());
				parbd.append(":starts_with:*");
				parbd.append(parmdata.getValue());
				parbd.append(")");
				
				if (parpint.hasNext()) {
					parbd.append("and");
				}
			}
        	
			parbd.append(")");
			
			if (fields != null && fields.size() > 0) {
				parbd.append("&fields=");
				
				for (int f = 0; f < fields.size(); f++) {
					if (f > 0) {
						parbd.append(",");
					}
					
					parbd.append(fields.get(f));
				}
			}
			
        	zohomod.setConnURL(parbd.toString());
        	zohomod.setHeaders(zohohead);
        	
        	wcc.comWithGet(zohomod);
        	
            InputStream recsres = wcc.getIns;
    	    
    	    String datastr = new BufferedReader(new InputStreamReader(recsres, "UTF-8")).lines().collect(Collectors.joining(System.lineSeparator()));
    	    
    	    wcc.disconnectMethod();
    	    
        	return datastr;
    	} catch(Exception e) {
    		log.error(e.getLocalizedMessage(), e);
    		return null;
    	}
    }
    
    public String addRecord(String scope, JSONObject insertdata) {
    	try {
    		String acctoken = auts.getIniAuthCode();
        	webCommunicationModel zohomod = new webCommunicationModel();
        	HashMap<String, String> zohohead = new HashMap<String, String>();
        	zohohead.put("Authorization", "Zoho-oauthtoken  " + acctoken);
        	zohohead.put("Content-Type", "application/json");
        	zohohead.put("Accept", "application/json");
			
        	zohomod.setConnURL("https://www.zohoapis.com/crm/v2/" + scope);
        	zohomod.setHeaders(zohohead);
        	zohomod.setBodys(insertdata);
        	
        	wcc.comWithPost(zohomod);

        	String resStr = wcc.postIns;
        	
    	    wcc.disconnectMethod();
    	    
        	return resStr;
    	} catch(Exception e) {
    		log.error(e.getLocalizedMessage(), e);
    		return null;
    	}
    }
    
    public String updateRecord(String scope, String recordid, JSONObject updatedata) {
    	try {
    		String acctoken = auts.getIniAuthCode();
        	webCommunicationModel zohomod = new webCommunicationModel();
        	HashMap<String, String> zohohead = new HashMap<String, String>();
        	zohohead.put("Authorization", "Zoho-oauthtoken  " + acctoken);
        	zohohead.put("Content-Type", "application/json");
        	zohohead.put("Accept", "application/json");
			
        	zohomod.setConnURL("https://www.zohoapis.com/crm/v2/" + scope + "/" + recordid);
        	zohomod.setHeaders(zohohead);
        	zohomod.setBodys(updatedata);
        	
        	wcc.comWithUpd(zohomod);

        	String resStr = wcc.updIns;
        	
    	    wcc.disconnectMethod();
    	    
        	return resStr;
    	} catch(Exception e) {
    		log.error(e.getLocalizedMessage(), e);
    		return null;
    	}
    }
    
    public String delRecord(String scope, String recordid) {
    	try {
    		String acctoken = auts.getIniAuthCode();
        	webCommunicationModel zohomod = new webCommunicationModel();
        	HashMap<String, String> zohohead = new HashMap<String, String>();
        	zohohead.put("Authorization", "Zoho-oauthtoken  " + acctoken);
        	
        	zohomod.setConnURL("https://www.zohoapis.com/crm/v2/" + scope + "/" + recordid);
        	zohomod.setHeaders(zohohead);
        	
        	wcc.comWithDel(zohomod);
        	
            String recsres = wcc.delIns;
    	    
    	    wcc.disconnectMethod();
    	    
        	return recsres;
    	} catch(Exception e) {
    		log.error(e.getLocalizedMessage(), e);
    		return null;
    	}
    }
}
