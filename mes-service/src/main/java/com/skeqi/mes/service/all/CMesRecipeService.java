package com.skeqi.mes.service.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;

/**
 * 配方
 * @author : FQZ
 * @Package: com.skeqi.mes.service.all
 * @date   : 2020年3月27日 上午11:49:37
 */
public interface CMesRecipeService {


	//查询总配方
	public List<CMesTotalRecipeT> findAllTotalRecipe(CMesTotalRecipeT total) throws ServicesException;

	//根据id或名称查询总配方
	public List<CMesTotalRecipeT> findTotalRecipeByParam(@Param("name")String name,@Param("id")Integer id)throws ServicesException;

	//添加总配方
	public Integer addTotalRecipe(CMesTotalRecipeT c)throws ServicesException;

	//修改总配方
	public Integer updateTotalRecipe(CMesTotalRecipeT c)throws ServicesException;

	//删除总配方
	public Integer delTotalRecipe(Integer id)throws ServicesException;


	//查询配方列表
	public List<CMesRecipe> findAllRecipe(CMesRecipe c)throws ServicesException;

	//根据id查询配方列表
	public List<CMesRecipe> findRecipeByParam(String name,Integer id,Integer totalId)throws ServicesException;

	//添加配方
	public Integer addRecipe(CMesRecipe c)throws ServicesException;

	//修改配方
	public Integer updateRecipe(CMesRecipe c)throws ServicesException;

	//删除配方
	public Integer delRecipe(Integer id)throws ServicesException;

	//查询所有工位
	public List<CMesStationT> findStation(CMesStationT c)throws ServicesException;

	public Integer editStatus(Integer id,Integer status)throws ServicesException;

}
