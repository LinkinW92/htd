package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmSurveyDetailsMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmSurveyDetails;
import com.skeqi.mes.service.chenj.srm.CSrmSurveyDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSurveyDetailsServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmSurveyDetailsServiceImpl implements CSrmSurveyDetailsService {

    @Resource
    private CSrmSurveyDetailsMapper cSrmSurveyDetailsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmSurveyDetailsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmSurveyDetails record) {
        return cSrmSurveyDetailsMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmSurveyDetails record) {
        return cSrmSurveyDetailsMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmSurveyDetails record) {
        return cSrmSurveyDetailsMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmSurveyDetails record) {
        return cSrmSurveyDetailsMapper.insertSelective(record);
    }

    @Override
    public CSrmSurveyDetails selectByPrimaryKey(Integer id) {
        return cSrmSurveyDetailsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmSurveyDetails record) {
        return cSrmSurveyDetailsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmSurveyDetails record) {
        return cSrmSurveyDetailsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmSurveyDetails> list) {
        return cSrmSurveyDetailsMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmSurveyDetails> list) {
        return cSrmSurveyDetailsMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmSurveyDetails> list) {
        return cSrmSurveyDetailsMapper.batchInsert(list);
    }

}

