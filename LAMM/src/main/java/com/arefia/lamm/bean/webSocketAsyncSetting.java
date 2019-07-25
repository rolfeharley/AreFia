package com.arefia.lamm.bean;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

public class webSocketAsyncSetting {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean dispatcherWebSocket() {
	    ServletRegistrationBean websreg = new ServletRegistrationBean(new DispatcherServlet(), "/");
	    websreg.setAsyncSupported(true);
	    return websreg;
	}
}
