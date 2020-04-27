package com.hengxin.sbd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hengxin.sbd.filter.ParameterFilter;
import com.hengxin.sbd.interceptor.UrlInterceptor;

@Configuration
@AutoConfigureAfter({ WebMvcAutoConfiguration.class })
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private UrlInterceptor urlInterceptor;

	@Bean
	public FilterRegistrationBean<ParameterFilter> filterRegistrationBean() {
		FilterRegistrationBean<ParameterFilter> filterRegister = new FilterRegistrationBean<>();
		filterRegister.setFilter(new ParameterFilter());
		return filterRegister;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
	}

}
