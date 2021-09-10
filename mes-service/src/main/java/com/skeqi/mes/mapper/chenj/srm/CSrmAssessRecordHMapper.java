package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmAssessRecordH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmAssessRecordHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmAssessRecordHRRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmAssessRecordHRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/20
 * @Classname CSrmAssessRecordHMapper
 * @Description ${Description}
 */

public interface CSrmAssessRecordHMapper {
    int insertSelective(CSrmAssessRecordH record);

    CSrmAssessRecordH selectByPrimaryKey(CSrmAssessRecordH record);

    int updateByPrimaryKeySelective(CSrmAssessRecordH record);

    int updateBatchSelective(List<CSrmAssessRecordH> list);

    int batchInsert(@Param("list") List<CSrmAssessRecordH> list);

    CSrmAssessRecordH selectFinallyData();

    List<CSrmAssessRecordHRsp> findReceivedAssess(CSrmAssessRecordHReq req);

    /**
     * 已收评估结果查询 /获取头档案数据
     * @param req
     * @return
     */
    List<CSrmAssessRecordHRRsp> selectByPrimaryKeyListH(CSrmAssessRecordHReq req);
}
