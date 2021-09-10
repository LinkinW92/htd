package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;

/**
 * @author yinp
 * @explain 加料队列
 * @date 2020-11-23
 */
public interface ChargingTaskqueueService {

	// 查询审批记录
	public PageInfo<CWmsApprovalT> findList(JSONObject json) throws Exception;

	// 查询入库队列
	public List<CWmsInTaskqueueT> findInTaskqueueTList(Map<String, Object> map) throws Exception;

	// 查询库存详情
	public PageInfo<JSONObject> findStorageDetail(Map<String, Object> map) throws Exception;

	// 加料不出库
	public boolean buchuku(Integer inTaskqueueId) throws Exception;

	/**
	 * 打印条码
	 *
	 * @param listNo
	 * @return
	 */
	public boolean printing(String listNo) throws Exception;

	/**
	 * 查询条码
	 *
	 * @param listNo
	 * @param materialId
	 * @return
	 */
	public List<JSONObject> findBarCode(String listNo, int materialId);

	/**
	 * 出库
	 *
	 * @param id
	 * @param locationId
	 */
	public void MaterialOutbound(int id, int locationId) throws Exception;

	/**
	 * 更新库位状态
	 *
	 * @param locationId
	 * @param locationStatus
	 */
	public void updateLocationStatus(int locationId, int locationStatus) throws Exception;

}
