package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * @package com.skeqi.mes.service.wms
 * @date 2020年2月18日
 * @author yp 入库队列
 *
 */
public interface InTaskqueueService {

	/**
	 * 查询
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public List<CWmsInTaskqueueT> findInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	// 通过托盘码查询库位坐标跟单号
	public CWmsInTaskqueueT findZuoBiaoAndListNo(String tray);

	/**
	 * 通过id查询
	 *
	 * @param inTaskqueueId
	 * @return
	 */
	public CWmsInTaskqueueT findInTaskqueueById(Integer inTaskqueueId);

	/**
	 * 通过托盘码查询
	 *
	 * @param inTaskqueueId
	 * @return
	 */
	public CWmsInTaskqueueT findInTaskqueueByTray(String trayCode);

	/**
	 * 新增
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public boolean addInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	/**
	 * 新增P 表
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public boolean addPInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	/**
	 * 更新
	 *
	 * @param inTaskqueue
	 * @return
	 */
	public boolean updateInTaskqueue(CWmsInTaskqueueT inTaskqueue);

	/**
	 * 删除
	 *
	 * @param inTaskqueueId
	 * @return
	 */
	public boolean deleteInTaskqueue(Integer inTaskqueueId);

	// 查询入库队列里的单据
	public PageInfo<CWmsApprovalT> findApproval(JSONObject json) throws Exception;

	// 查询物料库存
	public PageInfo<Map<String, Object>> findStorageDetail(String listNo, Integer locationId, Integer pageNumber)
			throws Exception;

	// 通过id查询库位
	public CWmsLocationT findLocation(Integer locationId);

	// 通过库位id查询出库队列
	public CWmsOutTaskqueueT findOutTaskqueue(Integer locationId);

	// XT355_356_357入库
	public boolean MaterialWarehousingXT355_356_357(JSONObject json) throws Exception;

	/**
	 * 查询托盘码是否存在
	 *
	 * @param tray
	 * @return
	 */
	public Integer findMaterialNumberCount(String tray);

	/**
	 * 平库入库
	 *
	 * @param id
	 * @return
	 */
	public boolean ruku(Integer inTaskqueueId, int locationStatus) throws Exception;

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
	 * @return
	 * @throws Exception
	 */
	public List<JSONObject> findBarCode(String listNo, Integer locationId, Integer materialId) throws Exception;

	/**
	 * 更新物料的条码
	 *
	 * @param json
	 * @return
	 */
	public boolean updateBarCode(String sourceBarCode, String presentBarCode) throws Exception;

	/**
	 * 直接入库
	 *
	 * @param inTaskqueueId
	 */
	public void directWarehousing(int inTaskqueueId, int locationStatus) throws Exception;

	/**
	 * 放行
	 *
	 * @param id
	 */
	public void TrayPass(int id, int locationId) throws Exception;

	/**
	 * 更新库位状态
	 *
	 * @param locationId
	 * @param locationStatus
	 */
	public void updateLocationStatus(int locationId, int locationStatus) throws Exception;

}
