package com.skeqi.mes.service.wf.linesidelibrary.checkInventory;

import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryT;

import java.util.List;

public interface LslCheckInventoryTService{

    int insertSelective(LslCheckInventoryT record,List<LslCheckInventoryDetailedT> detailedTList) throws Exception;

    LslCheckInventoryT selectByPrimaryKey(String number);

    List<LslCheckInventoryT> selectAll(LslCheckInventoryT checkInventoryT);

    Integer deleteByNumber(String number) throws Exception;
}
