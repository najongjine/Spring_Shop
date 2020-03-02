package com.biz.bbs.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.biz.bbs.domain.BBsVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileReaderService {
	public List<BBsVO> getBBsData(){
		// src/main/resources 폴더에있는 파일 정보 가져오기
		ClassPathResource cr=new ClassPathResource("bbs_data.txt");
		Path path;
		try {
			path=Paths.get(cr.getURI());
			List<String> lines=Files.readAllLines(path);
			List<BBsVO> bbsList=new ArrayList<BBsVO>();
			for(String line:lines) {
				String[] items=line.split(":");
				BBsVO bbsVO=new BBsVO();
				bbsVO.setB_id(Long.valueOf(items[0]));
				bbsVO.setB_p_id(Long.valueOf(items[1]));
				bbsVO.setB_writer(items[2]);
				String date_time=items[3];
				date_time+=":"+items[4];
				date_time+=":"+items[5];
				bbsVO.setB_date_time(date_time);
				bbsVO.setB_subject(items[6]);
				bbsVO.setB_content(items[7]);
				bbsList.add(bbsVO);
			}
			return bbsList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}//getbbsdata
	
	public List<BBsVO> getMain(List<BBsVO> bbsList){
		List<BBsVO> pList=null;
		// List 객체를 stream 객체로 변환
		// 원글만 뽑기
		pList=bbsList.stream().filter(vo->vo.getB_p_id()<=0).collect(Collectors.toList());
		return pList;
	}
	public List<BBsVO> getReply(List<BBsVO> bbsList,BBsVO bbsVO){
		//전체 기시글들(댓글, 원글 다 포함)과 기준으로 잡은 원글을 받음
		List<BBsVO> rList=new ArrayList<BBsVO>();
		
		// 원글을 먼저 리스트에 추가
		rList.add(bbsVO);
		
		List<BBsVO> tempList=null;
		//원글에 달린 댓글들을 뽑음
		tempList=bbsList.stream().filter(vo->vo.getB_p_id()==bbsVO.getB_id()).collect(Collectors.toList());
		
		// 댓글이 없으면 종료
		if(tempList.size()<1) return rList;
		
		// tempList에 데이터가 있으면
		// List에서 vo를 하나씩 추출하여
		// 다시 getReply() 호출
		// 더이상 리플이 없을때까지
		tempList.forEach(vo->{
			//원글을 담은 리스트에 댓글들을 추가 시켜줌과 동시에
			// 원글 기준을 댓글로 다시 잡고 재귀호출
			rList.addAll(getReply(bbsList, vo));
		});
		
		//이로써 난잡하게 저장된 게시글 리스트가 원글->댓글->댓글의댓글 순서로 정리됨 
		return rList;
	}
}
