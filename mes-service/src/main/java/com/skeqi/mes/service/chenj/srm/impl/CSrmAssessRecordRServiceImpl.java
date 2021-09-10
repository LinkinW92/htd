package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmAssessRecordRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmAssessRecordR;
import com.skeqi.mes.service.chenj.srm.CSrmAssessRecordRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessRecordRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmAssessRecordRServiceImpl implements CSrmAssessRecordRService {

    @Resource
    private CSrmAssessRecordRMapper cSrmAssessRecordRMapper;


    @Override
    public int insertSelective(CSrmAssessRecordR record) {
        return cSrmAssessRecordRMapper.insertSelective(record);
    }

    @Override
    public CSrmAssessRecordR selectByPrimaryKey(CSrmAssessRecordR record) {
        return cSrmAssessRecordRMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmAssessRecordR record) {
        return cSrmAssessRecordRMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int updateBatchSelective(List<CSrmAssessRecordR> list) {
        return cSrmAssessRecordRMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmAssessRecordR> list) {
        return cSrmAssessRecordRMapper.batchInsert(list);
    }

}



