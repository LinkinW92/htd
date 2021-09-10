package com.skeqi.mes.service.wf.productionQuality.dataCollection.params;

import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.params.MesDataCollectionParamsT;

import java.util.List;

public interface MesDataCollectionParamsTService{


    int deleteByPrimaryKey(String number);

    int insertSelective(MesDataCollectionParamsT record);

    MesDataCollectionParamsT selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(MesDataCollectionParamsT record);

    List<MesDataCollectionParamsT> selectAll(MesDataCollectionParamsT paramsT);
}
