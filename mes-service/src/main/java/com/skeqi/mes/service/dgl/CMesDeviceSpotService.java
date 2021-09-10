package com.skeqi.mes.service.dgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesDeviceSpotT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;

public interface CMesDeviceSpotService {

	/**
	 * 设备点检查询
	 * @param map
	 * @return
	 */
	List<CMesDeviceSpotT> findDeviceSpotList(Map<String, Object> map);
	/**
	 * 查询所有产线名称，ID
	 * @return
	 */
	List<CMesLineT> lineList();
	/**
	 * 根据产线ID查询设备名称，ID
	 * @param lineId
	 * @return
	 */
	List<CMesDeviceT> findlineById(@Param("@Param")Integer lineId);
	/**
	 * 新增设备点检
	 * @param spot
	 * @return
	 */
	Integer addDeviceSpot(CMesDeviceSpotT spot);
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	Integer delDeviceSpot(@Param("id") Integer id);
	/**
	 * 根据ID查询，回显
	 * @param id
	 * @return
	 */
	CMesDeviceSpotT findByDeviceSpotId(@Param("id") Integer id);
	/**
	 * 修改
	 * @param id
	 * @return
	 */
	Integer updateDeviceSpot(CMesDeviceSpotT spot);
}
