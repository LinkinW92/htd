package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.TrayManagementDao;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.util.ClientApiUseUtil;

@Service
public class TrayManagementServiceImpl implements TrayManagementService {

	@Autowired
	TrayManagementDao dao;

	@Override
	public List<JSONObject> findList(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.findList(json);
	}

	@Override
	public List<CWmsLocationT> findLocationListIdAndName() {
		// TODO Auto-generated method stub
		return dao.findLocationListIdAndName();
	}

	@Override
	public List<JSONObject> findMaterialNumberList(Integer locationId) {
		// TODO Auto-generated method stub
		return dao.findMaterialNumberList(locationId);
	}

	@Override
	public void putInNullTray(JSONObject json) throws Exception {

		Integer locationId = json.getInteger("locationId");
		String tray = json.getString("tray");

		CWmsLocationT location = dao.findLocation(locationId);
		if (location.getLocationStatus() != 0) {
			throw new Exception("查询到库位状态暂时不允许放入空托盘");
		}

		if (tray.length() != 13) {
			throw new Exception("托盘码规则不匹配");
		}

		if (tray.substring(0, 9).equals("SKQ-ND-L-")) {
			if (location.getTrayType() == 2) {
				throw new Exception("托盘高矮跟库位不匹配");
			}

		} else if (tray.substring(0, 9).equals("SKQ-ND-H-")) {
			if (location.getTrayType() == 1) {
				throw new Exception("托盘高矮跟库位不匹配");
			}
		}

		// 更新库位状态
		Integer result = dao.updateLocation(locationId, 6, tray);
		if (result != 1) {
			throw new Exception("更新库位状态失败");
		}

		// 需要调用的接口名称
		json.put("methodName", "ControlStock");
		// 任务类型
		json.put("Mission_Type", 6);
		// 任务号
		json.put("Mission_ID", 1);
		// 货物尺寸
		json.put("Goods_Size", location.getTrayType());
		// 条码值
		json.put("Pallet_Num", 1);
		// 取料X坐标
		json.put("From_Row", 1);
		// 取料Y坐标
		json.put("From_List", 1);
		// 取料Z坐标
		json.put("From_Layer", 1);
		// 放料X坐标
		json.put("To_Row", location.getLocationX());
		// 放料y坐标
		json.put("To_List", location.getLocationY());
		// 放料z坐标
		json.put("To_Layer", location.getLocationZ());

		// 调用客户端接口
		json = ClientApiUseUtil.UseApi(json);

		if (!json.getBoolean("remark")) {
			throw new Exception("调用客户端接口出现错误");
		}

	}

	@Override
	public void moveOutNullTray(int locationId) throws Exception {

		CWmsLocationT location = dao.findLocation(locationId);
		if (location==null) {
			throw new Exception("库位不存在");
		}

		// 更新库位状态
		Integer result = dao.updateLocation(locationId, 0, "");
		if (result != 1) {
			throw new Exception("更新库位状态失败");
		}

		JSONObject json = new JSONObject();

		// 需要调用的接口名称
		json.put("methodName", "ControlStock");
		// 任务类型
		json.put("Mission_Type", 6);
		// 任务号
		json.put("Mission_ID", 1);
		// 货物尺寸
		json.put("Goods_Size", location.getTrayType());
		// 条码值
		json.put("Pallet_Num", 1);
		// 取料X坐标
		json.put("From_Row", location.getLocationX());
		// 取料Y坐标
		json.put("From_List", location.getLocationY());
		// 取料Z坐标
		json.put("From_Layer", location.getLocationZ());
		// 放料X坐标
		json.put("To_Row", 2);
		// 放料y坐标
		json.put("To_List", 1);
		// 放料z坐标
		json.put("To_Layer", 1);

		// 调用客户端接口
		json = ClientApiUseUtil.UseApi(json);

		if (!json.getBoolean("remark")) {
			throw new Exception("调用客户端接口出现错误");
		}
	}

}
