package com.skeqi.mes.service.qh;

import java.util.List;
import java.util.Map;

public interface RMesTrackingTService{
    List<Map<String,Object>> findRMesTrackingByWorkOrderId(Integer workOrderId);

    List<Map<String, Object>> findPMesTrackingByProductIDAndWorkOrderId(Integer productId, String workOrderId);
}
