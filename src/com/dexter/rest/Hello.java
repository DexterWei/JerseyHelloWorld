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
	
	private static ArrayList<ArrayList<conversation>> chats = new ArrayList<ArrayList<conversation>>();
	public Hello(){
		if(chats.isEmpty()){
			for(int i=0;i<3;i++){
				chats.add( new ArrayList<conversation>());
			}
		}
	}
	
	@Path("/chats/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ArrayList<conversation>> ReturnChatsJSON(){
		return chats;
	}
	
	@Path("/chats/post")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateChatsJSON(conversation chat){
		chats.get(chat.sender.charAt(0)-'1').add(chat);
		String rst=""+chats.get(chat.sender.charAt(0)-'1');
		return Response.status(201).entity(rst).build();
	}
	
}
