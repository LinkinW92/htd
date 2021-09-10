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
         * 输入：订单号、订单类型、创建人、创建时间、采购员、公司、供应商、币种、状态、
         * 付款方式、行项目号、物料编码、数量、单位、单价、税率、收货地址、预计到货日期、采购申请号、操作标识
         * 处理：订单号为空生成订单编号（以PO开头+年月日+3位流水号）,更新订单头行表，保存后状态变成新建、提交审批后变成待审批，审批通过后即发布变成待确认，供应商确认后变成已确认（如果是ERP同步过来的，直接原始状态是待确认）
         */
        // 创建
        CSrmPurchaseOrderH cSrmPurchaseOrderH = null;
        if (("1").equals(cSrmPurchaseOrderHReq.getOperationSign())) {

            // 校验供应商编码是否存在
            if (null == cSrmSupplierMapper.selectSupplierCode(cSrmPurchaseOrderHReq.getSupplierCode())) {
                return Rjson.error("供应商代码不存在");
            }
            // 校验公司编码是否存在
            if (null == cSrmCompanyMapper.selectCompanyCode(cSrmPurchaseOrderHReq.getCompany())) {
                return Rjson.error("公司编码不存在");
            }
            // 校验合同编号是否存在
            CSrmContractH contractH = new CSrmContractH();
            contractH.setContractNo(cSrmPurchaseOrderHReq.getContractNo());
            if (cSrmContractHMapper.selectByPrimaryKey(contractH).size() == 0) {
                return Rjson.error("合同编号不存在");
            }

            // 校验采购需求单是否处于已分配状态
            CSrmPurchaseOrderRReq cSrmPurchaseOrderRReq = cSrmPurchaseOrderHReq.getPurList().stream().findFirst().orElse(null);
            if (cSrmPurchaseOrderRReq != null) {
                if (!StringUtil.eqNu(cSrmPurchaseOrderRReq.getPurchaseRequestNo())) {
                    return Rjson.error("'采购申请单号'不能为空");
                }
                CSrmPurchaseDemandH cSrmPurchaseDemandH = new CSrmPurchaseDemandH();
                cSrmPurchaseDemandH.setRequestCode(cSrmPurchaseOrderRReq.getPurchaseRequestNo());
                CSrmPurchaseDemandH purchaseDemandH = cSrmPurchaseDemandHMapper.selectByPrimaryKey(cSrmPurchaseDemandH);
                if (null == purchaseDemandH) {
                    return Rjson.error("创建失败，采购需求单号不存在");
                } else if (!("4").equals(purchaseDemandH.getStatus()) && !("6").equals(purchaseDemandH.getStatus())) {
                    return Rjson.error("创建失败，采购需求单号不处于已分配状态或者已询价");
                }

            }
            // 订单号为空生成订单编号（以PO开头+年月日+3位流水号）
            cSrmPurchaseOrderH = cSrmPurchaseOrderHMapper.selectFinallyData();
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (cSrmPurchaseOrderH == null) {
                // 未找到数据，从最新一条开始
                cSrmPurchaseOrderHReq.setOrderNumber("PO" + yyyyMMdd + 100);
            } else {
                int requestCode = Integer.parseInt(cSrmPurchaseOrderH.getOrderNumber().substring(10)) + 1;
                cSrmPurchaseOrderHReq.setOrderNumber("PO" + yyyyMMdd + requestCode);
            }

            // 新增采购头表
            cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
            BeanUtils.copyProperties(cSrmPurchaseOrderHReq, cSrmPurchaseOrderH);
            // 创建时间
            cSrmPurchaseOrderH.setCreateTime(new Date());
            // 返回创建时间给前端
            cSrmPurchaseOrderHReq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmPurchaseOrderH.getCreateTime()));
            cSrmPurchaseOrderHMapper.insertSelective(cSrmPurchaseOrderH);
            if (!CollectionUtils.isEmpty(cSrmPurchaseOrderHReq.getPurList())) {
                // 新增采购行表
                for (CSrmPurchaseOrderRReq item : cSrmPurchaseOrderHReq.getPurList()) {
                    // 校验参数
                    EqualsPoJoUtil.string(item.getCount(), "数量");
                    EqualsPoJoUtil.string(item.getUnit(), "单位");
                    EqualsPoJoUtil.string(item.getUnitPrice(), "不含税单价");
                    EqualsPoJoUtil.string(item.getTaxRate(), "税率");
                    EqualsPoJoUtil.dateYmd(item.getExpectedDateOfArrival(), "预计到货日期");
                    // 生成项目行号
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
                    // 预计到货日期
                    if (StringUtil.eqNu(item.getExpectedDateOfArrival())) {
                        cSrmPurchaseOrderR.setExpectedDateOfArrival(new SimpleDateFormat("yyyy-MM-dd").parse(item.getExpectedDateOfArrival()));
                    }

                    // 计算价税合计
                    cSrmPurchaseOrderR.setPriceTaxSum((new BigDecimal(item.getCount()).multiply(new BigDecimal(item.getUnitPrice()))).toString());
                    cSrmPurchaseOrderR.setCreateTime(new Date());
                    cSrmPurchaseOrderRMapper.insertSelective(cSrmPurchaseOrderR);
                }
            }


            return Rjson.success("创建成功", cSrmPurchaseOrderHReq);
        } else if (("2").equals(cSrmPurchaseOrderHReq.getOperationSign())) {
            // 校验订单号是否存在
            cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
            cSrmPurchaseOrderH.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
            List<CSrmPurchaseOrderH> selectByPrimaryKey = cSrmPurchaseOrderHMapper.selectByPrimaryKey(cSrmPurchaseOrderH);
            if (selectByPrimaryKey.size() < 1) {
                return Rjson.error("更新失败，订单号不存在");
            } else {
                // 更新采购头表
                cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
                BeanUtils.copyProperties(cSrmPurchaseOrderHReq, cSrmPurchaseOrderH);
                // 修改时间
                cSrmPurchaseOrderH.setUpdateTime(new Date());
                // 返回创建时间|修改时间给前端
                cSrmPurchaseOrderHReq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(selectByPrimaryKey.get(0).getCreateTime()));
                cSrmPurchaseOrderHReq.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmPurchaseOrderH.getUpdateTime()));
                cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(cSrmPurchaseOrderH);
                // 修改采购行表
                for (CSrmPurchaseOrderRReq item : cSrmPurchaseOrderHReq.getPurList()) {
                    // 校验参数
                    EqualsPoJoUtil.string(item.getCount(), "数量");
                    EqualsPoJoUtil.string(item.getUnit(), "单位");
                    EqualsPoJoUtil.string(item.getUnitPrice(), "单价");
                    EqualsPoJoUtil.string(item.getTaxRate(), "税率");
                    EqualsPoJoUtil.dateYmd(item.getExpectedDateOfArrival(), "预计到货日期");
                    // 校验当前行号是否为空，为空则代表式新增的行数据
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
                        // 预计到货日期
                        cSrmPurchaseOrderR.setExpectedDateOfArrival(new SimpleDateFormat("yyyy-MM-dd").parse(item.getExpectedDateOfArrival()));
                        cSrmPurchaseOrderR.setCreateTime(new Date());
                        // 赋值采购订单号
                        cSrmPurchaseOrderR.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
                        // 计算价税合计
                        cSrmPurchaseOrderR.setPriceTaxSum(String.valueOf(Integer.parseInt(item.getCount()) * Integer.parseInt(item.getUnitPrice())));
                        cSrmPurchaseOrderRMapper.insertSelective(cSrmPurchaseOrderR);

                    } else {
                        CSrmPurchaseOrderR cSrmPurchaseOrderR = new CSrmPurchaseOrderR();
                        BeanUtils.copyProperties(item, cSrmPurchaseOrderR);
                        // 预计到货日期
                        cSrmPurchaseOrderR.setExpectedDateOfArrival(new SimpleDateFormat("yyyy-MM-dd").parse(item.getExpectedDateOfArrival()));
                        cSrmPurchaseOrderR.setUpdateTime(new Date());
                        // 赋值采购订单号
                        cSrmPurchaseOrderR.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());

                        // 计算价税合计
                        cSrmPurchaseOrderR.setPriceTaxSum((new BigDecimal(item.getCount()).multiply(new BigDecimal(item.getUnitPrice()))).toString());
                        cSrmPurchaseOrderRMapper.updateByPrimaryKeySelective(cSrmPurchaseOrderR);
                    }

                }

                return Rjson.success("更新成功", cSrmPurchaseOrderHReq);
            }

        } else if (("3").equals(cSrmPurchaseOrderHReq.getOperationSign())) {
            // 校验订单号是否存在
            cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
            cSrmPurchaseOrderH.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
            List<CSrmPurchaseOrderH> selectByPrimaryKey = cSrmPurchaseOrderHMapper.selectByPrimaryKey(cSrmPurchaseOrderH);
            if (selectByPrimaryKey.size() < 1) {
                return Rjson.error("更新失败，订单号不存在");
            } else {
                // 修改合采购头表
                cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
                BeanUtils.copyProperties(cSrmPurchaseOrderHReq, cSrmPurchaseOrderH);
                cSrmPurchaseOrderH.setUpdateTime(new Date());
                cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(cSrmPurchaseOrderH);
                return Rjson.success("更新成功", null);
            }
        } else {
            return Rjson.error("操作标识错误");
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
        // 查询采购订单头数据
        cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
        cSrmPurchaseOrderH.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
        cSrmPurchaseOrderH.setBuyerType(cSrmPurchaseOrderHReq.getBuyerType());
        // 采购员类型(1.SRM系统采购员2.K3采购员)
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
            // 查询采购订单行数据
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
            // 删除头表记录
            CSrmPurchaseOrderH srmPurchaseOrderH = new CSrmPurchaseOrderH();
            srmPurchaseOrderH.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
            srmPurchaseOrderH.setDelete(true);
            cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(srmPurchaseOrderH);
            // 删除行表记录
            CSrmPurchaseOrderR srmPurchaseOrderR = new CSrmPurchaseOrderR();
            srmPurchaseOrderR.setOrderNumber(cSrmPurchaseOrderHReq.getOrderNumber());
            srmPurchaseOrderR.setDelete(true);
            cSrmPurchaseOrderRMapper.updateByPrimaryKeySelective(srmPurchaseOrderR);
        }


        // 删除送货单
        if (StringUtil.eqNu(cSrmPurchaseOrderHReq.getDeliveryNumber())) {
            // 删除头表记录
            CSrmSendCommodityH cSrmSendCommodityH = new CSrmSendCommodityH();
            cSrmSendCommodityH.setDeliveryNumber(cSrmPurchaseOrderHReq.getDeliveryNumber());
            cSrmSendCommodityH.setDelete(true);
            cSrmSendCommodityHMapper.updateByPrimaryKeySelective(cSrmSendCommodityH);
            // 删除行表记录
            CSrmSendCommodityR cSrmSendCommodityR = new CSrmSendCommodityR();
            cSrmSendCommodityR.setDeliveryNumber(cSrmPurchaseOrderHReq.getDeliveryNumber());
            cSrmSendCommodityR.setDelete(true);
            cSrmSendCommodityRMapper.updateByPrimaryKeySelective(cSrmSendCommodityR);

        }

        if (!CollectionUtils.isEmpty(cSrmPurchaseOrderHReq.getOrderNumberList())) {
            for (CSrmSendCommodityRReq item : cSrmPurchaseOrderHReq.getOrderNumberList()) {
                // 更新采购订单头表中 是否已创建送货单为 未创建
                CSrmPurchaseOrderH srmPurchaseOrderH = new CSrmPurchaseOrderH();
                srmPurchaseOrderH.setOrderNumber(item.getPurchaseOrderNo());
                srmPurchaseOrderH.setIsOpenTicket(0);
                cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(srmPurchaseOrderH);
            }

        }
        return Rjson.success();
    }
}


