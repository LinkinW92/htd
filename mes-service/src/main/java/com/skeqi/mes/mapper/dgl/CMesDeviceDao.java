package com.skeqi.mes.mapper.dgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesClientPurviewT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesStationT;

public interface CMesDeviceDao {

	/**
	 * 设备管理查询
	 * @param map
	 * @return
	 */
	List<CMesDeviceT> findDeviceList(Map<String, Object> map);

	/**
	 * 查询所有产线名称，ID
	 * @return
	 */
	List<CMesStationT> StationNameList();
	/**
	 * 查询所有产线名称，ID
	 * @return
	 */
	List<CMesLineT> lineList();
	/**
	 * 判断设备名称是否重复
	 * @param deviceName
	 * @return
	 */
	List<CMesDeviceT> findByDeviceName(@Param("deviceName") String deviceName);
	/**
	 * 新增设备管理
	 * @param map
	 */
	void addCMesDevice(Map<String, Object> map);
	/**
	 *  根据ID删除
	 * @param id
	 * @return
	 */
	Integer delDevice(@Param("id") Integer id);
	/**
	 * 根据ID查询，回显
	 * @param id
	 * @return
	 */
	List<CMesDeviceT> findByDeviceId(@Param("id") Integer id);
	/**
	 * 修改
	 * @param id
	 * @return
	 */
	Integer updateDevice(CMesDeviceT device);
}
