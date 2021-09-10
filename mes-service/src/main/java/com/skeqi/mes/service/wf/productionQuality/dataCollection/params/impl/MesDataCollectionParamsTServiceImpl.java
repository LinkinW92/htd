package com.skeqi.mes.service.wf.productionQuality.dataCollection.params.impl;

import com.skeqi.mes.mapper.wf.productionQuality.dataCollection.params.MesDataCollectionParamsTMapper;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.params.MesDataCollectionParamsT;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.params.MesDataCollectionParamsTService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MesDataCollectionParamsTServiceImpl implements MesDataCollectionParamsTService{

    @Resource
    private MesDataCollectionParamsTMapper mesDataCollectionParamsTMapper;

    @Override
    public int deleteByPrimaryKey(String number) {
        return mesDataCollectionParamsTMapper.deleteByPrimaryKey(number);
    }

    @Override
    public int insertSelective(MesDataCollectionParamsT record) {
        return mesDataCollectionParamsTMapper.insertSelective(record);
    }

    @Override
    public MesDataCollectionParamsT selectByPrimaryKey(String number) {
        return mesDataCollectionParamsTMapper.selectByPrimaryKey(number);
    }

    @Override
    public int updateByPrimaryKeySelective(MesDataCollectionParamsT record) {
        return mesDataCollectionParamsTMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MesDataCollectionParamsT> selectAll(MesDataCollectionParamsT paramsT) {
        return mesDataCollectionParamsTMapper.selectAll(paramsT);
    }

}
