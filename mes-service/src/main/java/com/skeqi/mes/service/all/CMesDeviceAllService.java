package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDeviceRepairT;
import com.skeqi.mes.pojo.CMesDeviceSpotT;
import com.skeqi.mes.pojo.CMesDeviceUpkeep;

public interface CMesDeviceAllService {


	public List<CMesDeviceSpotT> findAllSpot(CMesDeviceSpotT spot) throws ServicesException;

	public CMesDeviceSpotT findSpotByid(Integer id) throws ServicesException;

	public Integer addSpot(CMesDeviceSpotT spot) throws ServicesException;

	public Integer updateSpot(CMesDeviceSpotT spot) throws ServicesException;

	public Integer delSppot(Integer id) throws ServicesException;

	//设备维修列表
	public List<CMesDeviceRepairT> findAllRepair(CMesDeviceRepairT t) throws ServicesException;

	//根据id查询设备维修
	public CMesDeviceRepairT findRepairByid(Integer id) throws ServicesException;

	//添加设备维修
	public Integer addRepair(CMesDeviceRepairT t) throws ServicesException;

	//修改设备维修
	public Integer updateRepair(CMesDeviceRepairT t) throws ServicesException;

	//删除设备维修
	public Integer delRepair(Integer id) throws ServicesException;

	//设备保养列表
	public List<CMesDeviceUpkeep> findAllKeep(CMesDeviceUpkeep kepp) throws ServicesException;

	//根据id查询设备保养
	public CMesDeviceUpkeep findKeepByid(Integer id) throws ServicesException;

	//查询剩余寿命
	public Integer findSurplusTime(Integer id) throws ServicesException;

	//添加设备保养
	public Integer addKeep(CMesDeviceUpkeep keep) throws ServicesException;

	//修改设备保养
	public Integer updateKeep(CMesDeviceUpkeep keep) throws ServicesException;

	//修改下次保养时间
	public Integer updateKeppDate(Integer id) throws ServicesException;
}
