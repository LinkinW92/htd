package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DepartmentInformationService {


	public List<Map<String,Object>> showDepartmentInfoById(Integer customerId);

//	public List<Map<String,Object>> showDepartmentInfo();
	public List<Map<String,Object>> showDepartmentInfo(String customerName);

	public Integer addDepartmentInfo(Integer customerId,String customerDepartmrnt,String departDescription);


	public Integer editDepartmentInfo(Integer id,Integer customerId,String customerDepartmrnt,String departDescription);


	public Integer delDepartmentInfo(Integer id);

	public List<Map<String,Object>> showAllCustomerNameList();

	public List<String> showAllCustomerNameLists(Integer id);

	public String showAllCustomerNameListById( Integer id);

	public List<Map<String,Object>> showAllCustomerNameListsById(Integer id);

	public Integer countMemberNum(Integer departmentId);

}
