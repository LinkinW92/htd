package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSurvey;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSurveyReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSurveyService
 * @Description ${Description}
 */

public interface CSrmSurveyService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSurvey record);

    int insertOrUpdate(CSrmSurvey record);

    int insertOrUpdateSelective(CSrmSurvey record);

    int insertSelective(CSrmSurvey record);

    CSrmSurvey selectSurveyData(CSrmSurvey record);

    int updateByPrimaryKeySelective(CSrmSurvey record);

    int updateByPrimaryKey(CSrmSurvey record);

    int updateBatch(List<CSrmSurvey> list);

    int updateBatchSelective(List<CSrmSurvey> list);

    int batchInsert(List<CSrmSurvey> list);

    Rjson createSurvey(CSrmSurveyReq cSrmSurveyReq);

    Rjson examineSurvey(CSrmSurveyReq cSrmSurveyReq);


    /**
     * 查询调查表信息
     * @param req
     * @return
     */
    Rjson findCSrmSurveyData(CSrmSurveyReq req);

}


