package com.skeqi.mes.service.chenj.srm.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.CSrmMakeOutAnInvoice;
import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityH;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplier;
import com.skeqi.mes.pojo.chenj.srm.CSrmTheNumberAuditH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmMakeOutAnInvoiceReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmTheNumberAuditRRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceRRsp;
import com.skeqi.mes.service.chenj.srm.CSrmMakeOutAnInvoiceService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmMakeOutAnInvoiceServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmMakeOutAnInvoiceServiceImpl implements CSrmMakeOutAnInvoiceService {

    @Resource
    private CSrmMakeOutAnInvoiceMapper cSrmMakeOutAnInvoiceMapper;
    @Resource
    private CSrmSendCommodityHMapper cSrmSendCommodityHMapper;
    @Resource
    private CSrmSupplierMapper cSrmSupplierMapper;

    @Resource
    private CSrmCompanyMapper cSrmCompanyMapper;

    @Resource
    private CSrmTheNumberAuditHMapper cSrmTheNumberAuditHMapper;

    @Resource
    private CSrmTheNumberAuditRMapper cSrmTheNumberAuditRMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmMakeOutAnInvoiceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmMakeOutAnInvoice record) {
        return cSrmMakeOutAnInvoiceMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmMakeOutAnInvoice record) {
        return cSrmMakeOutAnInvoiceMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmMakeOutAnInvoice record) {
        return cSrmMakeOutAnInvoiceMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmMakeOutAnInvoice record) {
        return cSrmMakeOutAnInvoiceMapper.insertSelective(record);
    }

    @Override
    public CSrmMakeOutAnInvoice selectByPrimaryKey(CSrmMakeOutAnInvoice record) {
        return cSrmMakeOutAnInvoiceMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmMakeOutAnInvoice record) {
        return cSrmMakeOutAnInvoiceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmMakeOutAnInvoice record) {
        return cSrmMakeOutAnInvoiceMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmMakeOutAnInvoice> list) {
        return cSrmMakeOutAnInvoiceMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmMakeOutAnInvoice> list) {
        return cSrmMakeOutAnInvoiceMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmMakeOutAnInvoice> list) {
        return cSrmMakeOutAnInvoiceMapper.batchInsert(list);
    }

    @Override
    public Rjson updateInvoicingApplication(CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) throws ParseException {

        CSrmMakeOutAnInvoice cSrmMakeOutAnInvoice = null;
        if (("1").equals(cSrmTheNumberAuditHReq.getOperationSign())) {
            // 校验供应商代码是否存在
            CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmTheNumberAuditHReq.getSupplierCode());
            if (cSrmSupplier == null) {
                return Rjson.error("供应商代码不存在");
            }

            // 校验送货单号是否处于送货完成状态
            CSrmSendCommodityH commodityH = cSrmSendCommodityHMapper.selectByPrimaryDevStatus(cSrmTheNumberAuditHReq);
            if (null != commodityH) {
                if (!("5").equals(commodityH.getStatus())) {
                    return Rjson.error("该订单状态还处于送货中");
                }
            }


            // 校验送货单号是否存在
            CSrmSendCommodityH cSrmSendCommodityH = new CSrmSendCommodityH();
            cSrmSendCommodityH.setDeliveryNumber(cSrmTheNumberAuditHReq.getDeliveryNumber());
            cSrmSendCommodityH = cSrmSendCommodityHMapper.selectByPrimaryKey(cSrmSendCommodityH);
            if (cSrmSendCommodityH == null) {
                return Rjson.error("创建失败，送货单号不存在");
            }

            // 校验送货单号是否已创建并正处于审批中
            CSrmMakeOutAnInvoice anInvoice = new CSrmMakeOutAnInvoice();
            anInvoice.setDeliveryNumber(cSrmTheNumberAuditHReq.getDeliveryNumber());
            CSrmMakeOutAnInvoice outAnInvoice = cSrmMakeOutAnInvoiceMapper.selectByPrimaryKey(anInvoice);
            if (null != outAnInvoice) {
                return Rjson.error("创建失败，该送货单号已存在开票申请单");
            }

            // 开票申请单号为空生成开票申请单号（以INVR开头+年月日+3位流水号）
            cSrmMakeOutAnInvoice = cSrmMakeOutAnInvoiceMapper.selectFinallyData();
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (cSrmMakeOutAnInvoice == null) {
                // 未找到数据，从最新一条开始
                cSrmTheNumberAuditHReq.setTheNumberOfHeInvoiceApplication("INVR" + yyyyMMdd + 100);
            } else {
                int requestCode = Integer.parseInt(cSrmMakeOutAnInvoice.getTheNumberOfHeInvoiceApplication().substring(12)) + 1;
                cSrmTheNumberAuditHReq.setTheNumberOfHeInvoiceApplication("INVR" + yyyyMMdd + requestCode);
            }
            // 新增开票申请表
            cSrmMakeOutAnInvoice = new CSrmMakeOutAnInvoice();
            BeanUtils.copyProperties(cSrmTheNumberAuditHReq, cSrmMakeOutAnInvoice);
            // 创建时间
            cSrmMakeOutAnInvoice.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmTheNumberAuditHReq.getCreateTime()));
            // 确认时间
            cSrmMakeOutAnInvoice.setConfirmationTime(new Date());
            // 公司名称
            cSrmMakeOutAnInvoice.setCompanyCode(cSrmTheNumberAuditHReq.getCompanyCode());
//----------------------------------------------新增开票申请头行表--------------------------------------------------------------------------
            cSrmMakeOutAnInvoiceMapper.insertOrUpdateSelective(cSrmMakeOutAnInvoice);


            // 更新送货单头表中  是否已创建开票申请单(0.未创建1.已创建)  改为已创建
            CSrmSendCommodityH sendCommodityH = new CSrmSendCommodityH();
            sendCommodityH.setDeliveryNumber(cSrmTheNumberAuditHReq.getDeliveryNumber());
            sendCommodityH.setIsOpenTicket(1);
            cSrmSendCommodityHMapper.updateByPrimaryKeySelective(sendCommodityH);
            return Rjson.success("创建成功", cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
        } else if (("2").equals(cSrmTheNumberAuditHReq.getOperationSign())) {
            // 校验开票申请单号是否存在
            cSrmMakeOutAnInvoice = new CSrmMakeOutAnInvoice();
            cSrmMakeOutAnInvoice.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
            cSrmMakeOutAnInvoice = cSrmMakeOutAnInvoiceMapper.selectByPrimaryKey(cSrmMakeOutAnInvoice);
            if (cSrmMakeOutAnInvoice == null) {
                return Rjson.error("更新失败，开票申请单号不存在");
            } else {
                // 修改开票申请表
                cSrmMakeOutAnInvoice = new CSrmMakeOutAnInvoice();
                BeanUtils.copyProperties(cSrmTheNumberAuditHReq, cSrmMakeOutAnInvoice);
                cSrmMakeOutAnInvoice.setUpdateTime(new Date());
                cSrmMakeOutAnInvoiceMapper.updateByPrimaryKeySelective(cSrmMakeOutAnInvoice);
                return Rjson.success("更新成功", null);
            }


        } else if (("3").equals(cSrmTheNumberAuditHReq.getOperationSign())) {

            // 校验开票申请单号是否存在
            cSrmMakeOutAnInvoice = new CSrmMakeOutAnInvoice();
            cSrmMakeOutAnInvoice.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
            cSrmMakeOutAnInvoice = cSrmMakeOutAnInvoiceMapper.selectByPrimaryKey(cSrmMakeOutAnInvoice);
            if (cSrmMakeOutAnInvoice == null) {
                return Rjson.error("更新失败，开票申请单号不存在");
            } else {
                // 修改开票申请表
                cSrmMakeOutAnInvoice = new CSrmMakeOutAnInvoice();
                BeanUtils.copyProperties(cSrmTheNumberAuditHReq, cSrmMakeOutAnInvoice);
                cSrmMakeOutAnInvoice.setUpdateTime(new Date());
                cSrmMakeOutAnInvoiceMapper.updateByPrimaryKeySelective(cSrmMakeOutAnInvoice);
                // 更新 开票申请头表 c_srm_the_number_audit_h
                CSrmTheNumberAuditH cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
                cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
                cSrmTheNumberAuditH.setStatus(cSrmTheNumberAuditHReq.getStatus());
                cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);

                return Rjson.success("更新成功", null);
            }
        } else {
            return Rjson.error("操作标识错误");
        }
    }

    @Override
    public Rjson findInvoicingApplication(CSrmMakeOutAnInvoiceReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmMakeOutAnInvoiceMapper.findInvoicingApplication(req)));
    }


    @Override
    public Rjson findNoConsignmentInvoiceHR(CSrmMakeOutAnInvoiceReq req) {
        NoConsignmentInvoiceHRsp rsp = new NoConsignmentInvoiceHRsp();
        NoConsignmentInvoiceHRsp noConsignmentInvoiceH = cSrmMakeOutAnInvoiceMapper.findNoConsignmentInvoiceH(req);
        if (null == noConsignmentInvoiceH) {
            return Rjson.error("订单号不存在");
        } else {
            // 获取头数据
            BeanUtils.copyProperties(noConsignmentInvoiceH, rsp);
        }
        List<NoConsignmentInvoiceRRsp> noConsignmentInvoiceR = cSrmMakeOutAnInvoiceMapper.findNoConsignmentInvoiceR(req);
        // 赋值总不含税总金额
        rsp.setNoTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getNoTaxMoneyMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
        // 赋值总税额
        rsp.setSumTax(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getTax).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
        // 赋值含税总金额
        rsp.setTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getUnitMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        rsp.setRspList(new PageInfo<>(noConsignmentInvoiceR));
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
        json.put("status","1");
        // 赋值含税总金额
        json.put("taxMoneyCountMoney", rspList.stream().map(CSrmTheNumberAuditRRsp::getUnitMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
        // 赋值总税额
        json.put("sumTax", rspList.stream().map(CSrmTheNumberAuditRRsp::getTax).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
        json.put("rspList", new PageInfo<>(rspList));
        return Rjson.success(json);
    }
}


