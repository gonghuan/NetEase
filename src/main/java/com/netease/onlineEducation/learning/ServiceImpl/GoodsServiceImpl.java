package com.netease.onlineEducation.learning.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netease.onlineEducation.learning.Dao.GoodsDao;
import com.netease.onlineEducation.learning.Model.Cart;
import com.netease.onlineEducation.learning.Model.Goods;
import com.netease.onlineEducation.learning.Service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Resource
	private GoodsDao goodsDao;

	@Override
	public List<Goods> getAllGoods() {
		// TODO Auto-generated method stub
		return goodsDao.SelectAllGoods();
	}

	@Override
	public Goods getGoodsById(int id) {
		// TODO Auto-generated method stub
		return goodsDao.SelectGoodsById(id);
	}

	@Override
	public void insertCart(Cart cart) {
		// TODO Auto-generated method stub
		goodsDao.InsertChar(cart);
	}

	@Override
	public void insertGoods(Goods goods) {
		// TODO Auto-generated method stub
		goodsDao.InsertGoods(goods);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		goodsDao.DeleteById(id);
	}

	@Override
	public void updateById(Goods goods) {
		// TODO Auto-generated method stub
		goodsDao.updateById(goods);
	}

}
