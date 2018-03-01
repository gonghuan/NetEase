package com.netease.onlineEducation.learning.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netease.onlineEducation.learning.Dao.TestDao;
import com.netease.onlineEducation.learning.Service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Resource
	private TestDao testDao;
	
	@Override
	public boolean test(String name, String password) {
		// TODO Auto-generated method stub
		return (testDao.test(name, password) != null) ? true : false;
	}

}
