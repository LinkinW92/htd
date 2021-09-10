package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmContractHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurPartnerInfoRReq;
import com.skeqi.mes.pojo.chenj.srm.req.ContractObjectReq;
import com.skeqi.mes.pojo.chenj.srm.req.PartnerOrClauseReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmContractHRsp;
import com.skeqi.mes.service.chenj.srm.CSrmContractHService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.chenj.EqualsPoJoUtil;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmContractHServiceImpl implements CSrmContractHService {

    @Resource
    private CSrmContractHMapper cSrmContractHMapper;
    @Resource
    private CSrmContractRMapper cSrmContractRMapper;

    @Resource
    private CSrmContractAffiliateRMapper cSrmContractAffiliateRMapper;

    @Resource
    private CSrmPurPartnerInfoRMapper cSrmPurPartnerInfoRMapper;


    @Resource
    private CSrmSupplierMapper cSrmSupplierMapper;
    @Resource
    private CSrmCompanyMapper cSrmCompanyMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmContractHMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmContractH record) {
        return cSrmContractHMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmContractH record) {
        return cSrmContractHMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmContractH record) {
        return cSrmContractHMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmContractH record) {
        return cSrmContractHMapper.insertSelective(record);
    }

    @Override
    public List<CSrmContractH> selectByPrimaryKey(CSrmContractH record) {
        return cSrmContractHMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmContractH record) {
        return cSrmContractHMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmContractH record) {
        return cSrmContractHMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmContractH> list) {
        return cSrmContractHMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmContractH> list) {
        return cSrmContractHMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmContractH> list) {
        return cSrmContractHMapper.batchInsert(list);
    }

    @Override
    public Rjson updatePurchaseContract(CSrmContractHReq cSrmContractHReq) throws Exception {

        /**
         * 输入：合同编号、合同名称、创建人、合同性质、创建时间、公司代码、供应商代码、合同生效日期、合同终止日期、使用模板编号、行项目号、物料编码、数量、单位、币种、税率、状态、甲方、乙方、合同条款对象及对象值、操作标识
         * 处理：合同编号为空生成合同编号（以C开头+年月日+3位流水号）,更新合同头行表及附属信息表，保存后状态变成新建、提交审批后变成待审批，审批通过后变成待签署，供应商签署后变成待存档，存档后变成已存档。（如果是ERP同步过来的，直接原始状态是待签署）
         */
        // 校验供应商代码是否存在
        CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmContractHReq.getSupplierCode());
        if (cSrmSupplier == null) {
            return Rjson.error("供应商代码不存在");
        }
        // 操作标识为3不进行校验
        if (!("3").equals(cSrmContractHReq.getOperationSign())) {
            // 校验公司编码是否存在
            if (null == cSrmCompanyMapper.selectCompanyCode(cSrmContractHReq.getCompanyCode())) {
                return Rjson.error("公司编码不存在");
            }
        }
        // 创建
        CSrmContractH cSrmContractH = null;
        CSrmContractR cSrmContractR = null;
        CSrmContractAffiliateR cSrmContractAffiliateR = null;
        CSrmPurPartnerInfoR cSrmPurPartnerInfoR = null;
        if (("1").equals(cSrmContractHReq.getOperationSign())) {
            if (!EqualsUtil.StringEqualsNull(cSrmContractHReq.getContractNo())) {
                // 合同编号为空生成合同编号（以C开头+年月日+3位流水号）
                cSrmContractH = cSrmContractHMapper.selectFinallyData();
                String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                if (cSrmContractH == null) {
                    // 未找到数据，从最新一条开始
                    cSrmContractHReq.setContractNo("C" + yyyyMMdd + 100);
                } else {
                    int requestCode = Integer.parseInt(cSrmContractH.getContractNo().substring(9)) + 1;
                    cSrmContractHReq.setContractNo("C" + yyyyMMdd + requestCode);
                }
            } else {
                // 校验合同头表数据是否存在
                cSrmContractH = new CSrmContractH();
                cSrmContractH.setContractNo(cSrmContractHReq.getContractNo());
                List<CSrmContractH> cSrmContractHS = cSrmContractHMapper.selectByPrimaryKey(cSrmContractH);
                if (cSrmContractHS.size() > 1) {
                    return Rjson.error("创建失败，合同数据已存在");
                }

            }
            cSrmContractH = new CSrmContractH();
            BeanUtils.copyProperties(cSrmContractHReq, cSrmContractH);
            // 创建时间
            cSrmContractH.setCreateTime(new Date());
            // 合同生效时间
            cSrmContractH.setEffectiveDateOfContract(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmContractHReq.getEffectiveDateOfContract()));
            // 合同终止时间
            cSrmContractH.setDateOfTermination(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmContractHReq.getDateOfTermination()));

            // 新增合同行表   对应合同编辑页面中=》合同标的行数据
            // 标的对象值
            for (ContractObjectReq req : cSrmContractHReq.getContractObject()) {
                // 存储合同编号
                req.setContractNo(cSrmContractHReq.getContractNo());
                EqualsPoJoUtil.string(req.getMaterialCode(), "物料编码");
                EqualsPoJoUtil.integer(req.getCount(), "数量", true);
                EqualsPoJoUtil.integer(req.getUnit(), "单位", true);
                EqualsPoJoUtil.integer(req.getUnitPriceExcludingTax(), "不含税单价", true);
                EqualsPoJoUtil.integer(req.getCurrency(), "币种", true);
                EqualsPoJoUtil.date(req.getPaymentDate(), "支付日期");
                EqualsPoJoUtil.string(req.getTaxRate(), "税率");
                if (Integer.parseInt(req.getCurrency()) > 2) {
                    return Rjson.error(400, "'currency'超出范围值");
                }
                cSrmContractR = new CSrmContractR();
                // 生成项目行号
                CSrmContractR contractR = cSrmContractRMapper.selectFinallyData(cSrmContractHReq.getContractNo());
                if (contractR == null) {
                    req.setLineItemNo("1");
                } else {
                    int lineItemNo = Integer.parseInt(contractR.getLineItemNo()) + 1;
                    req.setLineItemNo(String.valueOf(lineItemNo));
                }
                // 新增
                BeanUtils.copyProperties(req, cSrmContractR);
                cSrmContractR.setCreateTime(new Date());
                cSrmContractRMapper.insertOrUpdateSelective(cSrmContractR);
            }

            if (null != cSrmContractHReq.getContractObject() && cSrmContractHReq.getContractObject().length > 0) {
                // 计算合同总额
                double total = Arrays.stream(cSrmContractHReq.getContractObject()).map(d -> Double.parseDouble(d.getCount()) * Double.parseDouble(d.getUnitPriceExcludingTax())).reduce(0.0, Double::sum);
                cSrmContractH.setContractRental(String.valueOf(total));
            }
            cSrmContractHMapper.insertOrUpdateSelective(cSrmContractH);


            // 对应合同编辑页面中=》采购合作伙伴信息行数据
            for (CSrmPurPartnerInfoRReq req : cSrmContractHReq.getPartnerOrClause()) {
                // 存储合同编号
                req.setContractNo(cSrmContractHReq.getContractNo());
                // 校验公司编码
                EqualsPoJoUtil.string(req.getCompanyCode(), "公司编码");
                // 校验公司编码是否存在
                if (null == cSrmCompanyMapper.selectCompanyCode(req.getCompanyCode())) {
                    return Rjson.error("'采购合作伙伴信息行'公司编码不存在");
                }
                // 校验当前记录  合同编号是否已存在
                cSrmPurPartnerInfoR = new CSrmPurPartnerInfoR();
                // 新增
                // 生成项目行号
                CSrmPurPartnerInfoR selectFinallyData = cSrmPurPartnerInfoRMapper.selectFinallyData(cSrmContractHReq.getContractNo());
                if (selectFinallyData == null) {
                    req.setLineItemNo("1");
                } else {
                    int lineItemNo = Integer.parseInt(selectFinallyData.getLineItemNo()) + 1;
                    req.setLineItemNo(String.valueOf(lineItemNo));
                }
                BeanUtils.copyProperties(req, cSrmPurPartnerInfoR);
                cSrmPurPartnerInfoR.setCreateTime(new Date());
                cSrmPurPartnerInfoRMapper.insertOrUpdateSelective(cSrmPurPartnerInfoR);
            }

            // 新增合同附属表    对应合同编辑页面中=》采购合同业务条款行数据
            // 获取合作伙伴与条款对象值
            for (PartnerOrClauseReq req : cSrmContractHReq.getClauseData()) {
                // 存储合同编号
                req.setContractNo(cSrmContractHReq.getContractNo());
                EqualsPoJoUtil.string(req.getObjectOfContractOrValue(), "合同条款对象及对象值");
                cSrmContractAffiliateR = new CSrmContractAffiliateR();
                // 生成项目行号
                CSrmContractAffiliateR selectFinallyData = cSrmContractAffiliateRMapper.selectFinallyData(cSrmContractHReq.getContractNo());
                if (selectFinallyData == null) {
                    req.setLineItemNo("1");
                } else {
                    int lineItemNo = Integer.parseInt(selectFinallyData.getLineItemNo()) + 1;
                    req.setLineItemNo(String.valueOf(lineItemNo));
                }
                // 新增
                BeanUtils.copyProperties(req, cSrmContractAffiliateR);
                cSrmContractAffiliateR.setCreateTime(new Date());
                cSrmContractAffiliateRMapper.insertOrUpdateSelective(cSrmContractAffiliateR);


            }

            return Rjson.success("创建成功", cSrmContractHReq);
        } else if (("2").equals(cSrmContractHReq.getOperationSign())) {
            // 校验合同编号是否存在
            cSrmContractH = new CSrmContractH();
            cSrmContractH.setContractNo(cSrmContractHReq.getContractNo());
            List<CSrmContractH> contractH = cSrmContractHMapper.selectByPrimaryKey(cSrmContractH);
            if (contractH.size() < 1) {
                return Rjson.error("更新失败，合同编号不存在");
            } else {
                // 修改合同头表
                cSrmContractH = new CSrmContractH();
                BeanUtils.copyProperties(cSrmContractHReq, cSrmContractH);
                // 创建时间
                cSrmContractH.setCreateTime(new Date());
                // 合同生效时间
                cSrmContractH.setEffectiveDateOfContract(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmContractHReq.getEffectiveDateOfContract()));
                // 合同终止时间
                cSrmContractH.setDateOfTermination(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmContractHReq.getDateOfTermination()));

                // 标的行对象值
                for (ContractObjectReq req : cSrmContractHReq.getContractObject()) {
                    EqualsPoJoUtil.string(req.getMaterialCode(), "物料编码");
                    EqualsPoJoUtil.integer(req.getCount(), "数量", true);
                    EqualsPoJoUtil.integer(req.getUnitPriceExcludingTax(), "不含税单价", true);
                    EqualsPoJoUtil.integer(req.getUnit(), "单位", true);
                    EqualsPoJoUtil.integer(req.getCurrency(), "币种", true);
                    EqualsPoJoUtil.integer(req.getTaxRate(), "税率", true);
                    EqualsPoJoUtil.date(req.getPaymentDate(), "支付日期");
                    if (Integer.parseInt(req.getCurrency()) > 2) {
                        return Rjson.error(400, "'currency'超出范围值");
                    }
                    // 存储合同编号
                    req.setContractNo(cSrmContractHReq.getContractNo());
                    cSrmContractR = new CSrmContractR();
                    // 行项目为空，程序认为是新增行
                    if (!StringUtil.eqNu(req.getLineItemNo())) {
                        // 生成项目行号
                        CSrmContractR contractR = cSrmContractRMapper.selectFinallyData(cSrmContractHReq.getContractNo());
                        if (contractR == null) {
                            req.setLineItemNo("1");
                        } else {
                            int lineItemNo = Integer.parseInt(contractR.getLineItemNo()) + 1;
                            req.setLineItemNo(String.valueOf(lineItemNo));
                        }
                        // 新增标的
                        BeanUtils.copyProperties(req, cSrmContractR);
                        cSrmContractR.setContractNo(cSrmContractHReq.getContractNo());
                        cSrmContractR.setCreateTime(new Date());
                        cSrmContractRMapper.insertOrUpdateSelective(cSrmContractR);
                    } else {
                        // 更新
                        BeanUtils.copyProperties(req, cSrmContractR);
                        cSrmContractR.setContractNo(cSrmContractHReq.getContractNo());
                        cSrmContractR.setUpdateTime(new Date());
                        cSrmContractRMapper.updateByPrimaryKeySelective(cSrmContractR);
                    }
                }
                if (null != cSrmContractHReq.getContractObject() && cSrmContractHReq.getContractObject().length > 0) {
                    // 计算合同总额
                    double total = Arrays.stream(cSrmContractHReq.getContractObject()).map(d -> Double.parseDouble(d.getCount()) * Double.parseDouble(d.getUnitPriceExcludingTax())).reduce(0.0, Double::sum);
                    cSrmContractH.setContractRental(String.valueOf(total));
                }
                cSrmContractHMapper.updateByPrimaryKeySelective(cSrmContractH);


                // 合作伙伴信息行数据
                for (CSrmPurPartnerInfoRReq req : cSrmContractHReq.getPartnerOrClause()) {
                    cSrmPurPartnerInfoR = new CSrmPurPartnerInfoR();
                    // 校验公司编码
                    EqualsPoJoUtil.string(req.getCompanyCode(), "公司编码");
                    // 校验公司编码是否存在
                    if (null == cSrmCompanyMapper.selectCompanyCode(req.getCompanyCode())) {
                        return Rjson.error("'采购合作伙伴信息行'公司编码不存在");
                    }
                    // 存储合同编号
                    req.setContractNo(cSrmContractHReq.getContractNo());
                    // 行项目为空，程序认为是新增行
                    if (!StringUtil.eqNu(req.getLineItemNo())) {
                        // 生成项目行号
                        CSrmPurPartnerInfoR selectFinallyData = cSrmPurPartnerInfoRMapper.selectFinallyData(cSrmContractHReq.getContractNo());
                        if (selectFinallyData == null) {
                            req.setLineItemNo("1");
                        } else {
                            int lineItemNo = Integer.parseInt(selectFinallyData.getLineItemNo()) + 1;
                            req.setLineItemNo(String.valueOf(lineItemNo));
                        }
                        // 新增
                        BeanUtils.copyProperties(req, cSrmPurPartnerInfoR);
                        cSrmPurPartnerInfoR.setContractNo(cSrmContractHReq.getContractNo());
                        cSrmPurPartnerInfoR.setCreateTime(new Date());
                        cSrmPurPartnerInfoRMapper.insertOrUpdateSelective(cSrmPurPartnerInfoR);
                    } else {
                        // 更新
                        BeanUtils.copyProperties(req, cSrmPurPartnerInfoR);
                        cSrmPurPartnerInfoR.setUpdateTime(new Date());
                        cSrmPurPartnerInfoR.setContractNo(cSrmContractHReq.getContractNo());
                        cSrmPurPartnerInfoRMapper.updateByPrimaryKeySelective(cSrmPurPartnerInfoR);
                    }
                }


                // 获取条款行对象值
                for (PartnerOrClauseReq req : cSrmContractHReq.getClauseData()) {
                    cSrmContractAffiliateR = new CSrmContractAffiliateR();
                    // 存储合同编号
                    req.setContractNo(cSrmContractHReq.getContractNo());
                    // 行项目为空，程序认为是新增行
                    if (!StringUtil.eqNu(req.getLineItemNo())) {
                        // 生成项目行号
                        CSrmContractAffiliateR selectFinallyData = cSrmContractAffiliateRMapper.selectFinallyData(cSrmContractHReq.getContractNo());
                        if (selectFinallyData == null) {
                            req.setLineItemNo("1");
                        } else {
                            int lineItemNo = Integer.parseInt(selectFinallyData.getLineItemNo()) + 1;
                            req.setLineItemNo(String.valueOf(lineItemNo));
                        }
                        // 新增
                        BeanUtils.copyProperties(req, cSrmContractAffiliateR);
                        cSrmContractAffiliateR.setContractNo(cSrmContractHReq.getContractNo());
                        cSrmContractAffiliateR.setCreateTime(new Date());
                        cSrmContractAffiliateRMapper.insertOrUpdateSelective(cSrmContractAffiliateR);
                    } else {

                        // 更新
                        BeanUtils.copyProperties(req, cSrmContractAffiliateR);
                        cSrmContractAffiliateR.setUpdateTime(new Date());
                        cSrmContractAffiliateR.setContractNo(cSrmContractHReq.getContractNo());
                        cSrmContractAffiliateRMapper.updateByPrimaryKeySelective(cSrmContractAffiliateR);
                    }
                }

                // 校验是否有需要删除的行数据
                // delTargetData   标的
                // delCooPratIveData 伙伴
                // delAgreementData  条款
                if (null != cSrmContractHReq.getDelTargetData() && cSrmContractHReq.getDelTargetData().length > 0) {
                    // 删除标的行数据
                    cSrmContractRMapper.deleteBatchSelective(cSrmContractHReq.getDelTargetData());
                }
                if (null != cSrmContractHReq.getDelCooPratIveData() && cSrmContractHReq.getDelCooPratIveData().length > 0) {
                    // 删除伙伴行数据
                    cSrmPurPartnerInfoRMapper.deleteBatchSelective(cSrmContractHReq.getDelCooPratIveData());
                }
                if (null != cSrmContractHReq.getDelAgreementData() && cSrmContractHReq.getDelAgreementData().length > 0) {
                    // 删除条款行数据
                    cSrmContractAffiliateRMapper.deleteBatchSelective(cSrmContractHReq.getDelAgreementData());
                }
                return Rjson.success("更新成功", cSrmContractHReq);

            }
        } else if (("3").equals(cSrmContractHReq.getOperationSign())) {
            // 校验合同编号是否存在
            cSrmContractH = new CSrmContractH();
            cSrmContractH.setContractNo(cSrmContractHReq.getContractNo());
            List<CSrmContractH> contractH = cSrmContractHMapper.selectByPrimaryKey(cSrmContractH);
            if (contractH.size() < 1) {
                return Rjson.error("更新失败，合同编号不存在");
            } else {
                // 修改合同头表
                cSrmContractH = new CSrmContractH();
                BeanUtils.copyProperties(cSrmContractHReq, cSrmContractH);
                cSrmContractHMapper.updateByPrimaryKeySelective(cSrmContractH);
                return Rjson.success("更新成功", cSrmContractHReq);
            }

        } else {
            return Rjson.error("操作标识错误");
        }
    }


    @Override
    public Rjson findPurchaseContractH(CSrmContractHReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmContractHMapper.selectByPrimaryList(req)));

    }


    @Override
    public Rjson findPurchaseContractHR(CSrmContractHReq req) {
        CSrmContractHRsp rsp = new CSrmContractHRsp();
        // 查询合同头信息
        CSrmContractHRsp cSrmContractHRsp = cSrmContractHMapper.selectByPrimaryData(req);
        if (null == cSrmContractHRsp) {
            return Rjson.error("合同头数据获取异常");
        } else {
            BeanUtils.copyProperties(cSrmContractHRsp, rsp);
        }

        // 查询合同标的信息
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        rsp.setContractObject(new PageInfo<>(cSrmContractRMapper.selectByPrimaryList(req)));
        // 查询采购合作伙伴信息
        PageHelper.startPage(req.getPageNumB(), req.getPageSizeB());
        rsp.setcSrmPurPartnerInfoRReqs(new PageInfo<>(cSrmPurPartnerInfoRMapper.selectByPrimaryList(req)));
        // 查询采购合同业务条款
        PageHelper.startPage(req.getPageNumY(), req.getPageSizeY());
        rsp.setPartnerOrClause(new PageInfo<>(cSrmContractAffiliateRMapper.selectByPrimaryList(req)));
        return Rjson.success(rsp);
    }

    @Override
    public Rjson findPurchaseContractAll(CSrmContractHReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmContractHMapper.selectByPrimaryAllList(req)));
    }

    @Override
    public Rjson delPurchaseContract(CSrmContractHReq cSrmContractHReq) {
        // 删除采购合同数据头
        CSrmContractH contractH = new CSrmContractH();
        contractH.setContractNo(cSrmContractHReq.getContractNo());
        contractH.setIsDelete(1);
        cSrmContractHMapper.updateByPrimaryKeySelective(contractH);
        // 删除采购合同数据行
        CSrmContractR contractR = new CSrmContractR();
        contractR.setContractNo(cSrmContractHReq.getContractNo());
        contractR.setIsDelete(1);
        cSrmContractRMapper.updateByPrimaryKeySelective(contractR);
        return Rjson.success();
    }
}


