package com.biz.score.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.score.dao.StudentDao;
import com.biz.score.domain.ScoreVO;
import com.biz.score.domain.StudentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
	private final StudentDao studentDao;

	public List<StudentVO> findAll() {
		List<StudentVO> studentList=studentDao.findAll();
		for(StudentVO studentVO:studentList) {
			int total=0;
			for(ScoreVO scoreVO:studentVO.getScoreList()) {
				total+=scoreVO.getS_score();
			}
			studentVO.setTotalScore(total);
			try {
				studentVO.setAverageScore((int)(total/studentVO.getScoreList().size()));
			} catch (Exception e) {
				studentVO.setAverageScore(0);
			}
			
		}
		
		return studentList;
	}

	public StudentVO findByStNum(long st_num) {
		List<StudentVO> studentList=this.findAll();
		studentList=this.sortByRank(studentList);
		log.debug("!!! studentList after sort: "+studentList.toString());
		StudentVO studentVO=null;
		for(StudentVO vo:studentList) {
			if(st_num==vo.getSt_num()) {
				studentVO=vo;
			}
		}
		return studentVO;
	}

	public int insert(StudentVO studentVO) {
		int ret=studentDao.insert(studentVO);
		return ret;
	}

	public int update(StudentVO studentVO) {
		int ret=studentDao.update(studentVO);
		return ret;
	}

	public int delete(long st_num) {
		// TODO Auto-generated method stub
		return studentDao.delete(st_num);
	}
	
	public List<StudentVO> sortByRank(List<StudentVO> studentList){
		Collections.sort(studentList, new Comparator<StudentVO>() {

			@Override
			public int compare(StudentVO o1, StudentVO o2) {
				// TODO Auto-generated method stub
				return o2.getTotalScore()-o1.getTotalScore();
			}
		});
		for(int i=0;i<studentList.size();i++) {
			studentList.get(i).setRank(i+1);
		}
		return studentList;
	}
}
