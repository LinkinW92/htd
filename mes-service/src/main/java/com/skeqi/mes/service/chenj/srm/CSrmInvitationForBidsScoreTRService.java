package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmInvitationForBidsScoreTR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmInvitationForBidsScoreTRService
 * @Description ${Description}
 */

public interface CSrmInvitationForBidsScoreTRService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmInvitationForBidsScoreTR record);

    int insertOrUpdate(CSrmInvitationForBidsScoreTR record);

    int insertOrUpdateSelective(CSrmInvitationForBidsScoreTR record);

    int insertSelective(CSrmInvitationForBidsScoreTR record);

    CSrmInvitationForBidsScoreTR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmInvitationForBidsScoreTR record);

    int updateByPrimaryKey(CSrmInvitationForBidsScoreTR record);

    int updateBatch(List<CSrmInvitationForBidsScoreTR> list);

    int updateBatchSelective(List<CSrmInvitationForBidsScoreTR> list);

    int batchInsert(List<CSrmInvitationForBidsScoreTR> list);

}


