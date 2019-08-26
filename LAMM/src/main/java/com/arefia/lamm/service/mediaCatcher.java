package com.arefia.lamm.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arefia.lamm.bean.webCommunication;
import com.arefia.lamm.dao.sysinfoDao;
import com.arefia.lamm.entity.sysinfoEntity;
import com.arefia.lamm.model.webCommunicationModel;

@Service
public class mediaCatcher {
    private static final Logger log = LogManager.getLogger(mediaCatcher.class);
	
    @Autowired
    sysinfoDao sido;
	
	@Autowired
	webCommunication wcc;
	
	public JSONObject getMedia(String mediaType, String mediaid) {
		JSONObject mediaResources = new JSONObject();
		
		try {
			sysinfoEntity sysiey = sido.findAll().get(0);
			
			HashMap<String, String> repHead = new HashMap<String, String>();
		    repHead.put("Content-Type", "application/json");
		    repHead.put("Authorization", "Bearer  " + sysiey.getChannel_token());
		    
		    webCommunicationModel repObj = new webCommunicationModel();
		    
		    repObj.setConnURL("https://api.line.me/v2/bot/message/" + mediaid + "/content");
		    repObj.setHeaders(repHead);
		    
		    wcc.comWithGet(repObj);
		    
		    InputStream mediaInp = wcc.getIns;
		    String basePathString = getClass().getResource("/").getPath().replaceAll("%20", " ");
            
		    switch (mediaType) {
		        case "image":
		        	BufferedImage imgbf = ImageIO.read(mediaInp);
		        	ImageIO.write(imgbf, "png", new File(basePathString + "static/lineResources/images/" + mediaid));
		        	mediaResources.put("IMAGEWIDTH", imgbf.getWidth());
		        	mediaResources.put("IMAGEHEIGHT", imgbf.getHeight());
		        	break;
		        case "video":
		        	FileOutputStream vdoos = new FileOutputStream(new File(basePathString + "static/lineResources/videos/" + mediaid));
		        	IOUtils.copy(mediaInp, vdoos);
		        	break;
		        case "audio":
		        	FileOutputStream adoos = new FileOutputStream(new File(basePathString + "static/lineResources/audios/" + mediaid));
		        	IOUtils.copy(mediaInp, adoos);
		        	break;
		        default:
		        	mediaResources.put("FILEEXTS", "");
		        	break;
		    }
		    
			return mediaResources;
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
			return null;
		} finally {
			wcc.disconnectMethod();
		}
	}
}
