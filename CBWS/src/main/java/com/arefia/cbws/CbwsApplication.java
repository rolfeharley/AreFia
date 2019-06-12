package com.arefia.cbws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arefia.cbws.service.orderDataHandler;
import com.arefia.cbws.tools.hmacCodeHandler;

@SpringBootApplication
public class CbwsApplication {
	private static final Logger log = LogManager.getLogger(CbwsApplication.class);
	
	@Autowired
	orderDataHandler odh;
	
	@Autowired
	hmacCodeHandler hch;
	
	public static void main(String[] args) {
		SpringApplication.run(CbwsApplication.class, args);
	}
}
