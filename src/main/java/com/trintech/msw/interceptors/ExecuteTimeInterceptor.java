package com.trintech.msw.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.trintech.msw.AppConfig;
import com.trintech.msw.AppConfig.Options;
import com.trintech.msw.AppConstants;

public class ExecuteTimeInterceptor extends BaseInterceptor {
	
	private final Logger logger = LoggerFactory.getLogger(ExecuteTimeInterceptor.class);
	
	private String LONG_RESPONSE_MSG = "The execution time for '%s' service, exceeded the response threshold for SessionID %s.  Expected <= %s ms but was %d ms ";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		setAttribute(request, AppConstants.START_TIME.getValue(), System.currentTimeMillis());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long) getAttribute(request, AppConstants.START_TIME.getValue());
		long elapsedTime = System.currentTimeMillis() - startTime;
		
		logger.info(request.getSession().getId() + " elapsedTime: " + String.valueOf(elapsedTime));
		if(elapsedTime > AppConfig.getLong(AppConfig.Options.RESPONSE_THRESHOLD)){
			logger.warn(String.format(LONG_RESPONSE_MSG, request.getRequestURI(), request.getSession().getId(), AppConfig.getString(Options.RESPONSE_THRESHOLD), elapsedTime));
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
