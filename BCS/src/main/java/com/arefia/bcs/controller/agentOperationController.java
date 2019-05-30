package com.arefia.bcs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arefia.bcs.dao.agentDao;
import com.arefia.bcs.dao.bankDao;
import com.arefia.bcs.dao.imtypeDao;
import com.arefia.bcs.dao.userDao;
import com.arefia.bcs.dao.useringroupDao;
import com.arefia.bcs.entity.agentEntity;
import com.arefia.bcs.entity.bankEntity;
import com.arefia.bcs.entity.imtypeEntity;
import com.arefia.bcs.entity.userEntity;
import com.arefia.bcs.entity.useringroupEntity;
import com.arefia.tools.passwordEncrypt;

@Controller
public class agentOperationController {
	private static final Logger log = LogManager.getLogger(agentOperationController.class);
	private SimpleDateFormat fsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat tsdd = new SimpleDateFormat("yyyy/MM/dd");
	
	@Autowired
	userDao usdao;
	
	@Autowired
	agentDao agdao;
	
	@Autowired
	imtypeDao imdao;
	
	@Autowired
	useringroupDao ugdao;
	
	@Autowired
	bankDao bkdao;
	
	@RequestMapping(value = "/getimtype", method = RequestMethod.GET)
	@ResponseBody
	public String getallimtype() {
		List<imtypeEntity> iment = imdao.findAll();
		JSONArray imArr = new JSONArray();
		
		if (iment.size() > 0) {
			for (int i = 0; i < iment.size(); i++) {
                JSONObject imObj = new JSONObject();
				
				imObj.put("imuid", iment.get(i).getImuid());
				imObj.put("imtype", iment.get(i).getImtype());
				
				imArr.put(imObj);
			}
			
			return imArr.toString();
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getuserlist", method = RequestMethod.GET)
	@ResponseBody
	public String getallusers() {
		List<userEntity> usent = usdao.findEnabledUser();
		JSONArray usArr = new JSONArray();
		
		if (usent.size() > 0) {
			for (int u = 0; u < usent.size(); u++) {
                JSONObject usObj = new JSONObject();
				
				usObj.put("accountid", usent.get(u).getAccountid());
				usObj.put("username", usent.get(u).getUsername());
				
				usArr.put(usObj);
			}

			return usArr.toString();
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/createnewagent", method = RequestMethod.POST)
	@ResponseBody
	public int addnewagent(HttpServletRequest request) {
		try {
			JSONObject agentnew = new JSONObject(request.getParameter("agentstr"));
			Date curdate = new Date();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			userEntity uent = new userEntity();
			agentEntity aent = new agentEntity();
			useringroupEntity ugent = new useringroupEntity();
			
			uent.setAccountid(agentnew.getString("accountid"));
			uent.setPasswd(passwordEncrypt.encodingPasswd(agentnew.getString("passwd")));
			uent.setUsername(agentnew.getString("username"));
			uent.setGender(agentnew.getString("gender"));
			uent.setBirthday(tsdd.format(tsdd.parse(agentnew.getString("birthday"))));
			uent.setIdentity(agentnew.getString("identity"));
			uent.setEmail(agentnew.getString("email"));
			uent.setWorktype(agentnew.getString("worktype"));
			uent.setReferrer(agentnew.getString("referrer"));
			uent.setReferrerid(agentnew.getString("referrerid"));
			uent.setIsenable("1");
			uent.setCreatedate(fsdf.format(curdate));
			uent.setCreator(auth.getName());
			uent.setDisplaylist("1");
			uent.setAddress(agentnew.getString("address"));

			usdao.saveAndFlush(uent);
			
			aent.setAccountid(agentnew.getString("accountid"));
			aent.setImtype(agentnew.getString("imtype"));
			aent.setImid(agentnew.getString("imid"));
			aent.setShopname(agentnew.getString("shopname"));
			aent.setMobile(agentnew.getString("mobile"));
			aent.setReferrer(agentnew.getString("referrer"));
			aent.setReferrerid(agentnew.getString("referrerid"));
			aent.setJoineddate(agentnew.getString("joineddate"));
			aent.setRemark(agentnew.getString("remark"));
			
			agdao.saveAndFlush(aent);
			
			ugent.setAccountid(agentnew.getString("accountid"));
			ugent.setGroupid("6553307f-9cd4-4ee8-a37e-d0769c2d25a6");
			ugent.setCreator(auth.getName());
			ugent.setCreatedate(fsdf.format(curdate));
			
			ugdao.saveAndFlush(ugent);
			
			return 0;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return 1;
		}	
	}
	
	@RequestMapping(value = "/queryuser", method = RequestMethod.GET)
	@ResponseBody
	public String getuserquery(@RequestParam("KEYWORD") String keyword) {
		List<Object[]> ubclist = usdao.findUserByCondition(keyword);
		JSONArray ubcArr = new JSONArray();
		
		if (ubclist.size() > 0) {
			for (int c = 0; c < ubclist.size(); c++) {
                JSONObject ubcObj = new JSONObject();
				
                ubcObj.put("ACCOUNTID", ubclist.get(c)[0]);
                ubcObj.put("USERNAME", ubclist.get(c)[1]);
                ubcObj.put("REFERRER", ubclist.get(c)[2]);
                ubcObj.put("PHONECOUNT", ubclist.get(c)[3]);
				
                ubcArr.put(ubcObj);
			}
			
			return ubcArr.toString();
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/queryphones", method = RequestMethod.GET)
	@ResponseBody
	public String getuserphones(@RequestParam("accountid") String accountid) {
		try {
			List<agentEntity> phlist = agdao.getPhoneList(accountid);
			JSONArray phArr = new JSONArray();
			
			if (phlist.size() > 0) {
				for (int p = 0; p < phlist.size(); p++) {
	                JSONObject phObj = new JSONObject();
					
	                phObj.put("MOBILE", phlist.get(p).getMobile());
	                phObj.put("IMTYPE", phlist.get(p).getImtype());
	                phObj.put("IMID", phlist.get(p).getImid());
	                phObj.put("SHOPNAME", phlist.get(p).getShopname());
	                phObj.put("JOINEDDATE", tsdd.format(fsdf.parse(phlist.get(p).getJoineddate())));
	                if (phlist.get(p).getRemark() == null || phlist.get(p).getRemark().equals("")) {
	                	phObj.put("REMARK", "");
	                } else {
	                	phObj.put("REMARK", phlist.get(p).getRemark());
	                }
					
	                phArr.put(phObj);
				}
				
				return phArr.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return null;
		}
	}
	
	@RequestMapping(value = "/querybanks", method = RequestMethod.GET)
	@ResponseBody
	public String getuserbanks(@RequestParam("accountid") String accountid) {
		List<bankEntity> bklist = bkdao.getBankList(accountid);
		JSONArray bkArr = new JSONArray();
		
		if (bklist.size() > 0) {
			for (int b = 0; b < bklist.size(); b++) {
                JSONObject bkObj = new JSONObject();
				
                bkObj.put("BANKCODE", bklist.get(b).getBankcode());
                bkObj.put("BANKACCOUNT", bklist.get(b).getBankaccount());
                bkObj.put("ISENABLED", bklist.get(b).getIsenabled());
                bkObj.put("ISDEFAULT", bklist.get(b).getIsdefault());
				
                bkArr.put(bkObj);
			}
			
			return bkArr.toString();
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/updatebankdefault", method = RequestMethod.POST)
	@ResponseBody
	public int updatedefaultbank(HttpServletRequest request) {
		try {
			JSONObject defbank = new JSONObject(request.getParameter("bankstr"));
			
			bkdao.updateDefault(defbank.getString("ACCOUNTID"), defbank.getString("BANKCODE"), defbank.getString("BANKACCOUNT"));
			
			return 0;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return 1;
		}	
	}
	
	@RequestMapping(value = "/updatebankenabled", method = RequestMethod.POST)
	@ResponseBody
	public int updatebankenabled(HttpServletRequest request) {
		try {
			JSONObject enabank = new JSONObject(request.getParameter("bankena"));
			
			bkdao.updateenabled(enabank.getString("ACCOUNTID"), enabank.getString("BANKCODE"), enabank.getString("BANKACCOUNT"), enabank.getString("ISENABLED"));
			
			return 0;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return 1;
		}	
	}
	
	
	@RequestMapping(value = "/createnewbank", method = RequestMethod.POST)
	@ResponseBody
	public int addnewbank(HttpServletRequest request) {
		try {
			JSONObject banknew = new JSONObject(request.getParameter("bankinfo"));
			bankEntity bkent = new bankEntity();
			
			bkent.setAccountid(banknew.getString("ACCOUNTID"));
			bkent.setBankcode(banknew.getString("BANKCODE"));
			bkent.setBankaccount(banknew.getString("BANKACCOUNT"));
			bkent.setIsdefault(banknew.getString("ISDEFAULT"));
			bkent.setIsenabled(banknew.getString("ISENABLED"));
			
			bkdao.saveAndFlush(bkent);
			
			return 0;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return 1;
		}	
	}
	
	@RequestMapping(value = "/createnewphone", method = RequestMethod.POST)
	@ResponseBody
	public int addnewphone(HttpServletRequest request) {
		try {
			JSONObject phonenew = new JSONObject(request.getParameter("phoneinfo"));
			agentEntity phent = new agentEntity();
			
			List<Object[]> refinfo = usdao.getreferrer(phonenew.getString("ACCOUNTID"));
			
			phent.setAccountid(phonenew.getString("ACCOUNTID"));
			phent.setImtype(phonenew.getString("IMTYPE"));
			phent.setImid(phonenew.getString("IMID"));
			phent.setShopname(phonenew.getString("SHOPNAME"));
			phent.setMobile(phonenew.getString("MOBILE"));
			phent.setReferrer(refinfo.get(0)[0].toString());
			phent.setReferrerid(refinfo.get(0)[1].toString());
			phent.setJoineddate(phonenew.getString("JOINEDDATE"));
			phent.setRemark(phonenew.getString("REMARK"));
			
			agdao.saveAndFlush(phent);
			
			return 0;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return 1;
		}	
	}
}
