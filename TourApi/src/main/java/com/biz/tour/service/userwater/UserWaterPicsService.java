package com.biz.tour.service.userwater;

import org.springframework.stereotype.Service;

import com.biz.tour.dao.userwater.FishUserWaterPicsDao;
import com.biz.tour.domain.userwater.FishUserWaterPicsVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserWaterPicsService {
	private final FishUserWaterPicsDao waterpicsDao;
	
	public long lastInsertID() {
		return waterpicsDao.last_insert_id();
	}
	
	public int insert(FishUserWaterPicsVO waterPicsVO) {
		int ret=waterpicsDao.insert(waterPicsVO);
		return ret;
	}
}
