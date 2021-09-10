package com.skeqi.mes.service.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsProjectType;

/**
 * @date 2020年3月5日
 * @author yinp
 * @ 项目类型
 */
public interface ProjectTypeService {

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	List<CWmsProjectType> findProjectTypeList(CWmsProjectType dx);

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	int addProjectType(CWmsProjectType dx) throws Exception;

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	int updateProjectType(CWmsProjectType dx) throws Exception;

	/**
	 * 删除
	 * @param projectTypeId
	 * @return
	 */
	int deleteProjectType(Integer projectTypeId);

}
