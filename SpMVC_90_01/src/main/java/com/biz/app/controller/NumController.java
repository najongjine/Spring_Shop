package com.biz.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.app.ScoreVO;
import com.biz.app.service.NumService;

@RequestMapping(value = "/number")
@Controller
public class NumController {
	@Autowired
	NumService nService;
	
	@ResponseBody
	@RequestMapping(value = "/add",produces = "text/html;charset=UTF-8")
	public String add() {
		int ret=nService.add(1, 54);
		return "두수의 덧셈 : "+ret;
	}
	
	/*
	 * 사용자가 /number/even 이라고 요청을 하면
	 * 1~100 까지 숫자 중에서 짝수의 덧셈만 수행하여 결과를 알려주겠다
	 */
	@ResponseBody
	@RequestMapping(value = "/even",produces = "text/html;charset=UTF-8")
	public String even() {
		//서비스에게 요청을 해서 짝수 덧셈을 수행해달라.
		int start=1;
		int end=100;
		int sum=nService.even(start,end);
		return "~짝수 덧셈 : "+sum;
	}
	
	/*
	 * 사용자가 요청한 변수=값 형태는 무조건 값이 문자열이다
	 * 만약 매개변수 type int 형으로 선언을 하면
	 * spring은 사용자의 변수를 수신한 후 Integer.valof(변수) 코드를 실행하여 문자열을 숫자로
	 * 변환 시키려 시도를 한다.
	 * 
	 * 그런데 수신한 값이 숫자로 변환이 불가능 하면 사용자에게 400 오류를 전송
	 */
	@ResponseBody
	@RequestMapping(value = "/num2even",produces = "text/html;charset=UTF-8")
	public String even(String num1,String num2) {
		//서비스에게 요청을 해서 짝수 덧셈을 수행해달라.
		int start=0;
		int end=0;
		try {
			start=Integer.valueOf(num1);
			end=Integer.valueOf(num2);
		} catch (Exception e) {
			return "요청 파라미터가 잘못 되었습니다";
		}
		int sum=nService.even(start,end);
		return "~짝수 덧셈 : "+sum;
	}
	
	@ResponseBody
	@RequestMapping(value = "/score",produces = "text/html;charset=UTF-8")
	public String score(String kor,String eng,String math, String sci, String music) {
		int intKor=0;
		int intEng=0;
		int intMath=0;
		int intSci=0;
		int intMusic=0;
		
		try {
			intKor=Integer.valueOf(kor);
			intEng=Integer.valueOf(eng);
			intMath=Integer.valueOf(math);
			intSci=Integer.valueOf(sci);
			intMusic=Integer.valueOf(music);
		} catch (Exception e) {
			return "요청 파라미터는 숫자로만 입력해 주세요";
		}
		
		int total=nService.total(intKor,intEng,intMath,intSci,intMusic);
		float avg=nService.avg(intKor,intEng,intMath,intSci,intMusic);
		
		String resp=String.format("총점은 %d, 평균은 %f 입니다", total,avg);
		return resp;
	}
	
	/*
	 * 매게변수로 Model 클래스를 설정하고
	 * model 객체에 addAttribute("변수명",값) 형식으로 내용을 추가하고
	 * rendering: jsp 파일을 return 하면 
	 * spring, tomcat은 model에 담겨있는 값과
	 * jsp파일을 비교하여 연관 변수들을 변환하여 html코드로 생성.
	 */
	@RequestMapping(value = "/score_jsp",produces = "text/html;charset=UTF-8")
	public String score_jsp(String kor,String eng,String math, String sci, String music,Model model) {
		int intKor=0;
		int intEng=0;
		int intMath=0;
		int intSci=0;
		int intMusic=0;
		
		try {
			intKor=Integer.valueOf(kor);
			intEng=Integer.valueOf(eng);
			intMath=Integer.valueOf(math);
			intSci=Integer.valueOf(sci);
			intMusic=Integer.valueOf(music);
		} catch (Exception e) {
			return "요청 파라미터는 숫자로만 입력해 주세요";
		}
		
		int total=nService.total(intKor,intEng,intMath,intSci,intMusic);
		float avg=nService.avg(intKor,intEng,intMath,intSci,intMusic);
		model.addAttribute("SUM", total);
		model.addAttribute("AVG", avg);
		
		return "score";
	}
	
	@RequestMapping(value = "/score_input",method=RequestMethod.GET)
	public String score_input() {
		return "score_input";
	}
	
	@RequestMapping(value = "/score_input",method=RequestMethod.POST)
	public String score_input(ScoreVO scoreVO,Model model) {
		nService.score(scoreVO);
		model.addAttribute("scoreVO", scoreVO);
		return "score_input";
	}
}
