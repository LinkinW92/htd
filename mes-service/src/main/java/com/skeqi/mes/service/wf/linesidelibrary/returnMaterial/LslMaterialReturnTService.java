package com.skeqi.mes.service.wf.linesidelibrary.returnMaterial;

import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedT;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnT;

import java.util.List;

public interface LslMaterialReturnTService{

    int insertSelective(LslMaterialReturnT record, List<LslMaterialReturnDetailedT> detailedTList) throws Exception;

    LslMaterialReturnT selectByPrimaryKey(String number);

    List<LslMaterialReturnT> selectAll(LslMaterialReturnT materialReturnT);

    Integer deleteByNumber(String number) throws Exception;
}
