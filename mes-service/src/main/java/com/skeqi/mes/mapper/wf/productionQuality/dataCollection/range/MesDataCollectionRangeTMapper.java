package com.skeqi.mes.mapper.wf.productionQuality.dataCollection.range;

import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.range.MesDataCollectionRangeT;

import java.util.List;

public interface MesDataCollectionRangeTMapper {
    int deleteByPrimaryKey(String number);

    int insertSelective(MesDataCollectionRangeT record);

    MesDataCollectionRangeT selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(MesDataCollectionRangeT record);

    List<MesDataCollectionRangeT> selectAll(MesDataCollectionRangeT rangeT);
}
