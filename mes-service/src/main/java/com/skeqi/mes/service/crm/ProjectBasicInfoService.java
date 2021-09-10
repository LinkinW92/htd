package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProjectBasicInfoService {

	//展示项目基础信息

	public List<Map<String,Object>> showProjectBasicInfo();

    //根据ID展示项目基础信息

	//	public List<Map<String,Object>> showProjectBasicInfoById(String id);
	public List<Map<String,Object>> showProjectBasicInfoById(String id,String projectName,String projectNo,String schemeNo,String ids);

	//新增项目基础信息

	public Integer addProjectBasicInfo(String projectName,String projectNo,String schemeNo,String projectAddress,String projectManager,String customerId);

	//编辑项目基础信息

	public Integer editProjectBasicInfo(Integer id,String projectName,String projectNo,String schemeNo,String projectAddress,String projectManager,String customerId);

	//删除项目基础信息

	public Integer delProjectBasicInfo(Integer id);

	//项目号查重
	public Integer countProjectNOById(String projectNo,String id);




}
