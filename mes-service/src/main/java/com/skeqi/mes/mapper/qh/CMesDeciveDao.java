package com.skeqi.mes.mapper.qh;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesDeviceT;

/**
 * 设备管理
 * @Package com.skeqi.mapper.qh
 * @ClassName: CMesDeciveDao
 * @Package com.skeqi.mapper.qh
 * @author: Wangj
 */
public interface CMesDeciveDao {

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
	Integer delTool(Integer id);
	/**
	 * 修改剩余维护天数
	 * @Title: alterSurplusMaintain
	 * @author WangJ
	 * @Description: 计算当前时间与下次维护时间天数差，得出剩余维护天数并修改
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
	 * @return Integer    返回类型
	 */
	Integer alterTool(CMesDeviceT c);

	/**
	 * 新增
	 * @Title: addTool
	 * @author WangJ
	 * @param @param id
	 * @param @return    参数
	 * @return Map<String, Object>    返回类型
	 */
	Integer addTool(CMesDeviceT c);

	/**
	 * 查询-ToolNo最大值
	 * @Title: findMaxToolNo
	 * @author WangJ
	 * @param @return    参数
	 * @return Integer    返回类型
	 */
//	Integer findMaxToolNo();
}
