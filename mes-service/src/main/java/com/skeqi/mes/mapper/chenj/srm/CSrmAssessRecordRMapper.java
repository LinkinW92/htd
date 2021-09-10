package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmAssessRecordR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmAssessRecordHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmAssessRecordRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/20
 * @Classname CSrmAssessRecordRMapper
 * @Description ${Description}
 */

public interface CSrmAssessRecordRMapper {
    int insertSelective(CSrmAssessRecordR record);

    CSrmAssessRecordR selectByPrimaryKey(CSrmAssessRecordR record);

    int updateByPrimaryKeySelective(CSrmAssessRecordR record);

    int updateBatchSelective(List<CSrmAssessRecordR> list);

    int batchInsert(@Param("list") List<CSrmAssessRecordR> list);

    CSrmAssessRecordR selectFinallyData(String fileNumber);


    /**
     * 获取行档案数据
     * @param req
     * @return
     */
    List<CSrmAssessRecordRRsp> selectByPrimaryKeyListR(CSrmAssessRecordHReq req);

}
