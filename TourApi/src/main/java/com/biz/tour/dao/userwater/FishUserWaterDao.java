package com.biz.tour.dao.userwater;

import org.apache.ibatis.annotations.Select;

import com.biz.tour.domain.userwater.FishUserWaterVO;

public interface FishUserWaterDao {
	@Select("select last_insert_id();")
	public long last_insert_id();
	
	public int insert(FishUserWaterVO userVO);
}
