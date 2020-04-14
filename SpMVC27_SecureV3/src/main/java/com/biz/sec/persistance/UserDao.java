package com.biz.sec.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.domain.UserVO;

public interface UserDao {
	public List<UserVO> selectAll();
	public void create_table(String create_table);
	
	@Select(" select id,user_name as username, user_pass as password, enabled, email, phone, address "
			+ " from tbl_users where user_name=#{username} ")
	public UserDetailsVO findByUserName(String username);
	public int insert(UserVO userVO);
	
	public UserDetailsVO findById(long id);
	
	public int update(UserDetailsVO userVO);
}
