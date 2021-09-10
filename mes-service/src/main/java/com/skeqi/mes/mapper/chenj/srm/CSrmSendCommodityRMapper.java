package com.skeqi.mes.mapper.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSendCommodityRReq;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmSendCommodityRMapper
 * @Description ${Description}
 */

public interface CSrmSendCommodityRMapper {
    int insertSelective(CSrmSendCommodityR record);

    CSrmSendCommodityR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmSendCommodityR record);

    int updateBatchSelective(List<CSrmSendCommodityR> list);

    int updateBatchSelectiveKThree(List<CSrmSendCommodityR> list);

    int insertOrUpdate(CSrmSendCommodityR record);

    int insertOrUpdateSelective(CSrmSendCommodityR record);

    List<CSrmSendCommodityRReq> selectByPrimaryList(CSrmSendCommodityR record);

    CSrmSendCommodityR selectFinallyData(String deliveryNumber);


}
