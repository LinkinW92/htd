package com.skeqi.mes.service.wjp;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesStationT;

public interface AllotmentManagementService {

	// 配方管理
	List<CMesRecipeT> findAll(Map<String, Object> map);

	// 产品配方清单
	List<CMesProductionRecipeT> findAllAllotment(Map<String, Object> map);

	@SuppressWarnings("rawtypes")
	List<CMesProductionRecipeT> findAllAllotmentByrId(Map map);
	//产品信息
	List<CMesProductionT> findProduction();

	/** 产品名称不可重复 **/
	int NotRepeatable(String recipeName);

	//工位信息
	List<CMesStationT> findStation();

	// 根据id查询配方
	CMesRecipeT findById(int id);

	/** 根据id查询总配方名称和配方名称**/
	CMesRecipeT findByIdToAndR(CMesRecipeT cMesRecipeT);

	//配方删除前判断是否解绑
	int deleteAllotmentAs(int id);

	// 配方刪除
	boolean deleteAllotment(int id);

	//解绑信息
	boolean deleteUntie(int id);

	//配方新增
	void addRectipe(CMesRecipeT cMesRecipeT);

	//新增
	@SuppressWarnings("rawtypes")
	void addProRec(Map map);

	//修改工位
	boolean updateStation(CMesStationT cMesStationT);

	//修改产品
	boolean updateProduction(CMesProductionT cMesProductionT);

	//修改配方
	boolean updateAllotment(CMesRecipeT cMesRecipeT);

	//最大值
	int getMaxNumber();

	@SuppressWarnings("rawtypes")
	void updateproductionRecipe(Map map);

	@SuppressWarnings("rawtypes")
	List<CMesProductionRecipeT> productionRecipe(Map map);

}
