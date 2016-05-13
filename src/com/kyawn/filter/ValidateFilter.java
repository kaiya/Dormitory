package com.kyawn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateFilter implements Filter {

	private ServletContext sc;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		// 1.可以进行配置（用户访问的页面，都可以在web.xml中进行配置）
		String login_page = sc.getInitParameter("login_page");
		String validate_page = sc.getInitParameter("validate_page");
		String common_page = sc.getInitParameter("common_page");
		String current_url = req.getServletPath();
		if (common_page.indexOf(current_url) != -1) {
			chain.doFilter(request, response);
		} else if (validate_page.indexOf(current_url) != -1 && session.getAttribute("user") != null) {
			chain.doFilter(request, response);
		} else {
			if (current_url.equals("/login.jsp")) {
			} else {
				session.setAttribute("return_uri", current_url);
				rep.sendRedirect(req.getContextPath() + login_page);
			}

		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

		sc = config.getServletContext();
	}

}
