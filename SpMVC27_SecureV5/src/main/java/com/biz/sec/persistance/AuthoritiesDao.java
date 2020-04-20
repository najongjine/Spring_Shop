package com.biz.sec.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.sec.domain.AuthorityVO;

public interface AuthoritiesDao {
	//사용자 이름으로 권한리스트 찾기
	@Select("select * from authorities where username=#{username}")
	List<AuthorityVO> findByUserName(String username);

	int insert(List<AuthorityVO> authList);

	@Delete("delete from authorities where username=#{username}")
	int delete(String username);
}
