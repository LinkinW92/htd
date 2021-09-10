package com.skeqi.mes.service.project;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesLineT;

public interface CMesProLIneService {



	public List<CMesLineT> findAllLine(@Param("name")String name) throws ServicesException;

	//添加产线
	public Integer addLine(CMesLineT line) throws ServicesException;

	public Integer updateLine(CMesLineT line) throws ServicesException;

	public Integer delLine(Integer id) throws ServicesException;

	public Integer findByName(String lineName);

	public Integer findProVrByLine(String productionVr);


	public List<CMesLineT> findproTypeByLine(String proType);

	public List<CMesLineT> findLineIdByProType(Integer lineId);

	public String findPidByType(Integer productionId);
}
