package com.skeqi.mes.mapper.wf.productionQuality.qualityInspectionRecord;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CMesQualityInspectionRecordDao {
    List<Map<String, Object>> findQuality(Map<String, Object> map);

    List<Map<String, Object>> findCheckListDetailBySN(@Param("sn") String sn, @Param("type")String type);

    Integer findQualityByCode(String code);

    Integer addCheckContent(Map<String, Object> map);

    Integer addQuality(Map<String, Object> map);

    List<Map<String, Object>> findCheckContent(Map<String, Object> map);

    Integer editCheckContent(Map<String, Object> map);

    Integer auditQuality(Map<String, Object> map);

    List<Map<String, Object>> findQualityAll(Map<String, Object> map);

    List<Map<String, Object>> findDisposeQuality(Map<String, Object> map);
}
