package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesRecipeVersion;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;
import com.skeqi.mes.pojo.CMesStationT;

/**
 * 导出/导入配方
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date   : 2020年3月14日 下午4:03:32
 */
public interface CMesImportRecipeDAO {

	//查询产品列表
	public List<CMesProductionT> findAllProduction(CMesProductionT c);

	//查询配方版本列表
	public List<CMesRecipeVersion> findAllRecipeVersion(CMesRecipeVersion version);

	//查询配方版本详情
	public List<CMesRecipeVersionDetail> findAllRecipeVersionDetail(@Param("id")Integer id);

	//查询当前配方
	public List<CMesRecipeDatilT> findAllRecipeDetail(Integer id);

	//查询产品类型
	public List<CMesRecipeTypeT> findRecipeType(String name);

	//根据工位名称查询工位信息
	public CMesStationT findStation(String name);

	//根据产品名称和产品规则查询是否存在
	public CMesProductionT findProduction(CMesProductionT t);

	//添加配方版本
	public Integer addRecipeVersion(CMesRecipeVersion version);

	//添加配方明细
	public Integer addRecipeVersionDetail(CMesRecipeVersionDetail version);

	//根据产品ID查询最大的版本号
	public CMesRecipeVersion findMaxVersion(Integer id);

	//根据版本ID删除版本表
	public Integer deleteVersion(Integer id);

	//根据版本ID删除版本明细
	public Integer deleteVersionDetail(Integer id);

	//根据产品和工位查询是否存在配方
	public List<CMesProductionRecipeT> findProductionRecipe(@Param("pname")String pname,@Param("sname")String sname);

	//删除配方明细
	public Integer delRecipeDetail(Integer id);

	//添加配方明细
	public Integer addRecipeDetail(CMesRecipeDatilT recipe);
}
