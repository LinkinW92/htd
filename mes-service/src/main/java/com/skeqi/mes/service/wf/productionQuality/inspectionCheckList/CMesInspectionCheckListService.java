package com.skeqi.mes.service.wf.productionQuality.inspectionCheckList;

import com.skeqi.mes.util.Rjson;

import java.util.List;
import java.util.Map;

public interface CMesInspectionCheckListService {
    List<Map<String,Object>> findCheckList(Map<String,Object> map);

    List<Map<String, Object>> findCheckListDetail(String checkListCode);

    Integer getNextCheckListCode();

    Rjson addCheckList(Map<String, Object> map) throws Exception;

    Rjson addCheckListDetail(Map<String, Object> map) throws Exception;

    Rjson editCheckList(Map<String, Object> map) throws Exception;

    Rjson deleteCheckList(Map<String, Object> map) throws Exception;

    Rjson deleteCheckListDetail(Map<String, Object> map) throws Exception;

    Rjson editCheckListDetailByStart(Map<String, Object> map) throws Exception;

    Rjson editCheckListDetail(Map<String, Object> map) throws Exception;

    Rjson addCheckListBatch(List<Map<String, Object>> data) throws Exception;

    Rjson addCheckListDetailBatch(List<Map<String, Object>> data) throws Exception;

    List<Map<String, Object>> findCheckListDetailAll(List<String> codeArray);

    Integer findCheckListDetailByCodeAndVersions(Map<String, Object> objectMap);

    Integer findCheckListByCodeAndType(Map<String, Object> objectMap);

    List<Map<String,Object>> findCheckListByProduceType(Map<String, Object> map);

    List<Map<String, Object>> selectCheckListDetailByVersions(Map<String, Object> hashMap);
}
