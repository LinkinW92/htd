package com.skeqi.mes.service.all;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.api.UpdateSnPT;

/**
 * @author Yinp
 * @date 2020年1月13日
 */
public interface UpdateSnPService {

	public Integer findOfflineNumber(Integer id);

	public RMesPlanT findAllPlan(String sn);

	public UpdateSnPT find1(String stationName, String lineName);

	public UpdateSnPT find2(String stationname, String lineName);

	public Integer insert1(UpdateSnPT dx);

	public Integer delete1(String tempStationName, String snBarconde, String lineName);

	public Integer delete2(String snBarconde);

	public Integer delete3(String snBarconde);

	public Integer delete4(String snBarconde);

	public UpdateSnPT find3(String snBarconde);

	public UpdateSnPT find4(String snBarconde);

	public UpdateSnPT find5(Integer routingId);

	public UpdateSnPT find6(Integer routingId, String tempStationId);

	public Integer update1(String tempStationName, String tempTrackingStatus, String tempStationIndex, String snBarconde);

	public Integer insert2(String snBarconde, String tempStationName, String tempStationLineId);

	public Integer delete5(String tempStationName, String snBarconde, String lineName);

	public Integer insert3(String snBarconde, String tempStationName, String tempStationLineId);

	public Integer insert4(UpdateSnPT dx);

	public Integer update2(String tempWorkOrderOffline,String tempCompletePlanCout, String tempCompleteOkPlanCount, String tempWorkOrderId);

	public Integer update3(String tempCompletePlanCout, String tempCompleteOkPlanCount, String tempPlanId);

	public Integer insert5(UpdateSnPT dx);

	public UpdateSnPT find7(String tempPlanId);

	public UpdateSnPT find8(String snBarconde);

	public Integer delete6(String tempPlanId);

	public Integer delete7(String snBarconde);

	public Integer delete8(Integer workId);

	public Integer update4(String tempCompletePlanCout, String tempCompleteOkPlanCount, String tempPlanId);

	public UpdateSnPT find9(String tempWorkOrderId);

	public Integer update5(String tempWorkOrderOffline, String tempWorkOrderId);

	public Integer update6(String tempWorkOrderOffline, String tempWorkOrderId);

	public Integer insert6(UpdateSnPT dx);

	public Integer insert7(UpdateSnPT dx);

	public Integer delete9(String tempStationName, String snBarconde, String lineName);

	public Integer delete10(String snBarconde);

	public Integer delete11(String snBarconde);

	public Integer delete12(String snBarconde);

	public Integer delete13(String snBarconde);

	public Integer insert8(String snBarconde, String tempStationName, String tempStationLineId);

	public Integer update7(String tempStationName, String tempTrackingStatus, String tempStationIndex, String snBarconde);

	public Integer delete14(String tempStationName, String snBarconde, String lineName);

	public UpdateSnPT find10(Integer routingId);

	public UpdateSnPT find11(Integer routingId, String tempStationId);

	public Integer update8(String tempStationName, String tempStationIndex, String snBarconde);

	public Integer insert9(String snBarconde, String tempStationName, String tempStationLineId);

	public Integer delete15(String tempStationName, String snBarconde, String lineName);

	public Integer insert10(String snBarconde, String tempStationName, String tempTrackingStatus,
			String tempStationLineId);

	public Integer update9(String tempStationName, String tempStationIndex, String snBarconde);

	public Integer delete16(String tempStationName, String snBarconde, String lineName);

	public Integer insertMaterialInstance(Map<String, Object> map);

	public Integer updateStationSerialFlag(Map<String, Object> map);

}
