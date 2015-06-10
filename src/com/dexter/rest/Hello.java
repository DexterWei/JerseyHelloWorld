package com.dexter.rest;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Hello {
	private static final String version="1.0.0";
	private static ArrayList<String> messages = new ArrayList<String>();
	@Path("/hello/{visitor}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String ReturnTitleHTML(@PathParam("visitor") String Name){
		messages.add(Name);
		String rst="";
		for(String msg :messages){
			rst+="<p>"+msg+"</p>";
		}
		return "<h1>Hello "+Name + ", Welcome to Dexter's webapp</h1>"+rst;
	}
	
	@Path("/hello/{visitor}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ReturnTitleTEXT(@PathParam("visitor") String Name){
		messages.add(Name);
		String rst="";
		for(String msg :messages){
			rst+=msg+"\n";
		}
		return "Hello "+Name + ", Welcome to Dexter's webapp\n"+rst;
	}
	
	@Path("/messages")
	@POST
	@Consumes("text/plain")
	public String postClichedMessage(String msg) {
	    // Store the message
		messages.add(msg);
		return "copy that";
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
