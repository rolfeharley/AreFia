package com.arefia.cbws.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.arefia.cbws.tools.hmacCodeHandler;

@Component
public class startPointComponent implements CommandLineRunner {
	private static final Logger log = LogManager.getLogger(startPointComponent.class);
	
    @Autowired
    hmacCodeHandler hmacc;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("------------------------------------------------------------\n" + hmacc.hmacEncoder("", ""));
	}
}
