package com.skeqi.mes.service.wf.linesidelibrary.impl;

import com.skeqi.mes.pojo.wf.linesidelibrary.LslMaterialInventoryT;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.linesidelibrary.CLslMaterialInventoryTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.CLslMaterialInventoryTService;

import java.util.List;
import java.util.Map;

@Service
public class CLslMaterialInventoryTServiceImpl implements CLslMaterialInventoryTService{

    @Resource
    private CLslMaterialInventoryTMapper cLslMaterialInventoryTMapper;

    @Override
    public List<Map<String, Object>> findAll(List<Map<String, Object>> data) {
        return cLslMaterialInventoryTMapper.findAll(data);
    }

    @Override
    public Integer postingAccountByList(List<Map<String,Object>> inventoryTList) {
        return cLslMaterialInventoryTMapper.postingAccountByList(inventoryTList);
    }

    @Override
    public Integer frozenInventoryByList(List<Map<String, Object>> inventoryTList) {
        return cLslMaterialInventoryTMapper.frozenInventoryByList(inventoryTList);
    }

    @Override
    public LslMaterialInventoryT selectByMaterialCode(String sn) {
        return cLslMaterialInventoryTMapper.selectByMaterialCode(sn);
    }

}
