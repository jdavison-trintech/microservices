package com.trintech.msw.models;

import java.io.Serializable;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="request")
public class DataTypeObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4434387738584708164L;
	@XmlTransient
	private UUID id = UUID.randomUUID();

	public DataTypeObject(){
		
	}
	
	protected Boolean validate(Pattern pat, String value){
		return pat.matcher(value).matches();
	}
	
	
	public UUID getId(){
		return id;
	}
}
