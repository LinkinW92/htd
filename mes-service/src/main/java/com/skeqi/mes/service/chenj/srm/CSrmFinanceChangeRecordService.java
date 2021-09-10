package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmFinanceChangeRecord;

/**
* @author ChenJ
* @date 2021/7/21
* @Classname CSrmFinanceChangeRecordService
* @Description ${Description}
*/

public interface CSrmFinanceChangeRecordService{


   int insertSelective(CSrmFinanceChangeRecord record);

   CSrmFinanceChangeRecord selectByPrimaryKey(Integer id);

   int updateByPrimaryKeySelective(CSrmFinanceChangeRecord record);

}
