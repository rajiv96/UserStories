package com.decoders.UserStories;

import java.sql.Timestamp;

public class CancelledTradeRepository {
private int id;
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
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public Timestamp getTime() {
	return Time;
}
public void setTime(Timestamp time) {
	Time = time;
}
public int getLimittime() {
	return Limittime;
}
public void setLimittime(int i) {
	Limittime = i;
}
public String getCurrpair() {
	return currpair;
}
public void setCurrpair(String currpair) {
	this.currpair = currpair;
}
public String getTradetype() {
	return tradetype;
}
public void setTradetype(String tradetype) {
	this.tradetype = tradetype;
}
private int uid;
private int size;
private String Type;
private Double price;
private Timestamp Time;
private int Limittime;
private String currpair;
private String tradetype;

}
