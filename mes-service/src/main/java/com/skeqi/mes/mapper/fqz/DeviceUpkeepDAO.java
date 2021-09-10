package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesDeviceUpkeep;

public interface DeviceUpkeepDAO {

	public List<CMesDeviceUpkeep> findAll(Map<String,Object> map);

	public Integer insertUpkeep(CMesDeviceUpkeep c);

	public Integer updateupkeep(CMesDeviceUpkeep c);

	public Integer deleteupkeep(String id);

	public CMesDeviceUpkeep findByid(String id);

	public Integer findTime(Integer id);

	public void updateDate(@Param("id")String id,@Param("value")String value);
}
