package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSourcingTemplateAffiliate;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSourcingTemplateAffiliateService
 * @Description ${Description}
 */

public interface CSrmSourcingTemplateAffiliateService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSourcingTemplateAffiliate record);

    int insertOrUpdate(CSrmSourcingTemplateAffiliate record);

    int insertOrUpdateSelective(CSrmSourcingTemplateAffiliate record);

    int insertSelective(CSrmSourcingTemplateAffiliate record);

    CSrmSourcingTemplateAffiliate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmSourcingTemplateAffiliate record);

    int updateByPrimaryKey(CSrmSourcingTemplateAffiliate record);

    int updateBatch(List<CSrmSourcingTemplateAffiliate> list);

    int updateBatchSelective(List<CSrmSourcingTemplateAffiliate> list);

    int batchInsert(List<CSrmSourcingTemplateAffiliate> list);

}


