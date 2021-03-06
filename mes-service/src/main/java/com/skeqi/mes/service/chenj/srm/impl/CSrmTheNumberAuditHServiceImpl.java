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
            // ?????????????????????????????????
            CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmTheNumberAuditHReq.getSupplierCode());
            if (cSrmSupplier == null) {
                return Rjson.error("????????????????????????");
            }

            // ??????????????????????????????????????????????????????INVR??????+?????????+3???????????????
            cSrmTheNumberAuditH = cSrmTheNumberAuditHMapper.selectFinallyData();
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (cSrmTheNumberAuditH == null) {
                // ???????????????????????????????????????
                cSrmTheNumberAuditHReq.setTheNumberOfHeInvoiceApplication("INVR" + yyyyMMdd + 100);
            } else {
                int requestCode = Integer.parseInt(cSrmTheNumberAuditH.getTheNumberOfHeInvoiceApplication().substring(12)) + 1;
                cSrmTheNumberAuditHReq.setTheNumberOfHeInvoiceApplication("INVR" + yyyyMMdd + requestCode);
            }
            // ?????????????????????
            cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
            BeanUtils.copyProperties(cSrmTheNumberAuditHReq, cSrmTheNumberAuditH);
            // ????????????
            cSrmTheNumberAuditH.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmTheNumberAuditHReq.getCreateTime()));

            cSrmTheNumberAuditHMapper.insertSelective(cSrmTheNumberAuditH);

            // ????????????????????????  ??????????????????????????????(0.?????????1.?????????)  ???????????????
            CSrmSendCommodityH sendCommodityH = new CSrmSendCommodityH();
            sendCommodityH.setDeliveryNumber(cSrmTheNumberAuditHReq.getDeliveryNumber());
            sendCommodityH.setIsOpenTicket(1);
            cSrmSendCommodityHMapper.updateByPrimaryKeySelective(sendCommodityH);

            // ?????????????????????
            if (!CollectionUtils.isEmpty(cSrmTheNumberAuditHReq.getReqList())) {
                for (CSrmTheNumberAuditRReq item : cSrmTheNumberAuditHReq.getReqList()) {
                    CSrmTheNumberAuditR cSrmTheNumberAuditR = new CSrmTheNumberAuditR();
                    item.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
                    BeanUtils.copyProperties(item, cSrmTheNumberAuditR);
                    cSrmTheNumberAuditR.setCreateTime(new Date());
                    cSrmTheNumberAuditRMapper.insertSelective(cSrmTheNumberAuditR);
                    // ???????????????????????? ????????????????????????????????? ?????????
                    CSrmSendCommodityH sendCommodityH1 = new CSrmSendCommodityH();
                    sendCommodityH1.setDeliveryNumber(item.getDeliveryNumber());
                    sendCommodityH1.setIsOpenTicket(1);
                    cSrmSendCommodityHMapper.updateByPrimaryKeySelective(sendCommodityH1);

                }
            }


            return Rjson.success("????????????", cSrmTheNumberAuditHReq);
        } else if (("2").equals(cSrmTheNumberAuditHReq.getOperationSign())) {
            // ????????????????????????????????????
            cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
            cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
            cSrmTheNumberAuditH = cSrmTheNumberAuditHMapper.selectByPrimaryKey(cSrmTheNumberAuditH);
            if (cSrmTheNumberAuditH == null) {
                return Rjson.error("??????????????????????????????????????????");
            } else {
                // ?????????????????????
                cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
                BeanUtils.copyProperties(cSrmTheNumberAuditHReq, cSrmTheNumberAuditH);
                cSrmTheNumberAuditH.setUpdateTime(new Date());
                cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);
                return Rjson.success("????????????", cSrmTheNumberAuditHReq);
            }


        } else if (("3").equals(cSrmTheNumberAuditHReq.getOperationSign())) {

            // ????????????????????????????????????
            cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
            cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
            cSrmTheNumberAuditH = cSrmTheNumberAuditHMapper.selectByPrimaryKey(cSrmTheNumberAuditH);
            if (cSrmTheNumberAuditH == null) {
                return Rjson.error("??????????????????????????????????????????");
            } else {
                // ?????? ?????????????????? c_srm_the_number_audit_h
                cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
                cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
                cSrmTheNumberAuditH.setStatus(cSrmTheNumberAuditHReq.getStatus());
                cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);

                return Rjson.success("????????????", null);
            }
        } else {
            return Rjson.error("??????????????????");
        }
    }


//
//    @Override
//    public Rjson noInvoiceMaintenanceSave(CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) {
//        // ??????????????????????????????
//        CSrmTheNumberAuditH cSrmTheNumberAuditH = null;
//        cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
//        cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
//        if (cSrmTheNumberAuditHMapper.selectByPrimaryList(cSrmTheNumberAuditH).size() == 0) {
//            // ??????????????????
//            cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
//            BeanUtils.copyProperties(cSrmTheNumberAuditHReq, cSrmTheNumberAuditH);
//
//            // ?????????????????? ????????????
//            // ??????????????????
//            cSrmTheNumberAuditH.setNoTaxCountMoney(cSrmTheNumberAuditHReq.getNoTaxMoneyCountMoney());
//            // ?????????
//            cSrmTheNumberAuditH.setCountTax(cSrmTheNumberAuditHReq.getSumTax());
//            // ???????????????
//            cSrmTheNumberAuditH.setTaxMoneyCountMoney(cSrmTheNumberAuditHReq.getTaxMoneyCountMoney());
//            // ????????????
//            cSrmTheNumberAuditH.setInventoryOrganization(cSrmTheNumberAuditHReq.getShippingAddress());
//            cSrmTheNumberAuditH.setCreateTime(new Date());
//            cSrmTheNumberAuditHMapper.insertSelective(cSrmTheNumberAuditH);
//
//            // ???????????????????????????????????????????????????
//            boolean flag = cSrmTheNumberAuditHReq.getReqList().stream().allMatch(e -> EqualsUtil.StringEqualsNull(e.getTheNumberOfHeInvoiceApplication()));
//            if (!flag) {
//                // ???????????????????????????
//                cSrmTheNumberAuditHReq.getReqList().forEach(item -> {
//                    item.setTheNumberOfHeInvoiceApplication(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication());
//                });
//            }
//            // ?????????????????????????????????????????????
//            cSrmTheNumberAuditRMapper.batchInsert(cSrmTheNumberAuditHReq.getReqList());
//        } else {
//            return Rjson.error("?????????????????????????????????");
//        }
//        return Rjson.success("????????????", null);
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
        // ?????????????????????
        CSrmTheNumberAuditHRsp rsp = new CSrmTheNumberAuditHRsp();

        CSrmTheNumberAuditH cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
        cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
        // ???????????????
        List<CSrmTheNumberAuditHRsp> cSrmTheNumberAuditHS = cSrmTheNumberAuditHMapper.selectByPrimaryList(cSrmTheNumberAuditH);
        if (cSrmTheNumberAuditHS.size() == 1) {
            cSrmTheNumberAuditHS.forEach(item -> {
                BeanUtils.copyProperties(item, rsp);
            });
        }

        CSrmTheNumberAuditR cSrmTheNumberAuditR = new CSrmTheNumberAuditR();
        cSrmTheNumberAuditR.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
        // ???????????????
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
            return Rjson.error("??????????????????");
        } else {
            // ???????????????
            BeanUtils.copyProperties(noConsignmentInvoiceH, rsp);
        }
        if (!CollectionUtils.isEmpty(req.getLineItemNo())) {
            List<NoConsignmentInvoiceRRsp> noConsignmentInvoiceR = cSrmTheNumberAuditRMapper.findNoConsignmentInvoiceR(req);
            // ???????????????????????????
            rsp.setNoTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getNoTaxMoneyMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
            // ???????????????
            rsp.setSumTax(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getTax).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
            // ?????????????????????
            rsp.setTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getUnitMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
            PageHelper.startPage(req.getPageNum(), req.getPageSize());
            rsp.setRspList(new PageInfo<>(noConsignmentInvoiceR));
        }
        // ???????????????????????????????????????
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
        // ?????????????????????????????????
        if (null == cSrmSupplierMapper.selectSupplierCode(req.getSupplierCode())) {
            return Rjson.error("????????????????????????");
        }
        // ????????????????????????????????????????????????
        if (StringUtil.eqNu(req.getDeliveryNumber()) || StringUtil.eqNu(req.getPurchaseRequestNo())) {
            CSrmSendCommodityH commodityH = cSrmSendCommodityHMapper.selectByPrimaryDevStatus(req);
            if (null == commodityH) {
                return Rjson.success("????????????", null);
            } else {
                if (!("5").equals(commodityH.getStatus())) {
                    return Rjson.error("?????????????????????????????????");
                }

            }
        }
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmTheNumberAuditHMapper.findCreateApplicationOrder(req)));

    }

    @Override
    public Rjson findNoConsignmentInvoiceBatchHR(CSrmTheNumberAuditHReq req) {
        // ?????????????????????
        JSONObject json = new JSONObject();
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<CSrmTheNumberAuditRRsp> rspList = cSrmTheNumberAuditRMapper.selectByPrimaryListRList(req);
        // ????????????
        json.put("status", "1");
        // ?????????????????????
        json.put("taxMoneyCountMoney", rspList.stream().map(CSrmTheNumberAuditRRsp::getUnitMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
        // ???????????????
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
            // ??????????????????
            CSrmTheNumberAuditH cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
            cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
            cSrmTheNumberAuditH.setIsDelete(true);
            cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);
            // ??????????????????
            CSrmTheNumberAuditR cSrmTheNumberAuditR = new CSrmTheNumberAuditR();
            cSrmTheNumberAuditR.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
            cSrmTheNumberAuditR.setDelete(true);
            cSrmTheNumberAuditRMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditR);
        }

        // ????????????????????????
        if (StringUtil.eqNu(req.getInvoiceReceivableNo())) {
            // ???????????????
            CSrmInvoiceReceivable cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
            cSrmInvoiceReceivable.setIsDelete(true);
            cSrmInvoiceReceivable.setInvoiceReceivableNo(req.getInvoiceReceivableNo());
            cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(cSrmInvoiceReceivable);
            // ???????????????
            CSrmInvoiceReceivableR receivableR = new CSrmInvoiceReceivableR();
            receivableR.setIsDelete(true);
            receivableR.setInvoiceReceivableNo(req.getInvoiceReceivableNo());
            cSrmInvoiceReceivableRMapper.updateByPrimaryKeySelective(receivableR);
        }



        if (!CollectionUtils.isEmpty(req.getUpdateList())) {
            for (CSrmTheNumberAuditRRsp item : req.getUpdateList()) {
                // ??????????????????????????? ??????????????????????????????????????? ?????????
                CSrmTheNumberAuditH srmTheNumberAuditH = new CSrmTheNumberAuditH();
                srmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(item.getTheNumberOfHeInvoiceApplication());
                srmTheNumberAuditH.setIsCreated(0);
                cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(srmTheNumberAuditH);
            }
        }


        return Rjson.success();
    }
}




