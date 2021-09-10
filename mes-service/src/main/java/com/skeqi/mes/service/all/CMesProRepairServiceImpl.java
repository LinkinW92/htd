package com.skeqi.mes.service.all;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.all.CMesProRepairDAO;
import com.skeqi.mes.mapper.all.CheckAllRecipePDao;
import com.skeqi.mes.mapper.all.CheckSnPDao;
import com.skeqi.mes.pojo.CMesReturnRepairT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;

@Service
@Transactional
public class CMesProRepairServiceImpl implements CMesProRepairService {

	@Autowired
	CMesProRepairDAO dao;

	@Autowired
    CheckSnPDao checkSNPDao;

	@Autowired
	CheckAllRecipePDao checkAllRecipeDao;


	@Override
	public RTrackingT findBySn(String sn) {
		// TODO Auto-generated method stub
		return dao.findBySn(sn);
	}

	@Override
	public String findProType(Integer id) {
		// TODO Auto-generated method stub
		return dao.findProType(id);
	}

	@Override
	public List<RMesKeypart> findKeyPartBySn(String sn) {
		// TODO Auto-generated method stub
		return dao.findKeyPartBySn(sn);
	}

	@Override
	public List<RMesBolt> findBoltBySn(String sn) {
		// TODO Auto-generated method stub
		return dao.findBoltBySn(sn);
	}

	@Override
	public List<RMesLeakage> findLeakageBySn(String sn) {
		// TODO Auto-generated method stub
		return dao.findLeakageBySn(sn);
	}

	@Override
	public Integer findRecipeId(String sn, Integer pro, Integer line) {
		// TODO Auto-generated method stub
		//**根据sn查询工艺和配方id**//
		Integer totalRecipeId = 0;
		RMesPlanT findAllPlan = checkSNPDao.findAllPlan(sn);
		if(findAllPlan!=null){
			totalRecipeId = findAllPlan.getTotalRecipeId();
		}else{
			totalRecipeId = checkAllRecipeDao.findDefaultTotal(line, pro);
		}
		return totalRecipeId;
	}

	@Override
	public Integer findRouting(String sn, Integer pro, Integer line) {
		// TODO Auto-generated method stub
		Integer routingId = 0;
		RMesPlanT findAllPlan = checkSNPDao.findAllPlan(sn);
		if(findAllPlan!=null){
			routingId = findAllPlan.getRoutingId();
		}else{
			routingId = checkAllRecipeDao.findDefaultRouting(line, pro);
		}
		return routingId;
	}

	@Override
	public List<CMesStationT> findStation(Integer id) {
		// TODO Auto-generated method stub
		List<CMesStationT> findStation = dao.findStation(id);
//		List<CMesStationT> list = new ArrayList<CMesStationT>();
//		for (Integer i : findStation) {
//			list.add(mapper.findStationByid(i));
//		}
		return findStation;
	}

	@Override
	public Integer insertReturnRepair(CMesReturnRepairT repair) {
		// TODO Auto-generated method stub
		return dao.insertReturnRepair(repair);
	}

	@Override
	public Integer insertReworkWay(ReworkWayT reworkWay) {
		// TODO Auto-generated method stub
		return dao.insertReworkWay(reworkWay);
	}

	@Override
	public Integer updateRTracking(String sn) {
		// TODO Auto-generated method stub
		return dao.updateRTracking(sn);
	}

	@Override
	public String findStationById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findStationById(id);
	}


}
