package com.skeqi.mes.service.wf.productionQuality.dataCollection.record;

import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.params.MesDataCollectionParamsT;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record.MesDataCollectionRecordT;

import java.util.List;
import java.util.Map;

public interface MesDataCollectionRecordTService{

    int insertSelective(MesDataCollectionRecordT record,List<MesDataCollectionParamsT> paramsDataList1) throws Exception;

    MesDataCollectionRecordT selectByPrimaryKey(String number);

    List<MesDataCollectionRecordT> selectAll(MesDataCollectionRecordT paramsT);

	List<MesDataCollectionRecordT> findRecordList(Map<String, Object> map);
}
