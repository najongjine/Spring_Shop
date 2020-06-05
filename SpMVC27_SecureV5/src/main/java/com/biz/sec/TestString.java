package com.biz.sec;

public class TestString {

	public static void main(String[] args) {
		String dummy1="dfdfsd";
		String dummy2="dfdfs";
		String _dummy1="dfdfsd";
		String _dummy11="dfdfsd";
		String _dummy12="dfdfsd";
		System.out.println("dummy1 hashcode: "+dummy1.hashCode());
		System.out.println("dummy2 hashcode: "+dummy2.hashCode());
		System.out.println("_dummy1 hashcode: "+_dummy1.hashCode());
		System.out.println("_dummy1 hashcode: "+_dummy11.hashCode());
		System.out.println("_dummy1 hashcode: "+_dummy12.hashCode());
		System.out.println("total hashcode: "+_dummy1.hashCode()+dummy1.hashCode()+dummy2.hashCode());

	}

}
