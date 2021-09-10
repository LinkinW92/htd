package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmTemplate;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTemplateReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmTemplateRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/9
 * @Classname CSrmTemplateMapper
 * @Description ${Description}
 */

public interface CSrmTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmTemplate record);

    int insertOrUpdate(CSrmTemplate record);

    int insertOrUpdateSelective(CSrmTemplate record);

    int insertSelective(CSrmTemplate record);

    CSrmTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmTemplate record);

    int updateByPrimaryKey(CSrmTemplate record);

    int updateBatch(List<CSrmTemplate> list);

    int updateBatchSelective(List<CSrmTemplate> list);

    int batchInsert(@Param("list") List<CSrmTemplate> list);

    CSrmTemplate selectTemplateName(String templateName);

    CSrmTemplate selectFinallyData();

    /**
     * 调查表模板查询
     * @return
     */
    List<CSrmTemplateRsp> findTemplate(CSrmTemplateReq req);
}
