package com.skeqi.mes.mapper.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.MaterialResponseParams;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialResponseT;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RLslMaterialResponseTMapper {
    int insertSelective(@Param("responseParams") List<RLslMaterialResponseT> responseTS);

    List<RLslMaterialResponseT> findMaterialResponseByRequestDetailId(Integer requestDetailId);
}
