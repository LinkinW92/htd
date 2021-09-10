package com.skeqi.mes.service.wf;

import java.util.List;
import java.util.Map;

public interface ProductionPageService {

    List<Map<String, Object>> findProduction();

    List<Map<String, Object>> findOnlineWorkOrder();

    List<Map<String, Object>> findEvent();

    List<Map<String, Object>> findProductionYield();

    List<Map<String, Object>> findOnlineShiftTeam();

    List<Map<String, Object>> finishedProduct();
}
