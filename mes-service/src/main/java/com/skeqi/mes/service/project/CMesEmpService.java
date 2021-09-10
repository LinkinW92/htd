package com.skeqi.mes.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesStationT;

public interface CMesEmpService {


	public List<CMesEmpT> findAllEmp(@Param("name")String name) throws ServicesException;


	public Integer addEmp(CMesEmpT emp) throws ServicesException;


	public Integer updateEmp(CMesEmpT emp) throws ServicesException;

	public Integer delEmp(Integer id) throws ServicesException;

	public List<CMesEmpTypeT> findsEmpType();


	public List<CMesStationT> findStation();

	public Integer findEmpTypeName(String name);

	public Integer findTeamName(String name);

	public Integer findStationName(String name);


	public Integer findEmpByName(String empNos);


	List<CMesEmpT> findStationNameById(CMesEmpT cMesEmpT) throws ServicesException;

}
