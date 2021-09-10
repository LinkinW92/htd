package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.crm.CustomerBaseInfoDao;
@Service
public class CustomerBaseInfoServiceImpl implements CustomerBaseInfoService {

	@Autowired
	private CustomerBaseInfoDao dao;
	@Override
	public List<Map<String, Object>> showCustomerBaseInfoList(String customerName,String customerType,String id) {
		// TODO Auto-generated method stub
		return dao.showCustomerBaseInfoList(customerName,customerType,id);
	}
	@Override
	public Integer addCustomerBaseInfo(String customerName, String customerType, String customerWebsite,
			String customerProfile, String businessPerson) {
		// TODO Auto-generated method stub
		return dao.addCustomerBaseInfo(customerName, customerType, customerWebsite, customerProfile, businessPerson);
	}
	@Override
	public Integer editCustomerBaseInfo(Integer id, String customerName, String customerType, String customerWebsite,
			String customerProfile, String businessPerson) {
		// TODO Auto-generated method stub
		return dao.editCustomerBaseInfo(id, customerName, customerType, customerWebsite, customerProfile, businessPerson);
	}
	@Override
	public Integer delCustomerBaseInfo(Integer id) {
		// TODO Auto-generated method stub
		return dao.delCustomerBaseInfo(id);
	}
	@Override
	public List<String> showAllCustomerNameList() {
		// TODO Auto-generated method stub
		return dao.showAllCustomerNameList();
	}
	@Override
	public String showCustomerNameById(Integer id) {
		// TODO Auto-generated method stub
		return dao.showCustomerNameById(id);
	}
	@Override
	public List<Map<String, Object>> showCustomerBaseAllInfoListById(Integer id) {
		// TODO Auto-generated method stub
		return dao.showCustomerBaseAllInfoListById(id);
	}
	@Override
	public Integer countDepartmentNum(Integer id) {
		// TODO Auto-generated method stub
		return dao.countDepartmentNum(id);
	}
	@Override
	public Integer countMemberNum(Integer id) {
		// TODO Auto-generated method stub
		return dao.countMemberNum(id);
	}

}
