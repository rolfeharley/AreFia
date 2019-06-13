package com.arefia.cbws.tools;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

public class hmacCodeHandler {
	private static final Logger log = LogManager.getLogger(hmacCodeHandler.class);
	
    public static String hmacEncoder(String secret, String oristr) {
    	return "OK";
//    	try {
//    		log.info("ORI:---------------------------------------------\n" + oristr);
//        	byte[] secsb = secret.getBytes("UTF-8");
//        	byte[] orisb = oristr.getBytes("UTF-8");
//    		Mac hmac = Mac.getInstance("HmacSHA256");
//        	SecretKeySpec hskey = new SecretKeySpec(secsb, "HmacSHA256");
//            hmac.init(hskey);
//            String hencstr = Base64.encodeBase64String(hmac.doFinal(orisb));
//            log.info("RES:---------------------------------------------\n" + hencstr);
//        	return hencstr;
//    	} catch(Exception e) {
//    		log.error(e.getLocalizedMessage(), e);
//        	return null;
//    	}
    }
}
