package com.decoders.UserStories;

import java.time.LocalTime;


public class MarketOrderRepository {
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	private int mid;
	private int uid;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public LocalTime getTime() {
		return Time;
	}
	public void setTime(LocalTime time) {
		Time = time;
	}
	public String getCurrpair() {
		return currpair;
	}
	public void setCurrpair(String currpair) {
		this.currpair = currpair;
	}
	private double price;
	Type type;
	private int size;
	private LocalTime Time  ;
	String currpair;
	
    }

