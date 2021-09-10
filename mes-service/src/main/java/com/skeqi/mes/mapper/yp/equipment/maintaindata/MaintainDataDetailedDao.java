package com.skeqi.mes.mapper.yp.equipment.maintaindata;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 保养记录详情
 *
 * @date2021年3月12日
 * @author yinp
 */
public interface MaintainDataDetailedDao {

	/**
	 * 查询保养记录详情
	 * @param checkDataId
	 * @return
	 */
	public List<JSONObject> list(@Param("checkDataId")int checkDataId);

	/**
	 * 更新保养记录详情
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

}
