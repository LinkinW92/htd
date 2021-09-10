package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.all.UpdateSnPDao;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.api.UpdateSnPT;
import com.skeqi.mes.service.all.UpdateSnPService;

@Service
public class UpdateSnPServiceImpl implements UpdateSnPService {

	@Autowired
	UpdateSnPDao dao;

	@Override
	public UpdateSnPT find1(String stationName, String lineName) {
		// TODO Auto-generated method stub
		return dao.find1(stationName, lineName);
	}

	@Override
	public UpdateSnPT find2(String stationname, String lineName) {
		// TODO Auto-generated method stub
		return dao.find2(stationname, lineName);
	}

	@Override
	public Integer insert1(UpdateSnPT dx) {
		// TODO Auto-generated method stub
		return dao.insert1(dx);
	}

	@Override
	public Integer delete1(String tempStationName, String snBarconde, String lineName) {
		// TODO Auto-generated method stub
		return dao.delete1(tempStationName, snBarconde, lineName);
	}

	@Override
	public Integer delete2(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.delete2(snBarconde);
	}

	@Override
	public Integer delete3(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.delete3(snBarconde);
	}

	@Override
	public Integer delete4(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.delete4(snBarconde);
	}

	@Override
	public UpdateSnPT find3(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.find3(snBarconde);
	}

	@Override
	public UpdateSnPT find4(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.find4(snBarconde);
	}

	@Override
	public UpdateSnPT find5(Integer routingId) {
		// TODO Auto-generated method stub
		return dao.find5(routingId);
	}

	@Override
	public UpdateSnPT find6(Integer routingId, String tempStationId) {
		// TODO Auto-generated method stub
		return dao.find6(routingId, tempStationId);
	}

	@Override
	public Integer update1(String tempStationName, String tempTrackingStatus, String tempStationIndex, String snBarconde) {
		// TODO Auto-generated method stub
		return dao.update1(tempStationName, tempTrackingStatus, tempStationIndex, snBarconde);
	}

	@Override
	public Integer insert2(String snBarconde, String tempStationName, String tempStationLineId) {
		// TODO Auto-generated method stub
		return dao.insert2(snBarconde, tempStationName, tempStationLineId);
	}

	@Override
	public Integer delete5(String tempStationName, String snBarconde, String lineName) {
		// TODO Auto-generated method stub
		return dao.delete5(tempStationName, snBarconde, lineName);
	}

	@Override
	public Integer insert3(String snBarconde, String tempStationName, String tempStationLineId) {
		// TODO Auto-generated method stub
		return dao.insert3(snBarconde, tempStationName, tempStationLineId);
	}

	@Override
	public Integer insert4(UpdateSnPT dx) {
		// TODO Auto-generated method stub
		return dao.insert4(dx);
	}

	@Override
	public Integer update2(String tempWorkOrderOffline,String tempCompletePlanCout, String tempCompleteOkPlanCount, String tempWorkOrderId) {
		// TODO Auto-generated method stub
		return dao.update2(tempWorkOrderOffline,tempCompletePlanCout, tempCompleteOkPlanCount, tempWorkOrderId);
	}

	@Override
	public Integer update3(String tempCompletePlanCout, String tempCompleteOkPlanCount, String tempPlanId) {
		// TODO Auto-generated method stub
		return dao.update3(tempCompletePlanCout, tempCompleteOkPlanCount, tempPlanId);
	}

	@Override
	public Integer insert5(UpdateSnPT dx) {
		// TODO Auto-generated method stub
		return dao.insert5(dx);
	}

	@Override
	public UpdateSnPT find8(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.find8(snBarconde);
	}

	@Override
	public Integer delete6(String tempPlanId) {
		// TODO Auto-generated method stub
		return dao.delete6(tempPlanId);
	}

	@Override
	public Integer delete7(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.delete7(snBarconde);
	}

	@Override
	public Integer delete8(Integer workId) {
		// TODO Auto-generated method stub
		return dao.delete8(workId);
	}

	@Override
	public Integer update4(String tempCompletePlanCout, String tempCompleteOkPlanCount, String tempPlanId) {
		// TODO Auto-generated method stub
		return dao.update4(tempCompletePlanCout, tempCompleteOkPlanCount, tempPlanId);
	}

	@Override
	public UpdateSnPT find9(String tempWorkOrderId) {
		// TODO Auto-generated method stub
		return dao.find9(tempWorkOrderId);
	}

	@Override
	public Integer update5(String tempWorkOrderOffline, String tempWorkOrderId) {
		// TODO Auto-generated method stub
		return dao.update5(tempWorkOrderOffline, tempWorkOrderId);
	}

	@Override
	public Integer update6(String tempWorkOrderOffline, String tempWorkOrderId) {
		// TODO Auto-generated method stub
		return dao.update6(tempWorkOrderOffline, tempWorkOrderId);
	}

	@Override
	public Integer insert6(UpdateSnPT dx) {
		// TODO Auto-generated method stub
		return dao.insert6(dx);
	}

	@Override
	public Integer insert7(UpdateSnPT dx) {
		// TODO Auto-generated method stub
		return dao.insert7(dx);
	}

	@Override
	public Integer delete9(String tempStationName, String snBarconde, String lineName) {
		// TODO Auto-generated method stub
		return dao.delete9(tempStationName, snBarconde, lineName);
	}

	@Override
	public Integer delete10(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.delete10(snBarconde);
	}

	@Override
	public Integer delete11(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.delete11(snBarconde);
	}

	@Override
	public Integer delete12(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.delete12(snBarconde);
	}

	@Override
	public Integer delete13(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.delete13(snBarconde);
	}

	@Override
	public Integer insert8(String snBarconde, String tempStationName, String tempStationLineId) {
		// TODO Auto-generated method stub
		return dao.insert8(snBarconde, tempStationName, tempStationLineId);
	}

	@Override
	public Integer update7(String tempStationName, String tempTrackingStatus, String tempStationIndex, String snBarconde) {
		// TODO Auto-generated method stub
		return dao.update7(tempStationName, tempTrackingStatus, tempStationIndex, snBarconde);
	}

	@Override
	public Integer delete14(String tempStationName, String snBarconde, String lineName) {
		// TODO Auto-generated method stub
		return dao.delete14(tempStationName, snBarconde, lineName);
	}

	@Override
	public UpdateSnPT find10(Integer routingId) {
		// TODO Auto-generated method stub
		return dao.find10(routingId);
	}

	@Override
	public UpdateSnPT find11(Integer routingId, String tempStationId) {
		// TODO Auto-generated method stub
		return dao.find11(routingId, tempStationId);
	}

	@Override
	public Integer update8(String tempStationName, String tempStationIndex, String snBarconde) {
		// TODO Auto-generated method stub
		return dao.update8(tempStationName, tempStationIndex, snBarconde);
	}

	@Override
	public Integer insert9(String snBarconde, String tempStationName, String tempStationLineId) {
		// TODO Auto-generated method stub
		return dao.insert9(snBarconde, tempStationName, tempStationLineId);
	}

	@Override
	public Integer delete15(String tempStationName, String snBarconde, String lineName) {
		// TODO Auto-generated method stub
		return dao.delete15(tempStationName, snBarconde, lineName);
	}

	@Override
	public Integer insert10(String snBarconde, String tempStationName, String tempTrackingStatus,
			String tempStationLineId) {
		// TODO Auto-generated method stub
		return dao.insert10(snBarconde, tempStationName, tempTrackingStatus, tempStationLineId);
	}

	@Override
	public Integer update9(String tempStationName, String tempStationIndex, String snBarconde) {
		// TODO Auto-generated method stub
		return dao.update9(tempStationName, tempStationIndex, snBarconde);
	}

	@Override
	public Integer delete16(String tempStationName, String snBarconde, String lineName) {
		// TODO Auto-generated method stub
		return dao.delete16(tempStationName, snBarconde, lineName);
	}

	@Override
	public UpdateSnPT find7(String tempPlanId) {
		// TODO Auto-generated method stub
		return dao.find7(tempPlanId);
	}

	@Override
	public RMesPlanT findAllPlan(String sn) {
		// TODO Auto-generated method stub
		return dao.findAllPlan(sn);
	}

	@Override
	public Integer findOfflineNumber(Integer id) {
		// TODO Auto-generated method stub
		return dao.findOfflineNumber(id);
	}

	@Override
	public Integer insertMaterialInstance(Map<String, Object> map) {
		List<Map<String, Object>> listPro = dao.getProduction(map);
		List<Map<String, Object>> listWor = dao.getWorkorder(map);
		if(listPro != null && listPro.size() > 0){
			map.put("MATERIAL_CODE", listPro.get(0).get("MATERIAL_CODE"));
			map.put("MATERIAL_NAME", listPro.get(0).get("PRODUCTION_NAME"));
		}
		if(listWor != null && listWor.size() > 0){
			map.put("WORKORDER_ID", listWor.get(0).get("WORKORDER_ID"));
		}
		return dao.insertMaterialInstance(map);
	}

	@Override
	public Integer updateStationSerialFlag(Map<String, Object> map) {
		return dao.updateStationSerialFlag(map);
	}

}
