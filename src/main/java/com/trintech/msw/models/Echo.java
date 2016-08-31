package com.trintech.msw.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class Echo {

	@XmlElement
	private String value;
	
	public Echo(){
		
	}

	public Echo(String _echo) {
		this.value = _echo;
	}

	public String getEcho() {
		return value;
	}
}
