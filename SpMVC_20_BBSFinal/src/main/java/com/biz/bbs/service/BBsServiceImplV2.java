package com.biz.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.repository.BbsDao;

/*
 * 다중 select를 수행하는 method들이 있고
 * 재귀호출에 의해서 계속되는 select문이 실행된다.
 * 이때 @transactional 설정하면 다중 select를 transaction으로 보호하여
 * 중간에 데이터 fetch가 누락되는것을 막을수 있다.
 */
@Transactional
@Service("bbsV2")
public class BBsServiceImplV2 extends BBsServiceImpl{
	
	public BBsServiceImplV2(BbsDao bbsDao) {
		super(bbsDao);
	}

	
	@Override
	public List<BBsVO> selectAll(){
		List<BBsVO> retList=bbsDao.selectLevel();
		return retList;
	}
}
