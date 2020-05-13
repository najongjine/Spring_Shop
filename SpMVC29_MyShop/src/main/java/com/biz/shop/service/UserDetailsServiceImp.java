package com.biz.shop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.shop.persistance.UserDao;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	private final UserDao userDao;
	
	public UserDetailsServiceImp(UserDao userDao) {
		this.userDao = userDao;

		//테이블 생성부분을 코딩하기 위한 방법
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
