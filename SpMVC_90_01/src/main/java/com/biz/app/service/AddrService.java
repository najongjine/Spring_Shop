package com.biz.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.app.AddrVO;

@Service
public class AddrService {
	public List<AddrVO> addrListAll(){
		List<AddrVO> addrList=new ArrayList<AddrVO>();
		AddrVO addrVO=new AddrVO();
		addrVO.setName("aaa");
		addrVO.setTel("aaa-111");
		addrVO.setPost("343");
		addrVO.setCity("서울");
		addrVO.setAddr("dfdf3");
		addrList.add(addrVO);
		
		addrVO=new AddrVO();
		addrVO.setName("bbb");
		addrVO.setTel("bbb-111");
		addrVO.setPost("344");
		addrVO.setCity("서울");
		addrVO.setAddr("dfdf3");
		addrList.add(addrVO);
		
		addrVO=new AddrVO();
		addrVO.setName("ccc");
		addrVO.setTel("ccc-111");
		addrVO.setPost("345");
		addrVO.setCity("서울");
		addrVO.setAddr("dfdf3");
		addrList.add(addrVO);
		
		return addrList;
	}
}
