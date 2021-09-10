package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmBankChangeRecord;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmBankChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmBankChangeRecordRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author ChenJ
* @date 2021/7/21
* @Classname CSrmBankChangeRecordMapper
* @Description ${Description}
*/

public interface CSrmBankChangeRecordMapper {
   int insertSelective(CSrmBankChangeRecord record);

   CSrmBankChangeRecord selectByPrimaryKey(Integer id);

   List<CSrmBankChangeRecordRsp> selectByPrimaryKeyList(CSrmSupplierChangeRecordReq req);

   int updateByPrimaryKeySelective(CSrmBankChangeRecord record);

   int updateBatchSelective(List<CSrmBankChangeRecordReq> list);

   int updateBatchSelectiveDel(List<CSrmBankChangeRecordRsp> list);

   int delData(CSrmBankChangeRecord changeRecord);

   int batchInsert(@Param("list") List<CSrmBankChangeRecordReq> list);

   int updateBatchSelectiveData(List<CSrmBankChangeRecordReq> list);
}
