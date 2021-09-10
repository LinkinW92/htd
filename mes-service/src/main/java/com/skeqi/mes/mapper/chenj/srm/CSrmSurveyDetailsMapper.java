package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSurveyDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSurveyDetailsMapper
 * @Description ${Description}
 */

public interface CSrmSurveyDetailsMapper {
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

    int batchInsert(@Param("list") List<CSrmSurveyDetails> list);




}
