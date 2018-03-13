package com.netease.onlineEducation.learning.Model;

import org.springframework.stereotype.Component;

@Component
public class Goods {
	private int id;
	private String name;
	private String abstracts;
	private String image;
	private double price;
	private String info;
	private int ownerid;
	
	
	
	public Goods() {
		super();
	}
	public Goods(String name, String abstracts, String image, double price, String info, int ownerid) {
		super();
		this.name = name;
		this.abstracts = abstracts;
		this.image = image;
		this.price = price;
		this.info = info;
		this.ownerid = ownerid;
	}
	public int getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
