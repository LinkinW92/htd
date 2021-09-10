package com.skeqi.mes.mapper.yp.wms.other;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 工位
 * @author yinp
 * @date 2021年8月19日
 */
public interface OtherStationDao {

	/**
	 * 通过产线查询工位
	 * @param lineId
	 * @return
	 */
	public List<JSONObject> findStationByLineId(@Param("lineId")Integer lineId);

}
