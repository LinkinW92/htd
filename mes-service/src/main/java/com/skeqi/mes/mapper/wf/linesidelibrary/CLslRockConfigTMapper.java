package com.skeqi.mes.mapper.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.CLslRockConfigT;

import java.util.List;
import java.util.Map;

public interface CLslRockConfigTMapper {
    List<CLslRockConfigT> findAllWorkplaceConfig();

    List<Map<String, Object>> findWorkOrderRecipeByLineId(CLslRockConfigT next);
}
