package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHRDelReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHRReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSupplierHRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplierHMapper
 * @Description ${Description}
 */

public interface CSrmSupplierHMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSupplierH record);

    int insertOrUpdate(CSrmSupplierH record);

    int insertOrUpdateSelective(CSrmSupplierH record);

    int insertSelective(CSrmSupplierH record);

    CSrmSupplierH selectByPrimaryKey(CSrmSupplierH record);

    CSrmSupplierH selectSupplierCode(@Param("supplierCode") String supplierCode, @Param("status") String status);

    int updateByPrimaryKeySelective(CSrmSupplierH record);

    int updateByPrimaryKey(CSrmSupplierH record);

    int updateBatch(List<CSrmSupplierH> list);

    int updateBatchSelective(List<CSrmSupplierH> list);

    int batchInsert(@Param("list") List<CSrmSupplierH> list);

    CSrmSupplierH selectFinallyData();

    List<CSrmSupplierHRRsp> selectByPrimaryHList(CSrmSupplierHRReq req);

    int deleteByPrimaryData(CSrmSupplierHRDelReq delReq);
}
