package com.skeqi.mes.mapper.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.CLslMaterialRequestDetailT;

import java.util.List;

public interface CLslMaterialRequestDetailTMapper {
    List<CLslMaterialRequestDetailT> findMaterialRequestDetailByRequestId(String billNo);

    Integer insertMultiple(List<CLslMaterialRequestDetailT> requestDetailTS);
}
