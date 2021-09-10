package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * 审批结果
 *
 * @author yinp
 *
 */
public interface ApprovalResultService {
	/**
	 * 查询审批单据
	 *
	 * @param map
	 * @return
	 */
	public List<CWmsApprovalT> findApprovalList(Map<String, Object> map);

	/**
	 * 查询库存详情
	 *
	 * @param map
	 * @return
	 */
	public List<CWmsStorageDetailT> findStorageDetailList(Map<String, Object> map);

	/**
	 * 查询库存详情
	 *
	 * @param map
	 * @return
	 */
	public List<CWmsStorageDetailT> findPStorageDetailList(Map<String, Object> map);
}
