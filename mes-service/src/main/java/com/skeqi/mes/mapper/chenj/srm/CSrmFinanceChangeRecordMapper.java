package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmFinanceChangeRecord;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmFinanceChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmFinanceChangeRecordRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author ChenJ
* @date 2021/7/21
* @Classname CSrmFinanceChangeRecordMapper
* @Description ${Description}
*/

public interface CSrmFinanceChangeRecordMapper {
   int insertSelective(CSrmFinanceChangeRecord record);

   CSrmFinanceChangeRecord selectByPrimaryKey(Integer id);

   List<CSrmFinanceChangeRecordRsp> selectByPrimaryKeyList(CSrmSupplierChangeRecordReq req);

   int updateByPrimaryKeySelective(CSrmFinanceChangeRecord record);

   int updateBatchSelective(List<CSrmFinanceChangeRecordReq> list);

   int updateBatchSelectiveDel(List<CSrmFinanceChangeRecordRsp> list);

   int batchInsert(@Param("list") List<CSrmFinanceChangeRecordReq> list);

   int delData(CSrmFinanceChangeRecord changeRecord);

   int updateBatchSelectiveData(List<CSrmFinanceChangeRecordReq> list);
}
