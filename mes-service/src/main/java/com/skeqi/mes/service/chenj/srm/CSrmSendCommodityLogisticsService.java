package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityLogistics;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSendCommodityLogisticsService
 * @Description ${Description}
 */

public interface CSrmSendCommodityLogisticsService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSendCommodityLogistics record);

    int insertOrUpdate(CSrmSendCommodityLogistics record);

    int insertOrUpdateSelective(CSrmSendCommodityLogistics record);

    int insertSelective(CSrmSendCommodityLogistics record);

    CSrmSendCommodityLogistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmSendCommodityLogistics record);

    int updateByPrimaryKey(CSrmSendCommodityLogistics record);

    int updateBatch(List<CSrmSendCommodityLogistics> list);

    int updateBatchSelective(List<CSrmSendCommodityLogistics> list);

    int batchInsert(List<CSrmSendCommodityLogistics> list);

}

