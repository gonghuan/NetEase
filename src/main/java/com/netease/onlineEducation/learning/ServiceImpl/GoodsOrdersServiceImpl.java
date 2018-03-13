package com.netease.onlineEducation.learning.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netease.onlineEducation.learning.Dao.GoodsOrdersDao;
import com.netease.onlineEducation.learning.Model.GoodsOrdersDto;
import com.netease.onlineEducation.learning.Service.GoodsOrdersService;

@Service
public class GoodsOrdersServiceImpl implements GoodsOrdersService {
	
	@Resource
	private GoodsOrdersDao goodsOrdersDao;
	
	@Override
	public List<GoodsOrdersDto> getAllGoodsAndOrders() {
		// TODO Auto-generated method stub
		return goodsOrdersDao.selectAllGoodsAndOrders();
	}
	
	@Override
	public List<GoodsOrdersDto> getAllGoodsAndOrdersByUserId(int userId){
		return goodsOrdersDao.selectAllGoodsAndOrdersByUserId(userId);
	}

}
