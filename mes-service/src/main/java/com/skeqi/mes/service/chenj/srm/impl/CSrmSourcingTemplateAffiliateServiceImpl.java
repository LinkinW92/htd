package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmSourcingTemplateAffiliateMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmSourcingTemplateAffiliate;
import com.skeqi.mes.service.chenj.srm.CSrmSourcingTemplateAffiliateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSourcingTemplateAffiliateServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmSourcingTemplateAffiliateServiceImpl implements CSrmSourcingTemplateAffiliateService {

    @Resource
    private CSrmSourcingTemplateAffiliateMapper cSrmSourcingTemplateAffiliateMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmSourcingTemplateAffiliateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmSourcingTemplateAffiliate record) {
        return cSrmSourcingTemplateAffiliateMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmSourcingTemplateAffiliate record) {
        return cSrmSourcingTemplateAffiliateMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmSourcingTemplateAffiliate record) {
        return cSrmSourcingTemplateAffiliateMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmSourcingTemplateAffiliate record) {
        return cSrmSourcingTemplateAffiliateMapper.insertSelective(record);
    }

    @Override
    public CSrmSourcingTemplateAffiliate selectByPrimaryKey(Integer id) {
        return cSrmSourcingTemplateAffiliateMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmSourcingTemplateAffiliate record) {
        return cSrmSourcingTemplateAffiliateMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmSourcingTemplateAffiliate record) {
        return cSrmSourcingTemplateAffiliateMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmSourcingTemplateAffiliate> list) {
        return cSrmSourcingTemplateAffiliateMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmSourcingTemplateAffiliate> list) {
        return cSrmSourcingTemplateAffiliateMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmSourcingTemplateAffiliate> list) {
        return cSrmSourcingTemplateAffiliateMapper.batchInsert(list);
    }

}


