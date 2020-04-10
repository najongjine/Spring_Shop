package com.biz.score.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.score.domain.ScoreVO;

public interface ScoreDao{
	@Select("select * from tbl_score")
	public List<ScoreVO> findAll();
	
	@Select("select * from tbl_score where s_num=#{s_num}")
	public List<ScoreVO> findBySNum(long s_num);
	
	@Select("select * from tbl_score where s_id=#{s_id}")
	public ScoreVO findById(long s_id);

	public int insert(ScoreVO scoreVO);
	public int update(ScoreVO scoreVO);
	
	@Delete("delete from tbl_score where s_id=#{s_id}")
	public int delete(long s_id);

}
