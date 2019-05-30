package com.arefia.lamm.utility;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class getClientInformation {
    public JSONObject getClientBasicInformation(HttpServletRequest request) {
    	String agentInfo = request.getHeader("User-Agent").toLowerCase();
    	JSONObject clientObject = new JSONObject();

    	if (agentInfo.indexOf("windows") >= 0 ) {
    		clientObject.put("OS", "Windows");
        } else if (agentInfo.indexOf("mac") >= 0) {
        	clientObject.put("OS", "Mac");
        } else if (agentInfo.indexOf("x11") >= 0) {
        	clientObject.put("OS", "Unix");
        } else if (agentInfo.indexOf("android") >= 0) {
        	clientObject.put("OS", "Android");
        } else if (agentInfo.indexOf("iphone") >= 0) {
        	clientObject.put("OS", "iPhone");
        } else {
        	clientObject.put("OS", "UnKnown");
        }
    	
    	if (agentInfo.contains("msie")) {
    		String ieVerStr = agentInfo.substring(agentInfo.indexOf("msie") + 5);
    		
    		ieVerStr = ieVerStr.substring(0, ieVerStr.indexOf(";"));
    		
    		if (ieVerStr.contains(".")) {
    			String ieIntStr = ieVerStr.substring(0, ieVerStr.indexOf("."));
    			String ieFloatStr = ieVerStr.substring(ieVerStr.indexOf(".") + 1);
    			
    			if (ieFloatStr.contains(".")) {
    				ieFloatStr = ieFloatStr.substring(0, ieFloatStr.indexOf("."));
    			}
    			
    			ieVerStr = ieIntStr + "." + ieFloatStr;
    		}
    		
    		clientObject.put("Browser", "IE");
    		clientObject.put("Browser_Version", ieVerStr);
        } else if (agentInfo.contains("safari") && agentInfo.contains("version")) {
        	String safariVerStr = agentInfo.substring(agentInfo.indexOf("version") + 8);
        	
        	if (safariVerStr.indexOf(" ") > -1) {
        		safariVerStr = safariVerStr.substring(0, safariVerStr.indexOf(" "));
        	}
        	
        	if (safariVerStr.contains(".")) {
    			String safariIntStr = safariVerStr.substring(0, safariVerStr.indexOf("."));
    			String safariFloatStr = safariVerStr.substring(safariVerStr.indexOf(".") + 1);
    			
    			if (safariFloatStr.contains(".")) {
    				safariFloatStr = safariFloatStr.substring(0, safariFloatStr.indexOf("."));
    			}
    			
    			safariVerStr = safariIntStr + "." + safariFloatStr;
    		}
        	
        	clientObject.put("Browser", "Safari");
    		clientObject.put("Browser_Version", safariVerStr);
        } else if ( agentInfo.contains("opr") || agentInfo.contains("opera")) {
        	String operaVerStr = "";
        	
        	clientObject.put("Browser", "Opera");
        	
            if(agentInfo.contains("opera")) {
            	operaVerStr = agentInfo.substring(agentInfo.indexOf("opera") + 6);
            	
            	if (operaVerStr.indexOf(" ") > -1) {
            	    operaVerStr = operaVerStr.substring(0, operaVerStr.indexOf(" "));
            	}
            } else if(agentInfo.contains("opr")) {
                operaVerStr = agentInfo.substring(agentInfo.indexOf("opr") + 4);
            	
            	if (operaVerStr.indexOf(" ") > -1) {
            	    operaVerStr = operaVerStr.substring(0, operaVerStr.indexOf(" "));
            	}
            }
            
            if (operaVerStr.contains(".")) {
    			String operaIntStr = operaVerStr.substring(0, operaVerStr.indexOf("."));
    			String operaFloatStr = operaVerStr.substring(operaVerStr.indexOf(".") + 1);
    			
    			if (operaFloatStr.contains(".")) {
    				operaFloatStr = operaFloatStr.substring(0, operaFloatStr.indexOf("."));
    			}
    			
    			operaVerStr = operaIntStr + "." + operaFloatStr;
    		}
            
            clientObject.put("Browser_Version", operaVerStr);
        } else if (agentInfo.contains("chrome")) {
        	String chromeVerStr = agentInfo.substring(agentInfo.indexOf("chrome") + 7);
        	
        	if (chromeVerStr.indexOf(" ") > -1) {
        		chromeVerStr = chromeVerStr.substring(0, chromeVerStr.indexOf(" "));
        	}
        	
        	if (chromeVerStr.contains(".")) {
    			String chromeIntStr = chromeVerStr.substring(0, chromeVerStr.indexOf("."));
    			String chromeFloatStr = chromeVerStr.substring(chromeVerStr.indexOf(".") + 1);
    			
    			if (chromeFloatStr.contains(".")) {
    				chromeFloatStr = chromeFloatStr.substring(0, chromeFloatStr.indexOf("."));
    			}
    			
    			chromeVerStr = chromeIntStr + "." + chromeFloatStr;
    		}
        	
        	clientObject.put("Browser", "Chrome");
    		clientObject.put("Browser_Version", chromeVerStr);
        } else if (agentInfo.contains("firefox")) {
            String firefoxVerStr = agentInfo.substring(agentInfo.indexOf("firefox") + 8);
        	
        	if (firefoxVerStr.indexOf(" ") > -1) {
        		firefoxVerStr = firefoxVerStr.substring(0, firefoxVerStr.indexOf(" "));
        	}
        	
        	if (firefoxVerStr.contains(".")) {
    			String firefoxIntStr = firefoxVerStr.substring(0, firefoxVerStr.indexOf("."));
    			String firefoxFloatStr = firefoxVerStr.substring(firefoxVerStr.indexOf(".") + 1);
    			
    			if (firefoxFloatStr.contains(".")) {
    				firefoxFloatStr = firefoxFloatStr.substring(0, firefoxFloatStr.indexOf("."));
    			}
    			
    			firefoxVerStr = firefoxIntStr + "." + firefoxFloatStr;
    		}
        	
        	clientObject.put("Browser", "Firefox");
    		clientObject.put("Browser_Version", firefoxVerStr);
        } else if(agentInfo.contains("rv")) {
        	String iexVerStr = agentInfo.substring(agentInfo.indexOf("rv") + 3, agentInfo.lastIndexOf(")"));
        	
        	if (iexVerStr.contains(".")) {
    			String iexIntStr = iexVerStr.substring(0, iexVerStr.indexOf("."));
    			String iexFloatStr = iexVerStr.substring(iexVerStr.indexOf(".") + 1);
    			
    			if (iexFloatStr.contains(".")) {
    				iexFloatStr = iexFloatStr.substring(0, iexFloatStr.indexOf("."));
    			}
    			
    			iexVerStr = iexIntStr + "." + iexFloatStr;
    		}
        	
        	clientObject.put("Browser", "IE");
    		clientObject.put("Browser_Version", iexVerStr);
        } else {
        	clientObject.put("Browser", "Unknow");
    		clientObject.put("Browser_Version", "-1");
        }
    	
    	return clientObject;
    }
}
