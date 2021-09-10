package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeVersion;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;

public interface RecipeVersionService {

	public Double listName(Integer pid);

	public void addVersion(Map<String,Object> map);

	public Integer listId();

	public void addVersionDetail(Integer recipeName,CMesRecipeVersionDetail recipe);

	public List<Map<String,Object>> findVersion(String vr);

	public List<CMesRecipeVersion> findPVersion(String productionVr);

	public String findId(String vername);

	public Integer findName(String name);

	public String findPName(String id);

	public List<CMesRecipeVersionDetail> recipeDatilListsTwo(Integer id,Double PVersion);

	public Integer findStationName(String name);

	public Integer findProductName(String name);

	public Integer findPCId(Integer pid,Integer sid);

	public Integer findTId(String name);

	public void addrecipe(Map<String,Object> map);

	public void deleterecipe(Integer id);

	public List<Integer> findRId(Integer id);

	public Integer findMaxId();

	public void deleteVersion(Integer id);

	public void ddelteVersionDetil(Integer verid);

	public CMesProductionT findPID(String pvr);

	public List<CMesRecipeDatilT> findRecipe(String id);
}
