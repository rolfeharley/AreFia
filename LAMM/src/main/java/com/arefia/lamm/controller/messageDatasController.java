package com.arefia.lamm.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arefia.lamm.dao.followersDao;
import com.arefia.lamm.dao.msgpushDao;
import com.arefia.lamm.dao.msgrecDao;
import com.arefia.lamm.entity.followersEntity;
import com.arefia.lamm.service.followerInfo;

@Controller
public class messageDatasController {
	private static final Logger log = LogManager.getLogger(messageDatasController.class);
	private SimpleDateFormat tsdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private SimpleDateFormat fsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat tsdd = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat tstt = new SimpleDateFormat("a hh:mm");
	private SimpleDateFormat fulldf = new SimpleDateFormat("yyyy/MM/dd a hh:mm");
	private String basePathString = getClass().getResource("/").getPath().replaceAll("%20", " ");
	
	@Autowired
	msgrecDao mrdao;
	
	@Autowired
	followersDao fsdao;
	
	@Autowired
	followerInfo gfi;
	
	@Autowired
	msgpushDao mpdao;
	
	@RequestMapping(value = "/messagedatas", method = RequestMethod.GET)
	@ResponseBody
	public String checkNewMessage() {
		try {
	        List<Object[]> messagesList = mrdao.checkAllMessages();
			
			if (messagesList.size() > 0) {
				JSONArray nmArr = new JSONArray();
				
				for (Object[] msg: messagesList) {
					JSONObject nmObj = new JSONObject();
					Date recDate = fsdf.parse(msg[5].toString());
					
					nmObj.put("MSGID", msg[0].toString());
					nmObj.put("MESSAGETYPE", msg[2].toString());
					nmObj.put("MESSAGE", msg[3].toString());
					nmObj.put("SOURCERID", msg[1].toString());
					nmObj.put("EVENTTIME", tsdf.format(recDate));
					
					if (msg[4] != null) {
					    nmObj.put("DURATION", msg[4].toString());
					} else {
						nmObj.put("DURATION", "");
					}
					nmObj.put("OBJECTTYPE", msg[6].toString());
					
					nmArr.put(nmObj);
				}

				return nmArr.toString();
			} else {
				return null;
			}
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "/conversationfollowers", method = RequestMethod.GET)
	@ResponseBody
	public String getFollowersInConversation() {
		try {
            List<Object[]> followersList = fsdao.getActiveFollowers();
			
			if (followersList.size() > 0) {
				JSONArray fsArr = new JSONArray();
				
				for (Object[] follower: followersList) {
					JSONObject fsObj = new JSONObject();
					Date lstDate = fsdf.parse(follower[8].toString());
					
					fsObj.put("USERID", follower[0].toString());
					
					fsObj.put("USERID", follower[0].toString());
					fsObj.put("DISPLAYNAME", follower[1].toString());
					fsObj.put("PICTUREURL", follower[2].toString());
					fsObj.put("STATUSMESSAGE", follower[3].toString());
					
					fsObj.put("MESSAGEFROM", follower[4].toString());
					fsObj.put("MSGTYPE", follower[5].toString());
					
					switch (follower[5].toString()) {
					    case "text":
							fsObj.put("MSG", follower[6].toString());
					    	break;
					    case "image":
					    	fsObj.put("MSG", "您收到圖片");
						    break;
					    case "audio":
					    	fsObj.put("MSG", "您收到聲音檔");
					    	break;
					    case "video":
					    	fsObj.put("MSG", "您收到影像檔");
					    	break;
					    case "sticker":
					    	fsObj.put("MSG", "您收到貼圖");
					    	break;
					    default:
					    	fsObj.put("MSG", "不支援的格式");
					    	break;
					    
					}
					
					fsObj.put("UNREADCOUNT", follower[7].toString());
					fsObj.put("LASTDATE", tsdd.format(lstDate));
					fsObj.put("LASTTIME", tstt.format(lstDate));
					
					fsArr.put(fsObj);
				}

				return fsArr.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return null;
		}
	}
	
	@RequestMapping(value = "/getfollowerinfos", method = RequestMethod.GET)
	@ResponseBody
	public String getSingleFollowerData(@RequestParam("followerid") String followerid) {
		JSONObject sfdObj = gfi.getFollowerInfo(followerid);

		if (sfdObj != null) {
			JSONObject ofsObj = new JSONObject();
			
			ofsObj.put("USERID", followerid);
			ofsObj.put("DISPLAYNAME", sfdObj.getString("displayName"));
			ofsObj.put("PICTUREURL", sfdObj.getString("pictureUrl"));
			if (sfdObj.has("statusMessage")) {
				ofsObj.put("STATUSMESSAGE", sfdObj.getString("statusMessage"));
			} else {
				ofsObj.put("STATUSMESSAGE", "");
			}
			
		    return ofsObj.toString();
		} else {
			return "";
		}
	}
	
	@RequestMapping(value = "/getfollowerindb", method = RequestMethod.GET)
	@ResponseBody
	public String getFollowerInDB(@RequestParam("followerid") String followerid) {
		List<followersEntity> finfo = fsdao.getSpecFollower(followerid);

		if (finfo != null && finfo.size() == 1) {
			JSONObject nfobj = new JSONObject();
			followersEntity spefl = finfo.get(0);
			
			nfobj.put("USERID", followerid);
			nfobj.put("DISPLAYNAME", spefl.getDisplayname());
			nfobj.put("PICTUREURL", spefl.getPictureurl());
			nfobj.put("STATUSMESSAGE", spefl.getStatusmessage());
			
		    return nfobj.toString();
		} else {
			return "";
		}
	}
	
	@RequestMapping(value = "/getfollowerinfolist", method = RequestMethod.GET)
	@ResponseBody
	public String getAllFollowerData() {
		
		List<followersEntity> afe = fsdao.findAll();
		JSONArray afarr = new JSONArray();

		if (afe.size() > 0) {
			for (followersEntity fent : afe) {
				JSONObject afObj = new JSONObject();
				
				afObj.put("USERID", fent.getUserid());
				
				JSONObject ffsObj = gfi.getFollowerInfo(fent.getUserid());
				
				if (ffsObj != null) {
					String stamsg = "";
					
					afObj.put("DISPLAYNAME", ffsObj.getString("displayName"));
					afObj.put("PICTUREURL", ffsObj.getString("pictureUrl"));
					
					if (ffsObj.has("statusMessage")) {
						stamsg = ffsObj.getString("statusMessage");					
					}
					
					afObj.put("STATUSMESSAGE", stamsg);
					
					fsdao.updateFollowerInfo(ffsObj.getString("displayName"), ffsObj.getString("pictureUrl"), stamsg, fent.getUserid());
				} else {
					afObj.put("DISPLAYNAME", fent.getDisplayname());
					afObj.put("PICTUREURL", fent.getPictureurl());
					afObj.put("STATUSMESSAGE", fent.getStatusmessage());
				}
				
				afarr.put(afObj);
			}
			
		    return afarr.toString();
		} else {
			return "";
		}
	}
	
	@RequestMapping(value = "/getfollowerMsgs", method = RequestMethod.GET)
	@ResponseBody
	public String getFollowerMsgs(@RequestParam("followerid") String followerid) {
		try {
			fsdao.updateRecieveAllFlag(followerid);
			List<Object[]> msgList = fsdao.getSingleFollowerMsg(followerid);
			
			if (msgList.size() > 0) {
				JSONArray msgArr = new JSONArray();
				
				for (Object[] msg: msgList) {
					JSONObject msgObj = new JSONObject();
					Date exeDate = fsdf.parse(msg[3].toString());
					
					msgObj.put("MSGFROM", msg[0].toString());
					msgObj.put("MSGTYPE", msg[1].toString());
					
					if (msg[1].toString().equals("image")) {
						BufferedImage imgbuff = null;
						File imgfile = new File(basePathString + "static/lineResources/images/" + msg[4].toString());

						if (imgfile.exists()) {
							imgbuff = ImageIO.read(imgfile);
							
							msgObj.put("IMAGEWIDTH", imgbuff.getWidth());
							msgObj.put("IMAGEHEIGHT", imgbuff.getHeight());
						} else {
							msgObj.put("IMAGEWIDTH", 0);
							msgObj.put("IMAGEHEIGHT", 0);
						}
					}
					
					msgObj.put("MSG", msg[2].toString());
					msgObj.put("EXETIME", fulldf.format(exeDate));
					msgObj.put("MSGID", msg[4].toString());
					
					msgArr.put(msgObj);
				}
				
				return msgArr.toString();
			} else {
				return null;
			}
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return null;
		}
	}
	
	@RequestMapping(value = "/realtimeupdate", method = RequestMethod.GET)
	@ResponseBody
	public void updateSingleMessage(@RequestParam("MSGID") String MSGID) {
		try {
			mrdao.updateSingleFlag(MSGID);		
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}
	
	@RequestMapping(value = "/movemsgtohistory", method = RequestMethod.POST)
	@ResponseBody
	public void moveMessagesToHistory(HttpServletRequest request) {
		String followerid = request.getParameter("followerid");
		
		mrdao.moveRecieveToHistory(followerid);
		mpdao.movePushToHistory(followerid);
	}
	
	@RequestMapping(value = "/gethistoryfollowers", method = RequestMethod.GET)
	@ResponseBody
	public String historyfollowerlist() {
		try {
	        List<Object[]> histUsersList = fsdao.getHistoryFollowers();
			
			if (histUsersList.size() > 0) {
				JSONArray histArr = new JSONArray();
				
				for (Object[] follower: histUsersList) {
					JSONObject histObj = new JSONObject();
					
					histObj.put("USERID", follower[0].toString());
					histObj.put("DISPLAYNAME", follower[1].toString());
					
					histArr.put(histObj);
				}

				return histArr.toString();
			} else {
				return null;
			}
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "/gethistoryconversation", method = RequestMethod.GET)
	@ResponseBody
	public String historyconversation(HttpServletRequest request) {
		try {
	        List<Object[]> histConve = fsdao.getSingleFollowerHistory(request.getParameter("followerid"), request.getParameter("stdate"), request.getParameter("eddate"));
			
			if (histConve.size() > 0) {
                JSONArray convArr = new JSONArray();
				
				for (Object[] conv: histConve) {
					JSONObject convObj = new JSONObject();
					Date exeDate = fsdf.parse(conv[3].toString());
					
					convObj.put("MSGFROM", conv[0].toString());
					convObj.put("MSGTYPE", conv[1].toString());
					
					if (conv[1].toString().equals("image")) {
						BufferedImage imgbuff = null;
						File imgfile = new File(basePathString + "static/lineResources/images/" + conv[4].toString());
						
						if (imgfile.exists()) {
							imgbuff = ImageIO.read(imgfile);
							
							convObj.put("IMAGEWIDTH", imgbuff.getWidth());
							convObj.put("IMAGEHEIGHT", imgbuff.getHeight());
						} else {
							convObj.put("IMAGEWIDTH", 0);
							convObj.put("IMAGEHEIGHT", 0);
						}
					}
					
					convObj.put("MSG", conv[2].toString());
					convObj.put("EXETIME", fulldf.format(exeDate));
					convObj.put("MSGID", conv[4].toString());
					
					convArr.put(convObj);
				}
				
				return convArr.toString();
			} else {
				return null;
			}
		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage(), ex);
			return null;
		}
	}
}
