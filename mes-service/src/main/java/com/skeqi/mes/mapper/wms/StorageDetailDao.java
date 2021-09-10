package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * @package com.skeqi.mapper.wms
 * @date 2020年2月17日
 * @author Yinp 库存详情
 */
public interface StorageDetailDao {

	/**
	 * 查询
	 *
	 * @param storageDetail
	 * @return
	 */
	public List<CWmsStorageDetailT> findStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 查询P表
	 *
	 * @param storageDetail
	 * @return
	 */
	public List<CWmsStorageDetailT> findPStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 新增
	 *
	 * @param storageDetail
	 * @return
	 */
	public Integer addStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 新增P表
	 *
	 * @param storageDetail
	 * @return
	 */
	public Integer addPStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 更新
	 *
	 * @param storageDetail
	 * @return
	 */
	public Integer updateStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 删除
	 *
	 * @param storageDetailId
	 * @return
	 */
	public Integer deleteStorageDetail(Integer storageDetailId);

	/**
	 * 查询物料库存
	 *
	 * @param dx
	 * @return
	 */
	public List<CWmsStorageDetailT> findStorageDetailList(Map<String, Object> map);

	/**
	 * 查询所有项目号的id+name
	 *
	 * @return
	 */
	public List<CWmsProject> findProjectIdName();

	/**
	 * 查询所有库位的Id+name
	 *
	 * @return
	 */
	public List<CWmsLocationT> findLocationIdName();

	/**
	 * 通过单据号查询条码
	 *
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findBarCode(@Param("listNo") String listNo, @Param("materialId") int materialId,
			@Param("locationId")Integer locationId, @Param("projectId")Integer projectId);

	/**
	 * 导出
	 *
	 * @return
	 */
	public List<JSONObject> exportExcel(JSONObject json);

}
