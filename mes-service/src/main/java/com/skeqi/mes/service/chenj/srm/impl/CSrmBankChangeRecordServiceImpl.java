package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmBankChangeRecordMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmBankChangeRecord;
import com.skeqi.mes.service.chenj.srm.CSrmBankChangeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ChenJ
* @date 2021/7/21
* @Classname CSrmBankChangeRecordServiceImpl
* @Description ${Description}
*/

@Service
public class CSrmBankChangeRecordServiceImpl implements CSrmBankChangeRecordService {

   @Resource
   private CSrmBankChangeRecordMapper cSrmBankChangeRecordMapper;

   @Override
   public int insertSelective(CSrmBankChangeRecord record) {
       return cSrmBankChangeRecordMapper.insertSelective(record);
   }

   @Override
   public CSrmBankChangeRecord selectByPrimaryKey(Integer id) {
       return cSrmBankChangeRecordMapper.selectByPrimaryKey(id);
   }

   @Override
   public int updateByPrimaryKeySelective(CSrmBankChangeRecord record) {
       return cSrmBankChangeRecordMapper.updateByPrimaryKeySelective(record);
   }

}
