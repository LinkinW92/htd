package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmAssessTemplateR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessTemplateRService
 * @Description ${Description}
 */

public interface CSrmAssessTemplateRService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmAssessTemplateR record);

    int insertOrUpdate(CSrmAssessTemplateR record);

    int insertOrUpdateSelective(CSrmAssessTemplateR record);

    int insertSelective(CSrmAssessTemplateR record);

    CSrmAssessTemplateR selectByPrimaryKey(CSrmAssessTemplateR record);

    int updateByPrimaryKeySelective(CSrmAssessTemplateR record);

    int updateByPrimaryKey(CSrmAssessTemplateR record);

    int updateBatch(List<CSrmAssessTemplateR> list);

    int updateBatchSelective(List<CSrmAssessTemplateR> list);

    int batchInsert(List<CSrmAssessTemplateR> list);

}


