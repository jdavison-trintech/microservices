package com.trintech.msw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.trintech.msw.models.Echo;
import com.trintech.msw.models.TenantCfg;

@Controller
@RequestMapping(value="/")
public class EchoController extends BaseController {

	@RequestMapping(value="/echo", method= RequestMethod.GET )
	public @ResponseBody Echo echoGet(WebRequest request){
		TenantCfg tenantCfg = getTenantCfg(request);
		return new Echo("GET" +":" +  tenantCfg.getName() + " : " + tenantCfg.getPlatform());
	}


	@RequestMapping(value="/echo", method= RequestMethod.POST )
	public @ResponseBody Echo echoPost(WebRequest request, @RequestBody String body){
		TenantCfg tenantCfg =  getTenantCfg(request);
		return new Echo("POST" +":" +  body + tenantCfg.getName());
	}
	
}
