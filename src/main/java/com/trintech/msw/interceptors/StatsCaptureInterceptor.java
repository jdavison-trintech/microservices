package com.trintech.msw.interceptors;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.trintech.msw.AppConfig;
import com.trintech.msw.AppConfig.Options;

public class StatsCaptureInterceptor extends BaseInterceptor {

	private final Logger logger = LoggerFactory.getLogger(StatsCaptureInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		captureHeaderInfo(request, request.getHeaderNames());
		captureSessionInfo(request.getSession(), request.getSession().getAttributeNames());
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		request.getSession().invalidate();

	}
	
	
	private void captureHeaderInfo(HttpServletRequest request, Enumeration<?> e) {
		if (AppConfig.getBoolean(Options.VERBOSE)) {
			StringBuilder sb = new StringBuilder();
			sb.append("\nRequest Headers\n");
			while (e.hasMoreElements()) {

				String name = (String) e.nextElement();
				sb.append("\t").append(name).append(" : ").append(request.getHeader(name)).append("\n");

			}
			sb.append("/Request Headers");
			logger.info(sb.toString());
		}
	}
	
	private void captureSessionInfo(HttpSession session, Enumeration<?> e) {
		if (AppConfig.getBoolean(Options.VERBOSE)) {
			StringBuilder sb = new StringBuilder();
			sb.append("\nSession Attributes\n");
			sb.append("\tSession Create Time: " +String.valueOf(session.getCreationTime()) + "\n");
			
			while (e.hasMoreElements()) {

				String name = (String) e.nextElement();
				sb.append("\t").append(name).append(" : ").append(session.getAttribute(name)).append("\n");

			}
			sb.append("/Session Attributes\n");
			logger.info(sb.toString());
			
		}
	}
}
