package com.trintech.msw;

import java.util.HashMap;
import java.util.Map;

public enum AppConstants {

	TENANT_CFG("tenantCfg"),
	TENANT("tenant"),
	HOST("host"),
	START_TIME("startTime");
	
	private static Map<String, Enum<AppConstants>> lookup = new HashMap<String, Enum<AppConstants>>();
	
	
	static {
		for(AppConstants e : AppConstants.values()){
			
			lookup.put(e.name(), e);
		}
	}
	
	
	public Enum<?> lookup(String key){
		return lookup.get(key);
	}

	private String value;

	AppConstants(String _value) {
		this.value = _value;
	}

	public String getValue() {
		return value;
	}

}
