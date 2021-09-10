package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmIndicatorsDimension;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmIndicatorsDimensionReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmIndicatorsDimensionService
 * @Description ${Description}
 */

public interface CSrmIndicatorsDimensionService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmIndicatorsDimension record);

    int insertOrUpdate(CSrmIndicatorsDimension record);

    int insertOrUpdateSelective(CSrmIndicatorsDimension record);

    int insertSelective(CSrmIndicatorsDimension record);

    CSrmIndicatorsDimension selectByPrimaryKey(CSrmIndicatorsDimension record);

    int updateByPrimaryKeySelective(CSrmIndicatorsDimension record);

    int updateByPrimaryKey(CSrmIndicatorsDimension record);

    int updateBatch(List<CSrmIndicatorsDimension> list);

    int updateBatchSelective(List<CSrmIndicatorsDimension> list);

    int batchInsert(List<CSrmIndicatorsDimension> list);

    Rjson createPerformanceEvaluation(CSrmIndicatorsDimensionReq cSrmIndicatorsDimensionReq);

    /**
     * 供应商绩效评估指标查询
     * @param cSrmIndicatorsDimensionReq
     * @return
     */
    Rjson findPerformanceEvaluation(CSrmIndicatorsDimensionReq cSrmIndicatorsDimensionReq);




}


