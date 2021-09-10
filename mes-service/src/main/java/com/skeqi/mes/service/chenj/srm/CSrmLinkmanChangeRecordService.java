package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmLinkmanChangeRecord;

/**
* @author ChenJ
* @date 2021/7/21
* @Classname CSrmLinkmanChangeRecordService
* @Description ${Description}
*/

public interface CSrmLinkmanChangeRecordService{


   int insertSelective(CSrmLinkmanChangeRecord record);

   CSrmLinkmanChangeRecord selectByPrimaryKey(CSrmLinkmanChangeRecord record);

   int updateByPrimaryKeySelective(CSrmLinkmanChangeRecord record);


}
