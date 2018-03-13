package com.netease.onlineEducation.learning.Dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.netease.onlineEducation.learning.Model.Orders;

@Repository
public interface OrdersDao {
	
	@Insert("insert into orders(userid, goodsid, price, amount, time) values(#{userid}, #{goodsid}, #{price}, #{amount}, #{time})")
	public void insertOrders(Orders orders);
}
