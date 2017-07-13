package com.decoders.UserStories;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class HistoricTradesRepository {
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public int getBuid() {
		return buid;
	}
	public void setBuid(int buid) {
		this.buid = buid;
	}
	public String getCurrpair() {
		return currpair;
	}
	public void setCurrpair(String currpair) {
		this.currpair = currpair;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
	private int suid;
	private int buid; 
	private String currpair ;
	private int size; 
	private double price ;
	private Timestamp Time;
}
