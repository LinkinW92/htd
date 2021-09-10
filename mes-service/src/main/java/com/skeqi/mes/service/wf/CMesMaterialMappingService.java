package com.skeqi.mes.service.wf;

import com.skeqi.mes.pojo.wf.CMesMaterialMapping;
import com.skeqi.mes.util.Rjson;

import java.util.List;
import java.util.Map;

public interface CMesMaterialMappingService{

    List<CMesMaterialMapping> findMaterialMapping(String supplierName);

    Rjson addMapping(CMesMaterialMapping mapping) throws Exception;

    List<CMesMaterialMapping> selectMapping(CMesMaterialMapping mapping);

    Rjson addsMapping(List<Map<String, Object>> data) throws Exception;
}
