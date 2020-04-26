package com.hengxin.sbd.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WebServerConfig {

	@Value("${http.port}")
	private int httpPort;

	@Bean
	public Connector connector() {
		Connector conn = new Connector();
		conn.setScheme("http");
		conn.setPort(httpPort);
		return conn;
	}

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		// 添加额外的Tomcat服务连接器
		serverFactory.addAdditionalTomcatConnectors(connector());
		return serverFactory;
	}
}
