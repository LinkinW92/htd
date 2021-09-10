package com.skeqi.mes.service.yp.wms.other;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 工位
 * @author yinp
 * @date 2021年8月19日
 */
public interface OtherStationService {

	/**
	 * 通过产线查询工位
	 * @param lineId
	 * @return
	 */
	public List<JSONObject> findStationByLineId(Integer lineId);

}
