package com.biz.bbs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
@Service
public class BBsServiceImpl implements BBsService{
	protected final BbsDao bbsDao;
	
	public BBsServiceImpl(BbsDao bbsDao) {
		this.bbsDao = bbsDao;
	}

	/*
	 * pagination 수행할때
	 * 원글 목록을 pagi 대상으로 할것인지
	 * 원글+댓글 포함한 목록 pagi 대상으로 할것인지.
	 */
	@Override
	public List<BBsVO> selectAll() {
		// 원글 중심이면 mainlist를 필요한 개수만큼 pagi로 분할하여 사용
		//원글만 전체 뽑음
		List<BBsVO> mainList=bbsDao.selectAll();
		
		//원글밑에 답글 형식으로 정리할 리스트
		List<BBsVO> retList=new ArrayList<BBsVO>();
		for(BBsVO vo:mainList) {
			// 각 각의 원글에 대하여 댓글을 뽑을거임, 두번째 인자는 깊이(옵션)
			retList.addAll(selectReply(vo,0));
		}
		
		//잘 정렬된 리스트(새로 만든것)를 리턴
		return retList;
	}

	private List<BBsVO> selectReply(BBsVO bbsVO,int depth){
		// 잘 정리된 리스트로 쓰일것을 새로 만듬
		List<BBsVO> retList=new ArrayList<BBsVO>();
		//깊이로 제목 수정(옵션)
		if(depth>0) {
			String b_subject="&nbsp;";
			for(int i=0;i<depth;i++) {
				b_subject+=" re:";
			}
			b_subject+=bbsVO.getB_subject();
			bbsVO.setB_subject(b_subject);			
		}
		
		//정리된걸로 쓰일 리스트에 첫번째로 원글 add
		// 원글의 중심은 재귀호출을 돌때마다 바뀜(댓글의 댓글이 원글이 될수도 있음)
		retList.add(bbsVO);
		
		//원글에 대한 댓글목록 뽑음
		List<BBsVO> tempList=bbsDao.findByPId(bbsVO.getB_id());
		//댓글이 없으면 원글만 포함된 리스트를 리턴.
		//원글의 중심은 재귀호출을 돌때마다 바뀜(댓글의 댓글이 원글이 될수도 있음)
		if(tempList.size()<1) return retList;
		
		//댓글의 댓글목록을 담을 리스트
		List<BBsVO> repList;
		
		//각 댓글들에 대하여
		for(BBsVO vo:tempList) {
			//댓글에 댓글을 또 찾음.
			// 이때가 댓글을 원글로 기준을 잡음
			// 찾았으면 댓글의 댓글목록을 replist에 담음
			repList=selectReply(vo,depth+1);
			
			//정리할 리스트레는 원글이 담겨있음.
			//원글 다음번째에 댓글들을 add 시킴.
			retList.addAll(repList);
		}

		/*
		 * 원글
		 * 	댓글
		 * 		댓글
		 * 			...
		 * 을 리턴 시킴
		 */
		return retList;
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
		// 작성일자 현재 저장하는 날짜로 세팅을 하자.
		LocalDateTime ldt=LocalDateTime.now();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		bbsVO.setB_date_time(ldt.format(df).toString());
		int ret=bbsDao.insert(bbsVO);
		return ret;
	}

	@Override
	public int delete(long b_id) {
		// TODO Auto-generated method stub
		return bbsDao.delete(b_id);
	}

	@Override
	public int update(BBsVO bbsVO) {
		// 작성일자 현재 저장하는 날짜로 세팅을 하자.
		LocalDateTime ldt=LocalDateTime.now();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		bbsVO.setB_date_time(ldt.format(df).toString());
		return bbsDao.update(bbsVO);
	}

}
