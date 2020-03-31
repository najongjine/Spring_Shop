package com.biz.models.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.biz.models.domain.UsersVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		
		locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/*-context.xml"}
		
		)
public class UserServiceTest {
	@Autowired
	UserService userService;

	@Test
	public void c_getUserTest() {
		UsersVO userVO=userService.getUser("admin");
		assertEquals(userVO.getUserId(), "admin");
		assertEquals(userVO.getUserName(), "admin");
		userVO=userService.getUser("guest");
		assertEquals(userVO.getUserId(), "guest");
		assertEquals(userVO.getUserName(), "guest");
		userVO=userService.getUser("dba");
		assertEquals(userVO.getUserId(), "dba");
		assertEquals(userVO.getUserName(), "dba");
	}

	@Test
	public void b_insertTest() {
		UsersVO userVO=UsersVO.builder().userId("korea").password("1111").userName("korea").userRolle("gov").build();
		int ret=userService.insert(userVO);
		assertEquals(ret,1,0);
		
		userVO=UsersVO.builder().userId("admin").password("admin").userName("admin").userRolle("admin").build();
		ret=userService.insert(userVO);
		assertEquals(ret,1,0);
		
		userVO=UsersVO.builder().userId("guest").password("guest").userName("guest").userRolle("guest").build();
		ret=userService.insert(userVO);
		assertEquals(ret,1,0);
		
		userVO=UsersVO.builder().userId("dba").password("dba").userName("dba").userRolle("dba").build();
		ret=userService.insert(userVO);
		assertEquals(ret,1,0);
	}
	
	@Test
	public void a_deleteTest() {
		int ret=userService.delete("admin");
		ret=userService.delete("guest");
		ret=userService.delete("dba");
		ret=userService.delete("korea");
		assertEquals(ret, 1,0);
	}
}
