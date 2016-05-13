package com.kyawn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Charset implements Filter {

	private String encoding = "UTF-8";
	protected FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		// 本过滤器默认编码是UTF-8，但也可以在web.xml配置文件里设置自己需要的编码
		if (filterConfig.getInitParameter("encoding") != null)
			encoding = filterConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest srequset, ServletResponse sresponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequset;
		request.setCharacterEncoding(encoding);
		filterChain.doFilter(srequset, sresponse);
	}

	public void destroy() {
		this.encoding = null;
	}

}