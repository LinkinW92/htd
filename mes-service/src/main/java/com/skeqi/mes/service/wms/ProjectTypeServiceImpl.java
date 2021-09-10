package com.skeqi.mes.service.wms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.ProjectTypeDao;
import com.skeqi.mes.pojo.wms.CWmsProjectType;

/**
 * @date 2020年3月5日
 * @author yinp @ 项目类型
 */
@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

	@Autowired
	ProjectTypeDao dao;

	/**
	 * 查询
	 *
	 * @param dx
	 * @return
	 */
	@Override
	public List<CWmsProjectType> findProjectTypeList(CWmsProjectType dx) {
		List<CWmsProjectType> list = dao.findProjectTypeList(dx);
		return list;
	}

	/**
	 * 新增
	 *
	 * @param dx
	 * @return
	 * @throws Exception
	 */
	@Override
	public int addProjectType(CWmsProjectType dx) throws Exception {
		int count = dao.findCountByName(dx.getProjectType(), 0);
		if(count!=0) {
			throw new Exception("产品类型名称已存在");
		}
		return dao.addProjectType(dx);
	}

	/**
	 * 更新
	 *
	 * @param dx
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateProjectType(CWmsProjectType dx) throws Exception {
		int count = dao.findCountByName(dx.getProjectType(), dx.getId());
		if(count!=0) {
			throw new Exception("产品类型名称已存在");
		}
		return dao.updateProjectType(dx);
	}

	/**
	 * 删除
	 *
	 * @param projectTypeId
	 * @return
	 */
	@Override
	public int deleteProjectType(Integer projectTypeId) {
		return dao.deleteProjectType(projectTypeId);
	}
}
