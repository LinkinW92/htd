package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;

/**
 * 配方dao
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date   : 2020年3月27日 上午11:10:45
 */
public interface CMesRecipeDAO {


	//查询总配方
	public List<CMesTotalRecipeT> findAllTotalRecipe(CMesTotalRecipeT total);

	//根据id或名称查询总配方
	public List<CMesTotalRecipeT> findTotalRecipeByParam(@Param("name")String name,@Param("id")Integer id);

	//添加总配方
	public Integer addTotalRecipe(CMesTotalRecipeT c);

	//修改总配方
	public Integer updateTotalRecipe(CMesTotalRecipeT c);

	//删除总配方
	public Integer delTotalRecipe(Integer id);


	//查询配方列表
	public List<CMesRecipe> findAllRecipe(CMesRecipe c);

	//根据id查询配方列表
	public List<CMesRecipe> findRecipeByParam(@Param("name")String name,@Param("id")Integer id,@Param("totalId")Integer totalId);

	//添加配方
	public Integer addRecipe(CMesRecipe c);

	//修改配方
	public Integer updateRecipe(CMesRecipe c);

	//删除配方
	public Integer delRecipe(Integer id);

	//根据配方id查询配方详情
	public List<CMesRecipeDatilT> findAllRecipeDatil(Integer id);

	//查询所有工位
	public List<CMesStationT> findStation(CMesStationT c);

	//将总配方设为默认配方
	public Integer editStatus(@Param("id")Integer id,@Param("status")Integer status);

	//将总配方设为默认配方(根据产品ID)
	public Integer editStatusPro(@Param("id")Integer id);

	//查询该产品是否存在默认
	Integer findDefaultStatus(@Param("id") Integer id,@Param("lineId") Integer lineId);

	public Integer findRecipeCountByStationId(CMesRecipe c);

}
