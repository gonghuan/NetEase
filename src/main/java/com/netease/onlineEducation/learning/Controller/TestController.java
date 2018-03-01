package com.netease.onlineEducation.learning.Controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netease.onlineEducation.learning.Service.TestService;

@Controller
public class TestController {
	@Resource
	private TestService testService;
	
	@RequestMapping(value="test/{name}/{password}")
	public void test(@PathVariable("name") String name, @PathVariable("password") String password){
		boolean isFound = testService.test(name, password);
		if(isFound) System.out.println("找到该用户");
		else System.out.println("没找到该用户");
	}
}
