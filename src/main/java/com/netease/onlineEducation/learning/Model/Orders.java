package com.netease.onlineEducation.learning.Model;

import org.springframework.stereotype.Component;

@Component
public class Orders {
	private int id;
	private int userid;
	private int goodsid;
	private double price;
	private int amount;
	private String time;
	
	public Orders() {
		super();
	}
	public Orders(int id, int userid, int goodsid, double price, int amount, String time) {
		super();
		this.id = id;
		this.userid = userid;
		this.goodsid = goodsid;
		this.price = price;
		this.amount = amount;
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
