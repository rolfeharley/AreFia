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
    	String httpMethod = "GET";
        String apiUrlBase = "https://api.cyberbiz.co";
        String apiUrlPath = "/v1/orders";
        String headers = "x-date request-line";        
        String dete = gmts.getGMTString();       
        String rline = httpMethod + " " + apiUrlPath + " HTTP/1.1";       
        String sig_str = "x-date: " + dete + "\n" + rline;        
        String signature = hmacc.hmacEncoder(cyberbizsecret, sig_str);        
        String authorization = "hmac username=\"" + cyberbizusername + "\", algorithm=\"hmac-sha256\", headers=\"" + headers + "\", signature=\"" + signature + "\"";
        
        headmap.put("Accept", "application/json");
    	headmap.put("Authorization", authorization);
    	headmap.put("X-Date", dete);
    	
    	ordermod.setConnURL(apiUrlBase + apiUrlPath + "?page=1&per_page=50&offset=0");
    	ordermod.setHeaders(headmap);
    	
    	wcm.comWithGet(ordermod);
    	
    	String orderlist = new BufferedReader(new InputStreamReader(wcm.getIns)).lines().collect(Collectors.joining(System.lineSeparator()));
    	
    	log.info("--------------------------------------------\n" + orderlist);
    }
}
