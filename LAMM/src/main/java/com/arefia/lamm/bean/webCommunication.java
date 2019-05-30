package com.arefia.lamm.bean;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.arefia.lamm.model.webCommunicationModel;

@Component
public class webCommunication {
	private static final Logger log = LogManager.getLogger(webCommunication.class);
	public InputStream getIns;
	public InputStream gpsIns;
	public HttpsURLConnection urlCon;
	public String postIns;
	
	public void comWithGet (webCommunicationModel gwco) {
		try {
			URL getUrl = new URL(gwco.getConnURL());
			urlCon = (HttpsURLConnection) getUrl.openConnection();
			
			urlCon.setRequestMethod("GET");
			
			if (gwco.getHeaders() != null) {
				Iterator<Map.Entry<String, String>> ghi = gwco.getHeaders().entrySet().iterator();
				
				while (ghi.hasNext()) {
					Map.Entry<String, String> gethead = ghi.next();
					
					urlCon.setRequestProperty(gethead.getKey(), gethead.getValue());
				}
			}
			
			urlCon.connect();
			
			if (urlCon.getResponseCode() == 200) {
				getIns = urlCon.getInputStream();
			} else {
				getIns = null;
			}
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
		}
	}
	
    public void comWithPost (webCommunicationModel pwco) {
    	try {
			URL postUrl = new URL(pwco.getConnURL());
			urlCon = (HttpsURLConnection) postUrl.openConnection();
			
			urlCon.setRequestMethod("POST");		
			
			if (pwco.getHeaders() != null) {
				Iterator<Map.Entry<String, String>> phi = pwco.getHeaders().entrySet().iterator();
				
				while (phi.hasNext()) {
					Map.Entry<String, String> posthead = phi.next();
					
					urlCon.setRequestProperty(posthead.getKey(), posthead.getValue());
				}
			}
			
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
	    	DataOutputStream reqWr = new DataOutputStream(urlCon.getOutputStream());
	    	
	    	if (pwco.getBodys() != null) {
		        reqWr.write(pwco.getBodys().toString().getBytes("UTF-8"));
	    	}
	    	
	    	if (pwco.getUrlparms() != null) {
	    		reqWr.write(pwco.getUrlparms().getBytes("UTF-8"));
	    	}
	    	
		    reqWr.close();
			
		    urlCon.connect();
			
			if (urlCon.getResponseCode() == 200) {
				postIns = "OK";
			} else {
				postIns= "NG";
			}
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
			postIns= "NG";
		}
	}
    
    public void getWithPost (webCommunicationModel swco) {
    	try {
			URL gpsUrl = new URL(swco.getConnURL());
			urlCon = (HttpsURLConnection) gpsUrl.openConnection();
			
			urlCon.setRequestMethod("POST");		
			
			if (swco.getHeaders() != null) {
				Iterator<Map.Entry<String, String>> shi = swco.getHeaders().entrySet().iterator();
				
				while (shi.hasNext()) {
					Map.Entry<String, String> gpshead = shi.next();
					
					urlCon.setRequestProperty(gpshead.getKey(), gpshead.getValue());
				}
			}
			
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
	    	DataOutputStream reqWr = new DataOutputStream(urlCon.getOutputStream());
	    	
	    	if (swco.getBodys() != null) {
		        reqWr.write(swco.getBodys().toString().getBytes("UTF-8"));
	    	}
	    	
	    	if (swco.getUrlparms() != null) {
	    		reqWr.write(swco.getUrlparms().getBytes("UTF-8"));
	    	}
	    	
		    reqWr.close();
			
		    urlCon.connect();
			
			if (urlCon.getResponseCode() == 200) {
				gpsIns = urlCon.getInputStream();
			} else {
				gpsIns = null;
			}
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
			gpsIns = null;
		}
	}
    
    public void disconnectMethod() {
		urlCon.disconnect();
    }
}
