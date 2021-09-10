package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmTemplateDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/9
 * @Classname CSrmTemplateDetailsMapper
 * @Description ${Description}
 */

public interface CSrmTemplateDetailsMapper {
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

    int batchInsert(@Param("list") List<CSrmTemplateDetails> list);
}
