package com.biz.bbs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	private final CommentDao cmtDao;

	@Override
	public List<CommentVO> selectAll() {
		// TODO Auto-generated method stub
		return cmtDao.selectAll();
	}

	@Override
	public CommentVO findById(long c_id) {
		// TODO Auto-generated method stub
		return cmtDao.findById(c_id);
	}

	@Override
	public List<CommentVO> findByPId(long c_p_id) {
		// TODO Auto-generated method stub
		return cmtDao.findByPId(c_p_id);
	}

	@Override
	public int insert(CommentVO commentVO) {
		// TODO Auto-generated method stub
		commentVO.setC_date_time("0");
		LocalDateTime ldt=LocalDateTime.now();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		commentVO.setC_date_time(ldt.format(df).toString());
		return cmtDao.insert(commentVO);
		
	}

	@Override
	public int update(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long c_id) {
		// TODO Auto-generated method stub
		return cmtDao.delete(c_id);
	}

	@Override
	public List<CommentVO> findByBId(long c_b_id) {
		// TODO Auto-generated method stub
		return cmtDao.findByBId(c_b_id);
	}
	
}
