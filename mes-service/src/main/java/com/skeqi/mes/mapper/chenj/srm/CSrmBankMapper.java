package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmBank;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreeSupplier;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmBankChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnterpriseReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmBankChangeRecordRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmBankMapper
 * @Description ${Description}
 */

public interface CSrmBankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmBank record);

    int insertOrUpdate(CSrmBank record);

    int insertOrUpdateSelective(CSrmBank record);

    int insertSelective(CSrmBank record);

    CSrmBank selectByPrimaryKey(Integer id);

    List<CSrmBankChangeRecordRsp> selectByPrimaryKeyList(CSrmEnterpriseReq req);

    int delData(CSrmBank bank);

    // 查询供应商代码是否存在
    CSrmBank selectSupplierCode(String supplierCode);

    int updateByPrimaryKeySelective(CSrmBank record);

    int updateByPrimaryKey(CSrmBank record);

    int updateBatch(List<CSrmBank> list);

    int updateBatchSelective(List<CSrmBank> list);

    int updateBatchSelectiveData(List<CSrmBankChangeRecordReq> list);

    int batchInsert(@Param("list") List<CSrmBank> list);

    int batchInsertData(@Param("list") List<CSrmBankChangeRecordRsp> list);


    int batchInsertKThree(@Param("list") List<KThreeSupplier> list);




}
