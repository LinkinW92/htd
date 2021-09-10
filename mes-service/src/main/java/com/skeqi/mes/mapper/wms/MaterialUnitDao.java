package com.skeqi.mes.mapper.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsMaterialUnitT;

/**
 * @date 2020年3月5日
 * @author yinp
 * @ 物料单位
 */
public interface MaterialUnitDao {

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	List<CWmsMaterialUnitT> findMaterialUnitList(CWmsMaterialUnitT dx);

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	Integer addMaterialUnit(CWmsMaterialUnitT dx);

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	Integer updateMaterialUnit(CWmsMaterialUnitT dx);

	/**
	 * 删除
	 * @param materialUnitId
	 * @return
	 */
	Integer deleteMaterialUnit(Integer materialUnitId);

}
