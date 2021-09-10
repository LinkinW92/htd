package com.skeqi.mes.mapper.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsMaterialT;

/**
 * @package com.skeqi.mapper.wms
 * @date 2020年2月17日
 * @author Yinp
 * 物料
 */
public interface WmsMaterialDao {

	/**
	 * 查询
	 * @param material
	 * @return
	 */
	public List<CWmsMaterialT> findMaterialList(CWmsMaterialT material);

	/**
	 * 查询所有物料ID、NAME
	 * @return
	 */
	public List<CWmsMaterialT> findMaterialAll();

	/**
	 * 新增
	 * @param material
	 * @return
	 */
	public Integer addMaterial(CWmsMaterialT material);

	/**
	 * 更新
	 * @param material
	 * @return
	 */
	public Integer updateMaterial(CWmsMaterialT material);

	/**
	 * 删除
	 * @param materialId
	 * @return
	 */
	public Integer deleteMaterial(Integer materialId);

}
