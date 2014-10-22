package com.zyngl.critique.model

abstract class BaseModel {

    private static final long serialVersionUID = 1L;
    Date dateCreated
    Date lastUpdated
    static constraints = {
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
    }

    abstract public transform(args)

    protected static parseinclude(args, removeMeFromInclude = 'none') {
        def includes = args.include ? args.include.tokenize(",") : []
        includes.remove(removeMeFromInclude)
        args = args.clone()
        args.include = includes.join(',')
        return [args, includes]

    }
}
