package com.biz.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.shop.dao.DeptDao;
import com.biz.shop.domain.DeptVO;
import com.biz.shop.persistence.DeptRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeptService {
	private final DeptRepository deptRepo;
	private final DeptDao deptDao;
	
	public DeptVO save(DeptVO deptVO) {
		return deptRepo.save(deptVO);
	}

	public DeptVO findById(long id) {
		// TODO Auto-generated method stub
		Optional<DeptVO> deptVO=deptRepo.findById(id);
		return deptVO.get();
	}

	public List<DeptVO> selectAll() {
		List<DeptVO> deptList=deptRepo.findAll();
		return deptList;
	}

	public List<DeptVO> findByDName(String search) {
		return deptDao.findByDName(search);
	}
}
