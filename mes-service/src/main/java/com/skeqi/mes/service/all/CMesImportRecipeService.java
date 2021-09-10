package com.skeqi.mes.service.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesRecipeVersion;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;
import com.skeqi.mes.pojo.CMesStationT;

public interface CMesImportRecipeService {

	//查询产品列表
	public List<CMesProductionT> findAllProduction(CMesProductionT c) throws ServicesException;

	//查询配方版本列表
	public List<CMesRecipeVersion> findAllRecipeVersion(CMesRecipeVersion version) throws ServicesException;

	//查询配方版本详情
	public List<CMesRecipeVersionDetail> findAllRecipeVersionDetail(Integer id) throws ServicesException;

	//查询当前配方
	public List<CMesRecipeDatilT> findAllRecipeDetail(Integer id) throws ServicesException;

	//查询产品类型
	public List<CMesRecipeTypeT> findRecipeType(String name) throws ServicesException;

	//根据工位名称查询工位信息
	public CMesStationT findStation(String name) throws ServicesException;

	//根据产品名称和产品规则查询是否存在
	public CMesProductionT findProduction(CMesProductionT t)throws ServicesException;

	//添加配方版本
	public Integer addRecipeVersion(CMesRecipeVersion version) throws ServicesException;

	//添加配方明细
	public Integer addRecipeVersionDetail(CMesRecipeVersionDetail version) throws ServicesException;

	public CMesRecipeVersion findMaxVersion(Integer id) throws ServicesException;

	//根据版本ID删除版本表
	public Integer deleteVersion(Integer id) throws ServicesException;

	//根据版本ID删除版本明细
	public Integer deleteVersionDetail(Integer id) throws ServicesException;

	//根据产品和工位查询是否存在配方
	public List<CMesProductionRecipeT> findProductionRecipe(String pname,String sname) throws ServicesException;

	//删除配方明细
	public Integer delRecipeDetail(Integer id) throws ServicesException;

	//添加配方明细
	public Integer addRecipeDetail(CMesRecipeDatilT recipe) throws ServicesException;
}
