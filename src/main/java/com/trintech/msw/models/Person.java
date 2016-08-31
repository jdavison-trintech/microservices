package com.trintech.msw.models;

import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
public class Person extends DataTypeObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4049089735768869655L;
	

	private UserName name;
//	private Email email;
//	private Country country;
	
	
	
	public Person(){
		
	}

	public static class UserName extends DataTypeObject{
		
		private Pattern pattern = Pattern.compile("\\w");
		private String name;
		
		public UserName(String _name){
				if(validate(pattern, name)){
					this.name = _name;
				}else{
//					throw new InvalidDataTypeObjectException();
				}
			
		}
	}
}
