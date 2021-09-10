package com.skeqi.mes.service.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.all.AssembleLeakagePDao;
import com.skeqi.mes.pojo.api.AssembleLeakagePT;
import com.skeqi.mes.service.all.AssembleLeakagePService;

@Service
public class AssembleLeakagePServiceImpl implements AssembleLeakagePService{

	@Autowired
	private AssembleLeakagePDao assembleLeakagePDao;


	@Override
	public JSONObject assembleWeigh(String snBarcode, String stationName, String weighValues, String emp,JSONObject jo) {

			JSONObject data = new JSONObject();

			Integer id = assembleLeakagePDao.queryAssembleWeighBySnAndStationName(snBarcode, stationName);

			if(id==null) {


				jo.put("code", "203");
				jo.put("msg","查询到的id为空,用户可能修改了产品配方");
				data.put("r", "203");
				jo.put("data", data);
				return jo;

			}else {

				assembleLeakagePDao.updateAssembleWeigh(weighValues,emp,id);
				jo.put("code", "0");
				jo.put("msg","");
				data.put("r", "0");
				jo.put("data", data);
				return jo;
			}


	}



	@Override
	public JSONObject assembleLeakage(String snBarcode, String lValues, String pValues, String rValues,
			String stationName, String emp, JSONObject jo) {
		JSONObject data = new JSONObject();

		Integer tempMaxId =	assembleLeakagePDao.getLeakageMaxIdBySnAndSt(snBarcode, stationName);
		if(tempMaxId==null) {
			jo.put("isSuccess", false);
			jo.put("errMsg","查询到的tempMaxId为空,用户可能修改了产品配方");
			jo.put("result", false);
			return jo;
		}

		AssembleLeakagePT assembleLeakagePT = assembleLeakagePDao.getAssembleKeypartPTById(tempMaxId);

		if(assembleLeakagePT.getTempP()!=null) {
			//判断是否装配过
			assembleLeakagePDao.inserteAssembleKeypartPT(stationName,snBarcode,assembleLeakagePT.getTempName(),pValues,lValues,rValues,emp);
			jo.put("isSuccess", true);
			jo.put("errMsg","");
//			data.put("r", "0");
			jo.put("result", true);
			return jo;

		}else {
			assembleLeakagePDao.updateAssembleKeypartPT(pValues, lValues, rValues, emp, tempMaxId);
			jo.put("isSuccess", true);
			jo.put("errMsg","");
//			data.put("r", "0");
			jo.put("result", true);
			return jo;
		}

	}

}
