package com.biz.bbs.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.bbs.domain.CommentVO;

public interface CommentDao {
	@Select("select * from tbl_comment order by C_DATE_TIME desc")
	public List<CommentVO> selectAll();
	
	@Select("select * from tbl_comment where c_id=#{c_id}")
	public CommentVO findById(long c_id);
	

	@Select("select * from tbl_comment where long c_p_id=#{long c_p_id} order by C_DATE_TIME desc")
	public List<CommentVO> findByPId(long c_p_id);
	
	/*
	 * 게시판 원글에 달린 코멘트 들만 추출하기
	 */
	@Select("select * from tbl_comment where c_b_id=#{c_b_id} order by C_DATE_TIME desc")
	public List<CommentVO> findByBId(long c_b_id);
	
	public int insert(CommentVO commentVO);
	public int update(CommentVO commentVO);
	
	@Delete("delete from tbl_comment where c_id=#{c_id}")
	public int delete(long c_id);
}
