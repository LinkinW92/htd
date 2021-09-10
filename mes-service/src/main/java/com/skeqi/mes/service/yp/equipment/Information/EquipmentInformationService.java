package com.skeqi.mes.service.yp.equipment.Information;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 设备资料
 * @date 2020-12-14
 */
public interface EquipmentInformationService {

	/**
	 * @explain 查询
	 * @param json
	 * @return
	 */
	public List<Map<String, Object>> list(JSONObject json);

	/**
	 * @explain 新增
	 * @param json
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * @explain 更新
	 * @param json
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * @explain 删除
	 * @param id
	 */
	public void delete(int id) throws Exception;

	/**
	 * @explain 查询产线
	 * @return
	 */
	public List<JSONObject> lineList();

	/**
	 * @explain 通过产线id查询工位
	 * @param lineId
	 * @return
	 */
	public List<JSONObject> stationListByLineId(int lineId);

	/*====================以下是自定义属性====================*/
	/**
	 * @explain 查询自定义列集合
	 * @param parentId
	 * @return
	 */
	public List<JSONObject> customColumnsList(int parentId);

	/**
	 * @explain 新增自定义列
	 * @param json
	 * @return
	 */
	public void customColumnsAdd(JSONObject json) throws Exception;

	/**
	 * @explain 更新自定义列
	 * @param json
	 * @return
	 */
	public void customColumnsUpdate(JSONObject json) throws Exception;

	/**
	 * @explain 删除自定义列
	 * @param json
	 * @return
	 */
	public void customColumnsDelete(int id) throws Exception;

	/*====================以下是详情====================*/
	/**
	 * @explain 通过id查询对象
	 * @param id
	 * @return
	 */
	public JSONObject objectById(int id);

	/**
	 * @explain 通过编号查询对象
	 * @param number
	 * @return
	 */
	public JSONObject objectByNumber(String number);

}
