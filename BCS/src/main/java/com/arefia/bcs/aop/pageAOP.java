package com.arefia.bcs.aop;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arefia.bcs.dao.menuDao;
import com.arefia.bcs.dao.usageDao;
import com.arefia.bcs.utility.detectIsAdmin;

@Aspect
@Component
public class pageAOP {
private static final Logger log = LogManager.getLogger(pageAOP.class);
	
	@Autowired
	menuDao menudao;
	
	@Autowired
	usageDao usagedao;
	
	@Autowired
	detectIsAdmin isadmin;
	
	@Pointcut("execution(* com.arefia.WOSS.controller.*.*(..))")
    public void pageLog() {}
	
	@Before("pageLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object targetObject  = joinPoint.getTarget();
		String objectName = targetObject.toString();
		
		objectName = objectName.substring(objectName.lastIndexOf(".") + 1, objectName.indexOf("@"));
		
		if (!objectName.equals("errorController") && !objectName.equals("denyController")) {
			try {
				if (auth == null) {
					response.sendRedirect("/deny");
				} else {
					String acccnt = menudao.getAccessSatus(objectName, auth.getName());
					
					if (!isadmin.isAdmin() && (acccnt == null || Integer.parseInt(acccnt) <= 0)) {
						response.sendRedirect("/deny");
					} else {
						String appid = menudao.getAppID(objectName);			
						String ipadd;
						
						if (request.getHeader("X-FORWARDED-FOR") == null || request.getHeader("X-FORWARDED-FOR").equals("")) {
							ipadd = request.getRemoteAddr();
				        } else {
				        	ipadd = request.getHeader("X-FORWARDED-FOR");
				        }
						
						String isNew = usagedao.isInsertAllow(appid, auth.getName());
						
						if (isNew == null || isNew.equals("1")) {
							usagedao.insertUsage(UUID.randomUUID().toString(), auth.getName(), appid, ipadd);
						} else {
							usagedao.updatetUsage(ipadd, isNew);
						}
					}
				}
			} catch(Exception e) {
				log.error(e.getLocalizedMessage(), e);
				response.sendRedirect("/deny");
			}
		}
	}
}
