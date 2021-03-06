package com.biz.shop.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/*
 * 키보드에서 문자열을 입력받아서 암호화된 문자열을 생성
 */
public class MySQLEncMain {

	public static void main(String[] args) {
		StandardPBEStringEncryptor pbEnc=new StandardPBEStringEncryptor();
		/*
		 * local comp 설정된 환경 변수들 목록을 가져와서 그중에 BIZ.COM 으로 등록된 값을 보여라
		 */
		Map<String,String> envList=System.getenv();
		System.out.println(envList.get("BIZ.COM"));
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("mysql user name >> ");
		String userName=scanner.nextLine();
		System.out.println("mysql Password >> ");
		String password=scanner.nextLine();
		
		/*
		 * 암호화를 하기위한 설정
		 */
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(envList.get("BIZ.COM"));
		
		String encUserName=pbEnc.encrypt(userName);
		String encPassword=pbEnc.encrypt(password);
		
		System.out.printf("userName: %s \n",encUserName);
		System.out.printf("password: %s \n",encPassword);
		
		ResourceLoader resLoader=new DefaultResourceLoader();
		Resource res=resLoader.getResource("file:src/main/resources/properties/db.connection.properties");
		
		String saveUserName=String.format("mysql.username=%s", encUserName);
		String savePassword=String.format("mysql.password=%s", encPassword);
		
		
		try {
			PrintWriter out=new PrintWriter(res.getFile());
			out.println(saveUserName);
			out.println(savePassword);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
	}

}
