package com.skeqi.mes.mapper.wf.linesidelibrary.returnMaterial;

import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnT;

import java.util.List;

public interface LslMaterialReturnTMapper {
    int insertSelective(LslMaterialReturnT record);

    LslMaterialReturnT selectByPrimaryKey(String number);

    List<LslMaterialReturnT> selectAll(LslMaterialReturnT materialReturnT);

    Integer deleteByNumber(String number);
}
