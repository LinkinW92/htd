package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.fqz.CheckoutlistDAO;
import com.skeqi.mes.pojo.CMesCheckoutListT;
import com.skeqi.mes.pojo.CMesCheckoutMethodT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;

@Service
@Transactional
public class CheckoutlistServiceImpl implements CheckoutlistService {

	@Autowired
	private CheckoutlistDAO dao;

	@Override
	public List<CMesCheckoutListT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public void insertcheckout(CMesCheckoutListT list) {
		// TODO Auto-generated method stub
		dao.insertcheckout(list);
	}

	@Override
	public void updatecheckout(CMesCheckoutListT list) {
		// TODO Auto-generated method stub
		dao.updatecheckout(list);
	}

	@Override
	public void deletecheckout(String id) {
		// TODO Auto-generated method stub
		dao.deletecheckout(id);
	}

	@Override
	public CMesCheckoutListT findByid(String id) {
		// TODO Auto-generated method stub
		return dao.findByid(id);
	}


	@Override
	public List<CMesProductionT> findPro() {
		// TODO Auto-generated method stub
		return dao.findPro();
	}

	@Override
	public Integer findOrdernumber(String ordernumber, String pid) {
		// TODO Auto-generated method stub
		return dao.findOrdernumber(ordernumber, pid);
	}

	@Override
	public String findCode(String id) {
		// TODO Auto-generated method stub
		return dao.findCode(id);
	}

	@Override
	public List<CMesCheckoutMethodT> findMethod() {
		// TODO Auto-generated method stub
		return dao.findMethod();
	}

	@Override
	public Integer findMethodId(String mid, String pid) {
		// TODO Auto-generated method stub
		return dao.findMethodId(mid, pid);
	}

}
