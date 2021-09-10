package com.skeqi.mes.service.zch;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface NextBarcodeService {

	JSONObject nextBarcode(Integer lineId, String lineName, String printFlag, Integer num);

	JSONObject printBarcode(String barcode);
	Map<String, Object> getLabelByIDAndNameAndLineId(Integer id,String labelName,Integer lineId);
}
