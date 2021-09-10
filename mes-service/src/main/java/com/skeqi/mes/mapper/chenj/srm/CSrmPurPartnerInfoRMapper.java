package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmPurPartnerInfoR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmContractHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurPartnerInfoRReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/5
 * @Classname CSrmPurPartnerInfoRMapper
 * @Description ${Description}
 */

public interface CSrmPurPartnerInfoRMapper {
    int insertSelective(CSrmPurPartnerInfoR record);

    List<CSrmPurPartnerInfoR> selectByPrimaryKey(CSrmPurPartnerInfoR record);

    int updateByPrimaryKeySelective(CSrmPurPartnerInfoR record);

    int updateBatchSelective(List<CSrmPurPartnerInfoR> list);

    int batchInsert(@Param("list") List<CSrmPurPartnerInfoR> list);

    int insertOrUpdate(CSrmPurPartnerInfoR record);

    int insertOrUpdateSelective(CSrmPurPartnerInfoR record);

    List<CSrmPurPartnerInfoRReq> selectByPrimaryList(CSrmContractHReq req);

    CSrmPurPartnerInfoR selectFinallyData(String contractNo);

    /**
     * 删除合作伙伴行数据
     * @param lineItemNoList
     * @return
     */
    int deleteBatchSelective(Integer[] lineItemNoList);
}
