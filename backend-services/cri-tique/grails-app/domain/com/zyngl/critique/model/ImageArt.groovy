package com.zyngl.critique.model

import com.zyngl.enums.ARTTYPE
import com.zyngl.enums.IMAGETYPE
import java.util.Date;
import java.util.SortedSet;

class ImageArt extends BaseModel{

	String url
	IMAGETYPE type
	ARTTYPE artType

	static constraints = {
    }


	@Override
	public Object transform(Object args) {
		// TODO Auto-generated method stub
		return null;
	}
}
