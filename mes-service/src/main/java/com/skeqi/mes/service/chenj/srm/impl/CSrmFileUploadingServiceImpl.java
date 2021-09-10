package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmFileUploadingMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmFileUploading;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmFileUploadingReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmFileUploadingRsp;
import com.skeqi.mes.service.chenj.srm.CSrmFileUploadingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ChenJ
* @date 2021/8/10
* @Classname CSrmFileUploadingServiceImpl
* @Description ${Description}
*/

@Service
public class CSrmFileUploadingServiceImpl implements CSrmFileUploadingService {

   @Resource
   private CSrmFileUploadingMapper cSrmFileUploadingMapper;

   @Override
   public int deleteByPrimaryKey(List<Integer> id) {
	   return cSrmFileUploadingMapper.deleteByPrimaryKey(id);
   }

   @Override
   public int insertSelective(CSrmFileUploading record) {
	   return cSrmFileUploadingMapper.insertSelective(record);
   }

   @Override
   public List<CSrmFileUploadingRsp> selectByPrimaryKey(CSrmFileUploadingReq record) {
	   return cSrmFileUploadingMapper.selectByPrimaryKey(record);
   }

   @Override
   public int updateByPrimaryKeySelective(CSrmFileUploading record) {
	   return cSrmFileUploadingMapper.updateByPrimaryKeySelective(record);
   }

   @Override
   public int updateBatchSelective(List<CSrmFileUploading> list) {
	   return cSrmFileUploadingMapper.updateBatchSelective(list);
   }

   @Override
   public int batchInsert(List<CSrmFileUploading> list) {
	   return cSrmFileUploadingMapper.batchInsert(list);
   }

}
