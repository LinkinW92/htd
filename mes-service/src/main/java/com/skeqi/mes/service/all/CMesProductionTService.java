package com.skeqi.mes.service.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesProductionT;

public interface CMesProductionTService {

	public List<CMesProductionT> findAllProduction(CMesProductionT pro) throws ServicesException;

	public CMesProductionT findProductionByid(Integer id) throws ServicesException;

	public Integer addProduction(CMesProductionT pro) throws ServicesException;

	public Integer updateProduction(CMesProductionT pro) throws ServicesException;

	public Integer delProduction(Integer id) throws ServicesException;

	public List<CMesLabelManagerT> findAllLable() throws ServicesException;

	public List<CMesProductionT> findAllProductionL();

	public Integer addProductionL(CMesProductionT cMesProductionT) throws Exception;

	public Integer delProductionL(Integer id);

	public Integer updateProductionL(CMesProductionT cMesProductionT) throws Exception;

	public CMesProductionT findProductionByidL(Integer id,String productionType);

    List<CMesProductionT> findProductTypeList();

	List<CMesProductionT> findProductionByCode(CMesProductionT cMesProductionT);

	public List<CMesProductionT> findProductionByNameAndId(String name,Integer id);
}
