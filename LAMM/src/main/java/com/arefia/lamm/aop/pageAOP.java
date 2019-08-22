package com.arefia.lamm.aop;

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

import com.arefia.lamm.dao.menuDao;
import com.arefia.lamm.utility.detectIsAdmin;

@Aspect
@Component
public class pageAOP {
private static final Logger log = LogManager.getLogger(pageAOP.class);
	
	@Autowired
	menuDao menudao;
	
	@Autowired
	detectIsAdmin isadmin;
	
	@Pointcut("execution(* com.arefia.lamm.controller.*.*(..))")
    public void pageLog() {}
	
	@Before("pageLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object targetObject  = joinPoint.getTarget();
		String objectName = targetObject.toString();
		
		objectName = objectName.substring(objectName.lastIndexOf(".") + 1, objectName.indexOf("@"));
		
		if (!objectName.equals("errorController") && !objectName.equals("denyController") && !objectName.equals("lineCallbackController") && 
			!objectName.equals("unsupportedController") && !objectName.equals("environmentController") && 
			!objectName.equals("zohoCallbackController") && !objectName.equals("zohotestcontroller") &&
			!objectName.equals("websocketConnectionInitial") && !objectName.equals("websocketPushMessage")) {
			try {
				if (auth == null || auth.getName() == null || auth.getName().equals("") || auth.getName().equals("anonymousUser")) {
					response.sendRedirect("/deny");
				} else {
					String acccnt = menudao.getAccessSatus(objectName, auth.getName());
					
					if (!isadmin.isAdmin() && (acccnt == null || Integer.parseInt(acccnt) <= 0)) {
						response.sendRedirect("/deny");
					}
				}
			} catch(Exception ex) {
				log.error(ex.getLocalizedMessage(), ex);
				response.sendRedirect("/deny");
			}
		}
	}
}
