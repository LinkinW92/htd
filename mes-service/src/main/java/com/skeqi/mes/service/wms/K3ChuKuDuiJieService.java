package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * k3出库对接
 * @author yinp
 *
 */
public interface K3ChuKuDuiJieService {

	public boolean getK3ExportNotifydetall(int userId) throws Exception;

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
	public boolean updateOutTaskqueueStationId(Integer id,Integer stationId);

	/**
	 * 异常处理
	 * @param json
	 * @return
	 */
	public boolean exceptionHandle(String exportId) throws Exception;

	/**
	 * 删除K3入库信息
	 *
	 * @param object
	 * @return
	 */
	public boolean deleteK3Number(Integer id)throws Exception ;

}
