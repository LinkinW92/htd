package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmAssessTemplateR;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/9
 * @Classname CSrmAssessTemplateRMapper
 * @Description ${Description}
 */

public interface CSrmAssessTemplateRMapper {
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

    int batchInsert(@Param("list") List<CSrmAssessTemplateR> list);
}
