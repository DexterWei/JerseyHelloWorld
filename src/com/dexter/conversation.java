package com.dexter;

public class conversation {
	public String sender;
	public String receiver;
	public String message;
	public String toString(){
		String str="";
		str="from:"+sender+",to:"+receiver+",message:"+message+"\n";
		return str;
	}
}
