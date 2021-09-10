package com.skeqi.mes.service.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.all.AssembleTrayPDao;
import com.skeqi.mes.service.all.AssembleTrayPService;

@Service
public class AssembleTrayPServiceImpl implements AssembleTrayPService {

	@Autowired
	private AssembleTrayPDao assembleTrayPDao;


	@Override
	public JSONObject checkTray(String line, String trayNum, JSONObject jo) {

		Integer trayNumber = assembleTrayPDao.checkTray(line,trayNum);
		JSONObject data = new JSONObject();
		if(trayNumber>0) {

			jo.put("code", "0");
			jo.put("msg","");
			data.put("r", "0");
			jo.put("data", data);
			return jo;


		}else {

			jo.put("code", "16");
			jo.put("msg","托盘号不存在");
			data.put("r", "16");
			jo.put("data", data);
			return jo;


		}

	}


	@Override
	public JSONObject assembleTray(String snBarcode, String stationName, String lineName, String trayNum,
			JSONObject jo) {

			JSONObject data = new JSONObject();
			Integer id = assembleTrayPDao.queryAssembleTrayBySn(snBarcode);

			if(id!=null) {

				assembleTrayPDao.updateAssembleTray(trayNum,id);
				//更新托盘号
				jo.put("code", "0");
				jo.put("msg","");
				data.put("r", "0");
				jo.put("data", data);
				return jo;

			}else {
				jo.put("code", "203");
				jo.put("msg","查询到的id为空,用户可能删除R_MES_TRACKING_T表中的数据");
				data.put("r", "203");
				jo.put("data", data);
				return jo;

			}

	}

}
