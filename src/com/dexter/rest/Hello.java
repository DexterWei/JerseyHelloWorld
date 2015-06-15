package com.dexter.rest;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dexter.conversation;

@Path("/")
public class Hello {

	private static final String version="1.0.0";
	private static ArrayList<String> messages = new ArrayList<String>();
	
	private static ArrayList<conversation> chats = new ArrayList<conversation>();
	
	@Path("/chats/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<conversation> ReturnChatsJSON(){
//		conversation chat = new conversation();
//		chat.sender="Server";
//		chat.receiver=Name;
//		chat.message="Hi "+chat.receiver+", welcome to visit "+chat.sender;
//
//		chats.add(chat);
		return chats;
	}
	
	@Path("/chats/post")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateChatsJSON(conversation chat){
		String rcv=""+chat;
		chats.add(chat);
		return Response.status(201).entity(rcv).build();
	}
	
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
