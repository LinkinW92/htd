package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSourcingTemplateAffiliate;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmSourcingTemplateAffiliateMapper
 * @Description ${Description}
 */

public interface CSrmSourcingTemplateAffiliateMapper {
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

    int batchInsert(@Param("list") List<CSrmSourcingTemplateAffiliate> list);
}
