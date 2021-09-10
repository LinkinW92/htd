package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmInvitationForBidsScoreTH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvitationForBidsScoreTHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmInvitationForBidsScoreTHRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmInvitationForBidsScoreTHMapper
 * @Description ${Description}
 */

public interface CSrmInvitationForBidsScoreTHMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmInvitationForBidsScoreTH record);

    int insertOrUpdate(CSrmInvitationForBidsScoreTH record);

    int insertOrUpdateSelective(CSrmInvitationForBidsScoreTH record);

    int insertSelective(CSrmInvitationForBidsScoreTH record);

    List<CSrmInvitationForBidsScoreTH> selectByPrimaryKey(CSrmInvitationForBidsScoreTH record);

    int updateByPrimaryKeySelective(CSrmInvitationForBidsScoreTH record);

    int updateByPrimaryKey(CSrmInvitationForBidsScoreTH record);

    int updateBatch(List<CSrmInvitationForBidsScoreTH> list);

    int updateBatchSelective(List<CSrmInvitationForBidsScoreTH> list);

    int batchInsert(@Param("list") List<CSrmInvitationForBidsScoreTH> list);

    CSrmInvitationForBidsScoreTH selectFinallyData();

    /**
     * 查询招标评分模板行头表数据
     * @param req
     * @return
     */
    List<CSrmInvitationForBidsScoreTHRsp> findIndexScore(CSrmInvitationForBidsScoreTHReq req);

}
