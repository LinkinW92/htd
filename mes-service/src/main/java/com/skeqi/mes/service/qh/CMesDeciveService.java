package com.skeqi.mes.service.qh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDeviceT;

public interface CMesDeciveService {

	/**
	 * 查询设备信息
	 *
	 * @author WangJ
	 * @param map
	 * @return
	 */
	List<CMesDeviceT> findAll(Map<String, Object> map);
	/**
	 * 删除
	 * @Title: delTool
	 * @author WangJ
	 * @param @param id
	 * @param @return    参数
	 * @return Integer    返回类型
	 */
	Integer delTool(Integer id) throws ServicesException ;
	/**
	 * 修改剩余维护天数
	 * @Title: alterSurplusMaintain
	 * @author WangJ
	 * @Description: 计算当前时间与下次维护时间天数差得出剩余维护天数
	 * @param @param c
	 * @param @return 参数
	 * @return Integer返回类型
	 * @throws
	 */
	Integer alterSurplusMaintain(CMesDeviceT c);
	/**
	 * 修改
	 * @Title: alterTool
	 * @author WangJ
	 * @param @return    参数
	 * @return Map<String, Object>    返回类型
	 */
	Map<String, Object> alterTool(CMesDeviceT c);

	/**
	 * 新增
	 * @Title: addTool
	 * @author WangJ
	 * @param @param id
	 * @param @return    参数
	 * @return Map<String, Object>    返回类型
	 */
	Map<String, Object> addTool(CMesDeviceT c);

	/**
	 * 查询-ToolNo最大值
	 * @Title: findMaxToolNo
	 * @author WangJ
	 * @param @return    参数
	 * @return Integer    返回类型
	 */
//	Integer findMaxToolNo();
}
