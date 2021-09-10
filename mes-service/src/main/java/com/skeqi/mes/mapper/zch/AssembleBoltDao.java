package com.skeqi.mes.mapper.zch;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AssembleBoltDao {

	Map<String, Object> getRecipeDetail(@Param("totalRecipeId") Object totalRecipeId, @Param("stationId") Object stationId, @Param("stepNo") Integer stepNo, @Param("type") Integer type);

	Map<String, Object> getBoltUnuse(@Param("SN") String snBarcode, @Param("stationName") String stationName, @Param("materialName") Object materialName);

	Integer getRemainBoltCount(@Param("SN") String snBarcode, @Param("stationName") String stationName, @Param("materialName") Object materialName);

	Integer getNgCount(@Param("SN") String snBarcode, @Param("stationName") String stationName, @Param("materialName") Object materialName);

	Integer insertBoltNg(Map<String, Object> boltMap);

	Integer updateBolt(Map<String, Object> boltMap);

	Integer updateBoltY(Map<String, Object> boltMap);

	Map<String, Object> getMaterialRecipeDetail(@Param("totalRecipeId") Object totalRecipeId, @Param("stationId") Object stationId, @Param("stepNo") Integer stepNo);

}
