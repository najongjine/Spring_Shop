package com.biz.tour.service.userwater;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.tour.dao.userwater.FishUserWaterDao;
import com.biz.tour.domain.userwater.FishUserWaterVO;
import com.biz.tour.service.util.GetCurrentDateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserWaterService {
	private final FishUserWaterDao waterDao;
	private final GetCurrentDateService curTimeService;
	
	public int insert(FishUserWaterVO waterVO) {
		String curTime=curTimeService.getCurDate();
		waterVO.setUf_date(curTime);
		int ret=waterDao.insert(waterVO);
		return ret;
	}
	
	public long getMaxID() {
		return waterDao.getMaxID();
	}

	public FishUserWaterVO findById(Long uf_id) {
		// TODO Auto-generated method stub
		return waterDao.findById(uf_id);
	}

	public int update(FishUserWaterVO userVO) {
		return waterDao.update(userVO);
	}

	public List<FishUserWaterVO> findByTitle(String inputStr, int pageno, int itemLimit) {
		// TODO Auto-generated method stub
		return waterDao.findByTitle(inputStr,pageno,itemLimit);
	}

	public List<FishUserWaterVO> findAll(int pageno, int itemLimit) {
		// TODO Auto-generated method stub
		return waterDao.findAll(pageno,itemLimit);
	}
}
