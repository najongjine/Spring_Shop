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
	
	/**
	 * @since 2020-04-09
	 * @author Jong park
	 * @param username
	 * @param password
	 * @return
	 * @update 2020-4-10
	 * Map<String,String> 구조의 VO 데이터를 UserVO로 변경
	 */
	public int insert(String username,String password) {
		//회원가입 form에서 전달받은 password 값을 암호화 시키는 과정
		String encPassword=passwordEncoder.encode(password);
		UserVO userVO=UserVO.builder().username(username).password(encPassword).build();
		int ret=userDao.insert(userVO);
		return ret;
	}

	public boolean isExistsUserName(String username) {
		UserVO userVO=userDao.findByUserName(username);
		//이미 DB에 회원정보(username)가 저장되어있다
		if(userVO!=null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		return false;
	}
}
