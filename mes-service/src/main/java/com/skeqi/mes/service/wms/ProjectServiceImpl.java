package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.ProjectDao;
import com.skeqi.mes.pojo.wms.CWmsProject;

/**
 * @package com.skeqi.mes.service.wms
 * @date 2020年2月17日
 * @author Yinp 项目
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDao dao;

	/**
	 * 查询
	 *
	 * @param project
	 * @return
	 */
	@Override
	public List<CWmsProject> findProjectList(CWmsProject project) {
		List<CWmsProject> projectList = dao.findProjectList(project);
		return projectList;
	}

	/**
	 * 新增
	 *
	 * @param project
	 * @return
	 * @throws Exception
	 */
	@Override
	public int addProject(CWmsProject project) throws Exception {

		int count = dao.findCountByNo(project.getManagementNo(), 0);
		if (count != 0) {
			throw new Exception("项目管理编号名称已经存在");
		}
		count = dao.findCountByName(project.getProjectName(), 0);
		if (count != 0) {
			throw new Exception("项目名称已经存在");
		}

		return dao.addProject(project);
	}

	/**
	 * 更新
	 *
	 * @param project
	 * @return
	 */
	@Override
	public int updateProject(JSONObject json) throws Exception {
		int count = dao.findCountByNo(json.getString("managementNo"), json.getInteger("id"));
		if (count != 0) {
			throw new Exception("项目管理编号名称已经存在");
		}
		count = dao.findCountByName(json.getString("managementNo"), json.getInteger("id"));
		if (count != 0) {
			throw new Exception("项目名称已经存在");
		}
		return dao.updateProject(json);
	}

	/**
	 * 删除
	 *
	 * @param projectId
	 * @return
	 */
	@Override
	public int deleteProject(Integer projectId) {
		return dao.deleteProject(projectId);
	}

	@Override
	public List<JSONObject> findProjectTypeAllIdAndName() {
		// TODO Auto-generated method stub
		return dao.findProjectTypeAllIdAndName();
	}

	@Override
	public boolean updateViewMode(Integer id) {
		Integer result = dao.updateViewMode(id);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

}
