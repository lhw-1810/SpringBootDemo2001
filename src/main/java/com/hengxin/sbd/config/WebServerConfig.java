package com.hengxin.sbd.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

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

	public ServletWebServerFactory servletWebServerFactory() {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		// 添加额外的Tomcat服务连接器
		serverFactory.addAdditionalTomcatConnectors(connector());
		return serverFactory;
	}
}
