package com.trintech.msw.interceptors;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.trintech.msw.AppConstants;
import com.trintech.msw.exception.InterceptorException;
import com.trintech.msw.models.TenantCfg;

public class MultiTenantInterceptor extends BaseInterceptor {

	private final static Logger logger = LoggerFactory.getLogger(MultiTenantInterceptor.class);
	
	static {
		try {
			u = JAXBContext.newInstance(TenantCfg.class).createUnmarshaller();
		} catch (JAXBException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		try
		{
			loadTenantConfig(request);

		} catch (InterceptorException e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	private void loadTenantConfig(HttpServletRequest request) throws InterceptorException {
		String tenant;
		try {
			tenant = getTenant(request);

			setAttribute(request, tenant, AppConstants.TENANT.getValue());

			TenantCfg tenantCfg = (TenantCfg) u.unmarshal(getResource(request, "tenants", tenant.concat(".xml")));
			logger.info("Loaded tenant '" + tenant + "' for SessionID: " + request.getSession().getId());
			request.setAttribute(AppConstants.TENANT_CFG.getValue(), tenantCfg);

		} catch (Exception e) {
			throw new InterceptorException(this.getClass().getName() + "::" + e.getMessage(), e.getCause());
		}

	}

	private String getTenant(HttpServletRequest request) throws UnknownHostException {
		String host = request.getHeader(AppConstants.HOST.getValue());
		if (host.indexOf(":") > 0) {
			return host.substring(0, host.indexOf(":"));
		} else if (host.lastIndexOf(".") > 0) {
			return host.substring(host.lastIndexOf("."));
		} else {
			throw new UnknownHostException(host);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
