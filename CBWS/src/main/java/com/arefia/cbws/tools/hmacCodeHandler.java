package com.arefia.cbws.tools;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class hmacCodeHandler {
	private static final Logger log = LogManager.getLogger(hmacCodeHandler.class);
	
    public String hmacEncoder(String secret, String oristr) {
    	try {
        	byte[] secsb = secret.getBytes("UTF-8");
    		Mac hmac = Mac.getInstance("HmacSHA256");
        	SecretKeySpec hskey = new SecretKeySpec(secsb, "HmacSHA256");
            hmac.init(hskey);
        	byte[] orisb = oristr.getBytes("UTF-8");
        	byte[] oriHmac = hmac.doFinal(orisb);
        	String hencstr = javax.xml.bind.DatatypeConverter.printBase64Binary(oriHmac);
        	return hencstr;
    	} catch(Exception e) {
    		log.error(e.getLocalizedMessage(), e);
        	return null;
    	}
    }
}
