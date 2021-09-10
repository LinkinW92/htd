package com.skeqi.mes.mapper.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * @package com.skeqi.mapper.wms
 * @date 2020年2月18日
 * @author yp 出库队列
 */
public interface OutTaskqueueDao {

	/**
	 * 查询审批表
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public List<CWmsApprovalT> findApproval(CWmsApprovalT dx);

	/**
	 * 查询
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public List<CWmsOutTaskqueueT> findOutTaskqueue(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 新增
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public Integer addOutTaskqueue(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 新增P表数据
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public Integer addPOutTaskqueue(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 更新
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public Integer updateOutTaskqueue(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 删除
	 *
	 * @param outTaskqueueId
	 * @return
	 */
	public Integer deleteOutTaskqueue(Integer outTaskqueueId);

	/**
	 * 查询审批表
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public List<CWmsApprovalT> findApprovalXT355_356_357(CWmsApprovalT dx);

	/**
	 * 查询出库队列 XT355_356_357
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public List<CWmsOutTaskqueueT> findOutTaskqueueXT355_356_357(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 查询库存详情表
	 *
	 * @param storageDetail
	 * @return
	 */
	public List<CWmsStorageDetailT> findStorageDetail(JSONObject json);

	/**
	 * 查询库存详情表
	 *
	 * @param storageDetail
	 * @return
	 */
	public List<JSONObject> findStorageDetailSum(JSONObject json);

	/**
	 * 查询物料库存
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public List<CWmsMaterialNumberT> findMaterialNumberList(Map<String, Object> map);

	/**
	 * 更新物料库存
	 *
	 * @param MaterialNumber
	 * @return
	 */
	public Integer updateMaterialNumber(CWmsMaterialNumberT materialNumber);

	/**
	 * 删除物料库存
	 *
	 * @param MaterialNumberId
	 * @return
	 */
	public Integer deleteMaterialNumber(Integer materialNumberId);

	/**
	 * 查询库位总物料库存
	 *
	 * @param locationId
	 * @return
	 */
	public Integer findLocationCount(Integer locationId);

	/**
	 * 更新
	 *
	 * @param location
	 * @return
	 */
	public Integer updateLocation(CWmsLocationT location);

	/**
	 * 通过用户名称查询用户
	 *
	 * @param userName
	 * @return
	 */
	public CMesUserT findUserByName(@Param("userName") String userName);

	/**
	 * 查询
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public JSONObject findOutTaskqueueById(@Param("id") Integer id);

	/**
	 * 通过单号跟库位id查询临时库存详情
	 *
	 * @param listNo
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findRStorageDetailByListNoAndLocationId(@Param("listNo") String listNo,
			@Param("locationId") int locationId);

	/**
	 * 新增库存详情永久表数据
	 *
	 * @param sql
	 * @return
	 */
	public int addPStorageDetail(@Param("sql") String sql);

	/**
	 * 删除临时库存详情
	 *
	 * @param listNo
	 * @param locationId
	 * @return
	 */
	public int deleteRStorageDetail(@Param("listNo") String listNo, @Param("locationId") int locationId);

	/**
	 * 查询将近出库的库存数据
	 *
	 * @param materialId
	 * @param locationId
	 * @param projectId
	 * @return
	 */
	public List<JSONObject> findMaterialNumber(@Param("materialId") int materialId, @Param("locationId") int locationId,
			@Param("projectId") int projectId);

	/**
	 * 通过id查询临时出库队列
	 *
	 * @param id
	 * @return
	 */
	public JSONObject findROutTaskqueueById(@Param("id") int id);

	/**
	 * 新增永久出库队列
	 *
	 * @param json
	 * @return
	 */
	public int addPOutTaskqueueJson(JSONObject json);

	/**
	 * 查询条码
	 * @param listNo
	 * @param materialId
	 * @param projectId
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findBarCode(@Param("listNo") String listNo, @Param("materialId") int materialId,
			@Param("projectId") int projectId, @Param("locationId") int locationId);

	/**
	 * 通过库位id查询该库位是否还有库存
	 * @param locationId
	 * @return
	 */
	public int findMaterialNumberLocationCount(@Param("locationId") int locationId);

	/**
	 * 修改库位状态跟托盘码
	 * @param id
	 * @return
	 */
	public int updateLocationStateAndTray(@Param("id") int id);

	/**
	 * 通过条码删除物料库存
	 * @param barCode
	 * @return
	 */
	public int deleteMaterialNumberByBarCode(@Param("barCode")String barCode);

	/**
	 * 通过库位id查询动作标记count
	 * @param locationId
	 * @return
	 */
	public int findROuTaskqueueFlagCountByLocationId(@Param("locationId")int locationId);

	/**
	 * 修改临时队列表动作标记
	 * @param id
	 * @return
	 */
	public int updateROuTaskqueueFlag(@Param("id")int id);

	/**
	 * 新
	 *
	 * 出库更新库存
	 * @param materialNumber
	 * @param materialNumberId
	 * @return
	 */
	public int updateStockMaterialNumber(@Param("materialNumber")int materialNumber,@Param("materialNumberId")int materialNumberId);

	/**
	 * 新
	 *
	 * 删除空库存的记录
	 * @return
	 */
	public int deleteNullStock();

}
