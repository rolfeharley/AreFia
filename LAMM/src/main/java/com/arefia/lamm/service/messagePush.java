package com.arefia.lamm.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.arefia.lamm.bean.webCommunication;
import com.arefia.lamm.dao.msgpushDao;
import com.arefia.lamm.dao.sysinfoDao;
import com.arefia.lamm.entity.msgpushEntity;
import com.arefia.lamm.entity.sysinfoEntity;
import com.arefia.lamm.model.webCommunicationModel;

@Service
public class messagePush {
	private static final Logger log = LogManager.getLogger(messagePush.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat udf = new SimpleDateFormat("yyyy/MM/dd a hh:mm");
	
	@Autowired
	webCommunication wcc;
	
	@Autowired
	msgpushDao pdo;
	
	@Autowired
	sysinfoDao sido;
	
	@Resource
    private SimpMessagingTemplate lineTemplate;
	
	public void push(String sourcerid, String msgtype, String filename, String msg, String pushid, String pushfid) {
		try {
			HashMap<String, String> repHead = new HashMap<String, String>();
		    JSONObject repBody = new JSONObject();
		    JSONArray remArr = new JSONArray();
		    JSONObject remObj = new JSONObject();
			UUID msgid = UUID.randomUUID();
			String fspath = getClass().getResource("/").getPath().replaceAll("%20", " ");
			String fileexts = ""; 
			int imgw = 0, imgh = 0;
			sysinfoEntity sysEnt = sido.findAll().get(0);
			
			if (msgtype != "flex" && filename != null && !filename.equals("")) {
				fileexts = filename.substring(filename.lastIndexOf("."));
			}
		    
			repHead.put("Content-Type", "application/json");
		    repHead.put("Authorization", "Bearer  " + sysEnt.getChannel_token());
		    
		    repBody.put("to", sourcerid);
		    
		    remObj.put("type", msgtype);
		    
		    switch (msgtype) {
		        case "text":
			        remObj.put("text", msg);
	    	        break;
	            case "sticker":
	    	        remObj.put("packageId", ""); 
	    	        remObj.put("stickerId", "");
	        	    break;
	            case "image":
	        	    fspath += "static/lineResources/images/" + pushfid.toString() + fileexts;
	        	    remObj.put("originalContentUrl", sysEnt.getSecurity_url() + "lineResources/images/" + pushfid.toString() + fileexts); 
	    	        remObj.put("previewImageUrl", sysEnt.getSecurity_url() + "lineResources/images/" + pushfid.toString() + fileexts);
	    	        
	    	        BufferedImage imgbuff = null;
					File imgfile = new File(fspath);
					
					imgbuff = ImageIO.read(imgfile);
					
					imgw = imgbuff.getWidth();
					imgh = imgbuff.getHeight();
	        	    break;
	            case "audio":
	        	    fspath += "static/lineResources/audios/" + pushfid.toString() + fileexts;
	        	    File audfs = new File(fspath);
	        	    long suddus = 0;
	        	    
	        	    AudioFileFormat audfft = AudioSystem.getAudioFileFormat(audfs);
	        	    
	        	    Map<String, Object> audpro = audfft.properties();
	        	    
	        	    if (audpro.containsKey("duration")) {
	        	    	suddus = (long) Math.round((((Long) audpro.get("duration")).longValue()) / 1000);
	    			}
	    			
	        	    JSONObject audicontobj = new JSONObject();
	        	    
	        	    audicontobj.put("type", "line");
	        	    
	        	    remObj.put("originalContentUrl", sysEnt.getSecurity_url() + "lineResources/audios/" + pushfid.toString() + fileexts);
	        	    remObj.put("duration", suddus);
	        	    remObj.put("contentProvider", audicontobj);
	        	    break;
	            case "video":
	        	    fspath += "static/lineResources/videos/" + pushfid.toString() + fileexts;

                    remObj.put("originalContentUrl", sysEnt.getSecurity_url() + "lineResources/videos/" + pushfid.toString() + fileexts); 
                    remObj.put("previewImageUrl", sysEnt.getSecurity_url() + "lineResources/videos/snapshot.jpg");
	        	    break;
	            case "flex":
	            	remObj.put("altText", "Welcome");
	            	
	            	JSONObject lv1con = new JSONObject();
	            	
	            	lv1con.put("type", "bubble");
	            	
	            	JSONObject bodycon = new JSONObject();
	            	
	            	bodycon.put("layout", "vertical");
	            	bodycon.put("type", "box");
	            	bodycon.put("spacing", "none");
	            	
	            	JSONArray lv2con = new JSONArray();
	            	
	            	JSONObject txtobj = new JSONObject();
	            	
	            	txtobj.put("type", "text");
	            	txtobj.put("text", msg);
	            	txtobj.put("wrap", true);
	            	txtobj.put("gravity", "center");
	            	txtobj.put("margin", "none");
	            	
	            	lv2con.put(txtobj);
	            	
	            	JSONObject buttonobj = new JSONObject();
	            	
	            	buttonobj.put("type", "button");
	            	buttonobj.put("style", "primary");
	            	buttonobj.put("margin", "xl");
	            	
	            	JSONObject actobj = new JSONObject();
	            	
	            	actobj.put("type", "uri");
	            	actobj.put("label", filename);
	            	actobj.put("uri", sysEnt.getSecurity_url() + pushfid);
	            	
	            	buttonobj.put("action", actobj);
	            	
	            	lv2con.put(buttonobj);
	            	
	            	bodycon.put("contents", lv2con);
	            	
	            	lv1con.put("body", bodycon);
	            	
	            	remObj.put("contents", lv1con);
	            	
	                break;
	            default:
	            	fspath += "static/lineResources/files/" + pushfid.toString() + fileexts;
	            	File fsfs = new File(fspath);	            	
	            	long fssize = fsfs.length();
	            	
	            	remObj.put("originalContentUrl", sysEnt.getSecurity_url() + "lineResources/videos/" + pushfid.toString() + fileexts); 
                    remObj.put("previewImageUrl", sysEnt.getSecurity_url() + "lineResources/videos/snapshot.jpg");
	        	    break;
		    }
		    
		    remArr.put(remObj);
		    
		    repBody.put("messages", remArr);
		  
		    webCommunicationModel repObj = new webCommunicationModel();
		    
		    repObj.setConnURL("https://api.line.me/v2/bot/message/push");
		    repObj.setHeaders(repHead);
		    repObj.setBodys(repBody);
		    
		    wcc.comWithPost(repObj);
			
		    if (!msgtype.equals("flex") && wcc.postIns.equals("OK")) {
				msgpushEntity pushObj = new msgpushEntity();
				Date cdt = new Date();
				
				pushObj.setMsgtype(msgtype);
				if (msgtype.equals("text")) {
				    pushObj.setMsgid(msgid.toString());
				} else {
					pushObj.setMsgid(pushfid.toString());
				}
				pushObj.setMsg(msg);
				pushObj.setFilename(filename);
				pushObj.setPushuser(pushid);
				pushObj.setSourcerid(sourcerid);
				pushObj.setPushtime(sdf.format(cdt));
				pushObj.setCheckflag("1");
				
				pdo.saveAndFlush(pushObj);
				
                JSONObject pumObj = new JSONObject();
                
                pumObj.put("OBJTYPE", "PUSH");
                pumObj.put("SOURCERID", sourcerid);
                pumObj.put("MSGTYPE", msgtype);
                if (msgtype.equals("text")) {
                	pumObj.put("MSGID", msgid.toString());
				} else {
					pumObj.put("MSGID", pushfid.toString());
				}
                pumObj.put("MSG", msg);
                pumObj.put("FILENAME", filename);
                pumObj.put("EXETIME", udf.format(cdt));
                pumObj.put("IMAGEWIDTH", imgw);
                pumObj.put("IMAGEHEIGHT", imgh);
				
				lineTemplate.convertAndSend("/topic/linemessage", pumObj.toString());
		    }
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
		} finally {
			wcc.disconnectMethod();
		}
	}
}
