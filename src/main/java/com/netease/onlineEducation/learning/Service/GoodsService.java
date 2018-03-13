package com.netease.onlineEducation.learning.Service;

import java.util.List;

import com.netease.onlineEducation.learning.Model.Cart;
import com.netease.onlineEducation.learning.Model.Goods;

public interface GoodsService {
	
	public List<Goods> getAllGoods();
	
	public Goods getGoodsById(int id);
	
	public void insertCart(Cart cart);
	
	public void insertGoods(Goods goods);
}
