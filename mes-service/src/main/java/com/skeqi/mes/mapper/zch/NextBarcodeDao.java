package com.skeqi.mes.mapper.zch;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface NextBarcodeDao {

	Map<String, Object> getLineByID(@Param("lineId") Integer lineId);

	Map<String, Object> getHighPriorityWorkorderByLineId(@Param("lineId") Object lineId);

	Map<String, Object> getProductByID(@Param("productId") Object pRODUCT_ID);

	Map<String, Object> getLabelByID(@Param("labelId") Object bARCODE_RULE_ID);

	Map<String, Object> getLabelByIDAndNameAndLineId(@Param("id") Integer id,@Param("labelName") String labelName,@Param("lineId") Integer lineId);

	Map<String, Object> getMaxPrintR(@Param("lineId") Integer lineId);

	Map<String, Object> getMaxPrintP(@Param("lineId") Integer lineId);

	Integer insertPrint(Map<String, Object> print);

	Integer updatePrintNumber(@Param("workorderId") Object workorderId);

	Integer updatePrintFlag(@Param("SN") String barcode);

	Map<String, Object> getLineByIDAndName(@Param("lineId")Integer lineId, @Param("lineName")String lineName);

}
