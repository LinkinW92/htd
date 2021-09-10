package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmKThreePurchaseAbuttingReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmKThreePurchaseAbuttingService
 * @Description ${Description}
 */

public interface CSrmKThreePurchaseAbuttingService {


    int insertSelective(CSrmKThreePurchaseAbutting record);

    /**
     * 添加K3采购对接日志
     * @param cSrmKThreePurchaseAbuttingReq
     * @return
     */
    Rjson addAlterRecord(CSrmKThreePurchaseAbuttingReq cSrmKThreePurchaseAbuttingReq);

    /**
     * 获取K3采购对接日志表
     */
    Rjson findAlterRecord(CSrmKThreePurchaseAbuttingReq req);


    /**
     * 根据单据类型查询采购对接日志数据
     * @param doType
     * @return
     */
    List<CSrmKThreePurchaseAbutting> findAlterRecordList(String doType);


    CSrmKThreePurchaseAbutting selectByPrimaryKey(CSrmKThreePurchaseAbutting cSrmKThreePurchaseAbutting);

    int updateBatchSelective(List<CSrmKThreePurchaseAbutting> list);

    int updateByPrimaryKeySelective(CSrmKThreePurchaseAbutting record);
}

