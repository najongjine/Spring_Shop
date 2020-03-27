package com.biz.tour.service.usersea;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.tour.dao.usersea.FishUserSeaDao;
import com.biz.tour.domain.usersea.FishUserSeaVO;
import com.biz.tour.service.util.GetCurrentDateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSeaService {
	private final FishUserSeaDao seaDao;
	private final GetCurrentDateService curTimeService;
	
	public int insert(FishUserSeaVO seaVO) {
		String curTime=curTimeService.getCurDate();
		seaVO.setUf_date(curTime);
		int ret=seaDao.insert(seaVO);
		return ret;
	}
	
	public long getMaxID() {
		return seaDao.getMaxID();
	}

	public FishUserSeaVO findById(Long uf_id) {
		// TODO Auto-generated method stub
		return seaDao.findById(uf_id);
	}

	public int update(FishUserSeaVO userVO) {
		return seaDao.update(userVO);
	}

	public List<FishUserSeaVO> findByTitle(String inputStr, int pageno, int itemLimit) {
		// TODO Auto-generated method stub
		return seaDao.findByTitle(inputStr,pageno,itemLimit);
	}

	public List<FishUserSeaVO> findAll(int pageno, int itemLimit) {
		// TODO Auto-generated method stub
		return seaDao.findAll(pageno,itemLimit);
	}
}
