package com.arefia.lamm.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class savePushFilesController {
	private static final Logger log = LogManager.getLogger(savePushFilesController.class);
	
	@RequestMapping(value = "/pushfiles", method = RequestMethod.POST)
	@ResponseBody
	public void savePushFiles(MultipartHttpServletRequest request) {
	    try {
	    	String msgtype = request.getParameter("msgtype");
	    	String pushfid = request.getParameter("pushfid");
	    	String fileexts = request.getParameter("fileexts");
	    	MultipartFile pushfile = request.getFile("pushfile");
	    	
	    	if (pushfile != null) {
		    	String fspath = getClass().getResource("/").getPath().replaceAll("%20", " ");
		    	File saveFile = null;
		    	
				switch (msgtype) {
				    case "image":
				    	FileInputStream imgin = (FileInputStream) pushfile.getInputStream(); 
				    	BufferedImage imgbuff = ImageIO.read(imgin);
				    	
				    	fspath += "static/lineResources/images/" + pushfid + ".png";
				    	
				    	saveFile = new File(fspath);
				    	
				    	ImageIO.write(imgbuff, "png", saveFile);
				    	
				    	imgin.close();
				    	break;
				    case "audio":
				    	InputStream audin = pushfile.getInputStream();
				    	byte[] audbuf = new byte[audin.available()];
				    	audin.read(audbuf);
				    	
				    	fspath += "static/lineResources/audios/" + pushfid + "." + fileexts;
				    	
				    	FileOutputStream audot = new FileOutputStream(new File(fspath));				    					    	
				    	audot.write(audbuf);
				    	
				    	audin.close();
				    	audot.flush();
				    	audot.close();
				    	break;
				    case "video":
				    	InputStream vidin = pushfile.getInputStream();
				    	byte[] vidbuf = new byte[vidin.available()];
				    	vidin.read(vidbuf);
				    	
				    	fspath += "static/lineResources/videos/" + pushfid + "." + fileexts;
				    	
				    	FileOutputStream vidot = new FileOutputStream(new File(fspath));
				    	vidot.write(vidbuf);
				    	
				    	vidin.close();
				    	vidot.flush();
				    	vidot.close();
				    	break;
				}
		    }
	    } catch (Exception e) {
	    	log.error(e.getLocalizedMessage(), e);
	    }
	}
}
