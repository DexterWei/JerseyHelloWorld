package com.dexter.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
	private static final String version="1.0.0";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String ReturnTitleHTML(){
		return "<h1>Hello Dexter</h1>";
	}
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ReturnTitleTEXT(){
		return "Hello Dexter";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String ReturnVersionHTML(){
		return "<h3>version: "+ version+"</h3>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ReturnVersionTEXT(){
		return "Version: "+version;
	}
}
