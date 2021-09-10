package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseOrderR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseOrderRService
 * @Description ${Description}
 */

public interface CSrmPurchaseOrderRService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmPurchaseOrderR record);

    int insertOrUpdate(CSrmPurchaseOrderR record);

    int insertOrUpdateSelective(CSrmPurchaseOrderR record);

    int insertSelective(CSrmPurchaseOrderR record);

    CSrmPurchaseOrderR selectByPrimaryKey(CSrmPurchaseOrderR record);

    int updateByPrimaryKeySelective(CSrmPurchaseOrderR record);

    int updateByPrimaryKey(CSrmPurchaseOrderR record);

    int updateBatch(List<CSrmPurchaseOrderR> list);

    int updateBatchSelective(List<CSrmPurchaseOrderR> list);

    int batchInsert(List<CSrmPurchaseOrderR> list);

}

