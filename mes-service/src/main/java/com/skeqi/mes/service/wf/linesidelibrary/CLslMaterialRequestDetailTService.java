package com.skeqi.mes.service.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.CLslMaterialRequestDetailT;

import java.util.List;

public interface CLslMaterialRequestDetailTService{

    List<CLslMaterialRequestDetailT> findMaterialRequestDetailByRequestId(String billNo);
}
