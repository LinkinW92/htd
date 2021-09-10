package com.skeqi.mes.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.project.CMesToolManager;

public interface CMesToolManagerService {


	public List<CMesToolManager> findAllTool(@Param("name")String name) throws ServicesException;


	public Integer addTool(CMesToolManager tool) throws ServicesException;



	public Integer updateTool(CMesToolManager tool) throws ServicesException;



	public Integer maintain(Integer id) throws ServicesException;



	public Integer delTool(Integer id)throws ServicesException;

	public Integer UseTool(@Param("id")Integer id,@Param("num")Integer num)throws ServicesException;

	public List<CMesStationT> findStation(Integer lineId)throws ServicesException;
}
