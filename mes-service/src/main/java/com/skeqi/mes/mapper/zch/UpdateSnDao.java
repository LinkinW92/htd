package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.zch.MesBoltT;
import com.skeqi.mes.pojo.zch.MesKeypartT;
import com.skeqi.mes.pojo.zch.MesLeakageT;
import com.skeqi.mes.pojo.zch.MesPrintT;

public interface UpdateSnDao {

	Map<String, Object> getTrackingR(@Param("SN") String snBarcode);

	Integer getSerialMax(@Param("routingId") Object routingId);

	Integer updateTrackingStation(@Param("stationName") String stationName, @Param("stationIndex") Object stationIndex, @Param("SN") String snBarcode);

	Integer insertStationPass(@Param("SN") String snBarcode, @Param("stationName") String stationName, @Param("lineId") Object lineId);

	Integer deleteStep(@Param("stationName") String stationName, @Param("SN") String snBarcode, @Param("lineName") String lineName);

	Integer updateSerialFlag(@Param("SN") String snBarcode, @Param("stationId") Object stationId);

	List<MesBoltT> findBoltList(@Param("SN") String snBarcode);

	List<MesKeypartT> findKeypartList(@Param("SN") String snBarcode);

	List<MesLeakageT> findLeakageList(@Param("SN") String snBarcode);

	Integer insertBoltBatch(@Param("list") List<MesBoltT> list);

	Integer insertKeypartBatch(@Param("list") List<MesKeypartT> list);

	Integer insertLeakageBatch(@Param("list") List<MesLeakageT> list);

	Integer deleteBolt(@Param("SN") String snBarcode);

	Integer deleteKeypart(@Param("SN") String snBarcode);

	Integer deleteLeakage(@Param("SN") String snBarcode);

	Integer updateOfflineNumber(@Param("workorderId") Object workorderId);

	List<MesPrintT> findPrintList(@Param("workorderId") Object workorderId);

	Integer insertPrintBatch(@Param("list") List<MesPrintT> list);

	Integer deletePrint(@Param("workorderId") Object workorderId);

	Integer insertWorkorder(Map<String, Object> workorderMap);

	Integer deleteWorkorder(@Param("workorderId") Object workorderId);

	Integer insertTracking(Map<String, Object> trackingMap);

	Integer deleteTracking(@Param("SN") String snBarcode);

	List<Map<String, Object>> findSerialFlagList(@Param("SN") String snBarcode);

	Integer insertSerialFlagBatch(@Param("list") List<Map<String, Object>> list);

	Integer deleteSerialFlag(@Param("SN") String snBarcode);

	Map<String, Object> getProductByID(@Param("productionId") Object productionId);

	Integer insertMaterialInstance(@Param("SN") String snBarcode, @Param("MATERIAL_CODE") Object MATERIAL_CODE
			, @Param("MATERIAL_NAME") Object MATERIAL_NAME, @Param("WORKORDER_ID") Object wORKORDER_ID);

}
