package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmTemplateDetails;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmTemplateDetailsService
 * @Description ${Description}
 */

public interface CSrmTemplateDetailsService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmTemplateDetails record);

    int insertOrUpdate(CSrmTemplateDetails record);

    int insertOrUpdateSelective(CSrmTemplateDetails record);

    int insertSelective(CSrmTemplateDetails record);

    CSrmTemplateDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmTemplateDetails record);

    int updateByPrimaryKey(CSrmTemplateDetails record);

    int updateBatch(List<CSrmTemplateDetails> list);

    int updateBatchSelective(List<CSrmTemplateDetails> list);

    int batchInsert(List<CSrmTemplateDetails> list);

}


