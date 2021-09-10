package com.skeqi.mes.mapper.wf.productionQuality.dataCollection.record;

import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record.MesDataCollectionRecordDetailT;

import java.util.List;

public interface MesDataCollectionRecordDetailTMapper {
    Integer addRecordDetailByList(List<MesDataCollectionRecordDetailT> detailList);
}
