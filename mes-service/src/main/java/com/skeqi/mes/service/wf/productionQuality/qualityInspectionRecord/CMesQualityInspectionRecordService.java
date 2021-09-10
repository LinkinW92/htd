package com.skeqi.mes.service.wf.productionQuality.qualityInspectionRecord;

import com.skeqi.mes.util.Rjson;

import java.util.List;
import java.util.Map;

public interface CMesQualityInspectionRecordService {
    List<Map<String, Object>> findQuality(Map<String, Object> map);

    List<Map<String, Object>> findCheckListDetailBySN(String sn, String type);

    Rjson addQuality(Map<String, Object> map) throws Exception;

    List<Map<String, Object>> findCheckContent(Map<String, Object> map);

    Rjson auditQuality(Map<String, Object> map) throws Exception;

    List<Map<String, Object>> findQualityAll(Map<String, Object> map);

    List<Map<String, Object>> findDisposeQuality(Map<String, Object> map);
}
