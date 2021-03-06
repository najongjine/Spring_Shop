package com.biz.sec.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.sec.domain.AuthorityVO;
import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.persistance.AuthoritiesDao;
import com.biz.sec.persistance.UserDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	private final  AuthoritiesDao authDao;
	
	public UserService(PasswordEncoder passwordEncoder, UserDao userDao,AuthoritiesDao authDao) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		this.authDao=authDao;
		
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
	 * Map<String,String> 구조의 VO 데이터를 UserDetailsVO로 변경
	 */
	@Transactional
	public int insert(String username,String password) {
		//회원가입 form에서 전달받은 password 값을 암호화 시키는 과정
		String encPassword=passwordEncoder.encode(password);
		UserDetailsVO userVO=UserDetailsVO.builder().username(username).password(encPassword).build();
		int ret=userDao.insert(userVO);
		//기본권한 수동 설정
		List<AuthorityVO> authList=new ArrayList<AuthorityVO>();
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
		authDao.insert(authList);
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

	public int update(UserDetailsVO userVO,String[] authList) {
		int ret= userDao.update(userVO);
		if(ret>0) {
			//bulk insert를 하기위해 List 하나 만들어주고
			List<AuthorityVO> authCollection=new ArrayList();
			for(String auth:authList) {//authList는 걍 문자열 배열
				if(auth.trim().isEmpty()) continue; //공백문자열이면 아래거 무시
				//유저이름과 권한을 세트로 묶기위해 vo에 세팅 해주고
				AuthorityVO authVO=AuthorityVO.builder().username(userVO.getUsername()).authority(auth).build();
				authCollection.add(authVO);
			}
			//전에 있던내용 싹 지우고
			authDao.delete(userVO.getUsername());
			//bulk insert 시작
			authDao.insert(authCollection);
		}
		return ret;
	}
	
	@Transactional
	public int update(UserDetailsVO userVO) {
		/*
		 * 기본형 변수, 객체 ,배열 모두 변수라고 할수 있다.
		 * 
		 * type형 변수(참조형 변수)
		 * 객체나 배열은 method로 해당 객체나 배열을 전달 하고 method에서 객체나 비열의 일부 요소의 값을 변경하면
		 * 원본의 객체나 배열에 반영이 됨.
		 * 
		 * 기본형 변수(primitive)
		 * int,char,long,double 등등으로 만든 변수는 method로 전달하여 method 내에서 값을 변경 하여도 원본은 절대 변경되지 않음.
		 */
		//Authentication >= Principal
		//session(메모리)의 내용도 업뎃한 내용으로 바꿔주고
		Authentication oldAuth=SecurityContextHolder.getContext().getAuthentication();
		UserDetailsVO oldUserDetailsVO=(UserDetailsVO) oldAuth.getPrincipal();
		oldUserDetailsVO.setEmail(userVO.getEmail());
		oldUserDetailsVO.setPhone(userVO.getPhone());
		oldUserDetailsVO.setAddress(userVO.getAddress());
		//기본 유저정보 DB에 업뎃 하고
		int ret= userDao.update(userVO);
		if(ret>0) {
			
			//회원정보 update후, Principal에 담겨있는 정보도 새로 갱신
			Authentication newAuth=new UsernamePasswordAuthenticationToken(oldUserDetailsVO,oldAuth.getCredentials(),
					oldAuth.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		return ret;
	}

	public boolean checkPassword(String password) {
		UserDetailsVO userVO=(UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return passwordEncoder.matches(password,userVO.getPassword());
	}
	
	public Collection<GrantedAuthority> getAuthorities(String[] authList){
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		for(String auth:authList) {//query로 날라온 권한 목록들
			if(!auth.trim().isEmpty()) {
				//spring security용 auth list로 복사
				SimpleGrantedAuthority sAuth=new SimpleGrantedAuthority(auth);
				authorities.add(sAuth);
			}
		}
		return authorities;
	}

	@Transactional
	public List<UserDetailsVO> selectAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

	public UserDetailsVO findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}
}
