package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierChangeRecord;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSupplierChangeRecordRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/21
 * @Classname CSrmSupplierChangeRecordMapper
 * @Description ${Description}
 */

public interface CSrmSupplierChangeRecordMapper {
    int insertSelective(CSrmSupplierChangeRecord record);

    CSrmSupplierChangeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmSupplierChangeRecord record);

    int updateByPrimaryKeySelectiveDel(CSrmSupplierChangeRecord record);


    int updateBatchSelective(List<CSrmSupplierChangeRecord> list);

    int batchInsert(@Param("list") List<CSrmSupplierChangeRecord> list);

    /**
     * 根据供应商代码和变更状态查询变更记录
     */
    List<CSrmSupplierChangeRecord> selectChangeRecord(@Param("supplierCode") String supplierCode, @Param("status") String status);

    CSrmSupplierChangeRecordRsp selectChangeRecordD(CSrmSupplierChangeRecordReq req);
}
