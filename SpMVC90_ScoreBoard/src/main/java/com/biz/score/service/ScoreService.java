package com.biz.score.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.score.dao.ScoreDao;
import com.biz.score.domain.ScoreVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreService {
	private final ScoreDao scoreDao;
	
	public List<ScoreVO> findAll() {
		// TODO Auto-generated method stub
		return scoreDao.findAll();
	}

	public List<ScoreVO> findBySNum(long s_num) {
		// TODO Auto-generated method stub
		return scoreDao.findBySNum(s_num);
	}

	public int insert(ScoreVO scoreVO) {
		// TODO Auto-generated method stub
		return scoreDao.insert(scoreVO);
	}

	public ScoreVO findById(long s_id) {
		// TODO Auto-generated method stub
		return scoreDao.findById(s_id);
	}

	public int update(ScoreVO scoreVO) {
		// TODO Auto-generated method stub
		return scoreDao.update(scoreVO);
	}

	public int delete(long s_id) {
		// TODO Auto-generated method stub
		return scoreDao.delete(s_id);
	}

}
