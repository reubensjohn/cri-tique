package com.zyngl.critique.controller

import grails.converters.*

import com.zyngl.exception.Z409Exception
import com.zyngl.exception.ZException

abstract class BaseController<T>  {
	
	static responseFormats = ['json', 'xml'] //two formats that are supported, JSON is by default
	
	abstract protected T show(params)
	abstract protected T list(params)
	abstract protected T save(params)
	abstract protected T remove(params)
	abstract protected T update(params)
	
	protected def result
	
	//every call with /api/*  will come here
	def api() {
		
		try {
			switch (request.method) {
				
				case "GET":
					String objectid = params.id?.toLong()
		
				    if (objectid) { //if asking for a particular object
						result = show(params)
				    } else { //asking for a list of objects
						result = list(params)
					}
					break
					
				case "POST":
					result = save(params)
					break
					
				case "PUT":
					rsult = update(params)
					break
					
				case "DELETE":
					result = remove(params)
					break
			}
		}
		catch (org.springframework.dao.DataIntegrityViolationException e) {
			withFormat {
				form {
					flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cow.label', default: 'Cow'), params.id])}"
					redirect(action: "show", id: params.id)
				}
				xml {
					response.status = 409 // Conflict
					render "${message(code: 'default.not.deleted.message', args: [message(code: 'cow.label', default: 'Cow'), params.id])}"
				}
				json {
					response.status = 409 // Conflict
					render "${message(code: 'default.not.deleted.message', args: [message(code: 'cow.label', default: 'Cow'), params.id])}"
				}
			}
		}
		catch(Z409Exception e) {
			withFormat {

				xml {
					response.status = 409 // conflict
					render e.getMessage()
				}
				json {
					response.status = 409 // conflict
					render e.getMessage()
				}
			}
		}
		catch(ZException e) {
			withFormat {

				xml {
					response.status = 400 // bad input
					render e.getMessage()
				}
				json {
					response.status = 400 // bad input
					render e.getMessage()
				}
			}
		}
			if (!result) { //nothing found to render
				withFormat renderNotFound
			} else {
				response.status = 200 // OK
				withFormat{
				json{  render result as JSON }
				xml { renderMapAsXml result, "${root}"}
			}
		}
	}
	
    protected transform(objs, args) {
        return objs ? (objs instanceof List ? objs.collect { p -> p.transform(args) } : objs.transform(args)) : []
    }
	
	def renderNotFound = {
		html {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cow.label', default: 'Cow'), params.id])}"
			redirect(action: "list")
		}
		xml {
			response.status = 404
			render "Not found."
		}
		json  {
			response.status = 404
			render "Not found."
		}
	}
}
