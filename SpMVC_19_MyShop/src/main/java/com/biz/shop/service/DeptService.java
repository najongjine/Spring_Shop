package com.biz.shop.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.DeptVO;
import com.biz.shop.repository.DeptDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeptService {
	private final DeptDao deptDao;
	
	public void save(DeptVO deptVO) {
		deptDao.save(deptVO);
	}

	public DeptVO findById(long id) {
		// TODO Auto-generated method stub
		Optional<DeptVO> deptVO=deptDao.findById(id);
		return deptVO.get();
	}
}
