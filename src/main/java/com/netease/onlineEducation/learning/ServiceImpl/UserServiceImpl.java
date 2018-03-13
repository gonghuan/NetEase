package com.netease.onlineEducation.learning.ServiceImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netease.onlineEducation.learning.Dao.UserDao;
import com.netease.onlineEducation.learning.Model.User;
import com.netease.onlineEducation.learning.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	@Override
	public User getUserByName(String name, String password) {
		// TODO Auto-generated method stub
		User user = userDao.selectByName(name);
		if(user == null){
			return null;
		}else{
			String mima = user.getPassword();
			try {
				MessageDigest digest = MessageDigest.getInstance("md5");
				byte[] result = digest.digest(mima.getBytes());
				StringBuffer buffer = new StringBuffer();
	            // 把每一个byte 做一个与运算 0xff;
	            for (byte b : result) {
	                // 与运算
	                int number = b & 0xff;// 加盐
	                String str = Integer.toHexString(number);
	                if (str.length() == 1) {
	                    buffer.append("0");
	                }
	                buffer.append(str);
	            }
	            String md5MiMa = buffer.toString();
	            System.out.println(md5MiMa);
	            if(md5MiMa.equals(password)){
	            	return user;
	            }else{
	            	return null;
	            }
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
	}

}
