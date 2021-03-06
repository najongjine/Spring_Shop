package com.biz.shop.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.shop.domain.Authorities;
import com.biz.shop.domain.Users;
import com.biz.shop.persistence.AuthRepository;
import com.biz.shop.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userDao;
	private final AuthRepository authDao;
	private final BCryptPasswordEncoder passEncoder;
	
	@Transactional
	public void userSave(Users userVO) {
		String userRole="ROLE_ADMIN";
		// 처음등록 사용자에게는 Admin을 부여하고
		//이후 사용자에게는 User를 부여하는 코드를 부착
		
		String plainPass=userVO.getPassword();
		String crypPass=passEncoder.encode(plainPass);
		userVO.setEnabled(true);
		
		userVO.setPassword(crypPass);
		
		Authorities auth=Authorities.builder().username(userVO.getUsername()).authority(userRole).build();
		userDao.save(userVO);
		authDao.save(auth);
	}
}
