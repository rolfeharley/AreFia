package com.arefia.cbws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CbwsApplication {	
	public static void main(String[] args) {
		SpringApplication.run(CbwsApplication.class, args);
		
		cbwsCheckInPoint cbip = new cbwsCheckInPoint();
		cbip.testGetOrder();
	}
}
