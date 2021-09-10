package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;

/**
 * 物料追加
 *
 * @author yinp
 *
 */
public interface MaterialAdditionDao {

	/**
	 * 通过id查询
	 * @param materialNumberId
	 * @return
	 */
	public CWmsMaterialNumberT findMaterialNumberById(Integer id);

	/**
	 * 新增
	 * @param storageDetail
	 * @return
	 */
	public Integer addStorageDetail(CWmsStorageDetailT storageDetail);

	/**
	 * 查询
	 * @param processApproval
	 * @return
	 */
	public List<ProcessApproval> findProcessApproval(ProcessApproval processApproval);

	/**
	 * 通过单号查询审批记录
	 * @param listNo
	 * @return
	 */
	public CWmsApprovalT findApprovalByListNo(String listNo);

	/**
	 * 新增
	 * @param approval
	 * @return
	 */
	public Integer addApproval(CWmsApprovalT approval);

	/**
	 * 新增
	 * @param approvalDetails
	 * @return
	 */
	public Integer addApprovalDetails(CWmsApprovalDetailsT approvalDetails);

	/**
	 * 查询
	 * @param processApprovalDetail
	 * @return
	 */
	public List<ProcessApprovalDetail> findProcessApprovalDetailList(Map<String, Object> map);

	/**
	 * 查询临时入库队列
	 * @param locationId
	 * @return
	 */
	public Integer findRInTaskqueueCount(@Param("locationId")Integer locationId);

	/**
	 * 查询临时出库队列
	 * @param locationId
	 * @return
	 */
	public Integer findROutTaskqueueCount(@Param("locationId")Integer locationId);

	/**
	 * 查询所有项目ID、NAME
	 * @return
	 */
	public List<CWmsProject> findProjectAll();

	/**
	 * 查询所有物料ID、NAME
	 * @return
	 */
	public List<CMesJlMaterialT> findMaterialAll();

	/**
	 * 查询是否有盘点记录未审批或者未执行
	 * @return
	 */
	public Integer queryWhetherThereIsInventory();

	/**
	 * 查询用户是否存在
	 * @param userId
	 * @return
	 */
	public CMesUserT findUserById(@Param("id")int id);

}
