package com.netease.onlineEducation.learning.Service;

import org.springframework.stereotype.Service;

import com.netease.onlineEducation.learning.Model.User;

@Service
public interface UserService {
	public User getUserByName(String name, String password);
}
