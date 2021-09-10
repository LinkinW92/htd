package com.skeqi.mes.mapper.wf.productionQuality.dataCollection.group;

import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.group.MesDataCollectionGroupT;

import java.util.List;

public interface MesDataCollectionGroupTMapper {
    int deleteByPrimaryKey(String number);

    int insertSelective(MesDataCollectionGroupT record);

    MesDataCollectionGroupT selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(MesDataCollectionGroupT record);

    List<MesDataCollectionGroupT> selectAll(MesDataCollectionGroupT groupT);
}
