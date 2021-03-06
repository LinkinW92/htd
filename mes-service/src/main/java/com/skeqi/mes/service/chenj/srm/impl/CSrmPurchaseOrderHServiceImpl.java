package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseOrderHFindReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseOrderHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseOrderRReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSendCommodityRReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseOrderHRsp;
import com.skeqi.mes.service.chenj.srm.CSrmPurchaseOrderHService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.chenj.EqualsPoJoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseOrderHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmPurchaseOrderHServiceImpl implements CSrmPurchaseOrderHService {

    @Resource
    private CSrmPurchaseOrderHMapper cSrmPurchaseOrderHMapper;

    @Resource
    private CSrmPurchaseDemandHMapper cSrmPurchaseDemandHMapper;

    @Resource
    private CSrmPurchaseOrderRMapper cSrmPurchaseOrderRMapper;

    @Resource
    private CSrmSupplierMapper cSrmSupplierMapper;
    @Resource
    private CSrmCompanyMapper cSrmCompanyMapper;

    @Resource
    private CSrmContractHMapper cSrmContractHMapper;

    @Resource
    private CSrmSendCommodityHMapper cSrmSendCommodityHMapper;

    @Resource
    private CSrmSendCommodityRMapper cSrmSendCommodityRMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmPurchaseOrderHMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmPurchaseOrderH record) {
        return cSrmPurchaseOrderHMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmPurchaseOrderH record) {
        return cSrmPurchaseOrderHMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmPurchaseOrderH record) {
        return cSrmPurchaseOrderHMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmPurchaseOrderH record) {
        return cSrmPurchaseOrderHMapper.insertSelective(record);
    }

    @Override
    public List<CSrmPurchaseOrderH> selectByPrimaryKey(CSrmPurchaseOrderH record) {
        return cSrmPurchaseOrderHMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmPurchaseOrderH record) {
        return cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmPurchaseOrderH record) {
        return cSrmPurchaseOrderHMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmPurchaseOrderH> list) {
        return cSrmPurchaseOrderHMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmPurchaseOrderH> list) {
        return cSrmPurchaseOrderHMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmPurchaseOrderH> list) {
        return cSrmPurchaseOrderHMapper.batchInsert(list);
    }

    @Override
    public Rjson updatePurchaseOrder(CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq) throws Exception {
        /**
         * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
         * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
         * ????????????????????????????????????????????????PO??????+?????????+3???????????????,??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????ERP???????????????????????????????????????????????????
         */
        // ??????
        CSrmPurchaseOrderH cSrmPurchaseOrderH = null;
        if (("1").equals(cSrmPurchaseOrderHReq.getOperationSign())) {

            // ?????????????????????????????????
            if (null == cSrmSupplierMapper.selectSupplierCode(cSrmPurchaseOrderHReq.getSupplierCode())) {
                return Rjson.error("????????????????????????");
            }
            // ??????????????????????????????
            if (null == cSrmCompanyMapper.selectCompanyCode(cSrmPurchaseOrderHReq.getCompany())) {
                return Rjson.error("?????????????????????");
            }
            // ??????????????????????????????
            CSrmContractH contractH = new CSrmContractH();
            contractH.setContractNo(cSrmPurchaseOrderHReq.getContractNo());
            if (cSrmContractHMapper.selectByPrimaryKey(contractH).size() == 0) {
                return Rjson.error("?????????????????????");
            }

            // ????????????????????????????????????????????????
            CSrmPurchaseOrderRReq cSrmPurchaseOrderRReq = cSrmPurchaseOrderHReq.getPurList().stream().findFirst().orElse(null);
            if (cSrmPurchaseOrderRReq != null) {
                if (!StringUtil.eqNu(cSrmPurchaseOrderRReq.getPurchaseRequestNo())) {
                    return Rjson.error("'??????????????????'????????????");
                }
                CSrmPurchaseDemandH cSrmPurchaseDemandH = new CSrmPurchaseDemandH();
                cSrmPurchaseDemandH.setRequestCode(cSrmPurchaseOrderRReq.getPurchaseRequestNo());
                CSrmPurchaseDemandH purchaseDemandH = cSrmPurchaseDemandHMapper.selectByPrimaryKey(cSrmPurchaseDemandH);
                if (null == purchaseDemandH) {
                    return Rjson.error("??????????????????????????????????????????");
                } else if (!("4").equals(purchaseDemandH.getStatus()) && !("6").equals(purchaseDemandH.getStatus())) {
                    return Rjson.error("????????????????????????????????????????????????????????????????????????");
                }

            }
            // ???????????????????????????????????????PO??????+?????????+3???????????????
            cSrmPurchaseOrderH = cSrmPurchaseOrderHMapper.selectFinallyData();
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (cSrmPurchaseOrderH == null) {
                // ???????????????????????????????????????
                cSrmPurchaseOrderHReq.setOrderNumber("PO" + yyyyMMdd + 100);
            } else {
                int requestCode = Integer.parseInt(cSrmPurchaseOrderH.getOrderNumber().substring(10)) + 1;
                cSrmPurchaseOrderHReq.setOrderNumber("PO" + yyyyMMdd + requestCode);
            }

            // ??????????????????
            cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
            BeanUtils.copyProperties(cSrmPurchaseOrderHReq, cSrmPurchaseOrderH);
            // ????????????
            cSrmPurchaseOrderH.setCreateTime(new Date());
            // ???????????????????????????
            cSrmPurchaseOrderHReq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmPurchaseOrderH.getCreateTime()));
            cSrmPurchaseOrderHMapper.insertSelective(cSrmPurchaseOrderH);
            if (!CollectionUtils.isEmpty(cSrmPurchaseOrderHReq.getPurList())) {
                // ??????????????????
                for (CSrmPurchaseOrderRReq item : cSrmPurchaseOrderHReq.getPurList()) {
                    // ????????????
                    EqualsPoJoUtil.string(item.getCount(), "??????");
                    EqualsPoJoUtil.string(item.getUnit(), "??????");
                    EqualsPoJoUtil.string(item.getUnitPrice(), "???????????????");
                    EqualsPoJoUtil.string(item.getTaxRate(), "??????");
                    EqualsPoJoUtil.dateYmd(item.getExpectedDateOfArrival(), "??????????????????");
                    // ??????????????????
                    CSrmPurchaseOrderR srmPurchaseOrderR = cSrmPurchaseOrderRMapper.selectFinallyData(cSrmPurchaseOrderHReq.getOrderNumber());
                    if (srmPurchaseOrderR == null) {
                        item.setLineItemNo("1");
                    } else {
                        int lineItemNo = Integer.parseInt(srmPurchaseOrderR.getLineItemNo()) + 1;
                        item.setLineItemNo(String.valueOf(lineItemNo));
                    }
                    CSrmPurchaseOrderR cSrmPurchaseOrderR = new CSrmPurchaseOrderR();
                    BeanUtils.copyProperties(item, cSrmPurchaseOrderR);
                    cSrmPurchaseOrderR.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
                    // ??????????????????
                    if (StringUtil.eqNu(item.getExpectedDateOfArrival())) {
                        cSrmPurchaseOrderR.setExpectedDateOfArrival(new SimpleDateFormat("yyyy-MM-dd").parse(item.getExpectedDateOfArrival()));
                    }

                    // ??????????????????
                    cSrmPurchaseOrderR.setPriceTaxSum((new BigDecimal(item.getCount()).multiply(new BigDecimal(item.getUnitPrice()))).toString());
                    cSrmPurchaseOrderR.setCreateTime(new Date());
                    cSrmPurchaseOrderRMapper.insertSelective(cSrmPurchaseOrderR);
                }
            }


            return Rjson.success("????????????", cSrmPurchaseOrderHReq);
        } else if (("2").equals(cSrmPurchaseOrderHReq.getOperationSign())) {
            // ???????????????????????????
            cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
            cSrmPurchaseOrderH.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
            List<CSrmPurchaseOrderH> selectByPrimaryKey = cSrmPurchaseOrderHMapper.selectByPrimaryKey(cSrmPurchaseOrderH);
            if (selectByPrimaryKey.size() < 1) {
                return Rjson.error("?????????????????????????????????");
            } else {
                // ??????????????????
                cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
                BeanUtils.copyProperties(cSrmPurchaseOrderHReq, cSrmPurchaseOrderH);
                // ????????????
                cSrmPurchaseOrderH.setUpdateTime(new Date());
                // ??????????????????|?????????????????????
                cSrmPurchaseOrderHReq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(selectByPrimaryKey.get(0).getCreateTime()));
                cSrmPurchaseOrderHReq.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmPurchaseOrderH.getUpdateTime()));
                cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(cSrmPurchaseOrderH);
                // ??????????????????
                for (CSrmPurchaseOrderRReq item : cSrmPurchaseOrderHReq.getPurList()) {
                    // ????????????
                    EqualsPoJoUtil.string(item.getCount(), "??????");
                    EqualsPoJoUtil.string(item.getUnit(), "??????");
                    EqualsPoJoUtil.string(item.getUnitPrice(), "??????");
                    EqualsPoJoUtil.string(item.getTaxRate(), "??????");
                    EqualsPoJoUtil.dateYmd(item.getExpectedDateOfArrival(), "??????????????????");
                    // ?????????????????????????????????????????????????????????????????????
                    if (!StringUtil.eqNu(item.getLineItemNo())) {
                        CSrmPurchaseOrderR srmPurchaseOrderR = cSrmPurchaseOrderRMapper.selectFinallyData(cSrmPurchaseOrderHReq.getOrderNumber());
                        if (srmPurchaseOrderR == null) {
                            item.setLineItemNo("1");
                        } else {
                            int lineItemNo = Integer.parseInt(srmPurchaseOrderR.getLineItemNo()) + 1;
                            item.setLineItemNo(String.valueOf(lineItemNo));
                        }
                        CSrmPurchaseOrderR cSrmPurchaseOrderR = new CSrmPurchaseOrderR();
                        BeanUtils.copyProperties(item, cSrmPurchaseOrderR);
                        // ??????????????????
                        cSrmPurchaseOrderR.setExpectedDateOfArrival(new SimpleDateFormat("yyyy-MM-dd").parse(item.getExpectedDateOfArrival()));
                        cSrmPurchaseOrderR.setCreateTime(new Date());
                        // ?????????????????????
                        cSrmPurchaseOrderR.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
                        // ??????????????????
                        cSrmPurchaseOrderR.setPriceTaxSum(String.valueOf(Integer.parseInt(item.getCount()) * Integer.parseInt(item.getUnitPrice())));
                        cSrmPurchaseOrderRMapper.insertSelective(cSrmPurchaseOrderR);

                    } else {
                        CSrmPurchaseOrderR cSrmPurchaseOrderR = new CSrmPurchaseOrderR();
                        BeanUtils.copyProperties(item, cSrmPurchaseOrderR);
                        // ??????????????????
                        cSrmPurchaseOrderR.setExpectedDateOfArrival(new SimpleDateFormat("yyyy-MM-dd").parse(item.getExpectedDateOfArrival()));
                        cSrmPurchaseOrderR.setUpdateTime(new Date());
                        // ?????????????????????
                        cSrmPurchaseOrderR.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());

                        // ??????????????????
                        cSrmPurchaseOrderR.setPriceTaxSum((new BigDecimal(item.getCount()).multiply(new BigDecimal(item.getUnitPrice()))).toString());
                        cSrmPurchaseOrderRMapper.updateByPrimaryKeySelective(cSrmPurchaseOrderR);
                    }

                }

                return Rjson.success("????????????", cSrmPurchaseOrderHReq);
            }

        } else if (("3").equals(cSrmPurchaseOrderHReq.getOperationSign())) {
            // ???????????????????????????
            cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
            cSrmPurchaseOrderH.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
            List<CSrmPurchaseOrderH> selectByPrimaryKey = cSrmPurchaseOrderHMapper.selectByPrimaryKey(cSrmPurchaseOrderH);
            if (selectByPrimaryKey.size() < 1) {
                return Rjson.error("?????????????????????????????????");
            } else {
                // ?????????????????????
                cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
                BeanUtils.copyProperties(cSrmPurchaseOrderHReq, cSrmPurchaseOrderH);
                cSrmPurchaseOrderH.setUpdateTime(new Date());
                cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(cSrmPurchaseOrderH);
                return Rjson.success("????????????", null);
            }
        } else {
            return Rjson.error("??????????????????");
        }
    }


    @Override
    public Rjson findPurchaseOrderHR(CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq) {
        CSrmPurchaseOrderHRsp rsp = null;
        CSrmPurchaseOrderH cSrmPurchaseOrderH = null;
        List<CSrmPurchaseOrderHRsp> rspList = null;
        CSrmPurchaseOrderR cSrmPurchaseOrderR = null;
        rsp = new CSrmPurchaseOrderHRsp();
        rspList = new ArrayList<>();
        // ???????????????????????????
        cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
        cSrmPurchaseOrderH.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
        cSrmPurchaseOrderH.setBuyerType(cSrmPurchaseOrderHReq.getBuyerType());
        // ???????????????(1.SRM???????????????2.K3?????????)
        CSrmPurchaseOrderHRsp orderHRsp = null;
        if (("1").equals(cSrmPurchaseOrderHReq.getBuyerType())) {
            orderHRsp = cSrmPurchaseOrderHMapper.selectCSrmPurchaseOrderH(cSrmPurchaseOrderH);
        } else if (("2").equals(cSrmPurchaseOrderHReq.getBuyerType())) {
            orderHRsp = cSrmPurchaseOrderHMapper.selectCSrmPurchaseOrderHKThree(cSrmPurchaseOrderH);
        }

        if (null == orderHRsp) {
            return Rjson.success();
        } else {
            BeanUtils.copyProperties(orderHRsp, rsp);
            // ???????????????????????????
            cSrmPurchaseOrderR = new CSrmPurchaseOrderR();
            cSrmPurchaseOrderR.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
            PageHelper.startPage(cSrmPurchaseOrderHReq.getPageNum(), cSrmPurchaseOrderHReq.getPageSize());
            rsp.setLineItemNoList(new PageInfo<>(cSrmPurchaseOrderRMapper.selectByPrimaryList(cSrmPurchaseOrderR)));
//            if (("1").equals(cSrmPurchaseOrderHReq.getBuyerType())) {
//                rsp.setLineItemNoList(new PageInfo<>(cSrmPurchaseOrderRMapper.selectByPrimaryList(cSrmPurchaseOrderR)));
//            } else if (("2").equals(cSrmPurchaseOrderHReq.getBuyerType())) {
//                rsp.setLineItemNoList(new PageInfo<>(cSrmPurchaseOrderRMapper.selectByPrimaryList(cSrmPurchaseOrderR)));
//            }
            rspList.add(rsp);
        }
        return Rjson.success(rspList);
    }


    @Override
    public Rjson findPurchaseOrderH(CSrmPurchaseOrderHFindReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmPurchaseOrderHMapper.selectByPrimaryList(req)));
    }

    @Override
    public Rjson findPurchaseOrderR(CSrmPurchaseOrderHFindReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmPurchaseOrderRMapper.selectByPrimaryRowList(req)));
    }

    @Override
    public Rjson delPurchaseOrder(CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq) {
        if (StringUtil.eqNu(cSrmPurchaseOrderHReq.getOrderNumber())) {
            // ??????????????????
            CSrmPurchaseOrderH srmPurchaseOrderH = new CSrmPurchaseOrderH();
            srmPurchaseOrderH.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
            srmPurchaseOrderH.setDelete(true);
            cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(srmPurchaseOrderH);
            // ??????????????????
            CSrmPurchaseOrderR srmPurchaseOrderR = new CSrmPurchaseOrderR();
            srmPurchaseOrderR.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
            srmPurchaseOrderR.setDelete(true);
            cSrmPurchaseOrderRMapper.updateByPrimaryKeySelective(srmPurchaseOrderR);
        }


        // ???????????????
        if (StringUtil.eqNu(cSrmPurchaseOrderHReq.getDeliveryNumber())) {
            // ??????????????????
            CSrmSendCommodityH cSrmSendCommodityH = new CSrmSendCommodityH();
            cSrmSendCommodityH.setDeliveryNumber(cSrmPurchaseOrderHReq.getDeliveryNumber());
            cSrmSendCommodityH.setDelete(true);
            cSrmSendCommodityHMapper.updateByPrimaryKeySelective(cSrmSendCommodityH);
            // ??????????????????
            CSrmSendCommodityR cSrmSendCommodityR = new CSrmSendCommodityR();
            cSrmSendCommodityR.setDeliveryNumber(cSrmPurchaseOrderHReq.getDeliveryNumber());
            cSrmSendCommodityR.setDelete(true);
            cSrmSendCommodityRMapper.updateByPrimaryKeySelective(cSrmSendCommodityR);

        }

        if (!CollectionUtils.isEmpty(cSrmPurchaseOrderHReq.getOrderNumberList())) {
            for (CSrmSendCommodityRReq item : cSrmPurchaseOrderHReq.getOrderNumberList()) {
                // ??????????????????????????? ??????????????????????????? ?????????
                CSrmPurchaseOrderH srmPurchaseOrderH = new CSrmPurchaseOrderH();
                srmPurchaseOrderH.setOrderNumber(item.getPurchaseOrderNo());
                srmPurchaseOrderH.setIsOpenTicket(0);
                cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(srmPurchaseOrderH);
            }

        }
        return Rjson.success();
    }
}


