package com.netease.onlineEducation.learning.Dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.netease.onlineEducation.learning.Model.User;

@Repository
public interface TestDao {
	@Select("select * from user where name = #{name} and password = #{password}")
	public User test(@Param("name") String name, @Param("password") String password);
}
