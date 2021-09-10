package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.pojo.wms.K3ExportNotifydetall;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * k3出库对接
 * @author yinp
 *
 */
public interface K3ChuKuDuiJieDao {

	/**
	 * 通过仓库名称查询仓库
	 * @param materialName
	 * @return
	 */
	public WarehouseT findWarehouse(String warehouseName);

	/**
	 * 通过项目名称查询项目
	 * @param materialName
	 * @return
	 */
	public CWmsProject findProject(String projectName);

	/**
	 * 新增项目信息
	 * @param dx
	 * @return
	 */
	public Integer addProject(CWmsProject dx);

	/**
	 * 通过物料名称查询物料
	 * @param materialName
	 * @return
	 */
	public CMesJlMaterialT findMtaerialName(String materialName);

	/**
	 * 新增物料信息
	 * @param dx
	 * @return
	 */
	public Integer addMaterial(CMesJlMaterialT dx);

	/**
	 * 新增K3出库记录表
	 * @param dx
	 * @return
	 */
	public Integer addK3ExportNotifydetall(K3ExportNotifydetall dx);

	/**
	 * 查询物料库存
	 * @param warehouseId
	 * @param projectId
	 * @param materialId
	 * @return
	 */
	public List<CWmsMaterialNumberT> findMaterialNumber(@Param("warehouseId")Integer warehouseId,
			@Param("projectId")Integer projectId,
			@Param("materialId")Integer materialId);

	/**
	 * 更新物料库存即将出库数量
	 * @param warehouseId
	 * @param projectId
	 * @param materialId
	 * @param lmminentRelease
	 * @return
	 */
	public Integer updateMaterialNumber(@Param("id")Integer id,
			@Param("lmminentRelease")Integer lmminentRelease);

	/**
	 * 新增出库队列
	 * @param dx
	 * @return
	 */
	public Integer addOutTaskqueue(CWmsOutTaskqueueT dx);

	/**
	 * 新增永久库存详情
	 * @param dx
	 * @return
	 */
	public Integer addStorageDetail(CWmsStorageDetailT dx);

	/**
	 * 查询出库队列
	 * @param outTaskqueue
	 * @return
	 */
	public List<CWmsOutTaskqueueT> findOutTaskqueue();

	/**
	 * 查询出库队列
	 * @param outTaskqueue
	 * @return
	 */
	public CWmsOutTaskqueueT findOutTaskqueueById(Integer id);

	/**
	 * 查询库存详情
	 * @param MaterialNumber
	 * @return
	 */
	public List<CWmsStorageDetailT> findStorageDetailList(Map<String,Object> map);

	/**
	 * 查询所有工位
	 * @return
	 */
	public List<CMesStationT> findStationList();

	/**
	 * 修改出库队列工位id
	 * @param id
	 * @param stationId
	 * @return
	 */
	public Integer updateOutTaskqueueStationId(@Param("id")Integer id,
			@Param("stationId")Integer stationId);

	/**
	 * 通过id查询K3出库信息
	 * @param id
	 * @return
	 */
	public K3ExportNotifydetall findK3Export(@Param("id")Integer id);

	/**
	 * 通过库位、物料、项目查询物料库存
	 * @param warehouseId
	 * @param projectId
	 * @param materialId
	 * @return
	 */
	public CWmsMaterialNumberT findMaterialNumberByLocationIdAndMaterialIdAndProjectId(
			@Param("locationId")Integer locationId,
			@Param("materialId")Integer materialId,
			@Param("projectId")Integer projectId);

	/**
	 * 删除永久库存详情
	 * @param dx
	 * @return
	 */
	public Integer deleteStorageDetail(@Param("id")Integer id);

	/**
	 * 删除出库队列
	 * @param dx
	 * @return
	 */
	public Integer deleteOutTaskqueue(@Param("id")Integer id);

	/**
	 * 查询审批流程
	 *
	 * @param processApproval
	 * @return
	 */
	public List<ProcessApproval> findProcessApproval(ProcessApproval processApproval);

	/**
	 * 查询审批流程详情
	 *
	 * @param processApprovalDetail
	 * @return
	 */
	public List<ProcessApprovalDetail> findProcessApprovalDetailList(Map<String, Object> map);

	/**
	 * 新增审批记录
	 *
	 * @param approval
	 * @return
	 */
	public Integer addApproval(CWmsApprovalT approval);

	/**
	 * 通过用户id查询用户
	 * @param userId
	 * @return
	 */
	public CMesUserT findUserById(@Param("userId")int userId);

}
