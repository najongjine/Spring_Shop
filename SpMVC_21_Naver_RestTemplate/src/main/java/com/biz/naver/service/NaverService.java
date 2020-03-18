package com.biz.naver.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.naver.config.NaverConfig;
import com.biz.naver.domain.NaverSearchCover;
import com.biz.naver.domain.NaverVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NaverService {
	private final String naver_news="https://openapi.naver.com/v1/search/news.json";
	private final String naver_book="https://openapi.naver.com/v1/search/book.json";
	private final String naver_movie="https://openapi.naver.com/v1/search/movie.json";
	
	public List<NaverVO> naverSearch(String cat,String search) {
		String naverURL="";
		if(cat.equalsIgnoreCase("NEWS")) {
			naverURL=naver_news;
		}
		else if(cat.equalsIgnoreCase("MOVIE")) {
			naverURL=naver_movie;
		}
		else {
			naverURL=naver_book;
		}
		try {
			//query parameter로 보낼거에 영문자 이외는 전부 엔코드
			String queryString=URLEncoder.encode(search,"UTF-8");
			naverURL+="?query="+queryString;
			
			//RestTemplate으로 조회하기 위해 Header값을 설정
			HttpHeaders header=new HttpHeaders();
			header.set("X-Naver-Client-Id",NaverConfig.NAVER_CLIENT_ID);//공공DB같이 헤더 설정이 필요 없으면 이부분은 빼도 됨
			header.set("X-Naver-Client-Secret",NaverConfig.NAVER_CLIENT_SEC);//공공DB같이 헤더 설정이 필요 없으면 이부분은 빼도 됨
			//header.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_XML));// 이건 XML로 받을때
			
			HttpEntity<String> entity=new HttpEntity<String>(header);
			
			//주소 변환
			URI restURI=new URI(naverURL);
			RestTemplate restTempl=new RestTemplate();
			
			//데이터를 받아서 사용할 객체 타입 지정
			ResponseEntity<String> strResult=null;
			
			ResponseEntity<NaverSearchCover> restResult=null;
			
			//문자열 형태로 받기
			strResult=restTempl.exchange(restURI, HttpMethod.GET,entity,String.class);
			restResult=restTempl.exchange(restURI, HttpMethod.GET,entity,NaverSearchCover.class);
			
			NaverSearchCover sc=restResult.getBody();
			log.debug("가져온 데이터 개수 {}",sc.total);
			if(Integer.valueOf(sc.total)<1) return null;
			else return sc.items;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
