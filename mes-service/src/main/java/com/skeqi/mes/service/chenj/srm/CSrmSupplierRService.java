package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierRReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSupplierRService
 * @Description ${Description}
 */

public interface CSrmSupplierRService {


    int insertSelective(CSrmSupplierR record);

    CSrmSupplierR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmSupplierR record);


    int updateBatchSelective(List<CSrmSupplierR> list);

    int batchInsert(List<CSrmSupplierR> list);

    /**
     * 供应商升降级申请单审批&评分
     */
    Rjson supplierExamineAndScore(CSrmSupplierRReq cSrmSupplierRReq);

}


