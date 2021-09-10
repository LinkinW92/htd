package com.skeqi.mes.mapper.wf.productionQuality.inspectionCheckList;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CMesInspectionCheckListDao {

    List<Map<String,Object>> findCheckList(Map<String,Object> map);

    List<Map<String, Object>> findCheckListDetail(@Param("checkListCode")String checkListCode);

    Integer getNextCheckListCode();

    Integer addCheckList(Map<String, Object> map);

    Integer addCheckListDetail(Map<String, Object> map);

    Integer selectCheckListByCode(@Param("code")String code);

    List<Map<String, Object>> selectCheckListDetailByVersions(Map<String, Object> map);

    Integer editCheckListDetail(Map<String, Object> map);

    Integer editCheckListDetailByVersions(@Param("versions")List<String> versions,@Param("code")String code);

    Integer deleteCheckListDetail(Map<String, Object> map);

    Integer deleteCheckList(Map<String, Object> map);

    Integer editCheckListDetailByStart(Map<String, Object> map);

    Integer updateCheckListDetail(Map<String, Object> map);

    Integer editCheckListDetailById(Map<String, Object> map);

    Integer addCheckListBatch(List<Map<String, Object>> data);

    Integer addCheckListDetailBatch(List<Map<String, Object>> data);

    List<Map<String, Object>> findCheckListDetailAll(List<String> codeArray);

    Integer findCheckListDetailByCodeAndVersions(Map<String, Object> objectMap);

    Integer findCheckListByCodeAndType(Map<String, Object> objectMap);

    Integer editCheckList(Map<String, Object> map);

    List<Map<String,Object>> findCheckListByProduceType(Map<String, Object> map);
}
