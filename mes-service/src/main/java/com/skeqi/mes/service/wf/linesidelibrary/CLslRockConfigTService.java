package com.skeqi.mes.service.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.CLslRockConfigT;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
public interface CLslRockConfigTService{

    List<CLslRockConfigT> findAllWorkplaceConfig();

    List<Map<String, Object>> findWorkOrderRecipeByLineId(CLslRockConfigT next);
}
