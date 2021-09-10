package com.skeqi.mes.mapper.wf.linesidelibrary.checkInventory;

import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryT;

import java.util.List;

public interface LslCheckInventoryTMapper {
    int insertSelective(LslCheckInventoryT record);

    LslCheckInventoryT selectByPrimaryKey(String number);

    List<LslCheckInventoryT> selectAll(LslCheckInventoryT checkInventoryT);

    Integer deleteByNumber(String number);
}
