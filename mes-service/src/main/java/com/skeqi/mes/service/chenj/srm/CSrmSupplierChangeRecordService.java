package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierChangeRecord;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierChangeRecordReq;
import com.skeqi.mes.util.Rjson;

import java.text.ParseException;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplierChangeRecordService
 * @Description ${Description}
 */

public interface CSrmSupplierChangeRecordService {


    int insertSelective(CSrmSupplierChangeRecord record);

    CSrmSupplierChangeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmSupplierChangeRecord record);

    int updateBatchSelective(List<CSrmSupplierChangeRecord> list);

    int batchInsert(List<CSrmSupplierChangeRecord> list);

    /**
     * 供应商信息变更编辑
     */
    Rjson changeRecordEdit(CSrmSupplierChangeRecordReq supplierChangeRecordReq) throws ParseException;

    /**
     * 供应商信息变更查询已保存数据
     */
    Rjson findChangeRecord(CSrmSupplierChangeRecordReq supplierChangeRecordReq);

    /**
     * 撤销企业信息变更数据
     */
    Rjson revocationChangeRecord(CSrmSupplierChangeRecordReq supplierChangeRecordReq);

//    /**
//     * 供应商信息变更查询变更中数据
//     */
//    Rjson findUpdateChangeRecord(CSrmSupplierChangeRecordReq supplierChangeRecordReq);





}

