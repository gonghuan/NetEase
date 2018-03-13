package com.netease.onlineEducation.learning.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	
	private FilterConfig config;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String urltmp = config.getInitParameter("filter_url");
		String[] urls = urltmp.split(";");
		String context = request.getContextPath();
		String url = request.getRequestURI();
		boolean flag = false;
		for(String uri : urls){
			if(url.equals(context + uri)){
				flag = true;
				break;
			}
		}
		if(flag || url.indexOf(".") > 0 || url.equals(context + "/")|| request.getSession().getAttribute("user") != null){
			chain.doFilter(request, response);
		}else{
			response.sendRedirect(context + "/login");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

}
