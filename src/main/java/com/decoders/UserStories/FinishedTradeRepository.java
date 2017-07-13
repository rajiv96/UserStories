package com.decoders.UserStories;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

public class FinishedTradeRepository {
	@Override
	public String toString()
	{
		String s = Integer.toString(buid) +", "+ Integer.toString(suid)+", "+Integer.toString(mid)+", "+Integer.toString(lid)+", "+ size +", "+ price +", "+ time+", "+currpair+"\n";
				return s;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getBuid() {
		return buid;
	}
	public void setBuid(int buid) {
		this.buid = buid;
	}
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
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
		return time;
	}
	public void setTime(Timestamp time2) {
		this.time = time2;
	}
	private int fid ;
	private int buid ;
	private int suid ;
	private int mid ;
	private int lid ;
	private String currpair;
	private int size;
	private double price;
	private Timestamp time;
}
