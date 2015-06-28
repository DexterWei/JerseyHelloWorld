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
	
	@Path("/chats/post")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateChatsJSON(String objString) throws UnknownHostException, JSONException {
		MongoDAO dao = new MongoDAO();
		MongoDAO.Connect();
		JSONObject obj = new JSONObject(objString);
		JSONObject rst = MongoDAO.QuerySupportedDevice(obj);
		return Response.status(201).entity(rst.toString()).build();
	}
	
}
