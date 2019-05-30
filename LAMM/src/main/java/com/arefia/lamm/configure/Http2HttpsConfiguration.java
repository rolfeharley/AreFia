package com.arefia.lamm.configure;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.undertow.UndertowOptions;

@Configuration
public class Http2HttpsConfiguration {
	@Bean
	public UndertowServletWebServerFactory undertowServletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addBuilderCustomizers(builder -> {
        	builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true).setServerOption(UndertowOptions.HTTP2_SETTINGS_ENABLE_PUSH,true);
        });
        return factory;
    }
}
