package com.skeqi.mes.mapper.wf.linesidelibrary.checkInventory;

import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedT;

import java.util.List;

public interface LslCheckInventoryDetailedTMapper {
    int insertSelective(LslCheckInventoryDetailedT record);

    LslCheckInventoryDetailedT selectByPrimaryKey(String number);

    List<LslCheckInventoryDetailedT> selectAll(LslCheckInventoryDetailedT detailedT);

    Integer addDetailedByList(List<LslCheckInventoryDetailedT> detailedTList);

    Integer deleteDetailedByCheckNumber(String number);
}
