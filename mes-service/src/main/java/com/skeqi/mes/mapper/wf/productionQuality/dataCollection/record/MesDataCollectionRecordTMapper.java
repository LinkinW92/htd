package com.skeqi.mes.mapper.wf.productionQuality.dataCollection.record;

import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record.MesDataCollectionRecordT;

import java.util.List;
import java.util.Map;

public interface MesDataCollectionRecordTMapper {

    int insertSelective(MesDataCollectionRecordT record);

    MesDataCollectionRecordT selectByPrimaryKey(String number);

    List<MesDataCollectionRecordT> selectAll(MesDataCollectionRecordT recordT);

	List<MesDataCollectionRecordT> findRecordList(Map<String, Object> map);
}
