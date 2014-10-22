package com.zyngl.critique.model


class Movie extends BaseModel{

	String name
	Date releaseDate
	String description
	String genre  //example thriller, action etc.
	String language
	BigDecimal rating
	SortedSet actors
	SortedSet directors
	SortedSet musicDirectors
	SortedSet tags
	
	List movieArt
	List trailers
	
	Boolean active = true
	Boolean audited = false

	
	static hasMany = [actors:Actor,
		directros:Director,
		musicDirectors:MusicDirector,
		movieArt:ImageArt,
		trailers:Trailer,
		tags:String]
		
	static fetchMode = [actors:"lazy",
			  directros:"lazy",
			  musicDirectors:"lazy",
			  movieArt:"lazy",
			  trailers:"lazy",
			  tags:"lazy"]
	
	static constraints = {
		releaseDate(nullable:true)
		description(nullable:true)
		genre(nullable:true)
		language(nullable:true)
		rating(nullable:true)
	}
	
	static mapping = {
		movieArt cascade:"all,delete-orphan"
		trailers cascade:"all,delete-orphan"
		name column:'Name', index:'Movie_Name_Idx'
	}

	
	@Override
	public Object transform(Object args) {
		// TODO Auto-generated method stub
		return null;
	}
}
