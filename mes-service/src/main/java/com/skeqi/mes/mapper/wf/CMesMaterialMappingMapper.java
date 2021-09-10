package com.skeqi.mes.mapper.wf;

import com.skeqi.mes.pojo.wf.CMesMaterialMapping;
import com.skeqi.mes.util.Rjson;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CMesMaterialMappingMapper {

    List<CMesMaterialMapping> findMaterialBatchMapping(@Param("supplierName") String supplierName);

    List<CMesMaterialMapping> selectBatchMapping(CMesMaterialMapping mapping);

    Integer addBatchMapping(CMesMaterialMapping mapping);

    Integer addBatchsMapping(List<Map<String, Object>> data);
}
