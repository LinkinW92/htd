package com.skeqi.mes.service.chenj.srm.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.common.utils.redis.RedisCache;
import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.finals.SrmFinal;
import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.CSrmInvoiceReceivable;
import com.skeqi.mes.pojo.chenj.srm.CSrmInvoiceReceivableR;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplier;
import com.skeqi.mes.pojo.chenj.srm.CSrmTheNumberAuditH;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOInvoice;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOInvoiceHReq;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOInvoiceRReq;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOInvoiceResult;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableRReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmInvoiceReceivableRsp;
import com.skeqi.mes.service.chenj.srm.CSrmInvoiceReceivableService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.chenj.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmInvoiceReceivableServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CSrmInvoiceReceivableServiceImpl implements CSrmInvoiceReceivableService {

    @Resource
    private CSrmInvoiceReceivableMapper cSrmInvoiceReceivableMapper;

    @Resource
    private CSrmInvoiceReceivableRMapper srmInvoiceReceivableRMapper;

    @Resource
    private CSrmTheNumberAuditHMapper cSrmTheNumberAuditHMapper;

    @Resource
    private CSrmSupplierMapper cSrmSupplierMapper;


    @Resource
    private CSrmMakeOutAnInvoiceMapper cSrmMakeOutAnInvoiceMapper;

	@Autowired
	private RedisCache redisCache;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmInvoiceReceivableMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CSrmInvoiceReceivable record) {
        return cSrmInvoiceReceivableMapper.insertSelective(record);
    }

    @Override
    public CSrmInvoiceReceivable selectByPrimaryKey(CSrmInvoiceReceivable record) {
        return cSrmInvoiceReceivableMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmInvoiceReceivable record) {
        return cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Rjson updateByPrimaryKeyKThree(CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq) throws ParseException {

        CSrmInvoiceReceivable cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
        BeanUtils.copyProperties(cSrmInvoiceReceivableReq, cSrmInvoiceReceivable);
        // ??????????????????
        if (StringUtil.eqNu(cSrmInvoiceReceivableReq.getPayTime())) {
            cSrmInvoiceReceivable.setPayTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmInvoiceReceivableReq.getPayTime()));
        }
        cSrmInvoiceReceivable.setUpdateTime(new Date());
        cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(cSrmInvoiceReceivable);
        return Rjson.success();
    }

    @Override
    public int updateBatchSelective(List<CSrmInvoiceReceivable> list) {
        return cSrmInvoiceReceivableMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmInvoiceReceivable> list) {
        return cSrmInvoiceReceivableMapper.batchInsert(list);
    }

    @Override
    public Rjson updateInvoiceReceivable(CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq, SrmSupplierTimer srmSupplierTimer) throws Exception {


        CSrmInvoiceReceivable cSrmInvoiceReceivable = null;
        if (("1").equals(cSrmInvoiceReceivableReq.getOperationSign())) {
            // ?????????????????????????????????
            CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmInvoiceReceivableReq.getSupplierCode());
            if (cSrmSupplier == null) {
                return Rjson.error("???????????????????????????????????????");
            } else {
                // ??????????????????????????????INV??????+?????????+3???????????????
                cSrmInvoiceReceivable = cSrmInvoiceReceivableMapper.selectFinallyData();
                String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                if (cSrmInvoiceReceivable == null) {
                    // ???????????????????????????????????????
                    cSrmInvoiceReceivableReq.setInvoiceReceivableNo("INV" + yyyyMMdd + 100);
                } else {
                    int invoiceReceivableNo = Integer.parseInt(cSrmInvoiceReceivable.getInvoiceReceivableNo().substring(11)) + 1;
                    cSrmInvoiceReceivableReq.setInvoiceReceivableNo("INV" + yyyyMMdd + invoiceReceivableNo);
                }

                // ????????????????????????
                cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
                BeanUtils.copyProperties(cSrmInvoiceReceivableReq, cSrmInvoiceReceivable);
                // ????????????
                if (StringUtil.eqNu(cSrmInvoiceReceivableReq.getDateOfIssue())) {
                    cSrmInvoiceReceivable.setDateOfIssue(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmInvoiceReceivableReq.getDateOfIssue()));
                }
                // ????????????
                cSrmInvoiceReceivable.setCreateTime(new Date());
                // ????????????????????????
//                cSrmInvoiceReceivableReq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmInvoiceReceivable.getCreateTime()));
                // ????????????
//                cSrmInvoiceReceivable.setPayTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmInvoiceReceivableReq.getPayTime()));
                // ????????????
//                cSrmInvoiceReceivable.setAcknowledgingTime(new Date());
                // ????????????????????????
//                cSrmInvoiceReceivableReq.setAcknowledgingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmInvoiceReceivable.getPayTime()));

                cSrmInvoiceReceivableMapper.insertSelective(cSrmInvoiceReceivable);

                // ??????????????????????????????
                CSrmTheNumberAuditH cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
                cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmInvoiceReceivableReq.getInvoiceApplicationNumber());
                // ????????????????????????????????? (0.????????? 1.?????????)
                cSrmTheNumberAuditH.setIsCreated(1);
                cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);


                // ????????????????????????
                if (!CollectionUtils.isEmpty(cSrmInvoiceReceivableReq.getInvoiceReceivableNoList())) {
                    // ????????????????????????
                    for (CSrmInvoiceReceivableRReq item : cSrmInvoiceReceivableReq.getInvoiceReceivableNoList()) {
                        CSrmInvoiceReceivableR receivableR = new CSrmInvoiceReceivableR();
                        item.setInvoiceReceivableNo(cSrmInvoiceReceivableReq.getInvoiceReceivableNo());
                        BeanUtils.copyProperties(item, receivableR);
                        receivableR.setCreateTime(new Date());
                        srmInvoiceReceivableRMapper.insertSelective(receivableR);
                    }


                }
            }

            return Rjson.success("????????????", cSrmInvoiceReceivableReq);
        } else if (("2").equals(cSrmInvoiceReceivableReq.getOperationSign())) {
            // ????????????????????????????????????
            cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
            cSrmInvoiceReceivable.setInvoiceReceivableNo(cSrmInvoiceReceivableReq.getInvoiceReceivableNo());
            cSrmInvoiceReceivable = cSrmInvoiceReceivableMapper.selectByPrimaryKey(cSrmInvoiceReceivable);
            if (cSrmInvoiceReceivable == null) {
                return Rjson.error("??????????????????????????????????????????");
            } else {
                // ?????????????????????
                cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
                BeanUtils.copyProperties(cSrmInvoiceReceivableReq, cSrmInvoiceReceivable);
                cSrmInvoiceReceivable.setUpdateTime(new Date());
                // ????????????????????????
                cSrmInvoiceReceivableReq.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmInvoiceReceivable.getUpdateTime()));

                cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(cSrmInvoiceReceivable);

                return Rjson.success("????????????", cSrmInvoiceReceivableReq);
            }

        } else if (("3").equals(cSrmInvoiceReceivableReq.getOperationSign())) {


            // ??????(1.??????2.?????????3.?????????4.?????????5.?????????)
            // OA??????????????????(??????,??????,??????)
            if (StringUtil.eqNu(cSrmInvoiceReceivableReq.getName())) {
                System.err.println("name??????" + cSrmInvoiceReceivableReq.getName());
                if ((CommonUtils.getRedisValue(redisCache,SrmFinal.PASS,"SRM OA???????????????????????????")).equals(cSrmInvoiceReceivableReq.getName())) {
                    cSrmInvoiceReceivableReq.setStatus("3");

                } else if ((CommonUtils.getRedisValue(redisCache,SrmFinal.REJECT,"SRM OA???????????????????????????")).equals(cSrmInvoiceReceivableReq.getName())) {
                    cSrmInvoiceReceivableReq.setStatus("2");

                } else if ((CommonUtils.getRedisValue(redisCache,SrmFinal.REVERSAL,"SRM OA???????????????????????????")).equals(cSrmInvoiceReceivableReq.getName())) {
                    cSrmInvoiceReceivableReq.setStatus("2");
                }
            }
            // ????????????????????????????????????
            cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
            cSrmInvoiceReceivable.setInvoiceReceivableNo(cSrmInvoiceReceivableReq.getInvoiceReceivableNo());
            cSrmInvoiceReceivable = cSrmInvoiceReceivableMapper.selectByPrimaryKey(cSrmInvoiceReceivable);
            if (cSrmInvoiceReceivable == null) {
                return Rjson.error("??????????????????????????????????????????");
            } else {
                // ????????????????????????
                cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
                BeanUtils.copyProperties(cSrmInvoiceReceivableReq, cSrmInvoiceReceivable);
                cSrmInvoiceReceivable.setUpdateTime(new Date());
                cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(cSrmInvoiceReceivable);


                // ???????????????????????? 3.?????????
                if (("3").equals(cSrmInvoiceReceivableReq.getStatus())) {
                    // ?????????????????????????????????????????????K3
                    if (StringUtil.eqNu(cSrmInvoiceReceivableReq.getServiceType()) && cSrmInvoiceReceivableReq.getPush()) {

						if (CommonUtils.getRedisValue(redisCache,SrmFinal.K_THREE_SERVICE_TYPE,"??????K3????????????").equals(String.valueOf(cSrmInvoiceReceivableReq.getServiceType()))) {
                            // ?????????K3
                            log.info("----????????????'???????????????'???K3----");
                            // ??????
                            // ?????????????????????
                            List<KThreePOInvoiceHReq> addList = new ArrayList<>();
                            // ?????????????????????
                            List<KThreePOInvoiceRReq> addItemsList = new ArrayList<>();

                            // ??????????????????????????????
                            KThreePOInvoice threePOInvoice = new KThreePOInvoice();
                            // ???????????????????????????  ?????????K3???????????????
                            BeanUtils.copyProperties(cSrmInvoiceReceivableReq, threePOInvoice);
                            KThreePOInvoiceHReq threePOInvoiceReq = new KThreePOInvoiceHReq();
                            BeanUtils.copyProperties(threePOInvoice, threePOInvoiceReq);

                            // ????????????
                            threePOInvoiceReq.setcDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmInvoiceReceivableReq.getCreateTime()));
                            // ????????????
                            threePOInvoiceReq.setmDate(new SimpleDateFormat("yyyy-MM-dd").format(cSrmInvoiceReceivable.getUpdateTime()));
                            addList.add(threePOInvoiceReq);

                            // ?????????????????????
                            BeanUtils.copyProperties(cSrmInvoiceReceivableReq, threePOInvoice);


                            // ??????????????????????????????
                            for (CSrmInvoiceReceivableRReq item : cSrmInvoiceReceivableReq.getInvoiceReceivableNoList()) {
                                KThreePOInvoiceRReq kThreePOInvoiceRReq = new KThreePOInvoiceRReq();
                                // ??????????????????????????????  ?????????K3???????????????
                                BeanUtils.copyProperties(item, threePOInvoice);
//                                // ??????
                                threePOInvoice.setLineItemNo(Integer.parseInt(item.getLineItemNo()));
                                // ??????
                                threePOInvoice.setCount(Integer.parseInt(item.getCount()));
                                // ????????????
                                threePOInvoice.setPurLineItemNo(Integer.parseInt(item.getPurLineItemNo()));
                                // ????????????
                                threePOInvoice.setUnitPrice(Integer.parseInt(item.getUnitPrice()));
                                // ????????????
                                threePOInvoice.setPriceTaxSum(Integer.parseInt(item.getPriceTaxSum()));
                                // ??????(%)
                                threePOInvoice.setTaxRate(Integer.parseInt(item.getTaxRate()));

                                // ??????
                                BeanUtils.copyProperties(threePOInvoice, kThreePOInvoiceRReq);
                                addItemsList.add(kThreePOInvoiceRReq);
                                // ???????????????
                                threePOInvoiceReq.setItems(addItemsList);
                            }

                            Map<String, Object> map = new HashMap<>();
                            map.put("jktype", "POInvoice");
                            map.put("method", "add");
                            map.put("data", addList);
							log.info("?????????????????????????????????[{}]", JSONUtil.toJsonStr(map.toString()));
                            KThreePOInvoiceResult threePOInvoiceResult = JSONObject.parseObject(srmSupplierTimer.sendPost(map), KThreePOInvoiceResult.class);
                            if (SrmFinal.SUCESS.equals(threePOInvoiceResult.getStatus())) {
								log.info("???????????????????????????[{}]", JSONUtil.toJsonStr(threePOInvoiceResult.toString()));
                            } else {
								log.error("???????????????????????????[{}]", JSONUtil.toJsonStr(threePOInvoiceResult.toString()));
                                throw new Exception("K3???????????????"+threePOInvoiceResult.toString());
                            }

                        }

                    }

                }


                return Rjson.success("????????????", null);
            }
        } else {
            return Rjson.error("??????????????????");
        }

    }

    @Override
    public Rjson findInvoiceReceivableH(CSrmInvoiceReceivableReq req) {
        CSrmInvoiceReceivable cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
        BeanUtils.copyProperties(req, cSrmInvoiceReceivable);
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmInvoiceReceivableMapper.selectByPrimaryList(cSrmInvoiceReceivable)));
    }

    @Override
    public Rjson findInvoiceReceivableHR(CSrmInvoiceReceivableReq req) {
        CSrmInvoiceReceivableRsp rsp = new CSrmInvoiceReceivableRsp();
        CSrmInvoiceReceivable cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
        BeanUtils.copyProperties(req, cSrmInvoiceReceivable);
        CSrmInvoiceReceivableRsp srmInvoiceReceivableRsp = cSrmInvoiceReceivableMapper.selectByPrimaryKeyRsp(cSrmInvoiceReceivable);
        if (null != srmInvoiceReceivableRsp) {
            BeanUtils.copyProperties(srmInvoiceReceivableRsp, rsp);
        }
        // ???????????????
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        rsp.setInvoiceReceivableNoList(new PageInfo<>(srmInvoiceReceivableRMapper.selectByPrimaryKey(req)));
        return Rjson.success(rsp);
    }

    @Override
    public Rjson delInvoiceReceivable(CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq) {

        // ???????????????
        CSrmInvoiceReceivable cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
        cSrmInvoiceReceivable.setIsDelete(true);
        cSrmInvoiceReceivable.setInvoiceReceivableNo(cSrmInvoiceReceivableReq.getInvoiceReceivableNo());
        cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(cSrmInvoiceReceivable);
        // ???????????????
        CSrmInvoiceReceivableR receivableR = new CSrmInvoiceReceivableR();
        receivableR.setIsDelete(true);
        receivableR.setInvoiceReceivableNo(cSrmInvoiceReceivableReq.getInvoiceReceivableNo());
        srmInvoiceReceivableRMapper.updateByPrimaryKeySelective(receivableR);
        return Rjson.success();
    }

    //    @Override
//    public Rjson findNoConsignmentInvoiceHR(CSrmTheNumberAuditHReq req) {
//        NoConsignmentInvoiceHRsp rsp = new NoConsignmentInvoiceHRsp();
//        NoConsignmentInvoiceHRsp noConsignmentInvoiceH = cSrmInvoiceReceivableMapper.findNoConsignmentInvoiceH(req);
//        if (null == noConsignmentInvoiceH) {
//            return Rjson.error("??????????????????");
//        } else {
//            // ???????????????
//            BeanUtils.copyProperties(noConsignmentInvoiceH, rsp);
//        }
//        if (!CollectionUtils.isEmpty(req.getLineItemNo())) {
//            List<NoConsignmentInvoiceRRsp> noConsignmentInvoiceR = cSrmInvoiceReceivableMapper.findNoConsignmentInvoiceR(req);
//            // ???????????????????????????
//            rsp.setNoTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getNoTaxMoneyMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
//            // ???????????????
//            rsp.setSumTax(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getTax).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
//            // ?????????????????????
//            rsp.setTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getUnitMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
//            PageHelper.startPage(req.getPageNum(), req.getPageSize());
//            rsp.setRspList(new PageInfo<>(noConsignmentInvoiceR));
//        }
//        // ???????????????????????????????????????
//        rsp.setStatus("1");
//        rsp.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        List<NoConsignmentInvoiceHRsp> list = new ArrayList<>();
//        list.add(rsp);
//        return Rjson.success(list);
//
//
//    }

}






