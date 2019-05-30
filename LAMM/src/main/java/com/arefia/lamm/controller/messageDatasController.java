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
					JSONObject rtObj = gfi.getFollowerInfo(follower[0].toString());
					JSONObject fsObj = new JSONObject();
					Date lstDate = fsdf.parse(follower[8].toString());
					
					fsObj.put("USERID", follower[0].toString());
					
					if (rtObj != null) {
						fsObj.put("DISPLAYNAME", rtObj.getString("displayName"));
						fsObj.put("PICTUREURL", rtObj.getString("pictureUrl"));
						if (rtObj.has("statusMessage")) {
							fsObj.put("STATUSMESSAGE", rtObj.getString("statusMessage"));
						} else {
							fsObj.put("STATUSMESSAGE", "");
						}
					} else {
						fsObj.put("USERID", follower[0].toString());
						fsObj.put("DISPLAYNAME", follower[1].toString());
						fsObj.put("PICTUREURL", follower[2].toString());
						fsObj.put("STATUSMESSAGE", follower[3].toString());
					}
					
					fsObj.put("MESSAGEFROM", follower[4].toString());
					fsObj.put("MSGTYPE", follower[5].toString());
					fsObj.put("MSG", follower[6].toString());
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
						File imgfile = new File(basePathString + "static/lineResources/images/" + msg[4].toString() + ".png");
						
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
						File imgfile = new File(basePathString + "static/lineResources/images/" + conv[4].toString() + ".png");
						
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