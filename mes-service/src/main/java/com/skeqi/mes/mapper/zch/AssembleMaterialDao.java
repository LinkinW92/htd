package com.skeqi.mes.mapper.zch;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AssembleMaterialDao {

	//查询条码是否被装配
    Integer findRKeypart(@Param("nums")String nums);
    Integer findPKeypart(@Param("nums")String nums);

	Map<String, Object> getKerpartRByMaterialName(@Param("SN") String sn, @Param("stationName") String stationName, @Param("materialName") String materialName);

	Integer updateAssembleKeypartPT(@Param("barcode") String barcode, @Param("emp") String emp, @Param("ID") Object iD);
	Map<String, Object> getKerpartRByMaterialPN(@Param("SN") String sn, @Param("stationName") String stationName, @Param("materialPn") Object mATERIALPN);

}
