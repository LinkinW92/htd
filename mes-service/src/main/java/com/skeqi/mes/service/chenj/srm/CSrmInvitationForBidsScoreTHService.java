package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmInvitationForBidsScoreTH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvitationForBidsScoreTHReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmInvitationForBidsScoreTHService
 * @Description ${Description}
 */

public interface CSrmInvitationForBidsScoreTHService {


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

    int batchInsert(List<CSrmInvitationForBidsScoreTH> list);

    Rjson createIndexScore(CSrmInvitationForBidsScoreTHReq cSrmInvitationForBidsScoreTHReq);

    /**
     * 查询招标评分模板行头表数据
     * @param cSrmInvitationForBidsScoreTHReq
     * @return
     */
   Rjson findIndexScore(CSrmInvitationForBidsScoreTHReq cSrmInvitationForBidsScoreTHReq);

}


