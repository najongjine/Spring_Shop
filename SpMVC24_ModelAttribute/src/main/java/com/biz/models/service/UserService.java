package com.biz.models.service;

import org.springframework.stereotype.Service;

import com.biz.models.dao.UserDao;
import com.biz.models.domain.UsersVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserDao userDao;
	/*
	 * TDD(데스트 주도형 개발, Test Driven Developer)
	 * 1. 클래스와 필요한 메서드의 이름만 만들고
	 * 2. 필요에 따라 매개변수와 리턴값만 설정 한다.
	 * 3. test에서 매개변수를 주입했을때 리턴되는 값이 일치 함을 테스트하는 코드를 작성한 후
	 * 4. 실제 class에서 가상의 일치하는 데이터를 만들고
	 * 5. 데이터를 return 하도록 코드를 작성.
	 * 6. 그리고 테스트를 수행하여 통과되도록 코드를 리팩토링하여
	 * 7. 임시로 클래스를 완성한다.
	 * 
	 * DAO 에서 데이터를 가져온후 ID에 따라 사용자 ID와 사용자 이름이 일치하는 데스트 코드를 작성해 둔상태.
	 * 1. DAO에 데이터를 저장할때 테스트 코드가 통과될수 있는 데이터를 insert 한 후
	 * 2. 다시한번 테스트를 수행하여 DAO와 연동되는것을 계속해서 확인한다.
	 */
	public UsersVO getUser(String userId) {
		/*
		 * UsersVO userVO = UsersVO.builder() .userId("admin") .password("12345")
		 * .userName("홍길동") .userRolle("admin") .build();
		 */
		UsersVO userVO=userDao.findByUserId("admin");
		if(userId.equals("admin")) {
			return userVO;
		} else if(userId.equals("guest")) {
			/*
			 * userVO.setUserId("guest"); userVO.setUserName("성춘향");
			 */
			userVO=userDao.findByUserId("guest");
		} else if(userId.equals("dba")) {
			/*
			 * userVO.setUserId("dba"); userVO.setUserName("이몽룡");
			 */
			userVO=userDao.findByUserId("dba");
		} else {
			userVO = null;
		}
		return userVO;
	}
	
	/*
	 * insert method는 UsersVO에 담긴 데이터를 받아서 
	 * insert를 수행한 후
	 * 결과를 return
	 * return 값은 데이터가 1개 이므로 정수 1이 된다.
	 * 이러한 테스트코드를 만들기 위해서 가상으로 return 1의 코드를 추가
	 */
	public int insert(UsersVO usersVO) {
		return userDao.insert(usersVO);
	}

	public int delete(String userId) {
		// TODO Auto-generated method stub
		return userDao.delete(userId);
	}
}
