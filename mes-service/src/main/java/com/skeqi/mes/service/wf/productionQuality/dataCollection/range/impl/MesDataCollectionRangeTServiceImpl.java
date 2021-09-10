package com.skeqi.mes.service.wf.productionQuality.dataCollection.range.impl;

import com.skeqi.mes.mapper.wf.productionQuality.dataCollection.range.MesDataCollectionRangeTMapper;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.range.MesDataCollectionRangeT;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.range.MesDataCollectionRangeTService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MesDataCollectionRangeTServiceImpl implements MesDataCollectionRangeTService{

    @Resource
    private MesDataCollectionRangeTMapper mesDataCollectionRangeTMapper;

    @Override
    public int deleteByPrimaryKey(String number) {
        return mesDataCollectionRangeTMapper.deleteByPrimaryKey(number);
    }

    @Override
    public int insertSelective(MesDataCollectionRangeT record) {
        return mesDataCollectionRangeTMapper.insertSelective(record);
    }

    @Override
    public MesDataCollectionRangeT selectByPrimaryKey(String number) {
        return mesDataCollectionRangeTMapper.selectByPrimaryKey(number);
    }

    @Override
    public int updateByPrimaryKeySelective(MesDataCollectionRangeT record) {
        return mesDataCollectionRangeTMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MesDataCollectionRangeT> selectAll(MesDataCollectionRangeT rangeT) {
        return mesDataCollectionRangeTMapper.selectAll(rangeT);
    }

}
