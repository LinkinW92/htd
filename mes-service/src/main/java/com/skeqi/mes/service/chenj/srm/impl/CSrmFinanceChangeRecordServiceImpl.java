package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmFinanceChangeRecordMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmFinanceChangeRecord;
import com.skeqi.mes.service.chenj.srm.CSrmFinanceChangeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ChenJ
* @date 2021/7/21
* @Classname CSrmFinanceChangeRecordServiceImpl
* @Description ${Description}
*/

@Service
public class CSrmFinanceChangeRecordServiceImpl implements CSrmFinanceChangeRecordService {

   @Resource
   private CSrmFinanceChangeRecordMapper cSrmFinanceChangeRecordMapper;

   @Override
   public int insertSelective(CSrmFinanceChangeRecord record) {
       return cSrmFinanceChangeRecordMapper.insertSelective(record);
   }

   @Override
   public CSrmFinanceChangeRecord selectByPrimaryKey(Integer id) {
       return cSrmFinanceChangeRecordMapper.selectByPrimaryKey(id);
   }

   @Override
   public int updateByPrimaryKeySelective(CSrmFinanceChangeRecord record) {
       return cSrmFinanceChangeRecordMapper.updateByPrimaryKeySelective(record);
   }


}
