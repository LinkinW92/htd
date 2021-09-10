package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseDemandH;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePORequest;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseDemandHRListReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseDemandHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseDemandHRListRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseDemandHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseDemandRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandHMapper
 * @Description ${Description}
 */

public interface CSrmPurchaseDemandHMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmPurchaseDemandH record);

    int insertOrUpdate(CSrmPurchaseDemandH record);

    int insertOrUpdateSelective(CSrmPurchaseDemandH record);

    int insertSelective(CSrmPurchaseDemandH record);

    CSrmPurchaseDemandH selectByPrimaryKey(CSrmPurchaseDemandH record);

    int updateByPrimaryKeySelective(CSrmPurchaseDemandH record);

    int updateByPrimaryKey(CSrmPurchaseDemandH record);

    int updateBatch(List<CSrmPurchaseDemandH> list);

    /**
     * 更新全部转单状态
     * @param list
     * @return
     */
    int updateBatchCSrmPurchaseDemandRSAll(List<String> list);


    /**
     * 更新部分转单状态
     * @param list
     * @return
     */
    int updateBatchCSrmPurchaseDemandRSPortion(List<String> list);

    int updateBatchSelectiveKThree(List<KThreePORequest> list);

    int updateBatchSelective(List<CSrmPurchaseDemandH> list);

    int batchInsert(@Param("list") List<CSrmPurchaseDemandH> list);

    int batchInsertKThree(@Param("list") List<KThreePORequest> list);

    int delKThreeData(@Param("list") List<CSrmKThreePurchaseAbutting> list);



    CSrmPurchaseDemandH selectFinallyData();

    /**
     * 采购需求管理头查询
     * @return
     */
    List<CSrmPurchaseDemandHRsp> findPurchaseDemandH(CSrmPurchaseDemandHReq req);


    /**
     * 采购需求管理头行合并查询
     * @return
     */
    List<CSrmPurchaseDemandHRListRsp>  findPurchaseDemandHRList(CSrmPurchaseDemandHRListReq reqList);


    /**
     * 采购需求管理行查询
     * @return
     */
    List<CSrmPurchaseDemandRRsp> findPurchaseDemandR(CSrmPurchaseDemandHReq req);




    /**
     * 采购需求分配
     */
    int allocationPurchaseDemand(CSrmPurchaseDemandHReq req);






}
