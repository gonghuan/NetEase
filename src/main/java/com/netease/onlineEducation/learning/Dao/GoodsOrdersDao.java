package com.netease.onlineEducation.learning.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.netease.onlineEducation.learning.Model.GoodsOrdersDto;

@Repository
public interface GoodsOrdersDao {
	
	public List<GoodsOrdersDto> selectAllGoodsAndOrders();
	
	public List<GoodsOrdersDto> selectAllGoodsAndOrdersByUserId(int userId);
}
