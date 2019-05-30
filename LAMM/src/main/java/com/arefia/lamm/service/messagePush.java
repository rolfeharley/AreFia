package com.arefia.lamm.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

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
	
	public void push(String sourcerid, String msgtype, String msg, String pushid, String pushfid) {
		try {
			HashMap<String, String> repHead = new HashMap<String, String>();
		    JSONObject repBody = new JSONObject();
		    JSONArray remArr = new JSONArray();
		    JSONObject remObj = new JSONObject();
			UUID msgid = UUID.randomUUID();
			String fspath = getClass().getResource("/").getPath().replaceAll("%20", " ");
			int imgw = 0, imgh = 0;
			sysinfoEntity sysEnt = sido.findAll().get(0);
		    
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
	        	    fspath += "static/lineResources/images/" + pushfid.toString() + ".png";
	        	    remObj.put("originalContentUrl", sysEnt.getSecurity_url() + "lineResources/images/" + pushfid.toString() + ".png"); 
	    	        remObj.put("previewImageUrl", sysEnt.getSecurity_url() + "lineResources/images/" + pushfid.toString() + ".png");
	    	        
	    	        BufferedImage imgbuff = null;
					File imgfile = new File(fspath);
					
					imgbuff = ImageIO.read(imgfile);
					
					imgw = imgbuff.getWidth();
					imgh = imgbuff.getHeight();
	        	    break;
	            case "audio":
	        	    fspath += "static/lineResources/audios/" + pushfid.toString() + ".mp3";
	        	    remObj.put("originalContentUrl", "lineResources/audios/" + pushfid.toString() + ".mp3"); 
	        	    remObj.put("duration", 0);
	        	    break;
	            case "video":
	        	    fspath += "static/lineResources/videos/" + pushfid.toString() + ".mp4";
	        	    remObj.put("originalContentUrl", "lineResources/videos/" + pushfid.toString() + ".mp4"); 
	        	    remObj.put("previewImageUrl", ""); 
	        	    break;
	            default:
	        	
	        	    break;
		    }
		    
		    remArr.put(remObj);
		    
		    repBody.put("messages", remArr);
		    
		    webCommunicationModel repObj = new webCommunicationModel();
		    
		    repObj.setConnURL("https://api.line.me/v2/bot/message/push");
		    repObj.setHeaders(repHead);
		    repObj.setBodys(repBody);
		    
		    wcc.comWithPost(repObj);
			
		    if (wcc.postIns.equals("OK")) {
				msgpushEntity pushObj = new msgpushEntity();
				Date cdt = new Date();
				
				pushObj.setMsgtype(msgtype);
				if (msgtype.equals("text")) {
				    pushObj.setMsgid(msgid.toString());
				} else {
					pushObj.setMsgid(pushfid.toString());
				}
				pushObj.setMsg(msg);
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