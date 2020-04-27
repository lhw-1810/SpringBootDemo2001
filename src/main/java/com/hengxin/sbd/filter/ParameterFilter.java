package com.hengxin.sbd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "parameterFilter", urlPatterns = "/**")
public class ParameterFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException {
		LOGGER.debug("ACTION ParameterFilter !");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {
			@Override
			public String getParameter(String name) {
				String value = req.getParameter(name);
				if (StringUtils.isNotBlank(value)) {
					return value.replaceAll("fuck", "***");
				}
				return super.getParameter(name);
			}

			@Override
			public String[] getParameterValues(String name) {
				String[] values = req.getParameterValues(name);
				if (values != null && values.length > 0) {
					values[0] = values[0].replaceAll("fuck", "***");
					return values;
				}
				return super.getParameterValues(name);
			}
		};
		// 放行
		fc.doFilter(wrapper, resp);
	}

	@Override
	public void destroy() {
		LOGGER.debug("DESTROY ParameterFilter !");
		Filter.super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("INIT ParameterFilter !");
		Filter.super.init(filterConfig);
	}

}
