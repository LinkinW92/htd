package com.skeqi.mes.service.wf.linesidelibrary.returnMaterial;

import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedT;

import java.util.List;

public interface LslMaterialReturnDetailedTService{
    int insertSelective(LslMaterialReturnDetailedT record);

    LslMaterialReturnDetailedT selectByPrimaryKey(String number);

    List<LslMaterialReturnDetailedT> selectAll(LslMaterialReturnDetailedT detailedT);

    Integer addDetailedByList(List<LslMaterialReturnDetailedT> detailedTList);

    Integer deleteDetailedByReturnNumber(String number);
}
