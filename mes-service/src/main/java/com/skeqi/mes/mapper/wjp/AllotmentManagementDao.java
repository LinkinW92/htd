package com.skeqi.mes.mapper.wjp;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesStationT;

public interface AllotmentManagementDao {

	/** 配发查询 **/
	List<CMesRecipeT> findAll(Map<String, Object> map);

	/** 配方清单列表 **/
	List<CMesProductionRecipeT> findAllAllotment(Map<String, Object> map);

	/** 产品信息 **/
	List<CMesProductionT> findProduction();

	/** 产品名称不可重复 **/
	int NotRepeatable(String recipeName);

	/** 工位信息 **/
	List<CMesStationT> findStation();

	/** 工位修改
	 * @return **/
	boolean updateStation(CMesStationT cMesStationT);

	/** 产品修改 **/
	boolean updateProduction(CMesProductionT cMesProductionT);

	/** 配方修改 **/
	boolean updateAllotment(CMesRecipeT cMesRecipeT);

	/** 根据id查詢 **/
	CMesRecipeT findById(int id);

	/** 根据id查询总配方名称和配方名称**/
	CMesRecipeT findByIdToAndR(CMesRecipeT cMesRecipeT);

	/** 配方删除前判断是否解绑 **/
	int deleteAllotmentAs(int id);

	/** 配方刪除 **/
	boolean deleteAllotment(int id);

	/** 解绑信息 **/
	boolean deleteUntie(int id);

	/** 配方新增 **/
	void addRectipe(CMesRecipeT cMesRecipeT);

	/** 鏂板**/
	@SuppressWarnings("rawtypes")
	void addProRec(Map map);

	/** 鏈�澶у�� **/
	int getMaxNumber();

	@SuppressWarnings("rawtypes")
	void updateproductionRecipe(Map map);

	@SuppressWarnings("rawtypes")
	List<CMesProductionRecipeT> productionRecipe(Map map);

	@SuppressWarnings("rawtypes")
	List<CMesProductionRecipeT> findAllAllotmentByrId(Map map);


}
