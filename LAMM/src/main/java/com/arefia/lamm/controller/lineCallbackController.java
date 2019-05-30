package com.arefia.lamm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arefia.lamm.dao.sysinfoDao;
import com.arefia.lamm.entity.sysinfoEntity;
import com.arefia.lamm.service.messageRecieve;
import com.arefia.lamm.utility.getHashSecretToken;

@RestController
public class lineCallbackController {	
	private static final Logger log = LogManager.getLogger(lineCallbackController.class);
	
	@Autowired
	sysinfoDao sido;
	
	@Autowired
	messageRecieve mrec;
		
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public void callback(HttpServletRequest request, @RequestBody String message) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        sysinfoEntity sysiey = sido.findAll().get(0);
        
    	try {
    		getHashSecretToken cmphash = new getHashSecretToken(sysiey.getChannel_secret(), message);
        	
    	    if (cmphash.getResultCode().equals(request.getHeader("X-Line-Signature"))) {  		    
    	    	mrec.recieve(message);
    	    	
    		    response.setStatus(HttpServletResponse.SC_OK);
    	    } else {
    	    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	    }
    	} catch(Exception ex) {
    		log.error(ex.getLocalizedMessage(), ex);
    		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	}
    }
}