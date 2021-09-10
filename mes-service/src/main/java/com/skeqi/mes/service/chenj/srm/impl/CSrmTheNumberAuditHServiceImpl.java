package com.skeqi.mes.service.chenj.srm.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditRReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmTheNumberAuditHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmTheNumberAuditRRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceRRsp;
import com.skeqi.mes.service.chenj.srm.CSrmTheNumberAuditHService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/4
 * @Classname CSrmTheNumberAuditHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmTheNumberAuditHServiceImpl implements CSrmTheNumberAuditHService {

    @Resource
    private CSrmTheNumberAuditHMapper cSrmTheNumberAuditHMapper;

    @Resource
    private CSrmTheNumberAuditRMapper cSrmTheNumberAuditRMapper;

    @Resource
    private CSrmSupplierMapper cSrmSupplierMapper;

    @Resource
    private CSrmInvoiceReceivableMapper cSrmInvoiceReceivableMapper;

    @Resource
    private CSrmInvoiceReceivableRMapper cSrmInvoiceReceivableRMapper;

    @Resource
    private CSrmSendCommodityHMapper cSrmSendCommodityHMapper;

    @Override
    public int insertSelective(CSrmTheNumberAuditH record) {
        return cSrmTheNumberAuditHMapper.insertSelective(record);
    }

    @Override
    public CSrmTheNumberAuditH selectByPrimaryKey(CSrmTheNumberAuditH record) {
        return cSrmTheNumberAuditHMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmTheNumberAuditH record) {
        return cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmTheNumberAuditH> list) {
        return cSrmTheNumberAuditHMapper.updateBatchSelective(list);
    }


    @Override
    public Rjson updateInvoicingApplication(CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) throws ParseException {

        CSrmTheNumberAuditH cSrmTheNumberAuditH = null;
        if (("1").equals(cSrmTheNumberAuditHReq.getOperationSign())) {
            // 校验供应商代码是否存在
            CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmTheNumberAuditHReq.getSupplierCode());
            if (cSrmSupplier == null) {
                return Rjson.error("供应商代码不存在");
            }

            // 开票申请单号为空生成开票申请单号（以INVR开头+年月日+3位流水号）
            cSrmTheNumberAuditH = cSrmTheNumberAuditHMapper.selectFinallyData();
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (cSrmTheNumberAuditH == null) {
                // 未找到数据，从最新一条开始
                cSrmTheNumberAuditHReq.setTheNumberOfHeInvoiceApplication("INVR" + yyyyMMdd + 100);
            } else {
                int requestCode = Integer.parseInt(cSrmTheNumberAuditH.getTheNumberOfHeInvoiceApplication().substring(12)) + 1;
                cSrmTheNumberAuditHReq.setTheNumberOfHeInvoiceApplication("INVR" + yyyyMMdd + requestCode);
            }
            // 新增开票申请表
            cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
            BeanUtils.copyProperties(cSrmTheNumberAuditHReq, cSrmTheNumberAuditH);
            // 创建时间
            cSrmTheNumberAuditH.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmTheNumberAuditHReq.getCreateTime()));

            cSrmTheNumberAuditHMapper.insertSelective(cSrmTheNumberAuditH);

            // 更新送货单头表中  是否已创建开票申请单(0.未创建1.已创建)  改为已创建
            CSrmSendCommodityH sendCommodityH = new CSrmSendCommodityH();
            sendCommodityH.setDeliveryNumber(cSrmTheNumberAuditHReq.getDeliveryNumber());
            sendCommodityH.setIsOpenTicket(1);
            cSrmSendCommodityHMapper.updateByPrimaryKeySelective(sendCommodityH);

            // 新增申请单行表
            if (!CollectionUtils.isEmpty(cSrmTheNumberAuditHReq.getReqList())) {
                for (CSrmTheNumberAuditRReq item : cSrmTheNumberAuditHReq.getReqList()) {
                    CSrmTheNumberAuditR cSrmTheNumberAuditR = new CSrmTheNumberAuditR();
                    item.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
                    BeanUtils.copyProperties(item, cSrmTheNumberAuditR);
                    cSrmTheNumberAuditR.setCreateTime(new Date());
                    cSrmTheNumberAuditRMapper.insertSelective(cSrmTheNumberAuditR);
                    // 更新送货单头表中 是否已创建开票申请单为 已创建
                    CSrmSendCommodityH sendCommodityH1 = new CSrmSendCommodityH();
                    sendCommodityH1.setDeliveryNumber(item.getDeliveryNumber());
                    sendCommodityH1.setIsOpenTicket(1);
                    cSrmSendCommodityHMapper.updateByPrimaryKeySelective(sendCommodityH1);

                }
            }


            return Rjson.success("创建成功", cSrmTheNumberAuditHReq);
        } else if (("2").equals(cSrmTheNumberAuditHReq.getOperationSign())) {
            // 校验开票申请单号是否存在
            cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
            cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
            cSrmTheNumberAuditH = cSrmTheNumberAuditHMapper.selectByPrimaryKey(cSrmTheNumberAuditH);
            if (cSrmTheNumberAuditH == null) {
                return Rjson.error("更新失败，开票申请单号不存在");
            } else {
                // 修改开票申请表
                cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
                BeanUtils.copyProperties(cSrmTheNumberAuditHReq, cSrmTheNumberAuditH);
                cSrmTheNumberAuditH.setUpdateTime(new Date());
                cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);
                return Rjson.success("更新成功", cSrmTheNumberAuditHReq);
            }


        } else if (("3").equals(cSrmTheNumberAuditHReq.getOperationSign())) {

            // 校验开票申请单号是否存在
            cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
            cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
            cSrmTheNumberAuditH = cSrmTheNumberAuditHMapper.selectByPrimaryKey(cSrmTheNumberAuditH);
            if (cSrmTheNumberAuditH == null) {
                return Rjson.error("更新失败，开票申请单号不存在");
            } else {
                // 更新 开票申请头表 c_srm_the_number_audit_h
                cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
                cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
                cSrmTheNumberAuditH.setStatus(cSrmTheNumberAuditHReq.getStatus());
                cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);

                return Rjson.success("更新成功", null);
            }
        } else {
            return Rjson.error("操作标识错误");
        }
    }


//
//    @Override
//    public Rjson noInvoiceMaintenanceSave(CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) {
//        // 校验申请单号是否存在
//        CSrmTheNumberAuditH cSrmTheNumberAuditH = null;
//        cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
//        cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
//        if (cSrmTheNumberAuditHMapper.selectByPrimaryList(cSrmTheNumberAuditH).size() == 0) {
//            // 保存头表数据
//            cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
//            BeanUtils.copyProperties(cSrmTheNumberAuditHReq, cSrmTheNumberAuditH);
//
//            // 以下字段不同 手动赋值
//            // 不含税总金额
//            cSrmTheNumberAuditH.setNoTaxCountMoney(cSrmTheNumberAuditHReq.getNoTaxMoneyCountMoney());
//            // 总税额
//            cSrmTheNumberAuditH.setCountTax(cSrmTheNumberAuditHReq.getSumTax());
//            // 含税总金额
//            cSrmTheNumberAuditH.setTaxMoneyCountMoney(cSrmTheNumberAuditHReq.getTaxMoneyCountMoney());
//            // 库存组织
//            cSrmTheNumberAuditH.setInventoryOrganization(cSrmTheNumberAuditHReq.getShippingAddress());
//            cSrmTheNumberAuditH.setCreateTime(new Date());
//            cSrmTheNumberAuditHMapper.insertSelective(cSrmTheNumberAuditH);
//
//            // 校验采购申请单号是否全部已经绑定值
//            boolean flag = cSrmTheNumberAuditHReq.getReqList().stream().allMatch(e -> EqualsUtil.StringEqualsNull(e.getTheNumberOfHeInvoiceApplication()));
//            if (!flag) {
//                // 手动绑定申请单号值
//                cSrmTheNumberAuditHReq.getReqList().forEach(item -> {
//                    item.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
//                });
//            }
//            // 先设置创建时间然后保存行表数据
//            cSrmTheNumberAuditRMapper.batchInsert(cSrmTheNumberAuditHReq.getReqList());
//        } else {
//            return Rjson.error("保存失败，您已经保存过");
//        }
//        return Rjson.success("保存成功", null);
//    }

    @Override
    public int batchInsert(List<CSrmTheNumberAuditH> list) {
        return cSrmTheNumberAuditHMapper.batchInsert(list);
    }


    @Override
    public Rjson findInvoicingApplication(CSrmTheNumberAuditHReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmTheNumberAuditHMapper.findInvoicingApplication(req)));
    }


    @Override
    public Rjson findNoInvoiceMaintenanceSave(CSrmTheNumberAuditHReq req) {
        // 初始化返回参数
        CSrmTheNumberAuditHRsp rsp = new CSrmTheNumberAuditHRsp();

        CSrmTheNumberAuditH cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
        cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
        // 查询头信息
        List<CSrmTheNumberAuditHRsp> cSrmTheNumberAuditHS = cSrmTheNumberAuditHMapper.selectByPrimaryList(cSrmTheNumberAuditH);
        if (cSrmTheNumberAuditHS.size() == 1) {
            cSrmTheNumberAuditHS.forEach(item -> {
                BeanUtils.copyProperties(item, rsp);
            });
        }

        CSrmTheNumberAuditR cSrmTheNumberAuditR = new CSrmTheNumberAuditR();
        cSrmTheNumberAuditR.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
        // 查询行信息
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        rsp.setReqList(new PageInfo<>(cSrmTheNumberAuditRMapper.selectByPrimaryList(cSrmTheNumberAuditR)));
        List<CSrmTheNumberAuditHRsp> list = new ArrayList<>();
        list.add(rsp);
        return Rjson.success(list);
    }

    @Override
    public Rjson findNoConsignmentInvoiceHR(CSrmTheNumberAuditHReq req) {
        NoConsignmentInvoiceHRsp rsp = new NoConsignmentInvoiceHRsp();
        NoConsignmentInvoiceHRsp noConsignmentInvoiceH = cSrmTheNumberAuditRMapper.findNoConsignmentInvoiceH(req);
        if (null == noConsignmentInvoiceH) {
            return Rjson.error("订单号不存在");
        } else {
            // 获取头数据
            BeanUtils.copyProperties(noConsignmentInvoiceH, rsp);
        }
        if (!CollectionUtils.isEmpty(req.getLineItemNo())) {
            List<NoConsignmentInvoiceRRsp> noConsignmentInvoiceR = cSrmTheNumberAuditRMapper.findNoConsignmentInvoiceR(req);
            // 赋值总不含税总金额
            rsp.setNoTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getNoTaxMoneyMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
            // 赋值总税额
            rsp.setSumTax(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getTax).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
            // 赋值含税总金额
            rsp.setTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getUnitMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
            PageHelper.startPage(req.getPageNum(), req.getPageSize());
            rsp.setRspList(new PageInfo<>(noConsignmentInvoiceR));
        }
        // 设置状态为新建并返回给前端
        rsp.setStatus("1");
        rsp.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        List<NoConsignmentInvoiceHRsp> list = new ArrayList<>();
        list.add(rsp);
        return Rjson.success(list);


    }


    @Override
    public Rjson findNoConsignmentInvoiceR(CSrmTheNumberAuditHReq req) {
        if (!StringUtil.eqNu(req.getDeliveryNumber()) && !StringUtil.eqNu(req.getOrderNumber()) && !StringUtil.eqNu(req.getReceivingGoodsDtStart()) && !StringUtil.eqNu(req.getReceivingGoodsDtStop()) && !StringUtil.eqNu(req.getCompanyName())) {
            return Rjson.success();
        }
        // 校验供应商代码是否存在
        if (null == cSrmSupplierMapper.selectSupplierCode(req.getSupplierCode())) {
            return Rjson.error("供应商代码不存在");
        }
        // 校验送货单号是否处于送货完成状态
        if (StringUtil.eqNu(req.getDeliveryNumber()) || StringUtil.eqNu(req.getPurchaseRequestNo())) {
            CSrmSendCommodityH commodityH = cSrmSendCommodityHMapper.selectByPrimaryDevStatus(req);
            if (null == commodityH) {
                return Rjson.success("查询成功", null);
            } else {
                if (!("5").equals(commodityH.getStatus())) {
                    return Rjson.error("该订单状态还处于送货中");
                }

            }
        }
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmTheNumberAuditHMapper.findCreateApplicationOrder(req)));

    }

    @Override
    public Rjson findNoConsignmentInvoiceBatchHR(CSrmTheNumberAuditHReq req) {
        // 查询申请行数据
        JSONObject json = new JSONObject();
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<CSrmTheNumberAuditRRsp> rspList = cSrmTheNumberAuditRMapper.selectByPrimaryListRList(req);
        // 新建状态
        json.put("status", "1");
        // 赋值含税总金额
        json.put("taxMoneyCountMoney", rspList.stream().map(CSrmTheNumberAuditRRsp::getUnitMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
        // 赋值总税额
        json.put("sumTax", rspList.stream().map(CSrmTheNumberAuditRRsp::getTax).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
        json.put("rspList", new PageInfo<>(rspList));
        return Rjson.success(json);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmTheNumberAuditHMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Rjson delInvoicingApplication(CSrmTheNumberAuditHReq req) {

        if (StringUtil.eqNu(req.getTheNumberOfHeInvoiceApplication())) {
            // 删除头表记录
            CSrmTheNumberAuditH cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
            cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
            cSrmTheNumberAuditH.setIsDelete(true);
            cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);
            // 删除行表记录
            CSrmTheNumberAuditR cSrmTheNumberAuditR = new CSrmTheNumberAuditR();
            cSrmTheNumberAuditR.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
            cSrmTheNumberAuditR.setDelete(true);
            cSrmTheNumberAuditRMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditR);
        }

        // 删除应收应付数据
        if (StringUtil.eqNu(req.getInvoiceReceivableNo())) {
            // 删除头数据
            CSrmInvoiceReceivable cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
            cSrmInvoiceReceivable.setIsDelete(true);
            cSrmInvoiceReceivable.setInvoiceReceivableNo(req.getInvoiceReceivableNo());
            cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(cSrmInvoiceReceivable);
            // 删除行数据
            CSrmInvoiceReceivableR receivableR = new CSrmInvoiceReceivableR();
            receivableR.setIsDelete(true);
            receivableR.setInvoiceReceivableNo(req.getInvoiceReceivableNo());
            cSrmInvoiceReceivableRMapper.updateByPrimaryKeySelective(receivableR);
        }



        if (!CollectionUtils.isEmpty(req.getUpdateList())) {
            for (CSrmTheNumberAuditRRsp item : req.getUpdateList()) {
                // 更新对账申请头表中 是否已创建应收应付申请单为 未创建
                CSrmTheNumberAuditH srmTheNumberAuditH = new CSrmTheNumberAuditH();
                srmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(item.getTheNumberOfHeInvoiceApplication());
                srmTheNumberAuditH.setIsCreated(0);
                cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(srmTheNumberAuditH);
            }
        }


        return Rjson.success();
    }
}




