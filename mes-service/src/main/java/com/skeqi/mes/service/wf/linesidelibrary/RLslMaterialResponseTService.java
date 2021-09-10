package com.skeqi.mes.service.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.MaterialResponseParams;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialResponseT;
import com.skeqi.mes.util.Rjson;

import java.util.List;

public interface RLslMaterialResponseTService{

    Rjson insertSelective(List<MaterialResponseParams> responseParams,String billNo,String picker) throws Exception;

    List<RLslMaterialResponseT> findMaterialResponseByRequestDetailId(Integer requestDetailId);
}
