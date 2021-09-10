package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesDeviceRepairT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;

/**
 * 设备维修
 * @author : FQZ
 * @Package: com.skeqi.mes.controller.fqz
 * @date   : 2019年10月14日 上午9:23:13
 */
public interface DeviceRepairDAO {

	public List<CMesDeviceRepairT> findAll(Map<String,Object> map);

	public List<CMesDeviceT> findDevices(String id);

	public CMesDeviceRepairT findByid(String id);

	public Integer insertRepair(CMesDeviceRepairT c);

	public Integer updateRepair(CMesDeviceRepairT c);

	public Integer deleteRepair(String id);

	public List<CMesLineT> findLine();

	public List<CMesDeviceT> findDevice();
}
