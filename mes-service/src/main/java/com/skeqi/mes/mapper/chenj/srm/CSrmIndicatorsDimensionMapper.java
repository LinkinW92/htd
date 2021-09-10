package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmIndicatorsDimension;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmIndicatorsDimensionReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmIndicatorsDimensionRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/9
 * @Classname CSrmIndicatorsDimensionMapper
 * @Description ${Description}
 */

public interface CSrmIndicatorsDimensionMapper {
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

    int batchInsert(@Param("list") List<CSrmIndicatorsDimension> list);

    /**
     * 评估指标定义查询
     * @param req
     * @return
     */
    List<CSrmIndicatorsDimensionRsp> findPerformanceEvaluation(CSrmIndicatorsDimensionReq req);


}
