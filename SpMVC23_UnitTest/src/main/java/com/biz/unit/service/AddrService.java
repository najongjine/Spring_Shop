package com.biz.unit.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AddrService {
	public Map<String,String> getAddr() {
		Map<String,String> addr=new HashMap<String,String>();
		addr.put("name","aaa");
		addr.put("addr","aaa addr");
		addr.put("tel","010-aaa");
		return addr;
	}
}
