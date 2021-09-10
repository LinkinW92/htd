package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsProjectType;

/**
 * @date 2020年3月5日
 * @author yinp
 * @ 项目类型
 */
public interface ProjectTypeDao {

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	List<CWmsProjectType> findProjectTypeList(CWmsProjectType dx);

	/**
	 * 通过类型名称查询同名数据条数
	 * @param name
	 * @return
	 */
	int findCountByName(@Param("name")String name, @Param("id")int id);

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	Integer addProjectType(CWmsProjectType dx);

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	Integer updateProjectType(CWmsProjectType dx);

	/**
	 * 删除
	 * @param projectTypeId
	 * @return
	 */
	Integer deleteProjectType(Integer projectTypeId);

}
