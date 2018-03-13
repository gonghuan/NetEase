package com.netease.onlineEducation.learning.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.netease.onlineEducation.learning.Model.Cart;

@Repository
public interface CartsDao {
	@Select("select * from shoppingCart where userid = #{userId}")
	public List<Cart> selectCartByUserId(int userId);
	
	@Delete("delete from shoppingCart where id = #{id}")
	public void deleteCartById(int id);
	
	@Select("select * from shoppingCart where goodsid = #{goodsId} and price = #{price}")
	public Cart selectCartByIdAndPrice(@Param("goodsId") int goodsId, @Param("price") double price);
	
	@Update("update shoppingCart set amount = #{amount} where goodsid = #{goodsId} and price = #{price}")
	public void updateCart(@Param("goodsId") int goodsId, @Param("price") double price, @Param("amount") int amount);
}
