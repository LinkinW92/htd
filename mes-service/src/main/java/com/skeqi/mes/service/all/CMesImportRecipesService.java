package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesImportT;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;

/**
 * 新版导出导入配方
 * @author : FQZ
 * @Package: com.skeqi.mes.service.all
 * @date   : 2020年4月6日 上午10:21:16
 */
public interface CMesImportRecipesService {

	//查询所有配方
	public List<CMesTotalRecipeT> findAllTotalRecipe() throws ServicesException;

	//查询总配方下的配方详情
	public List<Map<String,Object>> findAllRecipeDetail(Integer id) throws ServicesException;

	//查询总配方下的配方详情
	public List<CMesImportT> findAllRecipeDetailTwo(@Param("id")Integer id) throws ServicesException;

	//根据工位名称查询是否存在此工位
	public Integer findByNameStation(@Param("name")String name,@Param("lineId")Integer id) throws ServicesException;

	//根据操作类别名称查询ID
	public Integer findByNameType(@Param("name")String name) throws ServicesException;

	//添加总配方
	public Integer addTotalRecipe(CMesTotalRecipeT recipe) throws ServicesException;

	//查询最大的总配方ID
	public Integer findTotalId() throws ServicesException;

	//添加工位配方
	public Integer addRecipe(CMesRecipe recipe) throws ServicesException;

	//查询最大的工位配方
	public Integer findRecipeId() throws ServicesException;

	//添加配方明细
	public Integer addRecipeDatil(CMesRecipeDatilT datil)throws ServicesException;

	//根据产品名称查询ID
	public Integer findProductionId(@Param("pname")String pname) throws ServicesException;

	//根据产线名查询产线ID
	public Integer findLineId(@Param("lname")String lname);

	//根据总配方名称查询数量（判断是否重复）
	public Integer findCountByTotalName(String totalName);
}
