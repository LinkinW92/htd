package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.K3ImportNotifydetail;

/**
 * k3入库对接
 *
 * @author yinp
 *
 */
public interface K3RuKuDuiJieService {

	// 获取中间库的数据新增的系统数据库
	public boolean getMiddleDataBase() throws Exception;

	// 查询k3入库数据
	public List<K3ImportNotifydetail> findK3ImportNotifydetailList(Map<String, Object> map);

	// 查询库位
	public List<JSONObject> findLocationList();

	// 提交
	public boolean Submission(String tray, Integer locationId, String materialJson, int userId) throws Exception;

	/**
	 * 通过库位id查询是加料操作还是入库操作
	 *
	 * @param locationId
	 * @return
	 */
	public String findMaterialNumberCount(Integer locationId);

	/**
	 * 异常处理
	 *
	 * @param json
	 * @return
	 */
	public boolean exceptionHandle(String importId) throws Exception;

	/**
	 * 删除K3入库信息
	 *
	 * @param object
	 * @return
	 */
	public boolean deleteK3Number(Integer id)throws Exception ;

	/**
	 * 查询所有产品
	 * @return
	 */
	public List<JSONObject> projectList();

}
