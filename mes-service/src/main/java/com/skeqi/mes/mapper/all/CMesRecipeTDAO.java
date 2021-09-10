package com.skeqi.mes.mapper.all;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;

public interface CMesRecipeTDAO {

	//查询配方
	public List<CMesRecipeT> findAllRecipe(CMesRecipeT recipe);

	//根据id查询配方
	public CMesRecipeT findRecipeByid(Integer id);

	//查询配方表最大的配方id
	public Integer findRecipeMaxid();

	//添加配方
	public Integer addRecipe(CMesRecipeT recipe);

	//修改配方
	public Integer updateRecipe(CMesRecipeT recipe);

	//删除配方
	public Integer delRecipe(Integer id);

	//查询配方产品绑定表
	public List<CMesProductionRecipeT> findAllProRecipe(CMesProductionRecipeT recipe);

	public CMesProductionRecipeT findProRecipeByid(Integer id);

	//添加配方产品绑定表
	public Integer addProRecipe(CMesProductionRecipeT recipe);

	//删除配方产品绑定表
	public Integer delProRecipe(Integer id);

	//修改产品配方绑定表
	public void updateProRecipe(CMesProductionRecipeT cMesProductionRecipeT);

	//配方类型列表
	public List<CMesRecipeTypeT> findAllRecipeType();

	//配方明细列表
	public List<CMesRecipeDatilT> findAllRecipeDatil(CMesRecipeDatilT t);

	//根据产品ID查询配方列表
	public List<CMesRecipeT> findAllRecipeByPid(@Param("id")Integer id);

	//删除配方
	public Integer delRecipeDatil(Integer id);

	public Integer updateRecipeDetailStep(CMesRecipeDatilT t);

	//根据物料名称查询id
	public Integer findMaterialByid(@Param("name")String name);

	//根据配方ID查询最大的步序
	public Integer findMaxStepNo(Integer rid);

	//添加配方明细
	public Integer addRecipeDetail(CMesRecipeDatilT t);

	//根据ID查询配方明细列表
	public CMesRecipeDatilT findRecipeDetailByid(Integer id);

	//修改配方明细
	public Integer updateRecipeDetail(CMesRecipeDatilT t);

	//修改配方的工序
	public Integer updateRecipeStepNo(@Param("oldstepNO")Integer oldstepNO,@Param("newstepNo")Integer newstepNo,
										@Param("rid")Integer rid,@Param("flag")Integer flag);

	//查询物料列表
	public List<CMesJlMaterialT> findAllMaterial();
    // 查询配方明细id
	public List<CMesRecipeDatilT> findRecipeDetailId(CMesRecipeDatilT cMesRecipeDatilT);

	public Map<String, Object> getTotalRecipe(CMesRecipeDatilT recipe);

	public Integer insertTotalRecipe(Map<String, Object> totalRecipeMap);

	public String getLastRecipeName(Map<String, Object> totalRecipeMap);

	public List<Map<String, Object>> findRecipeByTotalId(Map<String, Object> totalRecipeMap);

	public Integer insertRecipeCopy(Map<String, Object> recipeMap);

	public List<Map<String, Object>> findRecipeDetailListByRecipeId(Map<String, Object> recipeMap);

	public Integer insertRecipeDetailCopy(Map<String, Object> recipeDetailMap);

	public Integer updateWorkorderRecipe(Map<String, Object> map);

	public Map<String, Object> getTotalRecipe2(CMesRecipeDatilT recipe);

}
