package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmInvoiceReceivableRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmInvoiceReceivableR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableRReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmInvoiceReceivableRRsp;
import com.skeqi.mes.service.chenj.srm.CSrmInvoiceReceivableRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ChenJ
* @date 2021/8/21
* @Classname CSrmInvoiceReceivableRServiceImpl
* @Description ${Description}
*/

@Service
public class CSrmInvoiceReceivableRServiceImpl implements CSrmInvoiceReceivableRService {

   @Resource
   private CSrmInvoiceReceivableRMapper cSrmInvoiceReceivableRMapper;

   @Override
   public int deleteByPrimaryKey(Integer id) {
       return cSrmInvoiceReceivableRMapper.deleteByPrimaryKey(id);
   }

   @Override
   public int insertSelective(CSrmInvoiceReceivableR record) {
       return cSrmInvoiceReceivableRMapper.insertSelective(record);
   }

   @Override
   public List<CSrmInvoiceReceivableRRsp> selectByPrimaryKey(CSrmInvoiceReceivableReq record) {
       return cSrmInvoiceReceivableRMapper.selectByPrimaryKey(record);
   }

   @Override
   public int updateByPrimaryKeySelective(CSrmInvoiceReceivableR record) {
       return cSrmInvoiceReceivableRMapper.updateByPrimaryKeySelective(record);
   }

   @Override
   public int updateBatchSelective(List<CSrmInvoiceReceivableR> list) {
       return cSrmInvoiceReceivableRMapper.updateBatchSelective(list);
   }

   @Override
   public int batchInsert(List<CSrmInvoiceReceivableRReq> list) {
       return cSrmInvoiceReceivableRMapper.batchInsert(list);
   }

}
