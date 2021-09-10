package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsProject;

/**
 * @package com.skeqi.mapper.wms
 * @date 2020年2月17日
 * @author Yinp
 * 项目
 */
public interface ProjectDao {

	/**
	 * 查询
	 * @param project
	 * @return
	 */
	public List<CWmsProject> findProjectList(CWmsProject project);

	/**
	 * 查询产品类别
	 * @param project
	 * @return
	 */
	public List<JSONObject> findProjectTypeAllIdAndName();

	/**
	 * 新增
	 * @param project
	 * @return
	 */
	public Integer addProject(CWmsProject project);

	/**
	 * 更新ViewMode
	 * @param project
	 * @return
	 */
	public Integer updateViewMode(@Param("id") Integer id);

	/**
	 * 更新
	 * @param project
	 * @return
	 */
	public Integer updateProject(JSONObject json);

	/**
	 * 删除
	 * @param projectId
	 * @return
	 */
	public Integer deleteProject(@Param("projectId")Integer projectId);

	/**
	 * 通过name查询产品数量
	 * @param projectId
	 * @return
	 */
	public int findCountByName(@Param("projectName")String projectName, @Param("id")int id);

	/**
	 * 通过管理编号查询产品数量
	 * @param projectId
	 * @return
	 */
	public int findCountByNo(@Param("no")String no, @Param("id")int id);

}
