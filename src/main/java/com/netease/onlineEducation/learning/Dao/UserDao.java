package com.netease.onlineEducation.learning.Dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.netease.onlineEducation.learning.Model.User;

@Repository
public interface UserDao {
	
	@Select("select * from user where name = #{name} and password = #{password}")
	public User selectByNameAndPassword(@Param("name") String name, @Param("password") String password);
	
	@Select("select * from user where name = #{name}")
	public User selectByName(String name);
}
