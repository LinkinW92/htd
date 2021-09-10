package com.skeqi.mes.mapper.wf.linesidelibrary.returnMaterial;

import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedDetailT;

import java.util.List;

public interface LslMaterialReturnDetailedDetailTMapper {

    int insertSelective(LslMaterialReturnDetailedDetailT record);

    LslMaterialReturnDetailedDetailT selectByPrimaryKey(String number);


    List<LslMaterialReturnDetailedDetailT> selectAll(LslMaterialReturnDetailedDetailT detailedDetailT);

    Integer addDetailedDetailByList(List<LslMaterialReturnDetailedDetailT> detailedDetailTList);

    Integer deleteDetailedDetailByReturnNumber(String number);
}
