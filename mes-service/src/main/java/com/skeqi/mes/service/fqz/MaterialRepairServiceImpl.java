package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.MaterialRepairDAO;
import com.skeqi.mes.pojo.CMESMaterialRepairT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesStationT;

@Service
public class MaterialRepairServiceImpl implements MaterialRepairService{

	@Autowired
	private MaterialRepairDAO dao;

	@Override
	public List<CMESMaterialRepairT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}
	@Override
	public List<CMesStationT> findStation() {
		// TODO Auto-generated method stub
		return dao.findStation();
	}
	@Override
	public List<CMesMaterialT> findMateial() {
		// TODO Auto-generated method stub
		return dao.findMateial();
	}
	@Override
	public void insertM(CMESMaterialRepairT c) {
		// TODO Auto-generated method stub
		dao.insertM(c);
	}
	@Override
	public void updatestatus(CMESMaterialRepairT c) {
		// TODO Auto-generated method stub
		dao.updatestatus(c);
	}
	@Override
	public void delmaterial(String id) {
		// TODO Auto-generated method stub
		dao.delmaterial(id);
	}

}
