package com.skeqi.mes.mapper.wf.productionQuality.dataCollection.params;

import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.params.MesDataCollectionParamsT;

import java.util.List;

public interface MesDataCollectionParamsTMapper {
    int deleteByPrimaryKey(String number);

    int insertSelective(MesDataCollectionParamsT record);

    MesDataCollectionParamsT selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(MesDataCollectionParamsT record);

    List<MesDataCollectionParamsT> selectAll(MesDataCollectionParamsT paramsT);
}
