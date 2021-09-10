package com.skeqi.mes.service.wf.linesidelibrary.checkInventory.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailTMapper;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailT;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailTService;

import java.util.List;

@Service
public class LslCheckInventoryDetailedDetailTServiceImpl implements LslCheckInventoryDetailedDetailTService{

    @Resource
    private LslCheckInventoryDetailedDetailTMapper lslCheckInventoryDetailedDetailTMapper;

    @Override
    public int insertSelective(LslCheckInventoryDetailedDetailT record) {
        return lslCheckInventoryDetailedDetailTMapper.insertSelective(record);
    }

    @Override
    public LslCheckInventoryDetailedDetailT selectByPrimaryKey(String number) {
        return lslCheckInventoryDetailedDetailTMapper.selectByPrimaryKey(number);
    }

    @Override
    public List<LslCheckInventoryDetailedDetailT> selectAll(LslCheckInventoryDetailedDetailT detailedDetailT) {
        return lslCheckInventoryDetailedDetailTMapper.selectAll(detailedDetailT);
    }

    @Override
    public Integer addDetailedDetailByList(List<LslCheckInventoryDetailedDetailT> detailedDetailTList) {
        return lslCheckInventoryDetailedDetailTMapper.addDetailedDetailByList(detailedDetailTList);
    }

    @Override
    public Integer deleteDetailedDetailByCheckNumber(String number) {
        return lslCheckInventoryDetailedDetailTMapper.deleteDetailedDetailByCheckNumber(number);
    }


}
