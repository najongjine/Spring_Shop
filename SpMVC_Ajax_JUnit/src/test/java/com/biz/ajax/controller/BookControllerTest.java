package com.biz.ajax.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations= {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}
		)
public class BookControllerTest {
	MockMvc mockMvc;
	
	@InjectMocks
	BookController bookController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@Test
	public void testInsertBad() throws Exception {
		mockMvc.perform(post("/book/insert")
				.param("b_title","java")
				.param("b_comp","길벗")
				.param("b_price","")
				)
		.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
		//.andExpect(status().isOk());
	}
	@Test
	public void testInsertOk() throws Exception {
		mockMvc.perform(post("/book/insert")
				.param("b_title","java")
				.param("b_comp","길벗")
				.param("b_price","15000")
				)
		//.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
		.andExpect(status().isOk());
	}

}
