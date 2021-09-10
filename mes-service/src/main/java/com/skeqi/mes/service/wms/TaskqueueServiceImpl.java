package com.skeqi.mes.service.wms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.TaskqueueDao;
import com.skeqi.mes.util.ClientApiUseUtil;

/**
 * @author yinp
 * @explain 队列维护
 * @date 2020-12-9
 */
@Service
public class TaskqueueServiceImpl implements TaskqueueService {

	@Autowired
	TaskqueueDao dao;

	/**
	 * @explain 查询队列集合
	 * @param type
	 * @return
	 */
	@Override
	public List<JSONObject> list(int type) {
		switch (type) {
		case 1:
			// 查询临时入库队列
			return dao.findRInTaskqueue();
		case 2:
			// 查询临时出库队列
			return dao.findROutTaskqueue();
		default:
			return null;
		}

	}

	/**
	 * @explain 删除队列
	 * @param type
	 * @param id
	 */
	@Override
	public void delete(int type, int condition, int id, String listNo, int locationId) throws Exception {
		int result = 0;
		switch (type) {
		case 1:
			// 删除临时入库队列
			result = dao.deleteRInTaskqueue(id);
			break;
		case 2:
			// 删除临时出库队列
			result = dao.deleteROutTaskqueue(id);
			break;
		default:
			break;
		}
		if (result != 1) {
			throw new Exception("还原数据失败！");
		}

		// 仅删除队列
		if (condition == 2) {
			return;
		}

		if (type == 2) {

			// 查询库存详情
			List<JSONObject> stoList = dao.findRStorageDetail(listNo, locationId);
			for (JSONObject sto : stoList) {

				// 修改库存即将出库数量
				result = dao.updateMaterialNumberLmminentRelease(sto.getInteger("materialNumber"),sto.getInteger("materialNumberId"));
				if (result != 1) {
					throw new Exception("还原数据失败！");
				}

			}
		}

		// 删除临时库存详情
		result = dao.deleteRStorageDetail(listNo, locationId);
		if (result <= 0) {
			throw new Exception("还原数据失败！");
		}
	}

	/**
	 * @explain 查询库位集合
	 * @return
	 */
	@Override
	public List<JSONObject> findLocation() {
		// TODO Auto-generated method stub
		return dao.findLocation();
	}

	/**
	 * @explain 手动模式
	 * @param tray
	 * @param Goods_Size
	 * @param From_Row
	 * @param From_List
	 * @param From_Layer
	 * @param To_Row
	 * @param To_List
	 * @param To_Layer
	 */
	@Override
	public void ControlStock(JSONObject json) throws Exception {

		// 需要调用的接口名称
		json.put("methodName", "ControlStock");
		// 任务类型
		json.put("Mission_Type", 6);
		// 任务号
		json.put("Mission_ID", 1);
		// 条码值
		json.put("Pallet_Num", 1);

		// 调用客户端接口
		json = ClientApiUseUtil.UseApi(json);

	}

}
