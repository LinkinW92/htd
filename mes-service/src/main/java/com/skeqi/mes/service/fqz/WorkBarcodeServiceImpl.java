package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.fqz.WrokBarcodeDAO;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesWorkBarcodeT;
import com.skeqi.mes.pojo.PMesStationPassT;
import com.skeqi.mes.pojo.PMesTrackingT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesTrackingT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;

@Service
@Transactional
public class WorkBarcodeServiceImpl implements WorkBarcodeService {

	@Autowired
	private WrokBarcodeDAO dao;
	@Override
	public List<CMesWorkBarcodeT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}
	@Override
	public List<PMesStationPassT> sndail(String sn) {
		// TODO Auto-generated method stub
		return dao.sndail(sn);
	}
	@Override
	public RMesTrackingT findR(String workId, String sn) {
		// TODO Auto-generated method stub
		return dao.findR(workId, sn);
	}
	@Override
	public PMesTrackingT findP(String workId, String sn) {
		// TODO Auto-generated method stub
		return dao.findP(workId, sn);
	}
	@Override
	public List<CMesWorkBarcodeT> findAllP(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAllP(map);
	}
	@Override
	public List<CMesProductionT> findPro(Integer id) {
		// TODO Auto-generated method stub
		return dao.findPro(id);
	}
	@Override
	public List<RMesPlanT> findPlan(String id) {
		// TODO Auto-generated method stub
		return dao.findPlan(id);
	}
	@Override
	public List<RMesWorkorderDetailT> findWorkor(String id) {
		// TODO Auto-generated method stub
		return dao.findWorkor(id);
	}
}
