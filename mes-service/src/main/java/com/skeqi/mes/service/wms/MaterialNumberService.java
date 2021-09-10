package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsProject;

/**
 * @package com.skeqi.mes.service.wms
 * @date 2020年2月17日
 * @author Yinp 物料库存
 */
public interface MaterialNumberService {

	/**
	 * 查询
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public List<CWmsMaterialNumberT> findMaterialNumberList(Map<String, Object> map);

	/**
	 * 查询导出
	 *
	 * @return
	 */
	public List<JSONObject> exportExcel(JSONObject json);

	/**
	 * 查询库位总库存
	 *
	 * @param locationId
	 * @return
	 */
	public Integer findLocationCount(Integer locationId);

	/**
	 * 通过id查询
	 *
	 * @param materialNumberId
	 * @return
	 */
	public CWmsMaterialNumberT findMaterialNumberById(Integer materialNumber);

	/**
	 * 新增
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public boolean addMaterialNumber(CWmsMaterialNumberT materialNumber);

	/**
	 * 更新
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public boolean updateMaterialNumber(CWmsMaterialNumberT materialNumber);

	/**
	 * 删除
	 *
	 * @param MaterialNumberId
	 * @return
	 */
	public boolean deleteMaterialNumber(Integer id, Integer locationId) throws Exception;

	/**
	 * 更新库存即将出库数量
	 *
	 * @param dx
	 * @param str
	 * @return
	 */
	public boolean updateLmminentRelease(CWmsMaterialNumberT dx, Integer str);

	/**
	 * 查询总库存
	 *
	 * @return
	 */
	public Integer findTotal(Integer projectId, Integer materialId, Integer warehouseId);

	/**
	 * 通过策列查询库存
	 *
	 * @param projectId
	 * @param materialId
	 * @param warehouseId
	 * @return
	 */
	public List<CWmsMaterialNumberT> findMaterialNumber(Integer strategy, Integer projectId, Integer materialId,
			Integer warehouseId);

	/**
	 * 查询所有项目ID、NAME
	 *
	 * @return
	 */
	public List<CWmsProject> findProjectAll(String projectName);

	/**
	 * 查询所有物料ID、NAME
	 *
	 * @return
	 */
	public List<JSONObject> findMaterialAll(String materialName);

	/**
	 * 查询所有库位ID、NAME
	 *
	 * @return
	 */
	public List<CWmsLocationT> findLocationAll();

	/**
	 * 查询条码
	 *
	 * @param materialId
	 * @param projectId
	 * @param locationId
	 * @return
	 */
	public List<Map<String, Object>> findBarCode(Integer materialId, Integer projectId, Integer locationId);

	/**
	 * 更新物料的条码
	 *
	 * @param json
	 * @return
	 */
	public boolean updateBarCode(int id, String presentBarCode) throws Exception;

	/**
	 * 一键转移
	 *
	 * @param str
	 * @throws Exception
	 */
	public void onekeyTransfer(String str, int locationId,int userId) throws Exception;

}
