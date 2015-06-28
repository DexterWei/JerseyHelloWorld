package com.dexter.rest;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.dexter.conversation;
import com.dexter.dao.MongoDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

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
	
	@Path("/chats/json")
	@GET
	@Produces("application/json")
	//try $curl --request GET 'http://localhost:8080/com.dexter.rest/rest/chats/json'
	public Response ReturnAppJson(){
		return Response.ok(chats).build();
	}
	
	
	@Path("/chats/post")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateChatsJSON(String objString) throws UnknownHostException, JSONException {
		MongoDAO dao = new MongoDAO();
		MongoDAO.Connect();
		JSONObject obj = new JSONObject(objString);
		MongoDAO.InsertJson(obj);
		return Response.status(201).entity(obj.toString()).build();
	}
	
}
