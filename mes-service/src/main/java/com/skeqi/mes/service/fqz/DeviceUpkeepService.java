package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesDeviceUpkeep;

public interface DeviceUpkeepService {
	public List<CMesDeviceUpkeep> findAll(Map<String,Object> map);

	public Integer insertUpkeep(CMesDeviceUpkeep c);

	public Integer updateupkeep(CMesDeviceUpkeep c);

	public Integer deleteupkeep(String id);

	public CMesDeviceUpkeep findByid(String id);

	public Integer findTime(Integer id);

	public void updateDate(String id,String value);
}
