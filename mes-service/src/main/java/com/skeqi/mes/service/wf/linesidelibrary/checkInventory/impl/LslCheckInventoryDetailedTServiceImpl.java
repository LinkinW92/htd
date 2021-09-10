package com.skeqi.mes.service.wf.linesidelibrary.checkInventory.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedTMapper;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedT;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedTService;

import java.util.List;

@Service
public class LslCheckInventoryDetailedTServiceImpl implements LslCheckInventoryDetailedTService{

    @Resource
    private LslCheckInventoryDetailedTMapper lslCheckInventoryDetailedTMapper;

    @Override
    public int insertSelective(LslCheckInventoryDetailedT record) {
        return lslCheckInventoryDetailedTMapper.insertSelective(record);
    }

    @Override
    public LslCheckInventoryDetailedT selectByPrimaryKey(String number) {
        return lslCheckInventoryDetailedTMapper.selectByPrimaryKey(number);
    }

    @Override
    public List<LslCheckInventoryDetailedT> selectAll(LslCheckInventoryDetailedT detailedT) {
        return lslCheckInventoryDetailedTMapper.selectAll(detailedT);
    }

    @Override
    public Integer addDetailedByList(List<LslCheckInventoryDetailedT> detailedTList) {
        return lslCheckInventoryDetailedTMapper.addDetailedByList(detailedTList);
    }

    @Override
    public Integer deleteDetailedByCheckNumber(String number) {
        return lslCheckInventoryDetailedTMapper.deleteDetailedByCheckNumber(number);
    }


}
