package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseDemandR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandRService
 * @Description ${Description}
 */

public interface CSrmPurchaseDemandRService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmPurchaseDemandR record);

    int insertOrUpdate(CSrmPurchaseDemandR record);

    int insertOrUpdateSelective(CSrmPurchaseDemandR record);

    int insertSelective(CSrmPurchaseDemandR record);

    List<CSrmPurchaseDemandR> selectByPrimaryKey(CSrmPurchaseDemandR record);

    int updateByPrimaryKeySelective(CSrmPurchaseDemandR record);

    int updateByPrimaryKey(CSrmPurchaseDemandR record);

    int updateBatch(List<CSrmPurchaseDemandR> list);

    int updateBatchSelective(List<CSrmPurchaseDemandR> list);
}




