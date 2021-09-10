package com.skeqi.mes.mapper.wf;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
public interface SchemePageDao {


    List<Map<String, Object>> findOrderTarget();

    List<Map<String, Object>> findOrderAlreadyProduce();

    List<Map<String, Object>> findBuyingSituation();

    List<Map<String, Object>> findProduction();

    List<Map<String, Object>> findEvent();

    List<Map<String, Object>> findOnlineOrder();

    List<Map<String, Object>> findOnlineWorkOrder();

    List<Map<String, Object>> findOnlineLine();

    List<Map<String, Object>> findLineOptimalRate();
}
