package com.netease.onlineEducation.learning.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.onlineEducation.learning.Model.User;
import com.netease.onlineEducation.learning.Service.UserService;

@Controller
@RequestMapping(value="user")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value="login")
	public @ResponseBody String login(@RequestParam("name") String name, @RequestParam("password") String password,
			HttpServletRequest request){
		System.out.println(password);
		User user = userService.getUserByName(name, password);
		if(user == null){
			return "no";
		}else{
			request.getSession().setAttribute("user", user);
			return "yes";
		}
	}
	
	@RequestMapping("logout")
	public @ResponseBody boolean logout(HttpServletRequest request){
		try{
			request.getSession().invalidate();
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
