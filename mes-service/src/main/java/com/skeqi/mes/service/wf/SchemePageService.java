package com.skeqi.mes.service.wf;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface SchemePageService {


    List<Map<String, Object>> findOrder();

    List<Map<String, Object>> findBuyingSituation() throws ParseException;

    List<Map<String, Object>> findProduction();

    List<Map<String, Object>> findEvent();

    List<Map<String, Object>> findOnlineOrder();

    List<Map<String, Object>> findOnlineWorkOrder();

    List<Map<String, Object>> findOnlineLine();

    List<Map<String, Object>> findLineOptimalRate();

}
