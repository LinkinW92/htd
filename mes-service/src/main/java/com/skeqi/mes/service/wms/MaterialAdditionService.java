package com.skeqi.mes.service.wms;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.wms.CWmsProject;

import scala.collection.generic.BitOperations.Int;

/**
 * 物料追加
 *
 * @author yinp
 *
 */
public interface MaterialAdditionService {

	// 新增session
	public boolean addSession(HttpSession session, Integer projectId, String projectName, Integer materialId,
			String materialName, Integer materialNumber);

	// 更新session
	public boolean updateSession(HttpSession session, JSONObject json);

	// 删除session
	public boolean deleteSession(HttpSession session, int index) throws Exception;

	// 保存加料
	public boolean addMaterialAddition(String feedingData, Integer materialNumberId,int userId) throws Exception;

	/**
	 * 查询所有项目ID、NAME
	 *
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
	public boolean queryWhetherThereIsInventory();

}
