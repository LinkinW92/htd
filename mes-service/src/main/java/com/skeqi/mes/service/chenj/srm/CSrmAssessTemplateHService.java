package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmAssessTemplateH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmAssessTemplateHReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessTemplateHService
 * @Description ${Description}
 */

public interface CSrmAssessTemplateHService {


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

    int batchInsert(List<CSrmAssessTemplateH> list);

    Rjson createEvaluationTemplate(CSrmAssessTemplateHReq cSrmAssessTemplateHReq);

    Rjson updateEvaluationTemplate(CSrmAssessTemplateHReq cSrmAssessTemplateHReq);


    /**
     * 评估模板定义查询
     * @param cSrmAssessTemplateHReq
     * @return
     */
    Rjson findEvaluationTemplate(CSrmAssessTemplateHReq cSrmAssessTemplateHReq);



}

