package com.skeqi.mes.mapper.qh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

@Mapper
public interface PMesTrackingTMapper {
    List<Map<String, Object>> findPMesTrackingByProductIDAndWorkOrderId(@Param("productId") Integer productId, @Param("workOrderId") String workOrderId);

    List<Map<String, Object>> findSNByLineId(String lineId);

    Integer editPMesTrackingBySN(Map<String, Object> map);
}
