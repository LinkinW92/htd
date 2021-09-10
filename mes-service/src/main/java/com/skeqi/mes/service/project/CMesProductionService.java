package com.skeqi.mes.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesProductionT;

public interface CMesProductionService {

	public List<CMesProductionT> findAllPro(@Param("name")String name) throws ServicesException;



	public Integer addPro(CMesProductionT pro) throws ServicesException;


	public Integer updatePro(CMesProductionT pro) throws ServicesException;


	public Integer delPro(@Param("id")Integer id) throws ServicesException;



	public Integer findByProductionType(String name);



	public Integer findByProductionTypeL(String productionType, Integer id);



	public Integer addProL(CMesProductionT t);



	public CMesProductionT findByProTypeL(String productionType);

	public CMesProductionT findProIdAndName(Integer id,String proName);
}
