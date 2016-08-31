package com.trintech.msw.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler. HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

	static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("Prehandle '" + request.getRequestURI() + "' for session Id " + request.getSession().getId());
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("Posthandle "+ request.getRequestURI() + " for session Id " + request.getSession().getId());
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("Object or ModelAndView Returned " + request.getRequestURI() + " for session Id " + request.getSession().getId());
		
		super.afterCompletion(request, response, handler, ex);
	}
}
