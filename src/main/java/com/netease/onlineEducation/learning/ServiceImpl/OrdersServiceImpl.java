package com.netease.onlineEducation.learning.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netease.onlineEducation.learning.Dao.OrdersDao;
import com.netease.onlineEducation.learning.Model.Orders;
import com.netease.onlineEducation.learning.Service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	
	@Resource
	private OrdersDao ordersDao;
	
	@Override
	public void insertOrders(Orders orders) {
		// TODO Auto-generated method stub
		ordersDao.insertOrders(orders);
	}

}
