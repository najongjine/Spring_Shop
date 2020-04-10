package com.biz.sec.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.AuthorityVO;
import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.domain.UserVO;
import com.biz.sec.persistance.AuthoritiesDao;
import com.biz.sec.persistance.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
/*
 * 사용자의 상세정보를 DB로부터 가져와서 spring security 여러곳에서 사용할수 있도록 VO에 연동하는 method
 * UserDetailService 인터페이스를 가져와서 Customizing
 */
public class UserDetailsServiceImp implements UserDetailsService{
	private final AuthoritiesDao authDao;
	private final UserDao userDao;
	
	/*
	 * DB로부터 데이터를 불러와서 UserdeailsVO에 데이터를 복사하여 연동하는 코드가 작성될곳 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO=userDao.findByUserName(username);
		
		//spring security가 사용할 detailVO 선언
		UserDetailsVO userDetails=new UserDetailsVO();
		userDetails.setUsername(userVO.getUsername());
		userDetails.setPassword(userVO.getPassword());
		userDetails.setEnabled(true);
		userDetails.setAccountNonExpired(true);
		userDetails.setAccountNonLocked(true);
		userDetails.setCredentialsNonExpired(true);
		
		//userDetails 안의 authority property는 이상한 객체로 되있어서
		//getAuthorities()라는 보일러 코드로 한번 전처리 해주고 값 셋팅
		userDetails.setAuthorities(this.getAuthorities(username));
		return userDetails;
	}
	
	/**
	 * authorities 테이블에서 권한 리스트를 가져오기
	 * 요건 보일러코드
	 */
	private Collection<GrantedAuthority> getAuthorities(String username){
		List<AuthorityVO> authList=authDao.findByUserName(username);
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		for(AuthorityVO vo:authList) {// db에 저장된 권한 목록들을
			//spring security용 auth list로 복사
			SimpleGrantedAuthority sAuth=new SimpleGrantedAuthority(vo.getAuthority());
			authorities.add(sAuth);
		}
		return null;
	}

}
