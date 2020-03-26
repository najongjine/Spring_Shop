package com.biz.tour.dao.userwater;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.tour.domain.userwater.FishUserWaterVO;

public interface FishUserWaterDao {
	@Select("select * from tbl_userfish_water")
	public List<FishUserWaterVO> findAll();
	
	@Select("select * from tbl_userfish_water where uf_id=#{uf_id}")
	public FishUserWaterVO findById(Long uf_id);
	
	@Select("select * from tbl_userfish_water where uf_title like concat ('%', #{uf_title} ,'%')")
	public List<FishUserWaterVO> findByTitle(String uf_title); 
	
	@Select("select max(uf_id) from tbl_userfish_water")
	public long getMaxID();
	
	public int insert(FishUserWaterVO userVO);

	public int update(FishUserWaterVO userVO);

}
