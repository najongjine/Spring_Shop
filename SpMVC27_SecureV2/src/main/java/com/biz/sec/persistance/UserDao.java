package com.biz.sec.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.sec.domain.UserVO;

public interface UserDao {
	public List<UserVO> selectAll();
	
	@Select("select user_name as username, user_pass as password, enabled from tbl_users where user_name=#{username}")
	public UserVO findByUserName(String username);
	public int insert(UserVO userVO);
}
