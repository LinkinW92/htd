package com.skeqi.mes.service.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsMaterialTypeT;

/**
 * @date 2020年3月5日
 * @author yinp
 * @ 物料类型
 */
public interface WmsMaterialTypeService {

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	List<CWmsMaterialTypeT> findMaterialTypeList(CWmsMaterialTypeT dx);

	/**
	 * 通过id查询
	 * @param dx
	 * @return
	 */
	CWmsMaterialTypeT findMaterialTypeById(Integer materialTypeId);

	/**
	 * 通过name查询
	 * @param dx
	 * @return
	 */
	CWmsMaterialTypeT findMaterialTypeByName(String materialTypeName);

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	boolean addMaterialType(CWmsMaterialTypeT dx);

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	boolean updateMaterialType(CWmsMaterialTypeT dx);

	/**
	 * 删除
	 * @param materialTypeId
	 * @return
	 */
	boolean deleteMaterialType(Integer materialTypeId);

}
