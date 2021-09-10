package com.skeqi.mes.mapper.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsMaterialTypeT;

/**
 * @date 2020年3月5日
 * @author yinp
 * @ 物料类型
 */
public interface WmsMaterialTypeDao {

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	List<CWmsMaterialTypeT> findMaterialTypeList(CWmsMaterialTypeT dx);

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	Integer addMaterialType(CWmsMaterialTypeT dx);

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	Integer updateMaterialType(CWmsMaterialTypeT dx);

	/**
	 * 删除
	 * @param materialTypeId
	 * @return
	 */
	Integer deleteMaterialType(Integer materialTypeId);

}
