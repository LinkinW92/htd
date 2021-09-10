package com.skeqi.mes.mapper.all;

import java.util.List;

import com.skeqi.mes.pojo.CMesDeviceRepairT;
import com.skeqi.mes.pojo.CMesDeviceSpotT;
import com.skeqi.mes.pojo.CMesDeviceUpkeep;

public interface CMesDeviceAllTDAO {

	//设备点检列表
	public List<CMesDeviceSpotT> findAllSpot(CMesDeviceSpotT spot);

	//根据id查询设备点检
	public CMesDeviceSpotT findSpotByid(Integer id);

	//添加设备点检表
	public Integer addSpot(CMesDeviceSpotT spot);

	//修改设备点检表
	public Integer updateSpot(CMesDeviceSpotT spot);

	//删除设备点检
	public Integer delSppot(Integer id);

	//设备维修列表
	public List<CMesDeviceRepairT> findAllRepair(CMesDeviceRepairT t);

	//根据id查询设备维修
	public CMesDeviceRepairT findRepairByid(Integer id);

	//添加设备维修
	public Integer addRepair(CMesDeviceRepairT t);

	//修改设备维修
	public Integer updateRepair(CMesDeviceRepairT t);

	//删除设备维修
	public Integer delRepair(Integer id);

	//设备保养列表
	public List<CMesDeviceUpkeep> findAllKeep(CMesDeviceUpkeep kepp);

	//根据id查询设备保养
	public CMesDeviceUpkeep findKeepByid(Integer id);

	//查询剩余寿命
	public Integer findSurplusTime(Integer id);

	//添加设备保养
	public Integer addKeep(CMesDeviceUpkeep keep);

	//修改设备保养
	public Integer updateKeep(CMesDeviceUpkeep keep);

	//修改下次保养时间
	public Integer updateKeppDate(Integer id);
}
