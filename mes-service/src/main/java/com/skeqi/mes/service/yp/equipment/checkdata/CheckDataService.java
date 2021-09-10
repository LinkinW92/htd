package com.skeqi.mes.service.yp.equipment.checkdata;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 点检记录
 *
 * @date2021年3月11日
 * @author yinp
 */
public interface CheckDataService {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 *
	 * @param json
	 * @throws Exception
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新
	 *
	 * @param json
	 * @throws Exception
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 查询所有产线跟设备
	 *
	 * @return
	 */
	public List<JSONObject> lineAndEquipment();

	/**
	 * 提交
	 *
	 * @param id
	 * @return
	 */
	public void sub(int id) throws Exception;

}
