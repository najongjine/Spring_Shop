package com.biz.tour.dao.userwater;

import org.apache.ibatis.annotations.Select;

import com.biz.tour.domain.userwater.FishUserWaterPicsVO;

public interface FishUserWaterPicsDao {
	@Select("select last_insert_id();")
	public long last_insert_id();

	int insert(FishUserWaterPicsVO waterPicsVO);

}
