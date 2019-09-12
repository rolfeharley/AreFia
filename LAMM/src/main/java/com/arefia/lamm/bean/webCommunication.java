package com.arefia.lamm.bean;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
	public HttpsURLConnection urlsCon;
	public String postIns;
	public String delIns;
	public String updIns;
	
	public void comWithGet (webCommunicationModel gwco) {
		try {
			URL getUrl = new URL(gwco.getConnURL());
			urlsCon = (HttpsURLConnection) getUrl.openConnection();
			
			urlsCon.setRequestMethod("GET");
			
			urlsCon.setRequestProperty("Accept-Charset", "utf-8");
			
			if (gwco.getHeaders() != null) {
				Iterator<Map.Entry<String, String>> ghi = gwco.getHeaders().entrySet().iterator();
				
				while (ghi.hasNext()) {
					Map.Entry<String, String> gethead = ghi.next();
					
					urlsCon.setRequestProperty(gethead.getKey(), gethead.getValue());
				}
			}
			
			urlsCon.connect();
			
			if (urlsCon.getResponseCode() >= 200 && urlsCon.getResponseCode() < 300) {
				getIns = urlsCon.getInputStream();
			} else {
				getIns = null;
			}
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
			getIns = null;
		}
	}
	
    public void comWithPost (webCommunicationModel pwco) {
    	try {
			URL postUrl = new URL(pwco.getConnURL());
			urlsCon = (HttpsURLConnection) postUrl.openConnection();
			
			urlsCon.setRequestMethod("POST");		
			
			urlsCon.setRequestProperty("Accept-Charset", "utf-8");
			
			if (pwco.getHeaders() != null) {
				Iterator<Map.Entry<String, String>> phi = pwco.getHeaders().entrySet().iterator();
				
				while (phi.hasNext()) {
					Map.Entry<String, String> posthead = phi.next();
					
					urlsCon.setRequestProperty(posthead.getKey(), posthead.getValue());
				}
			}
			
			urlsCon.setDoInput(true);
			urlsCon.setDoOutput(true);
	    	DataOutputStream reqWr = new DataOutputStream(urlsCon.getOutputStream());
	    	
	    	if (pwco.getBodys() != null) {
		        reqWr.write(pwco.getBodys().toString().getBytes("UTF-8"));
	    	}
	    	
	    	if (pwco.getUrlparms() != null) {
	    		reqWr.write(pwco.getUrlparms().getBytes("UTF-8"));
	    	}
	    	
		    reqWr.close();
			
		    urlsCon.connect();
			
			if (urlsCon.getResponseCode() >= 200 && urlsCon.getResponseCode() < 300) {
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
			urlsCon = (HttpsURLConnection) gpsUrl.openConnection();
			
			urlsCon.setRequestMethod("POST");		
			
			urlsCon.setRequestProperty("Accept-Charset", "utf-8");
			
			if (swco.getHeaders() != null) {
				Iterator<Map.Entry<String, String>> shi = swco.getHeaders().entrySet().iterator();
				
				while (shi.hasNext()) {
					Map.Entry<String, String> gpshead = shi.next();
					
					urlsCon.setRequestProperty(gpshead.getKey(), gpshead.getValue());
				}
			}
			
			urlsCon.setDoInput(true);
			urlsCon.setDoOutput(true);
	    	DataOutputStream reqWr = new DataOutputStream(urlsCon.getOutputStream());
	    	
	    	if (swco.getBodys() != null) {
		        reqWr.write(swco.getBodys().toString().getBytes("UTF-8"));
	    	}
	    	
	    	if (swco.getUrlparms() != null) {
	    		reqWr.write(swco.getUrlparms().getBytes("UTF-8"));
	    	}
	    	
		    reqWr.close();
			
		    urlsCon.connect();
			
			if (urlsCon.getResponseCode() >= 200 && urlsCon.getResponseCode() < 300) {
				gpsIns = urlsCon.getInputStream();
			} else {
				gpsIns = null;
			}
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
			gpsIns = null;
		}
	}
    
    public void comWithDel (webCommunicationModel dwco) {
		try {
			URL delUrl = new URL(dwco.getConnURL());
			urlsCon = (HttpsURLConnection) delUrl.openConnection();
			
			urlsCon.setRequestMethod("DELETE");
			
			urlsCon.setRequestProperty("Accept-Charset", "utf-8");
			
			if (dwco.getHeaders() != null) {
				Iterator<Map.Entry<String, String>> dhi = dwco.getHeaders().entrySet().iterator();
				
				while (dhi.hasNext()) {
					Map.Entry<String, String> delhead = dhi.next();
					
					urlsCon.setRequestProperty(delhead.getKey(), delhead.getValue());
				}
			}
			
			urlsCon.connect();
			
			if (urlsCon.getResponseCode() >= 200 && urlsCon.getResponseCode() < 300) {
				delIns = "OK";
			} else {
				delIns = "NG";
			}
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
			delIns = "NG";
		}
	}
    
    public void comWithUpd (webCommunicationModel uwco) {
		try {
			URL updUrl = new URL(uwco.getConnURL());
			urlsCon = (HttpsURLConnection) updUrl.openConnection();
			
			urlsCon.setRequestMethod("PUT");			

			urlsCon.setRequestProperty("Accept-Charset", "utf-8");
			
			if (uwco.getHeaders() != null) {
				Iterator<Map.Entry<String, String>> uhi = uwco.getHeaders().entrySet().iterator();
				
				while (uhi.hasNext()) {
					Map.Entry<String, String> updhead = uhi.next();
					
					urlsCon.setRequestProperty(updhead.getKey(), updhead.getValue());
				}
			}
			
			urlsCon.setDoInput(true);
			urlsCon.setDoOutput(true);
	    	DataOutputStream reqWr = new DataOutputStream(urlsCon.getOutputStream());
	    	
	    	if (uwco.getBodys() != null) {
		        reqWr.write(uwco.getBodys().toString().getBytes("UTF-8"));
	    	}
	    	
	    	if (uwco.getUrlparms() != null) {
	    		reqWr.write(uwco.getUrlparms().getBytes("UTF-8"));
	    	}
	    	
		    reqWr.close();
			
			urlsCon.connect();
			
			if (urlsCon.getResponseCode() >= 200 && urlsCon.getResponseCode() < 300) {
				updIns = "OK";
			} else {
				updIns = "NG";
			}
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
			updIns = "NG";
		}
	}
    
    public void disconnectMethod() {
		urlsCon.disconnect();
    }
}
