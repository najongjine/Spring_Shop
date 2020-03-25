package com.biz.tour.service.userwater;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.tour.dao.userwater.FishWaterCommentDao;
import com.biz.tour.domain.userwater.FishUserWaterCommentVO;
import com.biz.tour.service.util.GetCurrentDateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserWaterCommentService {
	private final FishWaterCommentDao wcDao;
	private final GetCurrentDateService dateService;

	public List<FishUserWaterCommentVO> findByFk(long ufc_fk) {
		// 댓글중 타 댓글에 의존이 없는 (pid=0)인 댓글들 뽑음
		List<FishUserWaterCommentVO> commentList=wcDao.findByFk(ufc_fk);
		
		//댓글 계층구조가 다 완성된 list를 담을 객체
		List<FishUserWaterCommentVO> sortedCommentList=new ArrayList<FishUserWaterCommentVO>();
		
		//재귀 함수를 통해서 pid 가 없는 답글 기준으로...
		for(FishUserWaterCommentVO vo:commentList) {
			sortedCommentList.addAll(sortReplyHeriarchy(vo, 0));
		}
		// TODO Auto-generated method stub
		return sortedCommentList;
	}

	/*
	 * 게시판 코멘트 답변 리스트 가져오기
	 */
	private List<FishUserWaterCommentVO> sortReplyHeriarchy(FishUserWaterCommentVO cmtVO, int depth){
		// pid 0 인 댓글 하나 기준으로 child 댓글 heriarchy 를 만들 list
		List<FishUserWaterCommentVO> retList=new ArrayList<FishUserWaterCommentVO>();
		if(depth>0) {
			String c_subject="&nbsp;";
			for(int i=0;i<depth;i++) {
				c_subject+=" re: ";
			}
			c_subject+=cmtVO.getUfc_title();
			cmtVO.setUfc_title(c_subject);
		}
		// 매개 변수로 받은 댓글vo(부모)vo를 list에 먼저 추가 시켜줌
		retList.add(cmtVO);
		
		//부모 id와 같은 pid를 가진 child 댓글들 뽑음.
		List<FishUserWaterCommentVO> tempList=wcDao.findByPId(cmtVO.getUfc_id());
		// child 댓글이 더이상 없으면 재귀함수 exit
		if(tempList.size()<1) return retList;
		
		for(FishUserWaterCommentVO vo:tempList) {
			// 각 child 댓글들을 기준으로 또 child-child 댓글이 있나 재귀함수로 확인.
			retList.addAll(sortReplyHeriarchy(vo, depth+1));
		}
		return retList;
	}

	public int insert(FishUserWaterCommentVO commentVO) {
		log.debug("!!! insert cmt service");
		// TODO Auto-generated method stub
		String curDate=dateService.getCurDate();
		commentVO.setUfc_date(curDate);
		return wcDao.insert(commentVO);
	}
}
