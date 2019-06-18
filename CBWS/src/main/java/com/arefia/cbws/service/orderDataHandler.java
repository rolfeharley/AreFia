package com.arefia.cbws.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arefia.cbws.bean.webCommunication;
import com.arefia.cbws.model.webCommunicationModel;
import com.arefia.cbws.tools.gmtDateTimeGenerator;
import com.arefia.cbws.tools.hmacCodeHandler;

@Service
public class orderDataHandler {
	private static final Logger log = LogManager.getLogger(orderDataHandler.class);
	
	@Autowired
	gmtDateTimeGenerator gmts;
	
    @Autowired
    hmacCodeHandler hmacc;
    
    @Value("${cyberbiz.username}")
    private String cyberbizusername;
    
    @Value("${cyberbiz.secret}")
    private String cyberbizsecret;
	
    public void getOrderRecords() {
    	webCommunication wcm = new webCommunication(); 
    	webCommunicationModel ordermod = new webCommunicationModel();
    	HashMap<String, String> headmap = new HashMap<String, String>();
    	String gmtstr = gmts.getGMTString();
    	StringBuilder headauth = new StringBuilder();
    	String sig_str = "x-date: " + gmtstr + "\\nGET /v1/customers HTTP/1.1";
    	
    	headauth.append("hmac username=" + cyberbizusername);
    	headauth.append(", algorithm=\"hmac-sha256\", headers=\"x-date request-line\", signature=\"");
    	headauth.append("signature=\"" + sig_str + "\"");
    	
    	headmap.put("Accept", "application/json");
    	headmap.put("X-Date", gmtstr);
    	headmap.put("Authorization", headauth.toString());
    	
    	
    	ordermod.setConnURL("https://api.cyberbiz.co/v1/orders?page=1&per_page=50&offset=0");
    	ordermod.setHeaders(headmap);
    	ordermod.setBodys(null);
    	ordermod.setUrlparms(null);
    	
    	wcm.comWithGet(ordermod);
    	
    	String orderlist = new BufferedReader(new InputStreamReader(wcm.gpsIns)).lines().collect(Collectors.joining(System.lineSeparator()));
    
        log.info("--------------------------------------------\n" + orderlist);
    }
}
