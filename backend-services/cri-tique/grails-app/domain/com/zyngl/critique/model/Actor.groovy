package com.zyngl.critique.model

class Actor extends BaseModel {

    String name
    Date dob
    BirthPlace birthPlace;
    String description
    SortedSet movies
    SortedSet tags

    List artistArt

    Boolean active = true
    Boolean audited = false

    static hasMany = [movies   : Movie,
                      artistArt: ImageArt,
                      tags     : String]

    static fetchMode = [movies   : "lazy",
                        artistArt: "lazy",
                        tags     : "lazy"]

    static belongsTo = [Movie]

    static constraints = {
        dob(nullable: true)
        description(nullable: true)
        birthPlace(nullable: true)
    }

    static mapping = {
        artistArt cascade: "all,delete-orphan"
        name column: 'Name', index: 'Actor_Name_Idx'
    }

    @Override
    public Object transform(Object args) {
        return null;
    }
}
