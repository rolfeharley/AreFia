package com.arefia.lamm.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arefia.lamm.bean.webCommunication;
import com.arefia.lamm.dao.areadataDao;
import com.arefia.lamm.dao.citysdataDao;
import com.arefia.lamm.dao.treedataDao;
import com.arefia.lamm.entity.areadataEntity;
import com.arefia.lamm.entity.citysdataEntity;
import com.arefia.lamm.entity.treedataEntity;
import com.arefia.lamm.model.webCommunicationModel;

@Controller
public class newUserInfoController {
	private static final Logger log = LogManager.getLogger(newUserInfoController.class);
	
	@Autowired
	webCommunication wcc;
	
	@Autowired
	citysdataDao cidao;
	
	@Autowired
	areadataDao ardao;
	
	@Autowired
	treedataDao trdao;
	
	@GetMapping("/memberinfo")
	public String createForm() {		
		return "/pages/newUserInfo";
	}
	
	@RequestMapping(value = "/getroccitys", method = RequestMethod.GET)
	@ResponseBody
	public String getROCCitys() {
		try {
			List<citysdataEntity> cityList = cidao.getAllCitysAsc();
			JSONArray cityArr = new JSONArray();
			
			for (int c = 0; c < cityList.size(); c++) {
				citysdataEntity city = cityList.get(c);
			    JSONObject cityObj = new JSONObject();
			    
			    cityObj.put("CITYID", city.getCityid());
			    cityObj.put("CITYNAME", city.getCityname());
			    
			    cityArr.put(cityObj);
			}
			
			return cityArr.toString();
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return "";
		}
	}
	
	@RequestMapping(value = "/getareaofcity", method = RequestMethod.GET)
	@ResponseBody
	public String getAreaOfCity(HttpServletRequest request) {
		try {
			if (request.getParameter("CITYID") == null) {
				return "";
			} else {
				List<areadataEntity> areaList = ardao.getAreaOfCityAsc(request.getParameter("CITYID").toString());
				JSONArray areaArr = new JSONArray();
				
				for (int a = 0; a < areaList.size(); a++) {
					areadataEntity area = areaList.get(a);
				    JSONObject areaObj = new JSONObject();
				    
				    areaObj.put("AREAID", area.getAreaid());
				    areaObj.put("AREANAME", area.getAreaname());
				    
				    areaArr.put(areaObj);
				}
				
				return areaArr.toString();
			}
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return "";
		}
	}
	
	@RequestMapping(value = "/gettreeofarea", method = RequestMethod.GET)
	@ResponseBody
	public String getTreeOfArea(HttpServletRequest request) {
		try {
			if (request.getParameter("AREAID") == null) {
				return "";
			} else {
				List<treedataEntity> treeList = trdao.getTreeOfAreaAsc(request.getParameter("AREAID").toString());
				JSONArray treeArr = new JSONArray();
				
				for (int t = 0; t < treeList.size(); t++) {
					treedataEntity tree = treeList.get(t);
				    JSONObject treeObj = new JSONObject();
				    
				    treeObj.put("TREEID", tree.getTreeid());
				    treeObj.put("TREENAME", tree.getTreename());
				    
				    treeArr.put(treeObj);
				}
				
				return treeArr.toString();
			}
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return "";
		}
	}
}
