package com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedDetailT;
import com.skeqi.mes.mapper.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedDetailTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedDetailTService;

import java.util.List;

@Service
public class LslMaterialReturnDetailedDetailTServiceImpl implements LslMaterialReturnDetailedDetailTService{

    @Resource
    private LslMaterialReturnDetailedDetailTMapper lslMaterialReturnDetailedDetailTMapper;

    @Override
    public int insertSelective(LslMaterialReturnDetailedDetailT record) {
        return lslMaterialReturnDetailedDetailTMapper.insertSelective(record);
    }

    @Override
    public LslMaterialReturnDetailedDetailT selectByPrimaryKey(String number) {
        return lslMaterialReturnDetailedDetailTMapper.selectByPrimaryKey(number);
    }

    @Override
    public List<LslMaterialReturnDetailedDetailT> selectAll(LslMaterialReturnDetailedDetailT detailedDetailT) {
        return lslMaterialReturnDetailedDetailTMapper.selectAll(detailedDetailT);
    }

    @Override
    public Integer addDetailedDetailByList(List<LslMaterialReturnDetailedDetailT> detailedDetailTList) {
        return lslMaterialReturnDetailedDetailTMapper.addDetailedDetailByList(detailedDetailTList);
    }

    @Override
    public Integer deleteDetailedDetailByReturnNumber(String number) {
        return lslMaterialReturnDetailedDetailTMapper.deleteDetailedDetailByReturnNumber(number);
    }
}
