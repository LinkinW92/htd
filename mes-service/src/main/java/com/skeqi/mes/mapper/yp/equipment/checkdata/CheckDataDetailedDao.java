package com.skeqi.mes.mapper.yp.equipment.checkdata;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 点检记录详情
 *
 * @date2021年3月11日
 * @author yinp
 */
public interface CheckDataDetailedDao {

	/**
	 * 查询点检记录详情
	 * @param checkDataId
	 * @return
	 */
	public List<JSONObject> list(@Param("checkDataId")int checkDataId);

	/**
	 * 更新点检记录详情
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

}
