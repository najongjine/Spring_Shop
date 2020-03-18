package com.biz.naver.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class NaverVO {
	@JsonProperty("title")
	private String title;//": "정의선 수석부회장, 현대모비스 사내이사 재선임",
	@JsonProperty("originallink")
	private String originallink;//": "http://www.ekn.kr/news/article.html?no=488402",
	@JsonProperty("link")
	private String link;//": "http://www.ekn.kr/news/article.html?no=488402",
	@JsonProperty("description")
	private String description;//": "현대모비스 주총 안건 통과 요건은 주주 과반 출석에 의결권 있는 <b>주식</b> 수의 4분의 1 이상 찬성이다. 찬성·반대 비율은 공개하지 않는다. 정 수석부회장은 지난해 3월 현대차와 현대모비스 대표이사에 오르며 그룹... ",
	@JsonProperty("pubDate")
	private String pubDate;//": "Wed, 18 Mar 2020 13:30:00 +0900"
	@JsonProperty("image")
	private String image;
	@JsonProperty("subtitle")
	private String subtitle;
	@JsonProperty("director")
	private String director;
	@JsonProperty("actor")
	private String actor;
	@JsonProperty("userRating")
	private String userRating;
	@JsonProperty("author")
	private String author;
	@JsonProperty("price")
	private String price;
	@JsonProperty("publisher")
	private String publisher;
	@JsonProperty("isbn")
	private String isbn;
	@JsonProperty("discount")
	private String discount;
	
}
