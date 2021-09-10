package com.skeqi.mes.service.qh;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

public interface PMesTrackingTService{
    List<Map<String,Object>> findPMesTrackingByProductIDAndWorkOrderId(Integer productId,String workOrderId);

    List<Map<String, Object>> findSNByLineId(String lineId);
}
