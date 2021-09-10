package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmProduct;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnterpriseReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSupplierChangeRecordRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmProductMapper
 * @Description ${Description}
 */

public interface CSrmProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmProduct record);

    int insertOrUpdate(CSrmProduct record);

    int insertOrUpdateSelective(CSrmProduct record);

    int insertSelective(CSrmProduct record);

    CSrmProduct selectByPrimaryKey(Integer id);

    CSrmSupplierChangeRecordRsp selectByPrimaryKeyList(CSrmEnterpriseReq req);

    // 查询供应商代码是否存在
    CSrmProduct selectSupplierCode(String supplierCode);


    int updateByPrimaryKeySelective(CSrmProduct record);

    int updateByPrimaryKey(CSrmProduct record);

    int updateBatch(List<CSrmProduct> list);

    int updateBatchSelective(List<CSrmProduct> list);

    int batchInsert(@Param("list") List<CSrmProduct> list);
}
