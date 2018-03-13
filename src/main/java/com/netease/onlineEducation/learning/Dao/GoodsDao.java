package com.netease.onlineEducation.learning.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.netease.onlineEducation.learning.Model.Cart;
import com.netease.onlineEducation.learning.Model.Goods;

@Repository
public interface GoodsDao {
	
	@Select("select * from goods")
	public List<Goods> SelectAllGoods();
	
	@Select("select * from goods where id=#{id}")
	public Goods SelectGoodsById(int id);
	
	@Insert("insert into shoppingCart(userid, goodsid, price, amount, goodsName) values(#{userid}, #{goodsid}, #{price}, #{amount}, #{goodsname})")
	public void InsertChar(Cart cart);
	
	@Insert("insert into goods(name, abstracts, image, price, info, ownerid) values(#{name}, #{abstracts}, #{image}, #{price}, #{info}, #{ownerid})")
	public void InsertGoods(Goods goods);
}
