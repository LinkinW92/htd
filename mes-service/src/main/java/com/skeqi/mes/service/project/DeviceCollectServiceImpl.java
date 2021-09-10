package com.skeqi.mes.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.DeviceDAO;
import com.skeqi.mes.pojo.project.CMesToolManager;
import com.skeqi.mes.pojo.project.DeviceCollect;
import com.skeqi.mes.pojo.wms.K3ExportNotifydetall;
import com.skeqi.mes.util.jdbc.AndonSQLServer;
import com.skeqi.mes.util.jdbc.SqlServerJDBC;

@Service
public class DeviceCollectServiceImpl implements DeviceCollectService{

	@Autowired
	DeviceDAO dao;

	@Override
	public void findTool() throws ServicesException{

		List<DeviceCollect> findDevice = AndonSQLServer.findDevice();
		for (DeviceCollect deviceCollect : findDevice) {
			CMesToolManager findTool = dao.findTool(deviceCollect.getDeviceNumber());
			if(findTool!=null){   //设备存在于设备表
				if(deviceCollect.getAlarmState()==1){   //故障
					Integer findFaultByTid = dao.findFaultByTid(findTool.getId());  //查询此设备是否存在故障表(根据设备id和0状态)
					if(findFaultByTid==null){   //此设备正处于故障状态，在故障表中可以查看
						String findDt = dao.findDt(findTool.getId());  //判断是否已响应
						if(findDt==null){   //如果没有已响应的
							dao.insertAndonFault(findTool.getLineName(), findTool.getStationName(), 0, findTool.getId(),deviceCollect.getLastUpdatetime());  //添加到故障表
						}else if(!findDt.equals(deviceCollect.getLastUpdatetime())){
							dao.insertAndonFault(findTool.getLineName(), findTool.getStationName(), 0, findTool.getId(),deviceCollect.getLastUpdatetime());  //添加到故障表
						}

					}

//					else{
//						mapper.updateAndonFault(findFaultByTid, 0);
//					}
				}
//				else if(deviceCollect.getAlarmState()==2){  //关机
//					Integer findFaultByTid = mapper.findFaultByTid(findTool.getId());  //查询此设备是否存在故障表
//					if(findFaultByTid!=null){   //存在则修改，否则新增
//						mapper.updateAndonFault(findFaultByTid, 3);
//					}else{
//						mapper.insertAndonFault(findTool.getLineName(), findTool.getStationName(), 3, findTool.getId());  //添加到故障表
//					}
//				}else if(deviceCollect.getAlarmState()==0){   //正常
//					Integer findFaultByTid = mapper.findFaultByTid(findTool.getId());  //查询此设备是否存在故障表
//					if(findFaultByTid!=null){   //存在则修改，否则新增
//						mapper.updateAndonFault(findFaultByTid, 4);
//					}else{
//						mapper.insertAndonFault(findTool.getLineName(), findTool.getStationName(), 4, findTool.getId());  //添加到故障表
//					}
//				}
			}
		}
	}


}
