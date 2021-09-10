package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.crm.ProjectBasicInfoDao;

@Service
public class ProjectBasicInfoServiceImpl implements ProjectBasicInfoService {

	@Autowired
	private ProjectBasicInfoDao dao;
	@Override
	public List<Map<String, Object>> showProjectBasicInfo() {
		// TODO Auto-generated method stub
		return dao.showProjectBasicInfo();
	}

	@Override
	public Integer addProjectBasicInfo(String projectName, String projectNo, String schemeNo, String projectAddress,
			String projectManager,String customerId) {
		// TODO Auto-generated method stub
		return dao.addProjectBasicInfo(projectName, projectNo, schemeNo, projectAddress, projectManager,customerId);
	}

	@Override
	public Integer editProjectBasicInfo(Integer id, String projectName, String projectNo, String schemeNo,
			String projectAddress, String projectManager,String customerId) {
		// TODO Auto-generated method stub
		return dao.editProjectBasicInfo(id, projectName, projectNo, schemeNo, projectAddress, projectManager,customerId);
	}

	@Override
	public Integer delProjectBasicInfo(Integer id) {
		// TODO Auto-generated method stub
		return dao.delProjectBasicInfo(id);
	}

	@Override
	public List<Map<String, Object>> showProjectBasicInfoById(String id,String projectName,String projectNo,String schemeNo,String ids) {
		// TODO Auto-generated method stub
		return dao.showProjectBasicInfoById(id,projectName,projectNo,schemeNo,ids);
	}

	@Override
	public Integer countProjectNOById(String projectNo,String id) {
		// TODO Auto-generated method stub
		return dao.countProjectNOById(projectNo,id);
	}



}
