package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmAssessRecordH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmAssessRecordHReq;
import com.skeqi.mes.util.Rjson;

import java.text.ParseException;
import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessRecordHService
 * @Description ${Description}
 */

public interface CSrmAssessRecordHService {

    int insertSelective(CSrmAssessRecordH record);

    CSrmAssessRecordH selectByPrimaryKey(CSrmAssessRecordH record);

    int updateByPrimaryKeySelective(CSrmAssessRecordH record);


    int updateBatchSelective(List<CSrmAssessRecordH> list);

    int batchInsert(List<CSrmAssessRecordH> list);

    /**
     * 创建评估档案
     * @param cSrmAssessRecordHReq
     * @return
     * @throws ParseException
     */
    Rjson createPerformanceEvaluationRecord(CSrmAssessRecordHReq cSrmAssessRecordHReq) throws ParseException;

    Rjson updatePerformanceEvaluationRecord(CSrmAssessRecordHReq cSrmAssessRecordHReq);

    Rjson findReceivedAssess(CSrmAssessRecordHReq cSrmAssessRecordHReq);


    /**
     * 供应商绩效评估档案头行查询
     */
    Rjson findPerformanceEvaluationRecordHR(CSrmAssessRecordHReq cSrmAssessRecordHReq);

    /**
     * 供应商绩效评估档案头查询
     */
    Rjson findPerformanceEvaluationRecordH(CSrmAssessRecordHReq cSrmAssessRecordHReq);




}



