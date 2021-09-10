package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseOrderH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseOrderHFindReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseOrderHReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseOrderHService
 * @Description ${Description}
 */

public interface CSrmPurchaseOrderHService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmPurchaseOrderH record);

    int insertOrUpdate(CSrmPurchaseOrderH record);

    int insertOrUpdateSelective(CSrmPurchaseOrderH record);

    int insertSelective(CSrmPurchaseOrderH record);

    List<CSrmPurchaseOrderH> selectByPrimaryKey(CSrmPurchaseOrderH record);

    int updateByPrimaryKeySelective(CSrmPurchaseOrderH record);

    int updateByPrimaryKey(CSrmPurchaseOrderH record);

    int updateBatch(List<CSrmPurchaseOrderH> list);

    int updateBatchSelective(List<CSrmPurchaseOrderH> list);

    int batchInsert(List<CSrmPurchaseOrderH> list);

    Rjson updatePurchaseOrder(CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq) throws Exception;

    Rjson findPurchaseOrderHR(CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq);

    Rjson findPurchaseOrderH(CSrmPurchaseOrderHFindReq purchaseOrderHFindReq);

    Rjson findPurchaseOrderR(CSrmPurchaseOrderHFindReq purchaseOrderHFindReq);

    Rjson delPurchaseOrder(CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq);

}


