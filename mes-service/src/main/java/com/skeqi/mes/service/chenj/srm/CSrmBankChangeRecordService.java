package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmBankChangeRecord;

/**
* @author ChenJ
* @date 2021/7/21
* @Classname CSrmBankChangeRecordService
* @Description ${Description}
*/

public interface CSrmBankChangeRecordService{


   int insertSelective(CSrmBankChangeRecord record);

   CSrmBankChangeRecord selectByPrimaryKey(Integer id);

   int updateByPrimaryKeySelective(CSrmBankChangeRecord record);

}
