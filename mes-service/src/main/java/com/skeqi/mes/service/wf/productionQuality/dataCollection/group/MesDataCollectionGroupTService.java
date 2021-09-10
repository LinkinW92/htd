package com.skeqi.mes.service.wf.productionQuality.dataCollection.group;

import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.group.MesDataCollectionGroupT;

import java.util.List;

public interface MesDataCollectionGroupTService{


    int deleteByPrimaryKey(String number);

    int insertSelective(MesDataCollectionGroupT record);

    MesDataCollectionGroupT selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(MesDataCollectionGroupT record);

    List<MesDataCollectionGroupT> selectAll(MesDataCollectionGroupT groupT);
}
