package com.skeqi.mes.service.project;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.project.CMesToolManager;

public interface DeviceCollectService {

	public void findTool() throws ServicesException;
}
