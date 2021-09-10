package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface CustomerBaseInfoService {

	public List<Map<String,Object>> showCustomerBaseInfoList(String customerName,String customerType,String id);

	public Integer addCustomerBaseInfo(String customerName,String customerType,String customerWebsite,String customerProfile,String businessPerson);

	public Integer editCustomerBaseInfo(Integer id,String customerName,String customerType,String customerWebsite,String customerProfile,String businessPerson);

	public Integer delCustomerBaseInfo(Integer id);

	public List<String> showAllCustomerNameList();

	public String showCustomerNameById(Integer id);

	public List<Map<String,Object>> showCustomerBaseAllInfoListById(Integer id);

	public Integer countDepartmentNum(Integer id);

	public Integer countMemberNum(Integer id);

}
