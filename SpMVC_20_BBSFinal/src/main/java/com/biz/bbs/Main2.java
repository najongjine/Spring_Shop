package com.biz.bbs;

import java.util.ArrayList;
import java.util.List;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.service.FileReaderService;

public class Main2 {
	public static void main(String[] args) {
		FileReaderService fService=new FileReaderService();
		
		//전체 작성한 글들(원글,댓글 다 포함)
		List<BBsVO> bbsList=fService.getBBsData();
		
		//원글만 뽑음
		List<BBsVO> pList=fService.getMain(bbsList);
		
		
		List<BBsVO> prList=new ArrayList<BBsVO>();
		pList.forEach(vo->{
			//원글 먼저 리스트에 넣어 주고
			prList.add(vo);
			
			// 전체 글들과 원글을 넘겨줌
			prList.addAll(fService.getReply(bbsList, vo));
		});
		prList.forEach(System.out::println);
	}
}
