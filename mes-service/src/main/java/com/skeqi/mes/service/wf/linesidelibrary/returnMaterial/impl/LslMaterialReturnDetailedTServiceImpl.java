package com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedT;
import com.skeqi.mes.mapper.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedTService;

import java.util.List;

@Service
public class LslMaterialReturnDetailedTServiceImpl implements LslMaterialReturnDetailedTService{

    @Resource
    private LslMaterialReturnDetailedTMapper lslMaterialReturnDetailedTMapper;

    @Override
    public int insertSelective(LslMaterialReturnDetailedT record) {
        return lslMaterialReturnDetailedTMapper.insertSelective(record);
    }

    @Override
    public LslMaterialReturnDetailedT selectByPrimaryKey(String number) {
        return lslMaterialReturnDetailedTMapper.selectByPrimaryKey(number);
    }

    @Override
    public List<LslMaterialReturnDetailedT> selectAll(LslMaterialReturnDetailedT detailedT) {
        return lslMaterialReturnDetailedTMapper.selectAll(detailedT);
    }

    @Override
    public Integer addDetailedByList(List<LslMaterialReturnDetailedT> detailedTList) {
        return lslMaterialReturnDetailedTMapper.addDetailedByList(detailedTList);
    }

    @Override
    public Integer deleteDetailedByReturnNumber(String number) {
        return lslMaterialReturnDetailedTMapper.deleteDetailedByReturnNumber(number);
    }

}
