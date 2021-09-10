package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.crm.DepartmentInformationDao;
import com.skeqi.mes.mapper.wms.DepartmentDao;
@Service
public class DepartmentInformationServiceImpl implements DepartmentInformationService {

	@Autowired
	private DepartmentInformationDao dao;
	@Override
	public List<Map<String, Object>> showDepartmentInfoById(Integer customerId) {
		// TODO Auto-generated method stub
		return dao.showDepartmentInfoById(customerId);
	}

	@Override
	public Integer addDepartmentInfo(Integer customerId, String customerDepartmrnt, String departDescription) {
		// TODO Auto-generated method stub
		return dao.addDepartmentInfo(customerId, customerDepartmrnt, departDescription);
	}

	@Override
	public Integer editDepartmentInfo(Integer id,Integer customerId, String customerDepartmrnt, String departDescription) {
		// TODO Auto-generated method stub
		return dao.editDepartmentInfo(id,customerId, customerDepartmrnt, departDescription);
	}

	@Override
	public Integer delDepartmentInfo(Integer id) {
		// TODO Auto-generated method stub
		return dao.delDepartmentInfo(id);
	}

	@Override
	public List<Map<String, Object>> showDepartmentInfo(String customerName) {
		// TODO Auto-generated method stub
		return dao.showDepartmentInfo(customerName);
	}

	@Override
	public List<Map<String, Object>> showAllCustomerNameList() {
		// TODO Auto-generated method stub
		return dao.showAllCustomerNameList();
	}

	@Override
	public List<String> showAllCustomerNameLists(Integer id) {
		// TODO Auto-generated method stub
		return dao.showAllCustomerNameLists(id);
	}

	@Override
	public String showAllCustomerNameListById(Integer id) {
		// TODO Auto-generated method stub
		return dao.showAllCustomerNameListById(id);
	}

	@Override
	public List<Map<String, Object>> showAllCustomerNameListsById(Integer id) {
		// TODO Auto-generated method stub
		return dao.showAllCustomerNameListsById(id);
	}

	@Override
	public Integer countMemberNum(Integer departmentId) {
		// TODO Auto-generated method stub
		return dao.countMemberNum(departmentId);
	}

}
