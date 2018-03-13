package com.netease.onlineEducation.learning.Service;

import java.util.List;

import com.netease.onlineEducation.learning.Model.GoodsOrdersDto;

public interface GoodsOrdersService {
	public List<GoodsOrdersDto> getAllGoodsAndOrders();
	public List<GoodsOrdersDto> getAllGoodsAndOrdersByUserId(int userId);
}
