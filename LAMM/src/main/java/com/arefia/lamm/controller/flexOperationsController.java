package com.arefia.lamm.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.arefia.lamm.bean.webCommunication;
import com.arefia.lamm.dao.contactsDao;
import com.arefia.lamm.dao.flexDetailDao;
import com.arefia.lamm.dao.flexFollowersDao;
import com.arefia.lamm.dao.flexMainDao;
import com.arefia.lamm.dao.sysinfoDao;
import com.arefia.lamm.dao.zohocondsDao;
import com.arefia.lamm.entity.flexdetailEntity;
import com.arefia.lamm.entity.flexfollowersEntity;
import com.arefia.lamm.entity.flexmainEntity;
import com.arefia.lamm.entity.zohocondsEntity;
import com.arefia.lamm.model.webCommunicationModel;

@Controller
public class flexOperationsController {
	private static final Logger log = LogManager.getLogger(flexOperationsController.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
	
	@Value("${web_cust_data.intzoho}")
    private String zohointe;
	
	@Autowired
	flexMainDao flexmdao;
	
	@Autowired
	flexDetailDao flexddao;
	
	@Autowired
	flexFollowersDao flexfdao;
	
	@Autowired
	sysinfoDao sido;
	
	@Autowired
	contactsDao cotdao;
	
	@Autowired
	webCommunication wcc;
	
	@Autowired
	zohocondsDao zcdd;
	
	@RequestMapping(value = "/saveflexmessage", method = RequestMethod.POST)
	@ResponseBody
	public String saveFlexContents(MultipartHttpServletRequest request) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String urlpath = sido.findAll().get(0).getSecurity_url();
			String fspath = getClass().getResource("/").getPath().replaceAll("%20", " ");
			String flexUid = request.getParameter("flexuid").toString();
			String flexMod = request.getParameter("flexmod").toString();
			String flexTitle = request.getParameter("flextitle").toString();
			JSONObject flexMsgObj = new JSONObject();
			JSONObject flexCntObj = new JSONObject();
			JSONObject flexBodyObj = new JSONObject();
			
            flexMsgObj.put("type", "flex");
		    flexMsgObj.put("altText", flexTitle);
		    flexMsgObj.put("contents", flexCntObj);
		    
		    flexCntObj.put("type", "bubble");
		    flexCntObj.put("body", flexBodyObj);
		    
		    flexBodyObj.put("type", "box");
		    flexBodyObj.put("spacing", "none");
		    
			switch(flexMod) {
			    case "1":
				    flexdetailEntity f11Ent = new flexdetailEntity();
			    	flexBodyObj.put("layout", "horizontal");
			    	
				    JSONArray f11Arr = new JSONArray();
				    JSONObject f11Obj = new JSONObject();
				    
				    f11Obj.put("margin", "none");
				    f11Obj.put("type", request.getParameter("block1type").toString());
				    f11Obj.put("gravity", "center");
				    
				    if (request.getParameter("block1type").toString().equals("image")) {
			            MultipartFile f11Image = request.getFile("block1image");
			            FileInputStream f11Inps = (FileInputStream) f11Image.getInputStream(); 
			            BufferedImage f11Buff = ImageIO.read(f11Inps);
			            String f11path = fspath + "static/lineResources/fleximgs/" + request.getParameter("block1content").toString() + ".png";
			          
			            File f11File = new File(f11path);
			          
			            ImageIO.write(f11Buff, "png", f11File);
			          
			            f11Obj.put("url", urlpath + "lineResources/fleximgs/" + request.getParameter("block1content").toString() + ".png");
			            f11Obj.put("size", "full");
			            f11Obj.put("aspectMode", "cover");
			            f11Obj.put("align", "center");
			        } else {
			            f11Obj.put("text", request.getParameter("block1content").toString().replace("<br>", "\n"));
			    	    f11Obj.put("wrap", true);
			        }
				    
				    if (!request.getParameter("block1link").toString().equals("")) {
				        JSONObject f11AObj = new JSONObject();
				        
				        f11AObj.put("type", "uri");
				        f11AObj.put("uri", request.getParameter("block1link").toString());
				        
				        f11Obj.put("action", f11AObj);
				    }
				    
				    f11Arr.put(f11Obj);
				    
				    flexBodyObj.put("contents", f11Arr);
				    
				    f11Ent.setUid(flexUid);
				    f11Ent.setBlocknum(1);
				    f11Ent.setBlocktype(request.getParameter("block1type").toString());
				    f11Ent.setBlockcontent(request.getParameter("block1content").toString());
				    f11Ent.setBlocklink(request.getParameter("block1link").toString());
				    
				    flexddao.saveAndFlush(f11Ent);
				    break;
			    case "2":
			    case "3":
			    case "4":
			    case "7":
			    	if (flexMod.equals("2")) {
				    	flexBodyObj.put("layout", "horizontal");
			    	} else {
				    	flexBodyObj.put("layout", "vertical");
			    	}
			    	
				    JSONArray f21Arr = new JSONArray();
				    JSONObject f21Obj = new JSONObject();
				    JSONObject f22Obj = new JSONObject();
				    
				    f21Obj.put("margin", "none");
				    f21Obj.put("type", request.getParameter("block1type").toString());
				    f21Obj.put("gravity", "center");
				    
				    if (request.getParameter("block1type").toString().equals("image")) {
			            MultipartFile f21Image = request.getFile("block1image");
			            FileInputStream f21Inps = (FileInputStream) f21Image.getInputStream(); 
			            BufferedImage f21Buff = ImageIO.read(f21Inps);
			            String f21path = fspath + "static/lineResources/fleximgs/" + request.getParameter("block1content").toString() + ".png";
			          
			            File f21File = new File(f21path);
			          
			            ImageIO.write(f21Buff, "png", f21File);
			          
			            f21Obj.put("url", urlpath + "lineResources/fleximgs/" + request.getParameter("block1content").toString() + ".png");
			            f21Obj.put("size", "full");
			            f21Obj.put("aspectMode", "cover");
			            f21Obj.put("align", "center");
			        } else {
			            f21Obj.put("text", request.getParameter("block1content").toString().replace("<br>", "\n"));
			    	    f21Obj.put("wrap", true);
			        }
				    
				    if (!request.getParameter("block1link").toString().equals("")) {
				        JSONObject f21AObj = new JSONObject();
				        
				        f21AObj.put("type", "uri");
				        f21AObj.put("uri", request.getParameter("block1link").toString());
				        
				        f21Obj.put("action", f21AObj);
				    }
				    
				    f21Arr.put(f21Obj);
				    
			    	flexdetailEntity f21Ent = new flexdetailEntity();
				    
				    f21Ent.setUid(flexUid);
				    f21Ent.setBlocknum(1);
				    f21Ent.setBlocktype(request.getParameter("block1type").toString());
				    f21Ent.setBlockcontent(request.getParameter("block1content").toString());
				    f21Ent.setBlocklink(request.getParameter("block1link").toString());
				    
				    flexddao.saveAndFlush(f21Ent);
				    
				    f22Obj.put("margin", "none");
				    f22Obj.put("type", request.getParameter("block2type").toString());
				    f22Obj.put("gravity", "center");
				    
				    if (request.getParameter("block2type").toString().equals("image")) {
			            MultipartFile f22Image = request.getFile("block2image");
			            FileInputStream f22Inps = (FileInputStream) f22Image.getInputStream(); 
			            BufferedImage f22Buff = ImageIO.read(f22Inps);
			            String f22path = fspath + "static/lineResources/fleximgs/" + request.getParameter("block2content").toString() + ".png";
			          
			            File f22File = new File(f22path);
			          
			            ImageIO.write(f22Buff, "png", f22File);
			          
			            f22Obj.put("url", urlpath + "lineResources/fleximgs/" + request.getParameter("block2content").toString() + ".png");
			            f22Obj.put("size", "full");
			            f22Obj.put("aspectMode", "cover");
			            f22Obj.put("align", "center");
			        } else {
			            f22Obj.put("text", request.getParameter("block2content").toString().replace("<br>", "\n"));
			    	    f22Obj.put("wrap", true);
			        }
				    
				    if (!request.getParameter("block2link").toString().equals("")) {
				        JSONObject f22AObj = new JSONObject();
				        
				        f22AObj.put("type", "uri");
				        f22AObj.put("uri", request.getParameter("block2link").toString());
				        
				        f22Obj.put("action", f22AObj);
				    }
				    
				    f21Arr.put(f22Obj);
				    				    
			    	flexdetailEntity f22Ent = new flexdetailEntity();
				    
				    f22Ent.setUid(flexUid);
				    f22Ent.setBlocknum(2);
				    f22Ent.setBlocktype(request.getParameter("block2type").toString());
				    f22Ent.setBlockcontent(request.getParameter("block2content").toString());
				    f22Ent.setBlocklink(request.getParameter("block2link").toString());
				    
				    flexddao.saveAndFlush(f22Ent);
				    
				    if (!flexMod.equals("4") && !flexMod.equals("7")) {
				        flexBodyObj.put("contents", f21Arr);
				    } else {
				    	JSONObject f23Obj = new JSONObject();
				    	
				    	f23Obj.put("margin", "none");
					    f23Obj.put("type", request.getParameter("block3type").toString());
					    f23Obj.put("gravity", "center");
					    
					    if (request.getParameter("block3type").toString().equals("image")) {
				            MultipartFile f23Image = request.getFile("block3image");
				            FileInputStream f23Inps = (FileInputStream) f23Image.getInputStream(); 
				            BufferedImage f23Buff = ImageIO.read(f23Inps);
				            String f23path = fspath + "static/lineResources/fleximgs/" + request.getParameter("block3content").toString() + ".png";
				          
				            File f23File = new File(f23path);
				          
				            ImageIO.write(f23Buff, "png", f23File);
				          
				            f23Obj.put("url", urlpath + "lineResources/fleximgs/" + request.getParameter("block3content").toString() + ".png");
				            f23Obj.put("size", "full");
				            f23Obj.put("aspectMode", "cover");
				            f23Obj.put("align", "center");
				        } else {
				            f23Obj.put("text", request.getParameter("block3content").toString().replace("<br>", "\n"));
				    	    f23Obj.put("wrap", true);
				        }
					    
					    if (!request.getParameter("block3link").toString().equals("")) {
					        JSONObject f23AObj = new JSONObject();
					        
					        f23AObj.put("type", "uri");
					        f23AObj.put("uri", request.getParameter("block3link").toString());
					        
					        f23Obj.put("action", f23AObj);
					    }
					    
					    f21Arr.put(f23Obj);
					    					    
				    	flexdetailEntity f23Ent = new flexdetailEntity();
					    
					    f23Ent.setUid(flexUid);
					    f23Ent.setBlocknum(3);
					    f23Ent.setBlocktype(request.getParameter("block3type").toString());
					    f23Ent.setBlockcontent(request.getParameter("block3content").toString());
					    f23Ent.setBlocklink(request.getParameter("block3link").toString());
					    
					    flexddao.saveAndFlush(f23Ent);
					    
					    flexBodyObj.put("contents", f21Arr);
				    }
				    break;
			    case "5":
			    case "6":
			    case "8":
			    	flexBodyObj.put("layout", "vertical");
			    	
			    	JSONArray f31Arr = new JSONArray();
			    	
			    	for (int c = 0; c < 2; c++) {
			    		JSONObject f311Obj = new JSONObject();
				    	JSONArray f311Arr = new JSONArray();
				    	
				    	f311Obj.put("type", "box");
				    	f311Obj.put("spacing", "none");
				    	f311Obj.put("layout", "horizontal");
				    	f311Obj.put("contents", f311Arr);
				    	
				    	for (int a = 1;a <= 3; a++) {
				    		int blockindex = 0;
				    		Boolean iscount = false;
				    		
				    		if (flexMod.equals("5")) {
				    			if (c == 0) {
				    				if (a < 3) {
				    					iscount = true;
				    					blockindex = a;
				    				}
				    			} else {
				    				if (a < 3) {
				    					iscount = true;
				    					blockindex = a + 2;
				    				}
				    			}
				    		} else if (flexMod.equals("6")) {
				    			if (c == 0) {
				    				if (a < 2) {
				    					iscount = true;
				    					blockindex = a;
				    				}
				    			} else {
				    				if (a < 3) {
				    					iscount = true;
				    					blockindex = a + 1;
				    				}
				    			}
				    		} else {
				    			iscount = true;
				    			blockindex = a + (c * 3);
				    		}
				    		
				    		if (iscount) {
				    			JSONObject f3111Obj = new JSONObject();
				    									    	
						    	f3111Obj.put("margin", "none");
						    	f3111Obj.put("type", request.getParameter("block" + String.valueOf(blockindex) + "type").toString());
						    	f3111Obj.put("gravity", "center");
						    	
							    if (request.getParameter("block" + String.valueOf(blockindex) + "type").toString().equals("image")) {
						            MultipartFile f3111Image = request.getFile("block" + String.valueOf(blockindex) + "image");
						            FileInputStream f3111Inps = (FileInputStream) f3111Image.getInputStream(); 
						            BufferedImage f3111Buff = ImageIO.read(f3111Inps);
						            String f3111path = fspath + "static/lineResources/fleximgs/" + request.getParameter("block" + String.valueOf(blockindex) + "content").toString() + ".png";
						          
						            File f3111File = new File(f3111path);
						          
						            ImageIO.write(f3111Buff, "png", f3111File);
						          
						            f3111Obj.put("url", urlpath + "lineResources/fleximgs/" + request.getParameter("block" + String.valueOf(blockindex) + "content").toString() + ".png");
						            f3111Obj.put("size", "full");
						            f3111Obj.put("aspectMode", "cover");
						            f3111Obj.put("align", "center");
						        } else {
						        	f3111Obj.put("text", request.getParameter("block" + String.valueOf(blockindex) + "content").toString().replace("<br>", "\n"));
						        	f3111Obj.put("wrap", true);
						        }
							    
							    if (!request.getParameter("block" + String.valueOf(blockindex) + "link").toString().equals("")) {
							        JSONObject f3111AObj = new JSONObject();
							        
							        f3111AObj.put("type", "uri");
							        f3111AObj.put("uri", request.getParameter("block" + String.valueOf(blockindex) + "link").toString());
							        
							        f3111Obj.put("action", f3111AObj);
							    }
							    
							    f311Arr.put(f3111Obj);
							    
							    flexdetailEntity f3Ent = new flexdetailEntity();
							    
							    f3Ent.setUid(flexUid);
							    f3Ent.setBlocknum(blockindex);
							    f3Ent.setBlocktype(request.getParameter("block" + String.valueOf(blockindex) + "type").toString());
							    f3Ent.setBlockcontent(request.getParameter("block" + String.valueOf(blockindex) + "content").toString());
							    f3Ent.setBlocklink(request.getParameter("block" + String.valueOf(blockindex) + "link").toString());
							    
							    flexddao.saveAndFlush(f3Ent);
				    		}
				    	}
				    	
				    	f31Arr.put(f311Obj);
			    	}
			    	
			    	flexBodyObj.put("contents", f31Arr);
			    	break;
			}
			
			flexmainEntity flexmEnt = new flexmainEntity();
			
			flexmEnt.setUid(flexUid);
			flexmEnt.setTitle(flexTitle);
			flexmEnt.setFlexmod(flexMod);
			flexmEnt.setCreatedate(sdf.format(new Date()));
			flexmEnt.setFlexobjstr(flexMsgObj.toString());
			flexmEnt.setCreator(auth.getName());

			flexmdao.saveAndFlush(flexmEnt);
			
			JSONObject resRetObj = new JSONObject();
			
			resRetObj.put("FLEXOBJSTR", flexMsgObj.toString());
			
			return resRetObj.toString();
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return "";
		}
	}
	
	@RequestMapping(value = "/saveflexfollowers", method = RequestMethod.POST)
	@ResponseBody
	public void saveFlexFollowers(HttpServletRequest request) {
		JSONObject ffObj = new JSONObject(request.getParameter("flexfollowers"));
		JSONArray ffArr = ffObj.getJSONArray("followerslist");
		
		for (int f = 0; f < ffArr.length(); f++) {
			JSONObject flObj = ffArr.getJSONObject(f);
			flexfollowersEntity flexfEnt = new flexfollowersEntity(); 
			
			flexfEnt.setUid(ffObj.getString("flexuid"));
			flexfEnt.setFollowerid(flObj.getString("followerid"));
			
			flexfdao.saveAndFlush(flexfEnt);
		}
	}
	
	@RequestMapping(value = "/getflexquery", method = RequestMethod.GET)
	@ResponseBody
	public String getQueryFlexResults(@RequestParam("KEYWORD") String keyword) {
		List<Object[]> msgsList = flexmdao.getFlexMessagesList(keyword);
		JSONArray msgsArr = new JSONArray();
		
		for (Object[] msgobjs: msgsList) {
			JSONObject msgsObj = new JSONObject();
			
			msgsObj.put("UID", msgobjs[0].toString());
			msgsObj.put("TITLE", msgobjs[1].toString());
			msgsObj.put("CREATEDATE", msgobjs[2].toString());
			
			msgsArr.put(msgsObj);
		}
		
		return msgsArr.toString();
	}
	
	@RequestMapping(value = "/getflexbyuid", method = RequestMethod.GET)
	@ResponseBody
	public String getSingleFlexByUid(@RequestParam("UID") String uid) {
		List<flexmainEntity> msterInfo = flexmdao.getSingleFlexMaster(uid);
		List<flexdetailEntity> detailInfo = flexddao.getSingleFlexDetail(uid);
		
		JSONObject flexObj = new JSONObject();
		JSONArray detailArr = new JSONArray();
		
		flexObj.put("UID", msterInfo.get(0).getUid());
		flexObj.put("FLEXMOD", msterInfo.get(0).getFlexmod());
		flexObj.put("TITLE", msterInfo.get(0).getTitle());
		flexObj.put("FLEXOBJSTR", msterInfo.get(0).getFlexobjstr());
		flexObj.put("DETAILS", detailArr);
		
		for (flexdetailEntity detent: detailInfo) {
			JSONObject detObj = new JSONObject();
			
			detObj.put("BLOCKNUM", detent.getBlocknum());
			detObj.put("BLOCKTYPE", detent.getBlocktype());
			detObj.put("BLOCKCONTENT", detent.getBlockcontent());
			detObj.put("BLOCKLINK", detent.getBlocklink());
			
			detailArr.put(detObj);
		}
		
		return flexObj.toString();
	}
	
	@RequestMapping(value = "/getlunecontacts", method = RequestMethod.GET)
	@ResponseBody
	public String getLineContactsList(@RequestParam("KEYWORD") String keyword) {
		JSONArray contactsArr = new JSONArray();
		
		if (zohointe.equals("1")) {
			
		} else {
			List<Object[]> contactsList = cotdao.getAllLineContacts(keyword);
			
			for (Object[] contact: contactsList) {
				JSONObject contactsObj = new JSONObject();
				
				contactsObj.put("COMPANY", contact[0].toString());
				contactsObj.put("CONTACT_NAME", contact[1].toString());
				contactsObj.put("LINE_UID", contact[2].toString());
				
				contactsArr.put(contactsObj);
			}
		}
		
		return contactsArr.toString();
	}
	
	@RequestMapping(value = "/getzohocontactconds", method = RequestMethod.GET)
	@ResponseBody
	public String getZohoContactsConditions() {
		JSONArray contactsArr = new JSONArray();
		List<zohocondsEntity> allconds = zcdd.findAllByOrderByOrderAsc();
		
		for (zohocondsEntity cond: allconds) {
			JSONObject contactsObj = new JSONObject();
			
			contactsObj.put("DISPLAY", cond.getDisplay_name());
			contactsObj.put("VALUE", cond.getValue());
			
			contactsArr.put(contactsObj);
		}
		
		return contactsArr.toString();
	}
	
	@RequestMapping(value = "/sendflexforcontacts", method = RequestMethod.POST)
	@ResponseBody
	public String sendFlexMsgToContacts(HttpServletRequest request) {
		JSONObject flexObj = new JSONObject(request.getParameter("SENDINFO").toString());
		JSONArray flsArr = flexObj.getJSONArray("FOLLOWERSLIST");
		JSONObject msgObj = new JSONObject(flexObj.getString("FLEXOBJSTR"));
		JSONObject sendObj = new JSONObject();
		JSONArray recArr = new JSONArray();
		JSONArray msgArr = new JSONArray();
		
		msgArr.put(msgObj);
		
		for (int f = 0; f < flsArr.length(); f++) {
			JSONObject flsObj = flsArr.getJSONObject(f);
			flexfollowersEntity ffEnt = new flexfollowersEntity();
			
			recArr.put(flsObj.getString("FOLLOWERID"));
			
			ffEnt.setFid(UUID.randomUUID().toString());
			ffEnt.setUid(flexObj.getString("UID"));
			ffEnt.setFollowerid(flsObj.getString("FOLLOWERID"));
			ffEnt.setSendtime(sdf.format(new Date()));
			
			flexfdao.saveAndFlush(ffEnt);
		}
		
		sendObj.put("to", recArr);
		sendObj.put("messages", msgArr);
		
		HashMap<String, String> flexHead = new HashMap<String, String>();
		
		flexHead.put("Content-Type", "application/json");
	    flexHead.put("Authorization", "Bearer  " + sido.findAll().get(0).getChannel_token());
	    
	    webCommunicationModel flexWebMod = new webCommunicationModel();
	    
	    flexWebMod.setConnURL("https://api.line.me/v2/bot/message/multicast");
        flexWebMod.setHeaders(flexHead);
        flexWebMod.setBodys(sendObj);
        
        wcc.comWithPost(flexWebMod);
		
		return wcc.postIns;
	}
}
