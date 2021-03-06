package com.biz.app.service;

import org.springframework.stereotype.Service;

import com.biz.app.ScoreVO;

/*
 * Service 클래스
 * @Service Annotation을 설정한 클래스
 * 
 * Controller가 사용자의 요청을 받았는데
 * 단순한 연산을 수행해서 결과를 보낼 사안이 아닐때
 *  1. 조금 복잡한 무언가를 수행해야 할때
 *   Controller의 기능을 보조하는 역활을 수행. 
 */
@Service
public class NumService {
	public int add(int num1, int num2) {
		int sum=0;
		sum=num1+num2;
		return sum;
	}

	public int even(int start, int end) {
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=start;i<=end;i++) {
			if(i%2==0) {
				sum+=i;
			}
		}
		return sum;
	}

	public int total(int intKor, int intEng, int intMath, int intSci, int intMusic) {
		// TODO Auto-generated method stub
		return intKor+intEng+intMath+intSci+intMusic;
	}

	public float avg(int intKor, int intEng, int intMath, int intSci, int intMusic) {
		// TODO Auto-generated method stub
		return total(intKor,intEng,intMath,intSci,intMusic)/5;
	}

	public void score(ScoreVO scoreVO) {
		int sum=scoreVO.getKor();
		sum+=scoreVO.getEng();
		sum+=scoreVO.getMath();
		sum+=scoreVO.getSci();
		sum+=scoreVO.getMusic();
		
		scoreVO.setSum(sum);
		scoreVO.setAvg(sum/5);
		
	}
}
