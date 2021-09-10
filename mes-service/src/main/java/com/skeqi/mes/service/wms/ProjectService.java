package com.skeqi.mes.service.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsProject;

/**
 * @package com.skeqi.mes.service.wms
 * @date 2020年2月17日
 * @author Yinp
 * 项目
 */
public interface ProjectService {

	/**
	 * 查询
	 * @param project
	 * @return
	 */
	public List<CWmsProject> findProjectList(CWmsProject project);

	/**
	 * 新增
	 * @param project
	 * @return
	 */
	public int addProject(CWmsProject project) throws Exception;

	/**
	 * 更新
	 * @param project
	 * @return
	 */
	public int updateProject(JSONObject json)throws Exception;

	/**
	 * 删除
	 * @param projectId
	 * @return
	 */
	public int deleteProject(Integer projectId);

	/**
	 * 查询产品类别
	 * @param project
	 * @return
	 */
	public List<JSONObject> findProjectTypeAllIdAndName();

	/**
	 * 更新ViewMode
	 * @param project
	 * @return
	 */
	public boolean updateViewMode(Integer id);

}
