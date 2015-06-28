package com.dexter.dao;

import java.net.UnknownHostException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class MongoDAO {
	private static MongoClient client=null;
	
	public static MongoClient Connect() throws UnknownHostException{
		if(client!=null)
			return client;
		try{
		client = new MongoClient("localhost" , 27017);
		}catch(UnknownHostException ex){
			System.err.println(ex);
		}
		return client;
	}

	public static void TryInsert(String from,String to,String message){
        DB db = client.getDB("test");
        DBCollection msgs = db.getCollection("msgs");
        msgs.insert(new BasicDBObject("from",from).append("to",to).append("message",message));
	}
	
	public static void InsertJson(JSONObject obj){
		DB db = client.getDB("IoT");
		DBCollection clnt=db.getCollection("clients");
		BasicDBObject dbj = (BasicDBObject)JSON.parse(obj.toString());
		clnt.insert(dbj);
	}
	
	public static JSONObject QuerySupportedDevice(JSONObject device) throws JSONException{
		DB db=client.getDB("IoT");
		DBCollection clnt= db.getCollection("bootstrap");
		//BasicDBObject dbj = (BasicDBObject)JSON.parse(device.toString());
		DBCursor rst= clnt.find(new BasicDBObject().append("Manufacturer", device.get("Manufacturer"))
				.append("Model",device.get("Model")));
		if(rst.hasNext()) {
			JSONObject obj = new JSONObject(rst.next().toString());
			return obj;
		}else{
			JSONObject obj = new JSONObject().append("status", "unsupported");
			return obj;
		}
		
	}
	
	public static void DisConnect(){
		client.close();
	}
	
	//only use main to test methods
	public static void main(String[] args) throws UnknownHostException{
		Connect();
		TryInsert("sender","receiver","text");
		TryInsert("sender","receiver","text");
		TryInsert("sender","receiver","text");
		TryInsert("sender","receiver","text");
		DisConnect();
	}
}
