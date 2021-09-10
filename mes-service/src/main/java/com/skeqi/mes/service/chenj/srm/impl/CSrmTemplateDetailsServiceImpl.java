package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmTemplateDetailsMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmTemplateDetails;
import com.skeqi.mes.service.chenj.srm.CSrmTemplateDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmTemplateDetailsServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmTemplateDetailsServiceImpl implements CSrmTemplateDetailsService {

    @Resource
    private CSrmTemplateDetailsMapper cSrmTemplateDetailsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmTemplateDetailsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmTemplateDetails record) {
        return cSrmTemplateDetailsMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmTemplateDetails record) {
        return cSrmTemplateDetailsMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmTemplateDetails record) {
        return cSrmTemplateDetailsMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmTemplateDetails record) {
        return cSrmTemplateDetailsMapper.insertSelective(record);
    }

    @Override
    public CSrmTemplateDetails selectByPrimaryKey(Integer id) {
        return cSrmTemplateDetailsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmTemplateDetails record) {
        return cSrmTemplateDetailsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmTemplateDetails record) {
        return cSrmTemplateDetailsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmTemplateDetails> list) {
        return cSrmTemplateDetailsMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmTemplateDetails> list) {
        return cSrmTemplateDetailsMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmTemplateDetails> list) {
        return cSrmTemplateDetailsMapper.batchInsert(list);
    }

}


