package com.biz.sec.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserVO;
import com.biz.sec.persistance.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	
	public int insert(String username,String password) {
		//회원가입 form에서 전달받은 password 값을 암호화 시키는 과정
		String encPassword=passwordEncoder.encode(password);
		Map<String,String> userVO=new HashMap<String, String>();
		userVO.put("username", username);
		userVO.put("password", encPassword);
		int ret=userDao.insert(userVO);
		return ret;
	}

	public boolean isExistsId(String username) {
		String user_name=userDao.findByUserName(username);
		if(user_name!=null && user_name.equalsIgnoreCase(username)) {
			return true;
		}
		return false;
	}
}
