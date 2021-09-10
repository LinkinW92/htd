package com.skeqi.mes.service.wjp;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesProductionT;

public interface ProductManagementService {

	//产品查询
	List<CMesProductionT> findAll();

	//产品新增
	Integer addProduction(CMesProductionT cMesProductionT);

	//名称和类型不能重复
	int NoRepeat(CMesProductionT cMesProductionT);
	int NoRepeats(CMesProductionT cMesProductionT);
	//产品修改-根据id查询
	CMesProductionT findProductionById(int id);

	//产品修改
	@SuppressWarnings("rawtypes")
	boolean updateProduction(Map map);

	//产品删除
	boolean deleteProduction(int id);

	int countProductionProcessByProductionId(Map<String, Object> map);

	int countBomByProductionId(Map<String, Object> map);

	int countRecipeByProductionId(Map<String, Object> map);

	int countPlanByProductionId(Map<String, Object> map);

	int countBarCodeByProduction(Map<String, Object> map);
}
