package com.trintech.msw;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.trintech.msw.AppConfig.Options;

@Component
@ComponentScan("com.trintech.msw")
public class AppConfig extends WebMvcConfigurerAdapter{
	public enum Options{
		VERBOSE, RESPONSE_THRESHOLD;
		
		static {
			for(Options e : Options.values()){
				lookup.put(e.name(), e);
			}
		}
		
		public Enum<?> lookup(String key){
			return lookup.get(key);
		}
	}
	
	private static Map<String, Enum<Options>> lookup = new HashMap<String, Enum<Options>>();
	private static Map<Enum<Options>, Object> appOptions = new HashMap<Enum<Options>, Object>();
	
	public AppConfig(){
		loadDefaults();
	}
	
	/**
	 * Default Option.  Will be overrode when options are loaded from source.
	 */
	private static void loadDefaults() {
		appOptions.put(Options.VERBOSE, Boolean.TRUE); 
		appOptions.put(Options.RESPONSE_THRESHOLD, 10L); //default 5 sec response time.
	}

	public static boolean getBoolean(Options option) {
		return (Boolean) appOptions.get(option);
	}

	public static long getLong(Options option) {
		return (Long) appOptions.get(option);
	}

	public static String getString(Options option) {
		return String.valueOf(appOptions.get(option));
	}
}
