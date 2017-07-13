package com.decoders.UserStories;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;
@Component
public class TradeRepository {
	private int Limittime;
	@Override
	public String toString()
	{
		
		
		String s = Integer.toString(id) +", "+ Integer.toString(uid)+", "+ size +", "+ Type +", "+ price +", "+ Time+", "+currpair+", "+tradetype+"\n";
				return s;
	}
	public int getLimittime() {
		return this.Limittime;
	}
	public void setLimittime(int limittime) {
		this.Limittime = limittime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Type getType() {
		return Type;
	}
	public void setType(Type type) {
		Type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Timestamp getTime() {
		return Time;
	}
	public void setTime(Timestamp time) {
		Time = time;
	}
	public String getCurrpair() {
		return currpair;
	}
	public void setCurrpair(String currpair) {
		this.currpair = currpair;
	}
	public tradetype getTradetype() {
		return tradetype;
	}
	public void setTradetype(tradetype tradetype) {
		this.tradetype = tradetype;
	}
	private int id ;
	private int uid; 
	private int size ;
	private Type Type ;
	private double price; 
	private Timestamp Time ;

	private String currpair ;
	private tradetype tradetype ;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String username;
}
