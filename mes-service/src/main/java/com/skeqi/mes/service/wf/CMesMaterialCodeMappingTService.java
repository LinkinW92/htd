package com.skeqi.mes.service.wf;

import com.skeqi.mes.pojo.wf.CMesMaterialCodeMappingT;
import com.skeqi.mes.util.Rjson;

import java.util.List;
import java.util.Map;

public interface CMesMaterialCodeMappingTService{

    List<CMesMaterialCodeMappingT> findMaterialCodeMapping(String supplierName);

    List<CMesMaterialCodeMappingT> selectCodeMapping(CMesMaterialCodeMappingT mapping);

    Rjson addMaterialCodeMapping(CMesMaterialCodeMappingT mapping) throws Exception;

    Rjson addMaterialCodesMapping(List<Map<String, Object>> data) throws Exception;
}
