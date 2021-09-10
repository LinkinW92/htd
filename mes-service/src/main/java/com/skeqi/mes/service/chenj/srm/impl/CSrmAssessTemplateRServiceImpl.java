package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmAssessTemplateRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmAssessTemplateR;
import com.skeqi.mes.service.chenj.srm.CSrmAssessTemplateRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessTemplateRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmAssessTemplateRServiceImpl implements CSrmAssessTemplateRService {

    @Resource
    private CSrmAssessTemplateRMapper cSrmAssessTemplateRMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmAssessTemplateRMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmAssessTemplateR record) {
        return cSrmAssessTemplateRMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmAssessTemplateR record) {
        return cSrmAssessTemplateRMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmAssessTemplateR record) {
        return cSrmAssessTemplateRMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmAssessTemplateR record) {
        return cSrmAssessTemplateRMapper.insertSelective(record);
    }

    @Override
    public CSrmAssessTemplateR selectByPrimaryKey(CSrmAssessTemplateR record) {
        return cSrmAssessTemplateRMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmAssessTemplateR record) {
        return cSrmAssessTemplateRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmAssessTemplateR record) {
        return cSrmAssessTemplateRMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmAssessTemplateR> list) {
        return cSrmAssessTemplateRMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmAssessTemplateR> list) {
        return cSrmAssessTemplateRMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmAssessTemplateR> list) {
        return cSrmAssessTemplateRMapper.batchInsert(list);
    }

}


