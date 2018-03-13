package com.netease.onlineEducation.learning.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netease.onlineEducation.learning.Dao.CartsDao;
import com.netease.onlineEducation.learning.Model.Cart;
import com.netease.onlineEducation.learning.Service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Resource
	private CartsDao cartsDao;
	
	@Override
	public List<Cart> getCartByUserId(int userId) {
		// TODO Auto-generated method stub
		return cartsDao.selectCartByUserId(userId);
	}

	@Override
	public void deleteCartById(int id) {
		// TODO Auto-generated method stub
		cartsDao.deleteCartById(id);
	}

	@Override
	public Cart getCartByIdAndPrice(int goodsId, double price) {
		// TODO Auto-generated method stub
		return cartsDao.selectCartByIdAndPrice(goodsId, price);
	}

	@Override
	public void updateCart(int goodsId, double price, int amount) {
		// TODO Auto-generated method stub
		cartsDao.updateCart(goodsId, price, amount);
	}

}
