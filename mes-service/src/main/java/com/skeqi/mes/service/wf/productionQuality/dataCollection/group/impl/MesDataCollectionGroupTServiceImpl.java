package com.skeqi.mes.service.wf.productionQuality.dataCollection.group.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.group.MesDataCollectionGroupT;
import com.skeqi.mes.mapper.wf.productionQuality.dataCollection.group.MesDataCollectionGroupTMapper;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.group.MesDataCollectionGroupTService;

import java.util.List;

@Service
public class MesDataCollectionGroupTServiceImpl implements MesDataCollectionGroupTService{

    @Resource
    private MesDataCollectionGroupTMapper mesDataCollectionGroupTMapper;

    @Override
    public int deleteByPrimaryKey(String number) {
        return mesDataCollectionGroupTMapper.deleteByPrimaryKey(number);
    }

    @Override
    public int insertSelective(MesDataCollectionGroupT record) {
        return mesDataCollectionGroupTMapper.insertSelective(record);
    }

    @Override
    public MesDataCollectionGroupT selectByPrimaryKey(String number) {
        return mesDataCollectionGroupTMapper.selectByPrimaryKey(number);
    }

    @Override
    public int updateByPrimaryKeySelective(MesDataCollectionGroupT record) {
        return mesDataCollectionGroupTMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MesDataCollectionGroupT> selectAll(MesDataCollectionGroupT groupT) {
        return mesDataCollectionGroupTMapper.selectAll(groupT);
    }

}
