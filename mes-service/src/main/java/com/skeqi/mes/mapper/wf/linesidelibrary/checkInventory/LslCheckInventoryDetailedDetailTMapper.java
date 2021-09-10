package com.skeqi.mes.mapper.wf.linesidelibrary.checkInventory;

import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailT;

import java.util.List;

public interface LslCheckInventoryDetailedDetailTMapper {
    int insertSelective(LslCheckInventoryDetailedDetailT record);

    LslCheckInventoryDetailedDetailT selectByPrimaryKey(String number);

    List<LslCheckInventoryDetailedDetailT> selectAll(LslCheckInventoryDetailedDetailT detailedDetailT);

    Integer addDetailedDetailByList(List<LslCheckInventoryDetailedDetailT> detailedDetailTList);

    Integer deleteDetailedDetailByCheckNumber(String number);
}
