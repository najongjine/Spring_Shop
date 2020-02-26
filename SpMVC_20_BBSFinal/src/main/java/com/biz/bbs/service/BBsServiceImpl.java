package com.biz.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.bbs.dao.BbsDao;
import com.biz.bbs.domain.BBsVO;

@Service
public class BBsServiceImpl implements BBsService{
	protected final BbsDao bbsDao;
	
	public BBsServiceImpl(BbsDao bbsDao) {
		this.bbsDao = bbsDao;
	}

	@Override
	public List<BBsVO> selectAll() {
		// TODO Auto-generated method stub
		return bbsDao.selectAll();
	}

	@Override
	public BBsVO findById(long b_id) {
		// TODO Auto-generated method stub
		return bbsDao.findById(b_id);
	}

	@Override
	public List<BBsVO> findBySubject(String b_subject) {
		// TODO Auto-generated method stub
		return bbsDao.findBySubject(b_subject);
	}

	@Override
	public List<BBsVO> findByWriter(String b_writer) {
		// TODO Auto-generated method stub
		return bbsDao.findByWriter(b_writer);
	}

	@Override
	public int insert(BBsVO bbsVO) {
		// TODO Auto-generated method stub
		return bbsDao.insert(bbsVO);
	}

	@Override
	public int delete(long b_id) {
		// TODO Auto-generated method stub
		return bbsDao.delete(b_id);
	}

	@Override
	public int update(BBsVO bbsVO) {
		// TODO Auto-generated method stub
		return bbsDao.update(bbsVO);
	}

}
