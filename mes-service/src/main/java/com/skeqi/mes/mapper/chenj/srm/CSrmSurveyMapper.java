package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmSurvey;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSurveyReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSurveyRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/9
 * @Classname CSrmSurveyMapper
 * @Description ${Description}
 */

public interface CSrmSurveyMapper {
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

    int batchInsert(@Param("list") List<CSrmSurvey> list);

    CSrmSurvey selectFinallyData();

    /**
     * 查询调查表信息
     * @param req
     * @return
     */
    List<CSrmSurveyRsp> findCSrmSurveyData(CSrmSurveyReq req);

}
