package com.skeqi.mes.service.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * @package com.skeqi.mes.service.wms
 * @date 2020年2月18日
 * @author yp 出库队列
 */
public interface OutTaskqueueService {

	/**
	 * 查询审批表
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public PageInfo<CWmsApprovalT> findApproval(Integer userId, String listNo, Integer pageNumber) throws Exception;

	/**
	 * 查询
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public List<CWmsOutTaskqueueT> findOutTaskqueue(String listNo, Integer userId) throws Exception;

	/**
	 * 通过id查询
	 *
	 * @param outTaskqueueId
	 * @return
	 */
	public CWmsOutTaskqueueT findOutTaskqueueById(Integer outTaskqueueId);

	/**
	 * 新增
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public boolean addOutTaskqueue(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 新增P表数据
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public boolean addPOutTaskqueue(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 更新
	 *
	 * @param outTaskqueue
	 * @return
	 */
	public boolean updateOutTaskqueue(CWmsOutTaskqueueT outTaskqueue);

	/**
	 * 删除
	 *
	 * @param outTaskqueueId
	 * @return
	 */
	public boolean deleteOutTaskqueue(Integer outTaskqueueId);

	/**
	 * 平库出库
	 *
	 * @param outTaskqueueId
	 * @return
	 */
	public boolean chuku(Integer outTaskqueueId) throws Exception;

	/**
	 * 查询库存详情表
	 *
	 * @param storageDetail
	 * @return
	 */
	public List<JSONObject> findStorageDetail(JSONObject json);

	/**
	 * 出库
	 *
	 * @param outTaskqueueId
	 * @return
	 */
	public boolean chuku0806(Integer outTaskqueueId,int locationId) throws Exception;

	/**
	 * 直接出库
	 *
	 * @param outTaskqueueId
	 * @param listNo
	 * @param tray
	 * @throws Exception
	 */
	public void directDelivery(int outTaskqueueId, String listNo, String tray, int locationId) throws Exception;

	/**
	 * 查询条码
	 * @param listNo
	 * @param materialId
	 * @param projectId
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findBarCode(String listNo, int materialId, int projectId, int locationId);

}
