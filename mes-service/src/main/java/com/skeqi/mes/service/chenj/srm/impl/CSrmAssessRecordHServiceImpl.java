package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.chenj.srm.CSrmAssessRecordHMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmAssessRecordRMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmSupplierMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmAssessRecordH;
import com.skeqi.mes.pojo.chenj.srm.CSrmAssessRecordR;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplier;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmAssessRecordHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmAssessRecordRReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmAssessRecordHRRsp;
import com.skeqi.mes.service.chenj.srm.CSrmAssessRecordHService;
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
 * @date 2021/6/7
 * @Classname CSrmAssessRecordHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmAssessRecordHServiceImpl implements CSrmAssessRecordHService {

    @Resource
    private CSrmAssessRecordHMapper cSrmAssessRecordHMapper;
    @Resource
    private CSrmAssessRecordRMapper cSrmAssessRecordRMapper;
    @Resource
    private CSrmSupplierMapper cSrmSupplierMapper;


    @Override
    public int insertSelective(CSrmAssessRecordH record) {
        return cSrmAssessRecordHMapper.insertSelective(record);
    }

    @Override
    public CSrmAssessRecordH selectByPrimaryKey(CSrmAssessRecordH record) {
        return cSrmAssessRecordHMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmAssessRecordH record) {
        return cSrmAssessRecordHMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmAssessRecordH> list) {
        return cSrmAssessRecordHMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmAssessRecordH> list) {
        return cSrmAssessRecordHMapper.batchInsert(list);
    }

    @Override
    public Rjson createPerformanceEvaluationRecord(CSrmAssessRecordHReq cSrmAssessRecordHReq) throws ParseException {
        // ?????????????????????????????????
        CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmAssessRecordHReq.getSupplierCode());
        if (cSrmSupplier == null) {
            return Rjson.error("?????????????????????");
        } else {

            CSrmAssessRecordH cSrmAssessRecordH = null;
            if (("1").equals(cSrmAssessRecordHReq.getOperationSign())) {
                // ????????????????????????PE??????+?????????+3???????????????????????????????????????????????????????????????
                cSrmAssessRecordH = cSrmAssessRecordHMapper.selectFinallyData();
                String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                if (cSrmAssessRecordH == null) {
                    // ???????????????????????????????????????
                    cSrmAssessRecordHReq.setFileNumber("PE" + yyyyMMdd + 100);
                } else {
                    int requestCode = Integer.parseInt(cSrmAssessRecordH.getFileNumber().substring(10)) + 1;
                    cSrmAssessRecordHReq.setFileNumber("PE" + yyyyMMdd + requestCode);
                }
                // ????????????????????????
                cSrmAssessRecordH = new CSrmAssessRecordH();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setCreateTime(new Date());
                // ??????????????????????????????????????????
                cSrmAssessRecordHReq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmAssessRecordH.getCreateTime()));
                cSrmAssessRecordHMapper.insertSelective(cSrmAssessRecordH);
                // ????????????????????????
                if (!CollectionUtils.isEmpty(cSrmAssessRecordHReq.getReqList())) {
                    for (CSrmAssessRecordRReq req : cSrmAssessRecordHReq.getReqList()) {
                        // ????????????
                        CSrmAssessRecordR assessRecordR = cSrmAssessRecordRMapper.selectFinallyData(cSrmAssessRecordHReq.getFileNumber());
                        if (assessRecordR == null) {
                            req.setLineNumber("1");
                        } else {
                            int lineItemNo = Integer.parseInt(assessRecordR.getLineNumber()) + 1;
                            req.setLineNumber(String.valueOf(lineItemNo));
                        }
                        req.setFileNumber(cSrmAssessRecordHReq.getFileNumber());
                        assessRecordR = new CSrmAssessRecordR();
                        BeanUtils.copyProperties(req, assessRecordR);
                        assessRecordR.setCreateTime(new Date());
                        // ??????????????????????????????????????????
                        req.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(assessRecordR.getCreateTime()));
                        assessRecordR.setSupplierCode(cSrmAssessRecordHReq.getSupplierCode());
                        cSrmAssessRecordRMapper.insertSelective(assessRecordR);
                    }
                }

                return Rjson.success("????????????", cSrmAssessRecordHReq);
            } else if (("2").equals(cSrmAssessRecordHReq.getOperationSign())) {
                cSrmAssessRecordH = new CSrmAssessRecordH();
                // ????????????????????????????????????
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setUpdateTime(new Date());
                cSrmAssessRecordHMapper.updateByPrimaryKeySelective(cSrmAssessRecordH);
                // ????????????????????????????????????
                if (!CollectionUtils.isEmpty(cSrmAssessRecordHReq.getReqList())) {
                    for (CSrmAssessRecordRReq req : cSrmAssessRecordHReq.getReqList()) {
                        CSrmAssessRecordR srmAssessRecordR = null;
                        // ??????????????????
                        req.setFileNumber(cSrmAssessRecordHReq.getFileNumber());
                        if (!StringUtil.eqNu(req.getLineNumber())) {
                            // ????????????
                            srmAssessRecordR = cSrmAssessRecordRMapper.selectFinallyData(cSrmAssessRecordHReq.getFileNumber());
                            if (srmAssessRecordR == null) {
                                req.setLineNumber("1");
                            } else {
                                int lineItemNo = Integer.parseInt(srmAssessRecordR.getLineNumber()) + 1;
                                req.setLineNumber(String.valueOf(lineItemNo));
                            }
                            // ??????
                            srmAssessRecordR = new CSrmAssessRecordR();
                            BeanUtils.copyProperties(req, srmAssessRecordR);
                            srmAssessRecordR.setCreateTime(new Date());
                            cSrmAssessRecordRMapper.insertSelective(srmAssessRecordR);
                        } else {
                            // ??????
                            srmAssessRecordR = new CSrmAssessRecordR();
                            BeanUtils.copyProperties(req, srmAssessRecordR);
                            srmAssessRecordR.setUpdateTime(new Date());
                            cSrmAssessRecordRMapper.updateByPrimaryKeySelective(srmAssessRecordR);
                        }
                    }
                }

                return Rjson.success("????????????", cSrmAssessRecordHReq);
            } else if (("3").equals(cSrmAssessRecordHReq.getOperationSign())) {
                // ????????????????????????????????????
                cSrmAssessRecordH = new CSrmAssessRecordH();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setUpdateTime(new Date());
                cSrmAssessRecordHMapper.updateByPrimaryKeySelective(cSrmAssessRecordH);
                return Rjson.success("????????????", cSrmAssessRecordHReq);
            } else {
                return Rjson.error("??????????????????");
            }
        }
    }

    @Override
    public Rjson updatePerformanceEvaluationRecord(CSrmAssessRecordHReq cSrmAssessRecordHReq) {
        // ????????????????????????????????????????????????
        // ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        // ???????????????????????????R????????????
        CSrmAssessRecordH cSrmAssessRecordH = new CSrmAssessRecordH();
        CSrmAssessRecordR cSrmAssessRecordR = null;
        cSrmAssessRecordH.setFileNumber(cSrmAssessRecordHReq.getFileNumber());
        cSrmAssessRecordH = cSrmAssessRecordHMapper.selectByPrimaryKey(cSrmAssessRecordH);
        if (null != cSrmAssessRecordH) {
            if (("1").equals(cSrmAssessRecordHReq.getOperationSign())) {
                // ????????????????????????
                cSrmAssessRecordH = new CSrmAssessRecordH();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setUpdateTime(new Date());
                cSrmAssessRecordHMapper.updateByPrimaryKeySelective(cSrmAssessRecordH);
                // ????????????????????????
                cSrmAssessRecordR = new CSrmAssessRecordR();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordR);
                cSrmAssessRecordR.setUpdateTime(new Date());
                cSrmAssessRecordRMapper.updateByPrimaryKeySelective(cSrmAssessRecordR);
            } else if (("2").equals(cSrmAssessRecordHReq.getOperationSign())) {
                // ????????????????????????
                cSrmAssessRecordH = new CSrmAssessRecordH();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setUpdateTime(new Date());
                cSrmAssessRecordHMapper.updateByPrimaryKeySelective(cSrmAssessRecordH);
            } else {
                return Rjson.error("??????????????????");
            }
        } else {
            return Rjson.error("???????????????????????????????????????????????????");
        }
        return Rjson.success("????????????", null);
    }

    @Override
    public Rjson findReceivedAssess(CSrmAssessRecordHReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmAssessRecordHMapper.findReceivedAssess(req)));
    }

    @Override
    public Rjson findPerformanceEvaluationRecordHR(CSrmAssessRecordHReq req) {
        CSrmAssessRecordHRRsp rsp = new CSrmAssessRecordHRRsp();
        // ?????????????????????
        if (StringUtil.eqNu(req.getFileNumber())) {
            List<CSrmAssessRecordHRRsp> rspList = cSrmAssessRecordHMapper.selectByPrimaryKeyListH(req);
            if (rspList.size() == 1) {
                rspList.forEach(item -> {
                    BeanUtils.copyProperties(item, rsp);
                });
            }
            // ?????????????????????
            rsp.setReqList(cSrmAssessRecordRMapper.selectByPrimaryKeyListR(req));
            return Rjson.success(rsp);
        } else {
            return Rjson.success();
        }
    }

    @Override
    public Rjson findPerformanceEvaluationRecordH(CSrmAssessRecordHReq req) {
        // ???????????????
        return Rjson.success(cSrmAssessRecordHMapper.selectByPrimaryKeyListH(req));
    }
}



