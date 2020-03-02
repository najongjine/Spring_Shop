package com.biz.bbs;

public class Main3 {
	public static void main(String[] args) {
		
	}
	
	/*
	 * 최초에 add(10)이 main에서 호출
	 * num 값은 10이 되고
	 * if문을 건너 뛰고 10+add()의 리턴값을 덧셈 
	 */
	public static int add(int num) {
		if(num<1) return 0;
		return num+add(num-1);
	}
}
