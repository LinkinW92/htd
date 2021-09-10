package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeVersion;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;

public interface RecipeVersionDAO {

	//查看当前最大版本号
	public Double listName(@Param("pname")Integer pid);
	//添加版本
	public void addVersion(Map<String,Object> map);
	//当前最大ID
	public Integer listId();
	//添加版本明细
	public void addVersionDetail(@Param("recipeName")Integer recipeName,@Param("info")CMesRecipeVersionDetail recipe);
	//查询配方版本号
	public List<Map<String,Object>> findVersion(@Param("vr")String vr);
	//根据产品查版本号
	public List<CMesRecipeVersion> findPVersion(String productionVr);
	//查询配方id查名称
	public String findId(String vername);
	//根据配方名称查ID
	public Integer findName(String name);
	//根据产品ID查名称
	public String findPName(String id);
	//查询配方明细
	public List<CMesRecipeVersionDetail> recipeDatilListsTwo(@Param("id")Integer id,@Param("PVersion")Double PVersion);
	//根据工位名称查询工位ID
	public Integer findStationName(@Param("name")String name);
	//根据产品名称查询产品ID
	public Integer findProductName(@Param("name")String name);
	//根据产品ID和工位ID查询配方ID
	public Integer findPCId(@Param("Pid")Integer pid,@Param("Sid")Integer sid);
	//根据类型名称查询类型id
	public Integer findTId(@Param("name")String name);
	//添加到配方明细表
	public void addrecipe(Map<String,Object> map);
	//删除旧的配方明细数据
	public void deleterecipe(@Param("id")Integer id);
	//根据配方明细表的配方id查询id
	public List<Integer> findRId(Integer id);
	//查询配方明细表最大的id
	public Integer findMaxId();
	//删除版本表
	public void deleteVersion(@Param("verid")Integer verid);
	//删除版本明细表
	public void ddelteVersionDetil(@Param("verid")Integer verid);
	//根据产品规则查询id换个名称
	public CMesProductionT findPID(@Param("Pvr")String pvr);
	//查询该产品下的配方
	public List<CMesRecipeDatilT> findRecipe(@Param("id")String id);
}
