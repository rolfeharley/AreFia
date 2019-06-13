package com.arefia.cbws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.arefia.cbws.service.orderDataHandler;
import com.arefia.cbws.tools.hmacCodeHandler;

public class cbwsCheckInPoint {
	private static final Logger log = LogManager.getLogger(cbwsCheckInPoint.class);

    public void testGetOrder() {
    	log.info("ORI:---------------------------------------------\n" + hmacCodeHandler.hmacEncoder("apidemo", "Hello"));
    }
}
