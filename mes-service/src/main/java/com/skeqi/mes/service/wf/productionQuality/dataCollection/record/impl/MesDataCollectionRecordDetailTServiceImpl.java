package com.skeqi.mes.service.wf.productionQuality.dataCollection.record.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.productionQuality.dataCollection.record.MesDataCollectionRecordDetailTMapper;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record.MesDataCollectionRecordDetailT;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.record.MesDataCollectionRecordDetailTService;

import java.util.List;

@Service
public class MesDataCollectionRecordDetailTServiceImpl implements MesDataCollectionRecordDetailTService{

    @Resource
    private MesDataCollectionRecordDetailTMapper mesDataCollectionRecordDetailTMapper;


    @Override
    public Integer addRecordDetailByList(List<MesDataCollectionRecordDetailT> detailList) {
        return mesDataCollectionRecordDetailTMapper.addRecordDetailByList(detailList);
    }
}
