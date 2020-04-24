package com.hengxin.sbd.modules.test.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/applicationTest.properties")
@ConfigurationProperties(prefix = "com.thorn.bird")
public class ConfigBean {
	private int port;
	private String name;
	private int age;
	private String desc;
	private String random;

	public ConfigBean() {
		super();
	}

	public ConfigBean(int port, String name, int age, String desc, String random) {
		super();
		this.port = port;
		this.name = name;
		this.age = age;
		this.desc = desc;
		this.random = random;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}
}
