package com.biz.tour.service.userwater;

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
	
	public long lastInsertID() {
		return waterDao.last_insert_id();
	}
	
	public int insert(FishUserWaterVO waterVO) {
		String curTime=curTimeService.getCurDate();
		waterVO.setUfwc_date(curTime);
		int ret=waterDao.insert(waterVO);
		return ret;
	}
}
