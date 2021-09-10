package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesDeviceRepairT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;

public interface DeviceRepairService {

		public List<CMesDeviceRepairT> findAll(Map<String,Object> map);

		public Integer insertRepair(CMesDeviceRepairT c);

		public Integer updateRepair(CMesDeviceRepairT c);

		public Integer deleteRepair(String id);

		public List<CMesLineT> findLine();

		public List<CMesDeviceT> findDevice();
		public CMesDeviceRepairT findByid(String id);

		public List<CMesDeviceT> findDevices(String id);
}
