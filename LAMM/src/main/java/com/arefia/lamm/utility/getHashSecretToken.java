package com.arefia.lamm.utility;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class getHashSecretToken {
	private static final Logger log = LogManager.getLogger(getHashSecretToken.class);
	private String hashSecret;
	
	public getHashSecretToken(String seccode, String message) {
		try {
    	    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
    	    SecretKeySpec secret_key = new SecretKeySpec(seccode.getBytes(), "HmacSHA256");
    	    sha256_HMAC.init(secret_key);

    	    hashSecret = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes("UTF-8")));
    	} catch(Exception ex) {
    		log.error(ex.getLocalizedMessage(), ex);
    	}
	}
	
	public String getResultCode() {
		return this.hashSecret;
	}
}
