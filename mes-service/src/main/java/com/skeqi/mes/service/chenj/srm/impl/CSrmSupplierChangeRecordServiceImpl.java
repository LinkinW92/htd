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
        // ?????????????????????????????????
        CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(supplierChangeRecordReq.getSupplierCode());
        if (cSrmSupplier == null) {
            return Rjson.error("????????????????????????");
        } else {

            // ?????????????????????
            String name = cSrmSupplier.getName();
            int status = cSrmSupplier.getStatus();
            cSrmSupplier = new CSrmSupplier();
            cSrmSupplier.setName(supplierChangeRecordReq.getCompanyName());
            // ?????????????????????
            List<CSrmSupplier> selectByPrimaryKey = cSrmSupplierMapper.selectByPrimaryKey(cSrmSupplier);
            if (!CollectionUtils.isEmpty(selectByPrimaryKey)) {
                // ??????????????????????????????
                if (!name.equals(selectByPrimaryKey.get(0).getName())) {
                    if (supplierChangeRecordReq.getCompanyName().equals(selectByPrimaryKey.get(0).getName())) {
                        return Rjson.error("???????????????????????????");
                    }
                }

            }

            // ??????
            if (("1").equals(supplierChangeRecordReq.getOperationSign())) {
                CSrmSupplierChangeRecord record = null;

                // ???????????????????????????????????????????????????  c_srm_supplier_change_record
                CSrmCompany cSrmCompany = cSrmCompanyMapper.selectSupplierCode(supplierChangeRecordReq.getSupplierCode());
                if (null != cSrmCompany) {
                    // ?????????????????????????????????
                    if (("3").equals(cSrmCompany.getIsAuth())) {
                        return Rjson.error("??????????????????????????????");
                    }
                }

                // ???????????????????????????????????????????????????  c_srm_supplier_change_record
                CSrmSupplierChangeRecordRsp cSrmSupplierChangeRecordRsp = cSrmSupplierChangeRecordMapper.selectChangeRecordD(supplierChangeRecordReq);
                record = new CSrmSupplierChangeRecord();
                BeanUtils.copyProperties(supplierChangeRecordReq, record);
                if (cSrmSupplierChangeRecordRsp != null) {
                    // ?????????????????????????????????
                    // ???????????????????????????????????????
                    if (StringUtil.eqNu(supplierChangeRecordReq.getRegisterDate())) {
                        record.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd").parse(supplierChangeRecordReq.getRegisterDate()));
                    }
                    record.setUpdateTime(new Date());
                    cSrmSupplierChangeRecordMapper.updateByPrimaryKeySelective(record);

                } else {
                    // ????????????????????????????????????
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
                    // ???????????????????????????
                    // ???????????????????????????????????????
                    BeanUtils.copyProperties(supplierChangeRecordReq, record);
                    record.setCreateTime(new Date());
                    if (StringUtil.eqNu(supplierChangeRecordReq.getRegisterDate())) {
                        record.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd").parse(supplierChangeRecordReq.getRegisterDate()));
                    }
                    cSrmSupplierChangeRecordMapper.insertSelective(record);


                }

                // ?????????????????? linkManList
                if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getLinkManList())) {
                    List<CSrmLinkmanChangeRecordRsp> cSrmLinkmanChangeRecordRsps = cSrmLinkmanChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                    // ???????????????????????????????????????
                    if (cSrmLinkmanChangeRecordRsps.size() > 0) {
                        // ??????id??????
                        cSrmLinkmanChangeRecordMapper.updateBatchSelectiveDel(cSrmLinkmanChangeRecordRsps);
                    }
                    // ??????
                    cSrmLinkmanChangeRecordMapper.batchInsert(supplierChangeRecordReq.getLinkManList());
                }

                //  ???????????????  financeList
                if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getFinanceList())) {
                    // ???????????????????????????????????????
                    List<CSrmFinanceChangeRecordRsp> cSrmFinanceChangeRecordRsps = cSrmFinanceChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                    if (cSrmFinanceChangeRecordRsps.size() > 0) {
                        //  ??????id??????
                        cSrmFinanceChangeRecordMapper.updateBatchSelectiveDel(cSrmFinanceChangeRecordRsps);
                    }
                    // ??????
                    cSrmFinanceChangeRecordMapper.batchInsert(supplierChangeRecordReq.getFinanceList());
                }
                //  ??????\???????????????  bankList
                if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getBankList())) {
                    // ???????????????????????????????????????
                    List<CSrmBankChangeRecordRsp> cSrmBankChangeRecordRsps = cSrmBankChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                    if (cSrmBankChangeRecordRsps.size() > 0) {
                        //  ??????id??????
                        cSrmBankChangeRecordMapper.updateBatchSelectiveDel(cSrmBankChangeRecordRsps);
                    }
                    // ??????
                    cSrmBankChangeRecordMapper.batchInsert(supplierChangeRecordReq.getBankList());
                }


                return Rjson.success("????????????", null);


                // ??????
            } else if (("2").equals(supplierChangeRecordReq.getOperationSign())) {

                // ?????????????????????????????????????????????
                CSrmSupplierChangeRecordReq recordReq = new CSrmSupplierChangeRecordReq();
                BeanUtils.copyProperties(supplierChangeRecordReq, recordReq);
                // ??????????????????
                recordReq.setStatus("0");
                CSrmSupplierChangeRecordRsp changeRecordRsp = cSrmSupplierChangeRecordMapper.selectChangeRecordD(recordReq);
                if (null == changeRecordRsp) {
                    return Rjson.error("?????????????????????");
                }
                // ???????????????????????????????????????
                if (status == 2) {
                    return Rjson.error("??????????????????????????????,??????????????????????????????");
                } else {
                    // ??????????????????????????????
                    CSrmSupplierChangeRecord record = new CSrmSupplierChangeRecord();
                    BeanUtils.copyProperties(supplierChangeRecordReq, record);
                    record.setUpdateTime(new Date());
                    cSrmSupplierChangeRecordMapper.updateByPrimaryKeySelective(record);


                    // ?????????????????? linkManList
                    if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getLinkManList())) {
                        List<CSrmLinkmanChangeRecordRsp> cSrmLinkmanChangeRecordRsps = cSrmLinkmanChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                        // ???????????????????????????????????????
                        if (cSrmLinkmanChangeRecordRsps.size() > 0) {
                            // ??????id??????
                            cSrmLinkmanChangeRecordMapper.updateBatchSelectiveDel(cSrmLinkmanChangeRecordRsps);
                        }
                        // ??????
                        cSrmLinkmanChangeRecordMapper.batchInsert(supplierChangeRecordReq.getLinkManList());
                    }

                    //  ???????????????  financeList
                    if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getFinanceList())) {
                        // ??????????????????????????????????????????
                        List<CSrmFinanceChangeRecordRsp> cSrmFinanceChangeRecordRsps = cSrmFinanceChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                        if (cSrmFinanceChangeRecordRsps.size() > 0) {
                            //  ??????id??????
                            cSrmFinanceChangeRecordMapper.updateBatchSelectiveDel(cSrmFinanceChangeRecordRsps);
                        }
                        // ??????
                        cSrmFinanceChangeRecordMapper.batchInsert(supplierChangeRecordReq.getFinanceList());
                    }
                    //  ??????\???????????????  bankList
                    if (!CollectionUtils.isEmpty(supplierChangeRecordReq.getBankList())) {
                        // ??????????????????????????????????????????
                        List<CSrmBankChangeRecordRsp> cSrmBankChangeRecordRsps = cSrmBankChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq);
                        if (cSrmBankChangeRecordRsps.size() > 0) {
                            //  ??????id??????
                            cSrmBankChangeRecordMapper.updateBatchSelectiveDel(cSrmBankChangeRecordRsps);
                        }
                        // ??????
                        cSrmBankChangeRecordMapper.batchInsert(supplierChangeRecordReq.getBankList());
                    }

                    // ??????????????????????????????????????????
                    CSrmCompany cSrmCompany = new CSrmCompany();
                    cSrmCompany.setIsAuth("3");
                    cSrmCompany.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
                    cSrmCompanyMapper.updateByPrimaryKeySelective(cSrmCompany);

                    cSrmSupplier = new CSrmSupplier();
                    cSrmSupplier.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
                    // ????????????????????????????????????????????????
                    cSrmSupplier.setStatus(2);
                    cSrmSupplier.setUpdateTime(new Date());
                    cSrmSupplierMapper.updateByPrimaryKeySelective(cSrmSupplier);
                    return Rjson.success("????????????,??????????????????????????????", null);
                }


            } else {
                return Rjson.error("??????????????????");
            }


        }

    }

    @Override
    public Rjson findChangeRecord(CSrmSupplierChangeRecordReq supplierChangeRecordReq) {
        CSrmSupplierChangeRecordRsp rsp = new CSrmSupplierChangeRecordRsp();
        // ??????\????????????
        CSrmSupplierChangeRecordRsp cSrmSupplierChangeRecordRsp = cSrmSupplierChangeRecordMapper.selectChangeRecordD(supplierChangeRecordReq);
        if (cSrmSupplierChangeRecordRsp != null) {
            BeanUtils.copyProperties(cSrmSupplierChangeRecordRsp, rsp);
        } else {
            return Rjson.success();
        }
        //  ??????????????????
        rsp.setLinkManList(cSrmLinkmanChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
        // ?????????????????????
        rsp.setFinanceList(cSrmFinanceChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
        //  ??????\???????????????
        rsp.setBankList(cSrmBankChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
        return Rjson.success(rsp);
    }

    @Override
    public Rjson revocationChangeRecord(CSrmSupplierChangeRecordReq supplierChangeRecordReq) {

        // ??????????????????/??????/????????????
        CSrmSupplierChangeRecord cSrmSupplierChangeRecord = new CSrmSupplierChangeRecord();
        cSrmSupplierChangeRecord.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
//        cSrmSupplierChangeRecord.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
        cSrmSupplierChangeRecord.setStatus("1");
        cSrmSupplierChangeRecordMapper.updateByPrimaryKeySelectiveDel(cSrmSupplierChangeRecord);
        // ???????????????????????????????????????
        CSrmLinkmanChangeRecord record = new CSrmLinkmanChangeRecord();
//        record.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
        record.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        record.setStatus("1");
        cSrmLinkmanChangeRecordMapper.delData(record);
        // ????????????????????????????????????
        CSrmFinanceChangeRecord changeRecord = new CSrmFinanceChangeRecord();
//        changeRecord.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
        changeRecord.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        changeRecord.setStatus("1");
        cSrmFinanceChangeRecordMapper.delData(changeRecord);
        // ????????????????????????????????????
        CSrmBankChangeRecord srmBankChangeRecord = new CSrmBankChangeRecord();
//        srmBankChangeRecord.setCompanyCode(supplierChangeRecordReq.getCompanyCode());
        srmBankChangeRecord.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        srmBankChangeRecord.setStatus("1");
        cSrmBankChangeRecordMapper.delData(srmBankChangeRecord);

        // ?????????????????????????????????
        CSrmSupplier cSrmSupplier = new CSrmSupplier();
        cSrmSupplier.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        // ???????????????????????????????????????, 3.???????????????
        cSrmSupplier.setStatus(3);
        cSrmSupplierMapper.updateByPrimaryKeySelective(cSrmSupplier);

        // ???????????????????????????????????????????????????
        CSrmCompany cSrmCompany = new CSrmCompany();
        cSrmCompany.setIsAuth("2");
        cSrmCompany.setSupplierCode(supplierChangeRecordReq.getSupplierCode());
        cSrmCompany.setUpdateTime(new Date());
        cSrmCompanyMapper.updateByPrimaryKeySelective(cSrmCompany);


        return Rjson.success("????????????", null);
    }


    //
//    @Override
//    public Rjson findUpdateChangeRecord(CSrmSupplierChangeRecordReq supplierChangeRecordReq) {
//        CSrmSupplierChangeRecordRsp rsp = new CSrmSupplierChangeRecordRsp();
//        List<CSrmSupplierChangeRecord> cSrmSupplierChangeRecords = cSrmSupplierChangeRecordMapper.selectChangeRecordD(supplierChangeRecordReq);
//        if (cSrmSupplierChangeRecords.size() == 1) {
//            BeanUtils.copyProperties(cSrmSupplierChangeRecords, rsp);
//        }else {
//            return Rjson.error("????????????????????????????????????");
//        }
//        //  ??????????????????
//        rsp.setLinkManList(cSrmLinkmanChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
//        // ?????????????????????
//        rsp.setFinanceList(cSrmFinanceChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
//        //  ??????\???????????????
//        rsp.setBankList(cSrmBankChangeRecordMapper.selectByPrimaryKeyList(supplierChangeRecordReq));
//        return Rjson.success(rsp);
//    }
}

