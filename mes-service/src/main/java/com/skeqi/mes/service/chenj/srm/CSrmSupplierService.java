package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplier;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnterpriseReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHReqs;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierReq;
import com.skeqi.mes.util.Rjson;

import java.text.ParseException;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSupplierService
 * @Description ${Description}
 */

public interface CSrmSupplierService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSupplier record);

    int insertOrUpdate(CSrmSupplier record);

    int insertOrUpdateSelective(CSrmSupplier record);

    int insertSelective(CSrmSupplier record);

    List<CSrmSupplier> selectByPrimaryKey(CSrmSupplier record);

    CSrmSupplier checkSupplierCode(String supplierCode, String account);

    int updateByPrimaryKeySelective(CSrmSupplier record);

    int updateByPrimaryKey(CSrmSupplier record);

    int updateBatch(List<CSrmSupplier> list);

    int updateBatchSelective(List<CSrmSupplier> list);

    int batchInsert(List<CSrmSupplier> list);

    /**
     * 供应商注册
     */
    Rjson addSupplier(CSrmSupplierReq cSrmSupplierReq);

    /**
     * 企业认证创建
     */
    Rjson addEnterprise(CSrmEnterpriseReq cSrmEnterpriseReq) throws ParseException;


    /**
     * 企业认证查询
     */
    Rjson findSupplierEnterpriseInfo(CSrmEnterpriseReq cSrmEnterpriseReq);

    /**
     * 供应商信息查询
     */
    Rjson findSupplierInfo(CSrmEnterpriseReq cSrmEnterpriseReq);


    /**
     * 查询企业认证信息
     */
    Rjson findEnterpriseInfo(CSrmEnterpriseReq cSrmEnterpriseReq);

    /**
     * 获取供应商认证状态
     */
    Rjson findSupplierStatus(CSrmEnterpriseReq cSrmEnterpriseReq);

    /**
     * 获取供应商认证信息
     */
    Rjson findSupplierAuthInfo(CSrmEnterpriseReq cSrmEnterpriseReq);


    /**
     * 企业认证审核
     */
    Rjson supplierAudit(CSrmEnterpriseReq cSrmEnterpriseReq, SrmSupplierTimer srmSupplierTimer) throws Exception;


    /**
     * 修改token值与token创建时间
     */
    Integer updateTokenCreateTimeOrToken(String tokenCreateTime, String userName, String token);

    CSrmSupplier findByToken(String token);

    /**
     * 查询供应商生命周期管理
     */
    Rjson findSupplierManagement(CSrmEnterpriseReq cSrmEnterpriseReq);


    /**
     * 查询供应商代码后名称
     */
    Rjson selectSupplierList(CSrmSupplierHReqs reqs);


}





