package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseDemandH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseDemandHRListReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseDemandHReq;
import com.skeqi.mes.util.Rjson;

import java.text.ParseException;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandHService
 * @Description ${Description}
 */

public interface CSrmPurchaseDemandHService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmPurchaseDemandH record);

    int insertOrUpdate(CSrmPurchaseDemandH record);

    int insertOrUpdateSelective(CSrmPurchaseDemandH record);

    int insertSelective(CSrmPurchaseDemandH record);

    CSrmPurchaseDemandH selectByPrimaryKey(CSrmPurchaseDemandH record);

    int updateByPrimaryKeySelective(CSrmPurchaseDemandH record);

    int updateByPrimaryKey(CSrmPurchaseDemandH record);

    int updateBatch(List<CSrmPurchaseDemandH> list);

    int updateBatchSelective(List<CSrmPurchaseDemandH> list);

    int batchInsert(List<CSrmPurchaseDemandH> list);

    Rjson createPurchaseDemand(CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq) throws Exception;

    /**
     * 采购需求单头查询
     * @param cSrmPurchaseDemandHReq
     * @return
     * @throws ParseException
     */
    Rjson findPurchaseDemandH(CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq);


    Rjson findPurchaseDemandHRList(CSrmPurchaseDemandHRListReq req);

    Rjson findPurchaseDemandR(CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq);


    Rjson allocationPurchaseDemand(CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq);


    Rjson delPurchaseDemand(CSrmPurchaseDemandHRListReq req);




//    Rjson updatePurchaseDemand(CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq)throws ParseException;;



}

