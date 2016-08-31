package com.trintech.msw.controllers;

import org.springframework.web.context.request.WebRequest;

import com.trintech.msw.AppConstants;
import com.trintech.msw.models.TenantCfg;


public class BaseController {
	
	protected TenantCfg getTenantCfg(WebRequest request) {
		return (TenantCfg) request.getAttribute(AppConstants.TENANT_CFG.getValue(), 0);
	}

}
