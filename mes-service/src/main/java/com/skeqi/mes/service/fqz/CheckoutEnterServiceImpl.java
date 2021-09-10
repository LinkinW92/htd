package com.skeqi.mes.service.fqz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.CheckoutEnterDAO;
import com.skeqi.mes.pojo.CMesCheckoutEnterT;
import com.skeqi.mes.pojo.CMesCheckoutListT;
import com.skeqi.mes.pojo.CMesFindT;
import com.skeqi.mes.pojo.CMesWarehouseListT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesEolT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.PMesPlanPrintT;

@Service
public class CheckoutEnterServiceImpl implements CheckoutEnterService{

	@Autowired
	private CheckoutEnterDAO dao;

	@Override
	public Integer findSN(String sn) {
		// TODO Auto-generated method stub
		return dao.findSN(sn);
	}

	@Override
	public CMesFindT findPro(String sn) {
		// TODO Auto-generated method stub
		return dao.findPro(sn);
	}

	@Override
	public List<CMesCheckoutListT> findCheckList(String sn) {
		// TODO Auto-generated method stub
		return dao.findCheckList(sn);
	}

	@Override
	public List<PMesKeypartT> findKeypart(String sn) {
		// TODO Auto-generated method stub
		return dao.findKeypart(sn);
	}

	@Override
	public List<PMesBoltT> findBolt(String sn) {
		// TODO Auto-generated method stub
		return dao.findBolt(sn);
	}

	@Override
	public List<PMesLeakageT> findLeakage(String sn) {
		// TODO Auto-generated method stub
		return dao.findLeakage(sn);
	}

	@Override
	public Integer findKeypartCount(String sn) {
		// TODO Auto-generated method stub
		return dao.findKeypartCount(sn);
	}

	@Override
	public Integer findBoltCount(String sn) {
		// TODO Auto-generated method stub
		return dao.findBoltCount(sn);
	}

	@Override
	public Integer findLeakageCount(String sn) {
		// TODO Auto-generated method stub
		return dao.findLeakageCount(sn);
	}

	@Override
	public void insertEnter(CMesCheckoutEnterT list) {
		// TODO Auto-generated method stub
		dao.insertEnter(list);
	}

	@Override
	public CMesCheckoutListT findByNo(String pid, String no) {
		// TODO Auto-generated method stub
		return dao.findByNo(pid, no);
	}

	@Override
	public PMesPlanPrintT findPlan(String sn) {
		// TODO Auto-generated method stub
		return dao.findPlan(sn);
	}

	@Override
	public void inserWareHouse(CMesWarehouseListT list) {
		// TODO Auto-generated method stub
		dao.inserWareHouse(list);
	}

	@Override
	public Integer findHouseSN(String sn) {
		// TODO Auto-generated method stub
		return dao.findHouseSN(sn);
	}

	@Override
	public Integer findEolCount(String sn) {
		// TODO Auto-generated method stub
		return dao.findEolCount(sn);
	}

	@Override
	public List<PMesEolT> findEol(String sn) {
		// TODO Auto-generated method stub
		return dao.findEol(sn);
	}



}
