package com.skeqi.mes.service.wjp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.wjp.ReworkRouteDao;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.PMesDefectReasonT;
import com.skeqi.mes.pojo.PMesStation;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;

@Service
public class ReworkRouteServiceImpl implements ReworkRouteService{

	@Autowired
	private ReworkRouteDao reworkRouteDao;

	//根据总成号查询
	@Transactional
	@Override
	public RTrackingT findReworkRoute(@SuppressWarnings("rawtypes") Map map) {
		return reworkRouteDao.findReworkRoute(map);
	}

	@Transactional
	@Override
	public List<RTrackingT> findReason() {
		return reworkRouteDao.findReason();
	}

	@Transactional
	@Override
	public void updReworkRoute(@SuppressWarnings("rawtypes") Map map) {
		reworkRouteDao.updReworkRoute(map);
	}

	@Transactional
	@Override
	public void insPtrakingT(@SuppressWarnings("rawtypes") Map map) {
		reworkRouteDao.insPtrakingT(map);
	}

	@Transactional
	@Override
	public void delReworkRoute(@SuppressWarnings("rawtypes") Map map) {
		reworkRouteDao.delReworkRoute(map);
	}

	@Transactional
	@Override
	public List<CMesStationT> findStationName() {
		return reworkRouteDao.findStationName();
	}

	@Transactional
	@Override
	public void updateReworkWayT(@SuppressWarnings("rawtypes") Map map) {
		reworkRouteDao.updateReworkWayT(map);
	}

	@Transactional
	@Override
	public void updRework(@SuppressWarnings("rawtypes") Map map) {
		reworkRouteDao.updRework(map);
	}

	@Transactional
	@Override
	public void insert(@SuppressWarnings("rawtypes") Map map) {
		reworkRouteDao.insert(map);
	}

	@Transactional
	@Override
	public CMesStationT stationName(@SuppressWarnings("rawtypes") Map map) {
		return reworkRouteDao.stationName(map);
	}

	@Transactional
	@Override
	public int findStationId(@SuppressWarnings("rawtypes") Map map) {
		return reworkRouteDao.findStationId(map);
	}

	@Transactional
	@Override
	public int deleteNo(@SuppressWarnings("rawtypes") Map map) {
		return reworkRouteDao.deleteNo(map);
	}

	@Transactional
	@Override
	public ReworkWayT selectNo(@SuppressWarnings("rawtypes") Map map) {
		return reworkRouteDao.selectNo(map);
	}

	@Override
	public List<ReworkWayT> findReworkBySn(Map map) {
		return reworkRouteDao.findReworkBySn(map);
	}

	@Override
	public List<PMesDefectReasonT> defectReason(Map map) {
		return reworkRouteDao.defectReason(map);
	}

	@Transactional
	@Override
	public void adddefectReason(Map map) {
		reworkRouteDao.adddefectReason(map);
	}

	@Transactional
	@Override
	public void delRTracking(Map map) {
		reworkRouteDao.delRTracking(map);
	}

	@Transactional
	@Override
	public void addPTracking(Map map) {
		reworkRouteDao.addPTracking(map);
	}

	@Override
	public List<CMesStationT> listStationByLineId(Map map) {
		return reworkRouteDao.listStationByLineId(map);
	}

}
