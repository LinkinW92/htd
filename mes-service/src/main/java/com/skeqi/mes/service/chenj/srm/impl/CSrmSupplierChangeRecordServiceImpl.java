package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmBankChangeRecordRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmFinanceChangeRecordRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmLinkmanChangeRecordRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSupplierChangeRecordRsp;
import com.skeqi.mes.service.chenj.srm.CSrmSupplierChangeRecordService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplierChangeRecordServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmSupplierChangeRecordServiceImpl implements CSrmSupplierChangeRecordService {

    @Resource
    private CSrmSupplierChangeRecordMapper cSrmSupplierChangeRecordMapper;

    @Resource
    private CSrmSupplierMapper cSrmSupplierMapper;
    @Resource
    private CSrmLinkmanChangeRecordMapper cSrmLinkmanChangeRecordMapper;
    @Resource
    private CSrmFinanceChangeRecordMapper cSrmFinanceChangeRecordMapper;
    @Resource
    private CSrmBankChangeRecordMapper cSrmBankChangeRecordMapper;


    @Resource
    private CSrmCompanyMapper cSrmCompanyMapper;


    @Resource
    private CSrmLinkmanMapper cSrmLinkmanMapper;
    @Resource
    private CSrmFinanceMapper cSrmFinanceMapper;
    @Resource
    private CSrmBankMapper cSrmBankMapper;


    @Override
    public int insertSelective(CSrmSupplierChangeRecord record) {
        return cSrmSupplierChangeRecordMapper.insertSelective(record);
    }

    @Override
    public CSrmSupplierChangeRecord selectByPrimaryKey(Integer id) {
        return cSrmSupplierChangeRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmSupplierChangeRecord record) {
        return cSrmSupplierChangeRecordMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int updateBatchSelective(List<CSrmSupplierChangeRecord> list) {
        return cSrmSupplierChangeRecordMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmSupplierChangeRecord> list) {
        return cSrmSupplierChangeRecordMapper.batchInsert(list);
    }

    @Override
    public Rjson changeRecordEdit(CSrmSupplierChangeRecordReq supplierChangeRecordReq) throws ParseException {
        // 查询供应商代码是否存在
        CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(supplierChangeRecordReq.getSupplierCode());
        if (cSrmSupplier == null) {
            return Rjson.error("供应商代码不存在");
        } else {

            // 存储供应商状态
            String name = cSrmSupplier.getName();
            int status = cSrmSupplier.getStatus();
            cSrmSupplier = new CSrmSupplier();
            cSrmSupplier.setName(supplierChangeRecordReq.getCompanyName());
            // 过滤本公司名称
            List<CSrmSupplier> selectByPrimaryKey = cSrmSupplierMapper.selectByPrimaryKey(cSrmSupplier);
            if (!CollectionUtils.isEmpty(selectByPrimaryKey)) {
                // 相同的公司不抛出异常
                if (!name.equals(selectByPrimaryKey.get(0).getName())) {
                    if (supplierChangeRecordReq.getCompanyName().equals(selectByPrimaryKey.get(0).getName())) {
                        return Rjson.error("该企业名称已被注册");
                    }
                }

            }

            // 保存
            if (("1").equals(supplierChangeRecordReq.getOperationSign())) {
                CSrmSupplierChangeRecord record = null;

                // 查询供应商代码是否已有变更中的记录  c_srm_supplier_change_record
                CSrmCompany cSrmCompany = cSrmCompanyMapper.selectSupplierCode(supplierChangeRecordReq.getSupplierCode());
                if (null != cSrmCompany) {
                    // 校验公司是否处于变更中
                    if (("3").equals(cSrmCompany.getIsAuth())) {
                        return Rjson.error("已有变更记录在变更中");
                    }
                }

                // 查询供应商代码是否已有保存中的记录  c_srm_supplier_change_record
                CSrmSupplierChangeRecordRsp cSrmSupplierChangeRecordRsp = cSrmSupplierChangeRecordMapper.selectChangeRecordD(supplierChangeRecordReq);
                record = new CSrmSupplierChangeRecord();
                BeanUtils.copyProperties(supplierChangeRecordReq, record);
                if (cSrmSupplierChangeRecordRsp != null) {
                    // 更新变更数据为最新数据
                    // 赋值公司基础信息、产品信息
                    if (StringUtil.eqNu(supplierChangeRecordReq.getRegisterDate())) {
                        record.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd").parse(supplierChangeRecordReq.getRegisterDate()));
                    }
                    record.setUpdateTime(new Date());
                    cSrmSupplierChangeRecordMapper.updateByPrimaryKeySelective(record);

                } else {
                    // 校验审批失败的数据并删除
                    CSrmSupplierChangeRecordReq recordReq = new CSrmSupplierChangeRecordReq();
                    recordReq.setStatus("1");
//                    recordReq.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
                    recordReq.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
                    if (null != cSrmSupplierChangeRecordMapper.selectChangeRecordD(recordReq)) {
                        record = new CSrmSupplierChangeRecord();
                        record.setStatus("1");
//                        record.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
                        record.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
                        cSrmSupplierChangeRecordMapper.updateByPrimaryKeySelectiveDel(record);
                    }
                    // 新增企业变更记录表
                    // 赋值公司基础信息、产品信息
                    BeanUtils.copyProperties(supplierChangeRecordReq, record);
                    record.setCreateTime(new Date());
                    if (StringUtil.eqNu(supplierChangeRecordReq.getRegisterDate())) {
                        record.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd").parse(supplierChangeRecordReq.getRegisterDate()));
                    }
                    cSrmSupplierChangeRecordMapper.insertSelective(record);


                }

                // 联系人行数据 linkManList
                if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getLinkManList())) {
                    List<CSrmLinkmanChangeRecordRsp> cSrmLinkmanChangeRecordRsps = cSrmLinkmanChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                    // 校验当前是否已有保存的记录
                    if (cSrmLinkmanChangeRecordRsps.size() > 0) {
                        // 根据id删除
                        cSrmLinkmanChangeRecordMapper.updateBatchSelectiveDel(cSrmLinkmanChangeRecordRsps);
                    }
                    // 新增
                    cSrmLinkmanChangeRecordMapper.batchInsert(supplierChangeRecordReq.getLinkManList());
                }

                //  财务行数据  financeList
                if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getFinanceList())) {
                    // 校验当前是否已有保存的记录
                    List<CSrmFinanceChangeRecordRsp> cSrmFinanceChangeRecordRsps = cSrmFinanceChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                    if (cSrmFinanceChangeRecordRsps.size() > 0) {
                        //  根据id删除
                        cSrmFinanceChangeRecordMapper.updateBatchSelectiveDel(cSrmFinanceChangeRecordRsps);
                    }
                    // 新增
                    cSrmFinanceChangeRecordMapper.batchInsert(supplierChangeRecordReq.getFinanceList());
                }
                //  银行\交易行数据  bankList
                if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getBankList())) {
                    // 校验当前是否已有保存的记录
                    List<CSrmBankChangeRecordRsp> cSrmBankChangeRecordRsps = cSrmBankChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                    if (cSrmBankChangeRecordRsps.size() > 0) {
                        //  根据id删除
                        cSrmBankChangeRecordMapper.updateBatchSelectiveDel(cSrmBankChangeRecordRsps);
                    }
                    // 新增
                    cSrmBankChangeRecordMapper.batchInsert(supplierChangeRecordReq.getBankList());
                }


                return Rjson.success("保存成功", null);


                // 提交
            } else if (("2").equals(supplierChangeRecordReq.getOperationSign())) {

                // 查询供应商代码是否有已保存记录
                CSrmSupplierChangeRecordReq recordReq = new CSrmSupplierChangeRecordReq();
                BeanUtils.copyProperties(supplierChangeRecordReq, recordReq);
                // 已保存的记录
                recordReq.setStatus("0");
                CSrmSupplierChangeRecordRsp changeRecordRsp = cSrmSupplierChangeRecordMapper.selectChangeRecordD(recordReq);
                if (null == changeRecordRsp) {
                    return Rjson.error("请先保存再提交");
                }
                // 查询该供应商是否处于认证中
                if (status == 2) {
                    return Rjson.error("已有变更记录在审核中,请耐心等待管理员审核");
                } else {
                    // 更新供应商变更记录表
                    CSrmSupplierChangeRecord record = new CSrmSupplierChangeRecord();
                    BeanUtils.copyProperties(supplierChangeRecordReq, record);
                    record.setUpdateTime(new Date());
                    cSrmSupplierChangeRecordMapper.updateByPrimaryKeySelective(record);


                    // 联系人行数据 linkManList
                    if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getLinkManList())) {
                        List<CSrmLinkmanChangeRecordRsp> cSrmLinkmanChangeRecordRsps = cSrmLinkmanChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                        // 校验当前是否已有保存的记录
                        if (cSrmLinkmanChangeRecordRsps.size() > 0) {
                            // 根据id删除
                            cSrmLinkmanChangeRecordMapper.updateBatchSelectiveDel(cSrmLinkmanChangeRecordRsps);
                        }
                        // 新增
                        cSrmLinkmanChangeRecordMapper.batchInsert(supplierChangeRecordReq.getLinkManList());
                    }

                    //  财务行数据  financeList
                    if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getFinanceList())) {
                        // 校验当前是否已有已提交的记录
                        List<CSrmFinanceChangeRecordRsp> cSrmFinanceChangeRecordRsps = cSrmFinanceChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                        if (cSrmFinanceChangeRecordRsps.size() > 0) {
                            //  根据id删除
                            cSrmFinanceChangeRecordMapper.updateBatchSelectiveDel(cSrmFinanceChangeRecordRsps);
                        }
                        // 新增
                        cSrmFinanceChangeRecordMapper.batchInsert(supplierChangeRecordReq.getFinanceList());
                    }
                    //  银行\交易行数据  bankList
                    if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getBankList())) {
                        // 校验当前是否已有已提交的记录
                        List<CSrmBankChangeRecordRsp> cSrmBankChangeRecordRsps = cSrmBankChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                        if (cSrmBankChangeRecordRsps.size() > 0) {
                            //  根据id删除
                            cSrmBankChangeRecordMapper.updateBatchSelectiveDel(cSrmBankChangeRecordRsps);
                        }
                        // 新增
                        cSrmBankChangeRecordMapper.batchInsert(supplierChangeRecordReq.getBankList());
                    }

                    // 更新公司表中认证状态为认证中
                    CSrmCompany cSrmCompany = new CSrmCompany();
                    cSrmCompany.setIsAuth("3");
                    cSrmCompany.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
                    cSrmCompanyMapper.updateByPrimaryKeySelective(cSrmCompany);

                    cSrmSupplier = new CSrmSupplier();
                    cSrmSupplier.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
                    // 状态更改为认证中，并进行重新审批
                    cSrmSupplier.setStatus(2);
                    cSrmSupplier.setUpdateTime(new Date());
                    cSrmSupplierMapper.updateByPrimaryKeySelective(cSrmSupplier);
                    return Rjson.success("提交成功,请耐心等待管理员审核", null);
                }


            } else {
                return Rjson.error("操作标识错误");
            }


        }

    }

    @Override
    public Rjson findChangeRecord(CSrmSupplierChangeRecordReq supplierChangeRecordReq) {
        CSrmSupplierChangeRecordRsp rsp = new CSrmSupplierChangeRecordRsp();
        // 公司\产品信息
        CSrmSupplierChangeRecordRsp cSrmSupplierChangeRecordRsp = cSrmSupplierChangeRecordMapper.selectChangeRecordD(supplierChangeRecordReq);
        if (cSrmSupplierChangeRecordRsp != null) {
            BeanUtils.copyProperties(cSrmSupplierChangeRecordRsp, rsp);
        } else {
            return Rjson.success();
        }
        //  联系人行数据
        rsp.setLinkManList(cSrmLinkmanChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
        // 获取财务行数据
        rsp.setFinanceList(cSrmFinanceChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
        //  银行\交易行数据
        rsp.setBankList(cSrmBankChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
        return Rjson.success(rsp);
    }

    @Override
    public Rjson revocationChangeRecord(CSrmSupplierChangeRecordReq supplierChangeRecordReq) {

        // 删除企业信息/产品/服务信息
        CSrmSupplierChangeRecord cSrmSupplierChangeRecord = new CSrmSupplierChangeRecord();
        cSrmSupplierChangeRecord.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
//        cSrmSupplierChangeRecord.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
        cSrmSupplierChangeRecord.setStatus("1");
        cSrmSupplierChangeRecordMapper.updateByPrimaryKeySelectiveDel(cSrmSupplierChangeRecord);
        // 删除联系人信息变更中的数据
        CSrmLinkmanChangeRecord record = new CSrmLinkmanChangeRecord();
//        record.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
        record.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        record.setStatus("1");
        cSrmLinkmanChangeRecordMapper.delData(record);
        // 删除财务信息变更中的数据
        CSrmFinanceChangeRecord changeRecord = new CSrmFinanceChangeRecord();
//        changeRecord.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
        changeRecord.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        changeRecord.setStatus("1");
        cSrmFinanceChangeRecordMapper.delData(changeRecord);
        // 删除银行信息变更中的数据
        CSrmBankChangeRecord srmBankChangeRecord = new CSrmBankChangeRecord();
//        srmBankChangeRecord.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
        srmBankChangeRecord.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        srmBankChangeRecord.setStatus("1");
        cSrmBankChangeRecordMapper.delData(srmBankChangeRecord);

        // 变更供应商状态为已认证
        CSrmSupplier cSrmSupplier = new CSrmSupplier();
        cSrmSupplier.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        // 本次操作为撤销企业认证申请, 3.已认证状态
        cSrmSupplier.setStatus(3);
        cSrmSupplierMapper.updateByPrimaryKeySelective(cSrmSupplier);

        // 更新公司表中认证状态变更为之前状态
        CSrmCompany cSrmCompany = new CSrmCompany();
        cSrmCompany.setIsAuth("2");
        cSrmCompany.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        cSrmCompany.setUpdateTime(new Date());
        cSrmCompanyMapper.updateByPrimaryKeySelective(cSrmCompany);


        return Rjson.success("撤销成功", null);
    }


    //
//    @Override
//    public Rjson findUpdateChangeRecord(CSrmSupplierChangeRecordReq supplierChangeRecordReq) {
//        CSrmSupplierChangeRecordRsp rsp = new CSrmSupplierChangeRecordRsp();
//        List<CSrmSupplierChangeRecord> cSrmSupplierChangeRecords = cSrmSupplierChangeRecordMapper.selectChangeRecordD(supplierChangeRecordReq);
//        if (cSrmSupplierChangeRecords.size() == 1) {
//            BeanUtils.copyProperties(cSrmSupplierChangeRecords, rsp);
//        }else {
//            return Rjson.error("未查询到处于变更中的记录");
//        }
//        //  联系人行数据
//        rsp.setLinkManList(cSrmLinkmanChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
//        // 获取财务行数据
//        rsp.setFinanceList(cSrmFinanceChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
//        //  银行\交易行数据
//        rsp.setBankList(cSrmBankChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
//        return Rjson.success(rsp);
//    }
}

