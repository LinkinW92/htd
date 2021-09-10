package com.skeqi.mes.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.project.PMesStationEqStatusT;

public interface PMesStationEqStatusService {



	public List<PMesStationEqStatusT> findAllEq(@Param("name")String name) throws ServicesException;


	public Integer addEq(PMesStationEqStatusT eq) throws ServicesException;


	public Integer udpateEq(PMesStationEqStatusT eq) throws ServicesException;


	public Integer delEq(Integer id) throws ServicesException;
}
