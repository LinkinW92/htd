package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesProductionT;

public interface CMesProductionTDAO {

	public List<CMesProductionT> findAllProduction(CMesProductionT pro);

	public List<CMesProductionT> findProductionByName(@Param("name")String name);

	public CMesProductionT findProductionByid(@Param("id")Integer id);

	public Integer addProduction(CMesProductionT pro);

	public Integer updateProduction(CMesProductionT pro);

	public Integer delProduction(@Param("id")Integer id);

	public List<CMesLabelManagerT> findAllLable();

	public List<CMesProductionT> findAllProductionL();

	public Integer addProductionL(CMesProductionT cMesProductionT)throws Exception ;



	public CMesProductionT findProductionByidL(@Param("id")Integer id,@Param("productionType")String productionType);

	public List<CMesProductionT> findProductionByNameL(@Param("name")String name,@Param("id")Integer id);

	public List<CMesProductionT> findProductionByNameAndId(@Param("name")String name,@Param("id")Integer id);

	List<CMesProductionT> findProductionByCode(CMesProductionT cMesProductionT);
}
