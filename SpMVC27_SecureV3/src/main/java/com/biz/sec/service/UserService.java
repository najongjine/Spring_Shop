package com.biz.sec.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.domain.UserVO;
import com.biz.sec.persistance.UserDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	
	public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		
		String create_user_table=" create table if not exists tbl_users( " + 
				" id bigint  primary key auto_increment, " + 
				" user_name varchar(50) unique, " + 
				" user_pass varchar(125), " + 
				" enabled boolean default true, " +
				" email varchar(50), " + 
				" phone varchar(20), " + 
				" address varchar(125) " + 
				" ) ";
		String create_auth_table=" create table if not exists authorities( " + 
				" id bigint  primary key auto_increment, " + 
				" username varchar(50), " + 
				" authority varchar(50) " + 
				" ) ";
		userDao.create_table(create_user_table);
		userDao.create_table(create_auth_table);
	}

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
		UserDetailsVO userVO=userDao.findByUserName(username);
		//이미 DB에 회원정보(username)가 저장되어있다
		if(userVO!=null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		return false;
	}

	public UserDetailsVO findById(long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	public int update(UserDetailsVO userVO) {
		// TODO Auto-generated method stub
		return userDao.update(userVO);
	}
}
