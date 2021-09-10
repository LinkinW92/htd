package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmLinkmanChangeRecord;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmLinkmanChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmLinkmanChangeRecordRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author ChenJ
* @date 2021/7/21
* @Classname CSrmLinkmanChangeRecordMapper
* @Description ${Description}
*/

public interface CSrmLinkmanChangeRecordMapper {
   int insertSelective(CSrmLinkmanChangeRecord record);

   CSrmLinkmanChangeRecord selectByPrimaryKey(CSrmLinkmanChangeRecord record);

   List<CSrmLinkmanChangeRecordRsp> selectByPrimaryKeyList(CSrmSupplierChangeRecordReq req);

   int updateByPrimaryKeySelective(CSrmLinkmanChangeRecord record);

   int updateBatchSelective(List<CSrmLinkmanChangeRecordReq> list);

   int updateBatchSelectiveDel(List<CSrmLinkmanChangeRecordRsp> list);

   int delData(CSrmLinkmanChangeRecord changeRecord);

   int batchInsert(@Param("list") List<CSrmLinkmanChangeRecordReq> list);

   int updateBatchSelectiveData(List<CSrmLinkmanChangeRecordReq> list);


}
