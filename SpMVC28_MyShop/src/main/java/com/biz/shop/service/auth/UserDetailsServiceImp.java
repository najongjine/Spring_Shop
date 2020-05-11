package com.biz.shop.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.shop.domain.AuthorityVO;
import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.persistance.AuthoritiesDao;
import com.biz.shop.persistance.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * A implent D, B implent D, C implent D 가 있다고 할시
 * 여러개의 클래스가 똑같은 인터페이스를 import 하면 
 * D d 이렇게 하면 햇갈려 버린다.
 * 
 * 그러면 스프링은 어느 클래스를 가져다 써야할지 햇갈린다.
 * 이때 @service("myService" ) A implent D 이렇게 하고
 * 가져다 쓰는곳에서 @Qualifier("myService") D d 이렇게 해주면 A 쪽 서비스를 가져다 쓴다.
 */
@Service("userDetailsService")
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
		
		//spring security가 사용할 detailVO 선언
		UserDetailsVO userVO=userDao.findByUserName(username);
		if(userVO==null) {
			throw new UsernameNotFoundException("User Name이 없습니다");
		}
		userVO.setAccountNonExpired(true);
		userVO.setAccountNonLocked(true);
		userVO.setCredentialsNonExpired(true);
		
		//userDetails 안의 authority property는 이상한 객체로 되있어서
		//getAuthorities()라는 보일러 코드로 한번 전처리 해주고 값 셋팅
		userVO.setAuthorities(this.getAuthorities(username));
		return userVO;
	}
	
	/**
	 * authorities 테이블에서 권한 리스트를 가져오기
	 * 요건 보일러코드
	 */
	private Collection<GrantedAuthority> getAuthorities(String username){
		List<AuthorityVO> authList=authDao.findByUserName(username);
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		for(AuthorityVO vo:authList) {// db에 저장된 권한 목록들을
			if(!vo.getAuthority().trim().isEmpty()) {
				//spring security용 auth list로 복사
				SimpleGrantedAuthority sAuth=new SimpleGrantedAuthority(vo.getAuthority());
				authorities.add(sAuth);
			}
		}
		return authorities;
	}

}
