package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSendCommodityRService
 * @Description ${Description}
 */

public interface CSrmSendCommodityRService {


    int insertOrUpdate(CSrmSendCommodityR record);

    int insertOrUpdateSelective(CSrmSendCommodityR record);

    int insertSelective(CSrmSendCommodityR record);

    CSrmSendCommodityR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmSendCommodityR record);

    int updateBatchSelective(List<CSrmSendCommodityR> list);


}



