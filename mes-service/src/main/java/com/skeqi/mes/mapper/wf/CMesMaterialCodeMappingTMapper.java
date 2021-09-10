package com.skeqi.mes.mapper.wf;

import com.skeqi.mes.pojo.wf.CMesMaterialCodeMappingT;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CMesMaterialCodeMappingTMapper {

    List<CMesMaterialCodeMappingT> findMaterialCodeMapping(@Param("supplierName") String supplierName);

    List<CMesMaterialCodeMappingT> selectCodeMapping(CMesMaterialCodeMappingT mapping);

    Integer addMaterialCodeMapping(CMesMaterialCodeMappingT mapping);

    Integer addMaterialCodesMapping(List<Map<String, Object>> data);
}
