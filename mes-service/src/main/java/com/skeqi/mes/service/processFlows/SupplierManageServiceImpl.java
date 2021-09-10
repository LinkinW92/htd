package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.processFlows.SupplierManageDao;

@Service
public class SupplierManageServiceImpl implements SupplierManageService {
@Autowired
private SupplierManageDao dao;

	@Override
	public List<Map<String, Object>> showAllSupplierInfos() {
		// TODO Auto-generated method stub
		return dao.showAllSupplierInfos();
	}

	@Override
	public Integer addSupplier(String supplier) {
		// TODO Auto-generated method stub
		return dao.addSupplier(supplier);
	}

	@Override
	public Integer updateSupplier(String supplier, String id) {
		// TODO Auto-generated method stub
		return dao.updateSupplier(supplier, id);
	}

	@Override
	public Integer delSupplier(Integer id) {
		// TODO Auto-generated method stub
		return dao.delSupplier(id);
	}

}
