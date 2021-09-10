package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSurveyDetails;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSurveyDetailsService
 * @Description ${Description}
 */

public interface CSrmSurveyDetailsService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSurveyDetails record);

    int insertOrUpdate(CSrmSurveyDetails record);

    int insertOrUpdateSelective(CSrmSurveyDetails record);

    int insertSelective(CSrmSurveyDetails record);

    CSrmSurveyDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmSurveyDetails record);

    int updateByPrimaryKey(CSrmSurveyDetails record);

    int updateBatch(List<CSrmSurveyDetails> list);

    int updateBatchSelective(List<CSrmSurveyDetails> list);

    int batchInsert(List<CSrmSurveyDetails> list);

}

