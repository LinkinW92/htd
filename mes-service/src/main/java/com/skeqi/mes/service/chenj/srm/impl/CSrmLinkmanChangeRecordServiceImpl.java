package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmLinkmanChangeRecordMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmLinkmanChangeRecord;
import com.skeqi.mes.service.chenj.srm.CSrmLinkmanChangeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ChenJ
* @date 2021/7/21
* @Classname CSrmLinkmanChangeRecordServiceImpl
* @Description ${Description}
*/

@Service
public class CSrmLinkmanChangeRecordServiceImpl implements CSrmLinkmanChangeRecordService {

   @Resource
   private CSrmLinkmanChangeRecordMapper cSrmLinkmanChangeRecordMapper;

   @Override
   public int insertSelective(CSrmLinkmanChangeRecord record) {
       return cSrmLinkmanChangeRecordMapper.insertSelective(record);
   }

   @Override
   public CSrmLinkmanChangeRecord selectByPrimaryKey(CSrmLinkmanChangeRecord record) {
       return cSrmLinkmanChangeRecordMapper.selectByPrimaryKey(record);
   }

   @Override
   public int updateByPrimaryKeySelective(CSrmLinkmanChangeRecord record) {
       return cSrmLinkmanChangeRecordMapper.updateByPrimaryKeySelective(record);
   }


}
