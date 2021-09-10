package com.skeqi.mes.service.yp.equipment.maintaindata;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 保养记录
 *
 * @date2021年3月12日
 * @author yinp
 */
public interface MaintainDataService {

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
