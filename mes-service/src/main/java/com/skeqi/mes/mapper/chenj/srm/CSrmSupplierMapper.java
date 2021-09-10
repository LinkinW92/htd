package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmSupplier;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreeSupplier;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnterpriseReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHReqs;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSupplierEnterpriseRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSupplierInfoRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSupplierManagementRsp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplierMapper
 * @Description ${Description}
 */
@Repository("cSrmSupplierMapper")
public interface CSrmSupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSupplier record);

    int insertOrUpdate(CSrmSupplier record);

    int insertOrUpdateSelective(CSrmSupplier record);

    int insertSelective(CSrmSupplier record);

    List<CSrmSupplier> selectByPrimaryKey(CSrmSupplier record);

    List<CSrmSupplierInfoRsp> selectSupplierInfo(CSrmSupplier cSrmSupplier);

    List<CSrmSupplierEnterpriseRsp> selectSupplierEnterpriseInfo(CSrmEnterpriseReq cSrmEnterpriseReq);

    /**
     *  获取供应商认证状态
     * @param cSrmEnterpriseReq
     * @return
     */
    String findSupplierStatus(CSrmEnterpriseReq cSrmEnterpriseReq);

    // 查询最后一条数据
    CSrmSupplier selectFinallyData();

    // 查询供应商代码是否存在
    CSrmSupplier selectSupplierCode(@Param("supplierCode") String supplierCode);

    int updateByPrimaryKeySelective(CSrmSupplier record);

    int updateByPrimaryKey(CSrmSupplier record);

    int updateBatch(List<CSrmSupplier> list);

    int updateBatchSelective(List<CSrmSupplier> list);

    int batchInsert(@Param("list") List<CSrmSupplier> list);

    // 根据供应商代码查询供应商
    CSrmSupplier checkSupplierCode(@Param("supplierCode") String supplierCode, @Param("account") String account);
    /**
     * 修改token值与token创建时间
     */
    Integer updateTokenCreateTimeOrToken(@Param("tokenCreateTime") String tokenCreateTime, @Param("userName") String userName, @Param("token") String token);

    CSrmSupplier findByToken(@Param("token") String token);

    /**
     * 根据供应商代码查询企业认证信息
     */
    CSrmSupplierEnterpriseRsp findEnterpriseAuthInfo(CSrmEnterpriseReq cSrmEnterpriseReq);

    /**
     * 查询供应商生命周期管理
     */
    List<CSrmSupplierManagementRsp> findSupplierManagement(CSrmEnterpriseReq cSrmEnterpriseReq);


    List<CSrmSupplierHRsp> selectSupplierList(CSrmSupplierHReqs cSrmSupplierHReqs);

    int batchInsertKThree(@Param("list") List<KThreeSupplier> list);



}
