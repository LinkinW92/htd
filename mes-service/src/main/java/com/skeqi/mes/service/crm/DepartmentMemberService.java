package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DepartmentMemberService {


//	public List<Map<String,Object>> showAllDepartmentMemberList();
	public List<Map<String,Object>> showAllDepartmentMemberList(String customerName,String departmentIName,String memberName);


	public Integer addDepartmentMember( Integer customerId,Integer customerDepartmentId, String name, String tel, String address, String hobby, String mail,String position);


	public Integer editDepartmentMember(Integer id,Integer customerId, Integer customerDepartmentId, String name, String tel, String address, String hobby, String mail,String position);


	public Integer delDepartmentMember(Integer id);

	public List<Map<String,Object>> showDepartmentListBycustomerId(Integer customerId);

//	public List<Map<String,Object>> showDepartmentMemberByCustomerId(Integer id);
	public List<Map<String,Object>> showDepartmentMemberByCustomerId(Integer id,String customerName,String departmentIName,String memberName);


}
