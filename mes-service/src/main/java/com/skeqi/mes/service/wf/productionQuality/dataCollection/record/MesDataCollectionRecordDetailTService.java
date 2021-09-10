package com.skeqi.mes.service.wf.productionQuality.dataCollection.record;

import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record.MesDataCollectionRecordDetailT;

import java.util.List;

public interface MesDataCollectionRecordDetailTService{

    Integer addRecordDetailByList(List<MesDataCollectionRecordDetailT> detailList);
}
