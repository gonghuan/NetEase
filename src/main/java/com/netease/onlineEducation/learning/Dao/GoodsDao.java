package com.netease.onlineEducation.learning.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	
	@Delete("delete from goods where id = #{id}")
	public void DeleteById(int id);
	
	@Update("update goods set name=#{name}, abstracts=#{abstracts}, image=#{image}, price=#{price}, info=#{info} where id=#{id}")
	public void updateById(Goods goods);
}
