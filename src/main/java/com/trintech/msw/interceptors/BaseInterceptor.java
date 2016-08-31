package com.trintech.msw.interceptors;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.xml.bind.Unmarshaller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseInterceptor implements  HandlerInterceptor{
	
	protected static Unmarshaller u;
	
	
	
	protected void setAttribute(HttpServletRequest request, String key, Object value) {
		request.setAttribute(key, value);
	}

	private String resourcePath = "/%s/%s/%s";
	private String defaultTenantName = String.format(resourcePath, "WEB-INF", "tenants", "default.xml");
	
	protected InputStream getResource(HttpServletRequest request, String path, String name) {
		InputStream is = request.getSession().getServletContext().getResourceAsStream(String.format(resourcePath, "WEB-INF", path, name)); 
		if(is == null){
			return request.getSession().getServletContext().getResourceAsStream(defaultTenantName);
		}
		return is;
	}


	protected Object getAttribute(HttpServletRequest request, String key) {
		return request.getAttribute(key);
	}

}
