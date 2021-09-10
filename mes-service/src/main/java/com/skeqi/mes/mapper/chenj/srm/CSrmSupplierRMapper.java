package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHRDelReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHRReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierRReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/20
 * @Classname CSrmSupplierRMapper
 * @Description ${Description}
 */

public interface CSrmSupplierRMapper {
    int insertSelective(CSrmSupplierR record);

    CSrmSupplierR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmSupplierR record);

    int updateBatchSelective(List<CSrmSupplierR> list);

    int batchInsert(@Param("list") List<CSrmSupplierR> list);

    Integer selectRequestCode(String requestCode);

    CSrmSupplierR selectFinallyData(String requestCode);

    List<CSrmSupplierRReq> selectByPrimaryKeyList(CSrmSupplierHRReq req);

    int deleteByPrimaryData(CSrmSupplierHRDelReq delReq);

}
