package com.zyngl.enums

enum ARTTYPE {
	EXTRALARGE("XXL"),
	LARGE("L"),
	MEDIUM("M"),
	SMALL("S"),
	THUMBNAIL("T")
	
	private final String value
   
	ARTTYPE(String value){
	 this.value = value;
	}
   
}
