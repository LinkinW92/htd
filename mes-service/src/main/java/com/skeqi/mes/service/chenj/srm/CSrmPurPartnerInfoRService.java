package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmPurPartnerInfoR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/5
 * @Classname CSrmPurPartnerInfoRService
 * @Description ${Description}
 */

public interface CSrmPurPartnerInfoRService {


    int insertSelective(CSrmPurPartnerInfoR record);

    List<CSrmPurPartnerInfoR> selectByPrimaryKey(CSrmPurPartnerInfoR record);

    int updateByPrimaryKeySelective(CSrmPurPartnerInfoR record);

    int updateBatchSelective(List<CSrmPurPartnerInfoR> list);

    int batchInsert(List<CSrmPurPartnerInfoR> list);

    int insertOrUpdate(CSrmPurPartnerInfoR record);

    int insertOrUpdateSelective(CSrmPurPartnerInfoR record);

}


