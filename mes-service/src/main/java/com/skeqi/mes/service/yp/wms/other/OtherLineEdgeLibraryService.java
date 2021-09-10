package com.skeqi.mes.service.yp.wms.other;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 线边库
 * @author yinp
 * @date 2021年8月19日
 */
public interface OtherLineEdgeLibraryService {

	/**
	 * 查询集合
	 * @param json
	 * @return
	 */
	public List<JSONObject> findList(JSONObject json);

}
