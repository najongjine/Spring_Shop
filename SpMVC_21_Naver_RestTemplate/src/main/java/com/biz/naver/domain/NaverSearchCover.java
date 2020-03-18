package com.biz.naver.domain;

import java.util.List;

import lombok.ToString;

@ToString
public class NaverSearchCover {
	public String lastBuildDate;
	public String total;
	public String start;
	public String display;
	public List<NaverVO> items;
}
