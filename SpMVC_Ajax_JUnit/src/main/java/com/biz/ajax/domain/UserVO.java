package com.biz.ajax.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserVO implements Serializable{
	private String userId;
	private String password;
	private String userName;
	private String rolle;
	
	public UserVO sampleVO() {
		UserVO userVO=UserVO.builder().userId("admin").password("12345").userName("홍길동")
				.rolle("admin").build();
		return userVO;
	}
}
