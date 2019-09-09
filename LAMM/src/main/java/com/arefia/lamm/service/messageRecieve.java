package com.arefia.lamm.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.arefia.lamm.dao.autoreplymsgDao;
import com.arefia.lamm.dao.contactsDao;
import com.arefia.lamm.dao.followersDao;
import com.arefia.lamm.dao.msgrecDao;
import com.arefia.lamm.entity.contactsEntity;
import com.arefia.lamm.entity.followersEntity;
import com.arefia.lamm.entity.msgrecieveEntity;

@Service
public class messageRecieve {
	private static final Logger log = LogManager.getLogger(messageRecieve.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdd = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat stt = new SimpleDateFormat("a hh:mm");
	
	@Value("${web_cust_data.intzoho}")
    private String zohointe;
	
    @Autowired
    msgrecDao redo;
    
    @Autowired
    mediaCatcher mcr;
    
    @Autowired
	followersDao fsdao;
    
    @Autowired
	followerInfo gfi;
    
    @Autowired
    messagePush mps;
    
    @Autowired
    contactsDao contdao;
    
    @Autowired
    zohoDataHandler zdhn;
    
    @Autowired
    zohoAuthService auts;
    
    @Autowired
    autoreplymsgDao aid;
    
    @Resource
    private SimpMessagingTemplate lineTemplate;
    
    public void recieve(String message) {
    	try {
    		JSONObject msgObj = new JSONObject(message);
        	JSONArray oriArr = msgObj.getJSONArray("events");
        	JSONObject detObj = oriArr.getJSONObject(0);
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	
        	if (!detObj.getString("replyToken").equals("00000000000000000000000000000000")) {
        		for (int i = 0; i < oriArr.length(); i++) {
            		JSONObject oriObj = oriArr.getJSONObject(i);
    		    	JSONObject sObj = oriObj.getJSONObject("source");
    		    	JSONObject mObj = oriObj.getJSONObject("message");
    		    	Timestamp ts = new Timestamp((long) oriObj.get("timestamp"));
            		Date tsp = ts;
            		
            		oriObj.remove("timestamp");
            		oriObj.put("recievedate", sdd.format(tsp));
            		oriObj.put("recievetime", stt.format(tsp));
            		
            		List<Object[]> checkcontact = contdao.checkFollowerInContact(sObj.getString("userId"));
            		
            		int isIncont = Integer.parseInt(checkcontact.get(0)[0].toString());
            		
            		if (isIncont == 0) {
            			contactsEntity contEnt = new contactsEntity();
            			
            			contEnt.setContid(UUID.randomUUID().toString());
            			contEnt.setLine_uid(sObj.getString("userId"));
            			
            			contdao.saveAndFlush(contEnt);
            		}
            		
            		List<Object[]> chkSourcer = fsdao.checkFollowerIsExist(sObj.getString("userId"));
            		
            		if (chkSourcer.get(0)[0].toString().equals("0")) {
            			JSONObject rtObj = gfi.getFollowerInfo(sObj.getString("userId"));
            			
            			followersEntity nfe = new followersEntity();
            			
            			nfe.setUserid(sObj.getString("userId"));
            			nfe.setDisplayname(rtObj.getString("displayName"));
            			nfe.setPictureurl(rtObj.getString("pictureUrl"));
            			
            			if (rtObj.has("statusMessage")) {
            				nfe.setStatusmessage(rtObj.getString("statusMessage"));
            			} else {
            				nfe.setStatusmessage("");
            			}
            			
            			fsdao.saveAndFlush(nfe);
            			
            			List<Object[]> welObj = aid.getWelcomeMsg();
            			
            			mps.push(sObj.getString("userId"), "flex", welObj.get(0)[1].toString(), welObj.get(0)[0].toString(), auth.getName(), welObj.get(0)[2].toString());
            		}
            		
            		if (zohointe.equals("1")) {
            			String acctoken = auts.getIniAuthCode();
            			HashMap<String, String> parmap = new HashMap<String, String>();
            			ArrayList<String> fidarr = new ArrayList<String>();
            			
            			parmap.put("Line_UID", sObj.getString("userId"));
            			fidarr.add("id");
            			
            			String lineinzoho = zdhn.getSpecRecord("Contacts", parmap, fidarr, "1", acctoken, "", "OR");
            			
            			if (lineinzoho == null || lineinzoho.equals("")) {
            				JSONObject isnline = new JSONObject();
            				JSONArray isndarr = new JSONArray();
            				JSONObject isndobj = new JSONObject();
            				
            				isndobj.put("Last_Name", "Undefined");
            				isndobj.put("Line_UID", sObj.getString("userId"));
            				isndarr.put(isndobj);
            				isnline.put("data", isndarr);
            				
            				zdhn.addRecord("Contacts", isnline, acctoken);
            			}
            		}
            		
            		msgrecieveEntity recm = new msgrecieveEntity();
                	
                	recm.setReplytoken(oriObj.getString("replyToken"));
                	recm.setSourcertype(sObj.getString("type"));
                	recm.setSourcerid(sObj.getString("userId"));
                	recm.setMsgtype(mObj.getString("type"));
                	recm.setMsgid(mObj.getString("id"));
                	
                	switch (mObj.getString("type")) {
        		        case "text":
        		    	    recm.setMsg(mObj.getString("text"));
        		    	    recm.setDuration("");
        		    	    
        		    	    break;
        		        case "sticker":
        		    	    JSONObject stickObj = new JSONObject();
        		    	    
        		    	    stickObj.put("stickerId", mObj.getString("stickerId"));
        		    	    stickObj.put("packageId", mObj.getString("packageId"));
        		    	    
        		    	    recm.setMsg(stickObj.toString());
        		    	    recm.setDuration(null);
        		        	break;
        		        case "image":
        		    	    JSONObject imgObj = new JSONObject();
        		    	    
        		    	    JSONObject imgInfo = saveMedia(mObj.getString("id"), "image");
        		    	    
        		    	    mObj.put("IMAGEWIDTH", imgInfo.get("IMAGEWIDTH").toString());
        		    	    mObj.put("IMAGEHEIGHT", imgInfo.get("IMAGEHEIGHT").toString());
        		    	    
        		    	    imgObj.put("contentProvider", mObj.getJSONObject("contentProvider"));

        		    	    recm.setMsg(imgObj.toString());
        		    	    recm.setDuration(null);
        		        	break;
        		        case "audio":
                            JSONObject audObj = new JSONObject();
                            
                            saveMedia(mObj.getString("id"), "audio");
                            
                            audObj.put("contentProvider", mObj.getJSONObject("contentProvider"));
                            
                            recm.setMsg(audObj.toString());
        		    	    recm.setDuration(mObj.getBigInteger("duration").toString());
        		        	break;
        		        case "video":
                            JSONObject vidObj = new JSONObject();
                            
                            saveMedia(mObj.getString("id"), "video");
                            
                            vidObj.put("contentProvider", mObj.getJSONObject("contentProvider"));
                            
                            recm.setMsg(vidObj.toString());
        		    	    recm.setDuration(mObj.getBigInteger("duration").toString());
        		        	break;
        		        default:
                            recm.setMsg("");
        		    	    recm.setDuration("");
        		        	break;
        		    }
                	
                	recm.setRecievetime(sdf.format(tsp));
                	recm.setChkflag("0");
                	
                	redo.saveAndFlush(recm);
            	}
        		
        		JSONObject recObj = new JSONObject();
        		
        		recObj.put("OBJTYPE", "RECIEVE");
        		recObj.put("RECIEVEARRAY", oriArr.toString());
        		
        		lineTemplate.convertAndSend("/topic/linemessage", recObj.toString());
        	}
    	} catch(Exception ex) {
    		log.error(ex.getLocalizedMessage(), ex);
    	}
    }
    
    private JSONObject saveMedia(String msgId, String mediaType) {
    	return mcr.getMedia(mediaType, msgId);
    }
}
