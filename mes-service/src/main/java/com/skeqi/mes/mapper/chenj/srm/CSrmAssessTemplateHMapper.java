package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmAssessTemplateH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmAssessTemplateHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmAssessTemplateHRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessTemplateHMapper
 * @Description ${Description}
 */

public interface CSrmAssessTemplateHMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmAssessTemplateH record);

    int insertOrUpdate(CSrmAssessTemplateH record);

    int insertOrUpdateSelective(CSrmAssessTemplateH record);

    int insertSelective(CSrmAssessTemplateH record);

    CSrmAssessTemplateH selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmAssessTemplateH record);

    int updateByPrimaryKey(CSrmAssessTemplateH record);

    int updateBatch(List<CSrmAssessTemplateH> list);

    int updateBatchSelective(List<CSrmAssessTemplateH> list);

    int batchInsert(@Param("list") List<CSrmAssessTemplateH> list);

    CSrmAssessTemplateH selectFinallyData();

    /**
     * 评估模板定义
     * @return
     */
    List<CSrmAssessTemplateHRsp> findEvaluationTemplate(CSrmAssessTemplateHReq req);
}
