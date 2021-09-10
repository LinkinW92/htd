package com.skeqi.mes.mapper.yp.equipment.Information;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 设备资料
 * @date 2020-12-14
 */
public interface EquipmentInformationDao {

	/**
	 * @explain 查询
	 * @param json
	 * @return
	 */
	public List<Map<String,Object>> list(JSONObject json);

	/**
	 * @explain 校验编号
	 * @param number
	 * @param id
	 * @return
	 */
	public int checkNumber(@Param("number")String number, @Param("id")Integer id);

	/**
	 * @explain 新增
	 * @param json
	 */
	public int add(JSONObject json);

	/**
	 * @explain 更新
	 * @param json
	 */
	public int update(JSONObject json);

	/**
	 * @explain 删除
	 * @param id
	 */
	public int delete(@Param("id")int id);

	/**
	 * @explain 新增设备工位信息
	 * @param sql
	 * @return
	 */
	public int addEquipmentInformationStation(@Param("sql")String sql);

	/**
	 * @explain 删除设备工位信息
	 * @param parentId
	 * @return
	 */
	public int deleteEquipmentInformationStation(@Param("parentId")int parentId);

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
	public List<JSONObject> stationListByLineId(@Param("lineId")int lineId);

	/*====================以下是自定义属性====================*/
	/**
	 * @explain 查询自定义列集合
	 * @param parentId
	 * @return
	 */
	public List<JSONObject> customColumnsList(@Param("parentId")int parentId);

	/**
	 * @explain 校验自定义列 列名
	 * @param id
	 * @param columnName
	 * @return
	 */
	public int checkcustomColumnsColumnName(@Param("id")Integer id,@Param("parentId") int parentId, @Param("columnName")String columnName);

	/**
	 * @explain 新增自定义列
	 * @param json
	 * @return
	 */
	public int customColumnsAdd(JSONObject json);

	/**
	 * @explain 更新自定义列
	 * @param json
	 * @return
	 */
	public int customColumnsUpdate(JSONObject json) ;

	/**
	 * @explain 删除自定义列
	 * @param json
	 * @return
	 */
	public int customColumnsDelete(@Param("id")int id) ;

	/*====================以下是详情====================*/
	/**
	 * @explain 通过id查询对象
	 * @param id
	 * @return
	 */
	public JSONObject objectById(@Param("id")int id);

	/**
	 * @explain 通过编号查询对象
	 * @param number
	 * @return
	 */
	public JSONObject objectByNumber(@Param("number")String number);

	/*==============一下是事件=============*/
	/**
	 * @explain 新增事件
	 * @param parentId
	 * @param event
	 */
	public int addEvent(@Param("parentId")int parentId, @Param("event")int event);

}
