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
        // 转换付款时间
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
            // 校验供应商编码是否存在
            CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmInvoiceReceivableReq.getSupplierCode());
            if (cSrmSupplier == null) {
                return Rjson.error("创建失败，供应商代码不存在");
            } else {
                // 生成应收发票编号（以INV开头+年月日+3位流水号）
                cSrmInvoiceReceivable = cSrmInvoiceReceivableMapper.selectFinallyData();
                String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                if (cSrmInvoiceReceivable == null) {
                    // 未找到数据，从最新一条开始
                    cSrmInvoiceReceivableReq.setInvoiceReceivableNo("INV" + yyyyMMdd + 100);
                } else {
                    int invoiceReceivableNo = Integer.parseInt(cSrmInvoiceReceivable.getInvoiceReceivableNo().substring(11)) + 1;
                    cSrmInvoiceReceivableReq.setInvoiceReceivableNo("INV" + yyyyMMdd + invoiceReceivableNo);
                }

                // 新增应收发票头表
                cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
                BeanUtils.copyProperties(cSrmInvoiceReceivableReq, cSrmInvoiceReceivable);
                // 开票日期
                if (StringUtil.eqNu(cSrmInvoiceReceivableReq.getDateOfIssue())) {
                    cSrmInvoiceReceivable.setDateOfIssue(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmInvoiceReceivableReq.getDateOfIssue()));
                }
                // 创建时间
                cSrmInvoiceReceivable.setCreateTime(new Date());
                // 前端回显创建时间
//                cSrmInvoiceReceivableReq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmInvoiceReceivable.getCreateTime()));
                // 付款时间
//                cSrmInvoiceReceivable.setPayTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmInvoiceReceivableReq.getPayTime()));
                // 确认时间
//                cSrmInvoiceReceivable.setAcknowledgingTime(new Date());
                // 前端回显确认时间
//                cSrmInvoiceReceivableReq.setAcknowledgingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmInvoiceReceivable.getPayTime()));

                cSrmInvoiceReceivableMapper.insertSelective(cSrmInvoiceReceivable);

                // 更新创建开票申请状态
                CSrmTheNumberAuditH cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
                cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(cSrmInvoiceReceivableReq.getInvoiceApplicationNumber());
                // 更改为已创建开票申请单 (0.未创建 1.已创建)
                cSrmTheNumberAuditH.setIsCreated(1);
                cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);


                // 新增应收发票行表
                if (!CollectionUtils.isEmpty(cSrmInvoiceReceivableReq.getInvoiceReceivableNoList())) {
                    // 新增应收发票行表
                    for (CSrmInvoiceReceivableRReq item : cSrmInvoiceReceivableReq.getInvoiceReceivableNoList()) {
                        CSrmInvoiceReceivableR receivableR = new CSrmInvoiceReceivableR();
                        item.setInvoiceReceivableNo(cSrmInvoiceReceivableReq.getInvoiceReceivableNo());
                        BeanUtils.copyProperties(item, receivableR);
                        receivableR.setCreateTime(new Date());
                        srmInvoiceReceivableRMapper.insertSelective(receivableR);
                    }


                }
            }

            return Rjson.success("创建成功", cSrmInvoiceReceivableReq);
        } else if (("2").equals(cSrmInvoiceReceivableReq.getOperationSign())) {
            // 校验应收发票编号是否存在
            cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
            cSrmInvoiceReceivable.setInvoiceReceivableNo(cSrmInvoiceReceivableReq.getInvoiceReceivableNo());
            cSrmInvoiceReceivable = cSrmInvoiceReceivableMapper.selectByPrimaryKey(cSrmInvoiceReceivable);
            if (cSrmInvoiceReceivable == null) {
                return Rjson.error("更新失败，应收发票编号不存在");
            } else {
                // 修改应收发票表
                cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
                BeanUtils.copyProperties(cSrmInvoiceReceivableReq, cSrmInvoiceReceivable);
                cSrmInvoiceReceivable.setUpdateTime(new Date());
                // 前端回显更新时间
                cSrmInvoiceReceivableReq.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmInvoiceReceivable.getUpdateTime()));

                cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(cSrmInvoiceReceivable);

                return Rjson.success("更新成功", cSrmInvoiceReceivableReq);
            }

        } else if (("3").equals(cSrmInvoiceReceivableReq.getOperationSign())) {


            // 状态(1.新建2.待审核3.待付款4.待收款5.已完成)
            // OA审批回调类型(通过,驳回,撤销)
            if (StringUtil.eqNu(cSrmInvoiceReceivableReq.getName())) {
                System.err.println("name的值" + cSrmInvoiceReceivableReq.getName());
                if ((CommonUtils.getRedisValue(redisCache,SrmFinal.PASS,"SRM OA审批回调类型：通过")).equals(cSrmInvoiceReceivableReq.getName())) {
                    cSrmInvoiceReceivableReq.setStatus("3");

                } else if ((CommonUtils.getRedisValue(redisCache,SrmFinal.REJECT,"SRM OA审批回调类型：驳回")).equals(cSrmInvoiceReceivableReq.getName())) {
                    cSrmInvoiceReceivableReq.setStatus("2");

                } else if ((CommonUtils.getRedisValue(redisCache,SrmFinal.REVERSAL,"SRM OA审批回调类型：撤销")).equals(cSrmInvoiceReceivableReq.getName())) {
                    cSrmInvoiceReceivableReq.setStatus("2");
                }
            }
            // 校验应收发票编号是否存在
            cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
            cSrmInvoiceReceivable.setInvoiceReceivableNo(cSrmInvoiceReceivableReq.getInvoiceReceivableNo());
            cSrmInvoiceReceivable = cSrmInvoiceReceivableMapper.selectByPrimaryKey(cSrmInvoiceReceivable);
            if (cSrmInvoiceReceivable == null) {
                return Rjson.error("更新失败，应收发票编号不存在");
            } else {
                // 修改应收发票头表
                cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
                BeanUtils.copyProperties(cSrmInvoiceReceivableReq, cSrmInvoiceReceivable);
                cSrmInvoiceReceivable.setUpdateTime(new Date());
                cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(cSrmInvoiceReceivable);


                // 应收发票单状态为 3.待付款
                if (("3").equals(cSrmInvoiceReceivableReq.getStatus())) {
                    // 校验是否需要将应收开票单推送至K3
                    if (StringUtil.eqNu(cSrmInvoiceReceivableReq.getServiceType()) && cSrmInvoiceReceivableReq.getPush()) {

						if (CommonUtils.getRedisValue(redisCache,SrmFinal.K_THREE_SERVICE_TYPE,"推送K3服务凭证").equals(String.valueOf(cSrmInvoiceReceivableReq.getServiceType()))) {
                            // 推送至K3
                            log.info("----开始推送'应收发票单'至K3----");
                            // 新增
                            // 封装头请求参数
                            List<KThreePOInvoiceHReq> addList = new ArrayList<>();
                            // 封装行请求参数
                            List<KThreePOInvoiceRReq> addItemsList = new ArrayList<>();

                            // 赋值应收发票单头数据
                            KThreePOInvoice threePOInvoice = new KThreePOInvoice();
                            // 赋值应收发票头数据  触发与K3字段映射值
                            BeanUtils.copyProperties(cSrmInvoiceReceivableReq, threePOInvoice);
                            KThreePOInvoiceHReq threePOInvoiceReq = new KThreePOInvoiceHReq();
                            BeanUtils.copyProperties(threePOInvoice, threePOInvoiceReq);

                            // 创建时间
                            threePOInvoiceReq.setcDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmInvoiceReceivableReq.getCreateTime()));
                            // 修改时间
                            threePOInvoiceReq.setmDate(new SimpleDateFormat("yyyy-MM-dd").format(cSrmInvoiceReceivable.getUpdateTime()));
                            addList.add(threePOInvoiceReq);

                            // 赋值头数据映射
                            BeanUtils.copyProperties(cSrmInvoiceReceivableReq, threePOInvoice);


                            // 获取应收发票单行数据
                            for (CSrmInvoiceReceivableRReq item : cSrmInvoiceReceivableReq.getInvoiceReceivableNoList()) {
                                KThreePOInvoiceRReq kThreePOInvoiceRReq = new KThreePOInvoiceRReq();
                                // 赋值应收发票单行数据  触发与K3字段映射值
                                BeanUtils.copyProperties(item, threePOInvoice);
//                                // 行号
                                threePOInvoice.setLineItemNo(Integer.parseInt(item.getLineItemNo()));
                                // 数量
                                threePOInvoice.setCount(Integer.parseInt(item.getCount()));
                                // 订单行号
                                threePOInvoice.setPurLineItemNo(Integer.parseInt(item.getPurLineItemNo()));
                                // 含税单价
                                threePOInvoice.setUnitPrice(Integer.parseInt(item.getUnitPrice()));
                                // 价税合计
                                threePOInvoice.setPriceTaxSum(Integer.parseInt(item.getPriceTaxSum()));
                                // 税率(%)
                                threePOInvoice.setTaxRate(Integer.parseInt(item.getTaxRate()));

                                // 赋值
                                BeanUtils.copyProperties(threePOInvoice, kThreePOInvoiceRReq);
                                addItemsList.add(kThreePOInvoiceRReq);
                                // 赋值行数据
                                threePOInvoiceReq.setItems(addItemsList);
                            }

                            Map<String, Object> map = new HashMap<>();
                            map.put("jktype", "POInvoice");
                            map.put("method", "add");
                            map.put("data", addList);
							log.info("【推送应收发票单入参】[{}]", JSONUtil.toJsonStr(map.toString()));
                            KThreePOInvoiceResult threePOInvoiceResult = JSONObject.parseObject(srmSupplierTimer.sendPost(map), KThreePOInvoiceResult.class);
                            if (SrmFinal.SUCESS.equals(threePOInvoiceResult.getStatus())) {
								log.info("【应收发票单出参】[{}]", JSONUtil.toJsonStr(threePOInvoiceResult.toString()));
                            } else {
								log.error("【应收发票单出参】[{}]", JSONUtil.toJsonStr(threePOInvoiceResult.toString()));
                                throw new Exception("K3服务异常："+threePOInvoiceResult.toString());
                            }

                        }

                    }

                }


                return Rjson.success("更新成功", null);
            }
        } else {
            return Rjson.error("操作标识错误");
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
        // 查询行数据
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        rsp.setInvoiceReceivableNoList(new PageInfo<>(srmInvoiceReceivableRMapper.selectByPrimaryKey(req)));
        return Rjson.success(rsp);
    }

    @Override
    public Rjson delInvoiceReceivable(CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq) {

        // 删除头数据
        CSrmInvoiceReceivable cSrmInvoiceReceivable = new CSrmInvoiceReceivable();
        cSrmInvoiceReceivable.setIsDelete(true);
        cSrmInvoiceReceivable.setInvoiceReceivableNo(cSrmInvoiceReceivableReq.getInvoiceReceivableNo());
        cSrmInvoiceReceivableMapper.updateByPrimaryKeySelective(cSrmInvoiceReceivable);
        // 删除行数据
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
//            return Rjson.error("订单号不存在");
//        } else {
//            // 获取头数据
//            BeanUtils.copyProperties(noConsignmentInvoiceH, rsp);
//        }
//        if (!CollectionUtils.isEmpty(req.getLineItemNo())) {
//            List<NoConsignmentInvoiceRRsp> noConsignmentInvoiceR = cSrmInvoiceReceivableMapper.findNoConsignmentInvoiceR(req);
//            // 赋值总不含税总金额
//            rsp.setNoTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getNoTaxMoneyMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
//            // 赋值总税额
//            rsp.setSumTax(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getTax).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
//            // 赋值含税总金额
//            rsp.setTaxMoneyCountMoney(noConsignmentInvoiceR.stream().map(NoConsignmentInvoiceRRsp::getUnitMoney).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
//            PageHelper.startPage(req.getPageNum(), req.getPageSize());
//            rsp.setRspList(new PageInfo<>(noConsignmentInvoiceR));
//        }
//        // 设置状态为新建并返回给前端
//        rsp.setStatus("1");
//        rsp.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        List<NoConsignmentInvoiceHRsp> list = new ArrayList<>();
//        list.add(rsp);
//        return Rjson.success(list);
//
//
//    }

}






