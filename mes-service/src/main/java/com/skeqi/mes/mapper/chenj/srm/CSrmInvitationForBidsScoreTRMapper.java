package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmInvitationForBidsScoreTR;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmInvitationForBidsScoreTRMapper
 * @Description ${Description}
 */

public interface CSrmInvitationForBidsScoreTRMapper {
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

    int batchInsert(@Param("list") List<CSrmInvitationForBidsScoreTR> list);
}
