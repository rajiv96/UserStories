package com.decoders.UserStories;

import java.time.LocalTime;

public class LimitOrderRepository {
private int lid;
public int getLid() {
	return lid;
}
public void setLid(int lid) {
	this.lid = lid;
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
	return type;
}
public void setType(Type type) {
	this.type = type;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public LocalTime getTime() {
	return time;
}
public void setTime(LocalTime time) {
	this.time = time;
}
public String getCurrpair() {
	return currpair;
}
public void setCurrpair(String currpair) {
	this.currpair = currpair;
}
private int  uid;
private int size;
private Type type;
private int price;
private LocalTime time;
private String currpair;
}
