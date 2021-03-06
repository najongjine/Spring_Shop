package com.biz.bbs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Service("cmtV1")
@Slf4j
public class CommentServiceImpl implements CommentService {
	protected final CommentDao cmtDao;

	public CommentServiceImpl(CommentDao cmtDao) {
		super();
		this.cmtDao = cmtDao;
	}

	@Override
	public List<CommentVO> selectAll() {
		// TODO Auto-generated method stub
		return cmtDao.selectAll();
	}

	@Override
	public CommentVO findById(long c_id) {
		// TODO Auto-generated method stub
		CommentVO cmtVO= cmtDao.findById(c_id);
		return cmtVO;
	}

	/*
	 * 게시판 원글에대한 코멘트 리스트 가져오기
	 */
	public List<CommentVO> findByBId(long c_b_id){
		List<CommentVO> cmtList=cmtDao.findByBId(c_b_id);
		List<CommentVO> retList=new ArrayList<CommentVO>();
		for(CommentVO vo:cmtList) {
			retList.addAll(findByBIdReply(vo, 0));
		}
		return retList;
	}
	
	@Override
	public List<CommentVO> findByPId(long c_p_id) {
		// TODO Auto-generated method stub
		List<CommentVO> cmtList= cmtDao.findByPId(c_p_id);
		return cmtList;
	}

	/*
	 * 게시판 코멘트 답변 리스트 가져오기
	 */
	private List<CommentVO> findByBIdReply(CommentVO cmtVO, int depth){
		List<CommentVO> retList=new ArrayList<CommentVO>();
		if(depth>0) {
			String c_subject="&nbsp;";
			for(int i=0;i<depth;i++) {
				c_subject+=" re: ";
			}
			c_subject+=cmtVO.getC_subject();
			cmtVO.setC_subject(c_subject);
		}
		retList.add(cmtVO);
		List<CommentVO> tempList=cmtDao.findByPId(cmtVO.getC_id());
		if(tempList.size()<1) return retList;
		
		for(CommentVO vo:tempList) {
			retList.addAll(findByBIdReply(vo, depth+1));
		}
		return retList;
	}
	@Override
	public int insert(CommentVO commentVO) {
		// TODO Auto-generated method stub
		if (commentVO.getC_id() > 0) {
			int ret = cmtDao.update(commentVO);
			return ret;
		} else {
			LocalDateTime ldt = LocalDateTime.now();
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			commentVO.setC_date_time(ldt.format(df).toString());
			return cmtDao.insert(commentVO);
		}
	}

	@Override
	public int update(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return cmtDao.update(commentVO);
	}

	@Override
	public int delete(long c_id) {
		// TODO Auto-generated method stub
		return cmtDao.delete(c_id);
	}

}
