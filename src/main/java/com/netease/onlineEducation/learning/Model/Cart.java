package com.netease.onlineEducation.learning.Model;

import org.springframework.stereotype.Component;

@Component
public class Cart {
	private int id;
	private int userid;
	private int goodsid;
	private double price;
	private int amount;
	private String goodsname;
	
	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public Cart() {
		super();
	}

	public Cart(int userid, int goodsid, double price, int amount, String goodsName) {
		super();
		this.userid = userid;
		this.goodsid = goodsid;
		this.price = price;
		this.amount = amount;
		this.goodsname = goodsName;
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
