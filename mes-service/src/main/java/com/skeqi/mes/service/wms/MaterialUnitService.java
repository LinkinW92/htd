package com.skeqi.mes.service.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsMaterialUnitT;

/**
 * @date 2020年3月5日
 * @author yinp
 * @ 物料单位
 */
public interface MaterialUnitService {

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	List<CWmsMaterialUnitT> findMaterialUnitList(CWmsMaterialUnitT dx);

	/**
	 * 通过id查询
	 * @param dx
	 * @return
	 */
	CWmsMaterialUnitT findMaterialUnitById(Integer materialUnitId);

	/**
	 * 通过name查询
	 * @param dx
	 * @return
	 */
	CWmsMaterialUnitT findMaterialUnitByName(String materialUnitName);

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	boolean addMaterialUnit(CWmsMaterialUnitT dx);

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	boolean updateMaterialUnit(CWmsMaterialUnitT dx);

	/**
	 * 删除
	 * @param materialUnitId
	 * @return
	 */
	boolean deleteMaterialUnit(Integer materialUnitId);

}
