package com.biz.score.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.biz.score.domain.ScoreVO;
import com.biz.score.domain.StudentVO;

public interface StudentDao{

	@Select("select * from tbl_student")
	@Results( //constraint 조건이 걸린
		value= {
			@Result(property="st_num", column = "st_num"),
			@Result(property = "scoreList", column = "st_num", javaType = List.class, 
			many=@Many(select = "findBySocreByStNum"))
				}
			)
	public List<StudentVO> findAll();
	
	@Select("select * from tbl_score where s_num=#{st_num}")
	public List<ScoreVO> findBySocreByStNum(long st_num);

	@Select("select * from tbl_student where st_num=#{st_num}")
	@Results( //constraint 조건이 걸린
			value= {
				@Result(property="st_num", column = "st_num"),
				@Result(property = "scoreList", column = "st_num", javaType = List.class, 
				many=@Many(select = "findBySocreByStNum"))
					}
				)
	public StudentVO findByStNum(long st_num);

	public int insert(StudentVO studentVO);
	public int update(StudentVO studentVO);
	
	@Delete("delete from tbl_student where st_num=#{st_num}")
	public int delete(long st_num);

}
