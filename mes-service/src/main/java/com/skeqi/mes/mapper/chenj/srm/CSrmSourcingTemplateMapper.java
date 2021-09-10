package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSourcingTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmSourcingTemplateMapper
 * @Description ${Description}
 */

public interface CSrmSourcingTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSourcingTemplate record);

    int insertOrUpdate(CSrmSourcingTemplate record);

    int insertOrUpdateSelective(CSrmSourcingTemplate record);

    int insertSelective(CSrmSourcingTemplate record);

    List<CSrmSourcingTemplate> selectByPrimaryKey(CSrmSourcingTemplate cSrmSourcingTemplate);

    int updateByPrimaryKeySelective(CSrmSourcingTemplate record);

    int updateByPrimaryKey(CSrmSourcingTemplate record);

    int updateBatch(List<CSrmSourcingTemplate> list);

    int updateBatchSelective(List<CSrmSourcingTemplate> list);

    int batchInsert(@Param("list") List<CSrmSourcingTemplate> list);
    CSrmSourcingTemplate selectFinallyData();
}
