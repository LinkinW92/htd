package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.crm.DepartmentMemberDao;
@Service
public class DepartmentMemberServiceImpl implements DepartmentMemberService {

	@Autowired
	private DepartmentMemberDao dao;
	@Override
	public List<Map<String, Object>> showAllDepartmentMemberList(String customerName,String departmentIName,String memberName) {
		// TODO Auto-generated method stub
		return dao.showAllDepartmentMemberList(customerName,departmentIName,memberName);
	}

	@Override
	public Integer addDepartmentMember(Integer customerId, Integer customerDepartmentId, String name, String tel,
			String address, String hobby, String mail,String position) {
		// TODO Auto-generated method stub
		return dao.addDepartmentMember(customerId, customerDepartmentId, name, tel, address, hobby, mail,position);
	}

	@Override
	public Integer editDepartmentMember(Integer id, Integer customerId, Integer customerDepartmentId, String name,
			String tel, String address, String hobby, String mail,String position) {
		// TODO Auto-generated method stub
		return dao.editDepartmentMember(id, customerId, customerDepartmentId, name, tel, address, hobby, mail,position);
	}

	@Override
	public Integer delDepartmentMember(Integer id) {
		// TODO Auto-generated method stub
		return dao.delDepartmentMember(id);
	}

	@Override
	public List<Map<String, Object>> showDepartmentListBycustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return dao.showDepartmentListBycustomerId(customerId);
	}

	@Override
	public List<Map<String, Object>> showDepartmentMemberByCustomerId(Integer id,String customerName,String departmentIName,String memberName) {
		// TODO Auto-generated method stub
		return dao.showDepartmentMemberByCustomerId(id,customerName,departmentIName,memberName);
	}

}
