package com.skeqi.mes.service.all;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

public interface CheckAllRecipePService {

	JSONObject checkAllRecipe(String snBarcode,Integer productionId, Integer productionLine, String stationType, Integer stationId,
			JSONObject jo);

	JSONObject checkClientLogin(String username, String password, String stationEmp, String lineEmpName, JSONObject jo);

	JSONObject checkPlanSn(String snPlan,JSONObject jo);

	JSONObject checkWorkorderSn(String snPlan,JSONObject jo);

	JSONObject checkProduction(String snProduction, String sId, JSONObject jo);

	JSONObject checkProduction1(String snProduction, String pId, JSONObject jo);

	public Integer findDefaultTotal(@Param("lineId")Integer lineId,@Param("pid")Integer pid);
	public Integer findDefaultRouting(@Param("lineId")Integer lineId,@Param("pid")Integer pid);
	public Integer findLineId(String lineName);
}
