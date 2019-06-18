package com.arefia.cbws.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

@Service
public class gmtDateTimeGenerator {
	public String getGMTString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss zzz");
	 
	    Date date = new Date();
	 
	    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	      
        return sdf.format(date);	      
	}
}
