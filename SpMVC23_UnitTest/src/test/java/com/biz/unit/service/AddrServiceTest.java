package com.biz.unit.service;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/*-context.xml"})
public class AddrServiceTest {
	@Autowired
	AddrService as;
	
	@Test
	public void test() {
		Map<String,String> addr=as.getAddr();
		Map<String,String> addr1=as.getAddr();
		assertEquals(addr1, addr);
	}

}
