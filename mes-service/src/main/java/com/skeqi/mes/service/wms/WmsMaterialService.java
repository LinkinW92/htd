package com.skeqi.mes.service.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsMaterialT;

/**
 * @package com.skeqi.mes.service.wms
 * @date 2020年2月17日
 * @author Yinp 物料
 */
public interface WmsMaterialService {
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
	 * 通过id查询
	 * @param materialId
	 * @return
	 */
	public CWmsMaterialT findMaterialById(Integer materialId);

	/**
	 * 通过name查询
	 * @param materialName
	 * @return
	 */
	public CWmsMaterialT findMaterialByName(String materialName);

	/**
	 * 新增
	 * @param material
	 * @return
	 */
	public boolean addMaterial(CWmsMaterialT material);

	/**
	 * 更新
	 * @param material
	 * @return
	 */
	public boolean updateMaterial(CWmsMaterialT material);

	/**
	 * 删除
	 * @param materialId
	 * @return
	 */
	public boolean deleteMaterial(Integer materialId);

}
