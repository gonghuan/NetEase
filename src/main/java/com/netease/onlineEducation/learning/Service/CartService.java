package com.netease.onlineEducation.learning.Service;

import java.util.List;

import com.netease.onlineEducation.learning.Model.Cart;

public interface CartService {
	public List<Cart> getCartByUserId(int userId);
	
	public void deleteCartById(int id);
	
	public Cart getCartByIdAndPrice(int goodsId, double price);
	
	public void updateCart(int goodsId, double price, int amount);
}
