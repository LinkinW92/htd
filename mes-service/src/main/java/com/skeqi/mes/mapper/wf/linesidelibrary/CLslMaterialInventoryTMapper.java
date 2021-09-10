package com.skeqi.mes.mapper.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.LslMaterialInventoryT;

import java.util.List;
import java.util.Map;

public interface CLslMaterialInventoryTMapper {
    List<Map<String, Object>> findAll(List<Map<String, Object>> data);

    /**
     * 盘点 过账
     * @param inventoryTList
     * @return
     */
    Integer postingAccountByList(List<Map<String,Object>> inventoryTList);

    /**
     * 退料 冻结
     * @param inventoryTList
     * @return
     */
    Integer frozenInventoryByList(List<Map<String, Object>> inventoryTList);

    LslMaterialInventoryT selectByMaterialCode(String sn);
}
