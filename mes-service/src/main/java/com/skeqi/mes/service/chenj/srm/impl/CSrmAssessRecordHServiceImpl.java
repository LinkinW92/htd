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
        // 校验供应商代码是否存在
        CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmAssessRecordHReq.getSupplierCode());
        if (cSrmSupplier == null) {
            return Rjson.error("考评对象不存在");
        } else {

            CSrmAssessRecordH cSrmAssessRecordH = null;
            if (("1").equals(cSrmAssessRecordHReq.getOperationSign())) {
                // 生成档案编号（以PE开头+年月日+3位流水号），更新评估档案头行表，状态为新建
                cSrmAssessRecordH = cSrmAssessRecordHMapper.selectFinallyData();
                String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                if (cSrmAssessRecordH == null) {
                    // 未找到数据，从最新一条开始
                    cSrmAssessRecordHReq.setFileNumber("PE" + yyyyMMdd + 100);
                } else {
                    int requestCode = Integer.parseInt(cSrmAssessRecordH.getFileNumber().substring(10)) + 1;
                    cSrmAssessRecordHReq.setFileNumber("PE" + yyyyMMdd + requestCode);
                }
                // 新增评估档案头表
                cSrmAssessRecordH = new CSrmAssessRecordH();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setCreateTime(new Date());
                // 存储建档时间，返回给页面显示
                cSrmAssessRecordHReq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmAssessRecordH.getCreateTime()));
                cSrmAssessRecordHMapper.insertSelective(cSrmAssessRecordH);
                // 新增评估档案行表
                if (!CollectionUtils.isEmpty(cSrmAssessRecordHReq.getReqList())) {
                    for (CSrmAssessRecordRReq req : cSrmAssessRecordHReq.getReqList()) {
                        // 生成行号
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
                        // 存储建档时间，返回给页面显示
                        req.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(assessRecordR.getCreateTime()));
                        assessRecordR.setSupplierCode(cSrmAssessRecordHReq.getSupplierCode());
                        cSrmAssessRecordRMapper.insertSelective(assessRecordR);
                    }
                }

                return Rjson.success("创建成功", cSrmAssessRecordHReq);
            } else if (("2").equals(cSrmAssessRecordHReq.getOperationSign())) {
                cSrmAssessRecordH = new CSrmAssessRecordH();
                // 更新供应商升降级申请头表
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setUpdateTime(new Date());
                cSrmAssessRecordHMapper.updateByPrimaryKeySelective(cSrmAssessRecordH);
                // 更新供应商升降级申请行表
                if (!CollectionUtils.isEmpty(cSrmAssessRecordHReq.getReqList())) {
                    for (CSrmAssessRecordRReq req : cSrmAssessRecordHReq.getReqList()) {
                        CSrmAssessRecordR srmAssessRecordR = null;
                        // 存储申请单号
                        req.setFileNumber(cSrmAssessRecordHReq.getFileNumber());
                        if (!StringUtil.eqNu(req.getLineNumber())) {
                            // 生成行号
                            srmAssessRecordR = cSrmAssessRecordRMapper.selectFinallyData(cSrmAssessRecordHReq.getFileNumber());
                            if (srmAssessRecordR == null) {
                                req.setLineNumber("1");
                            } else {
                                int lineItemNo = Integer.parseInt(srmAssessRecordR.getLineNumber()) + 1;
                                req.setLineNumber(String.valueOf(lineItemNo));
                            }
                            // 新增
                            srmAssessRecordR = new CSrmAssessRecordR();
                            BeanUtils.copyProperties(req, srmAssessRecordR);
                            srmAssessRecordR.setCreateTime(new Date());
                            cSrmAssessRecordRMapper.insertSelective(srmAssessRecordR);
                        } else {
                            // 更新
                            srmAssessRecordR = new CSrmAssessRecordR();
                            BeanUtils.copyProperties(req, srmAssessRecordR);
                            srmAssessRecordR.setUpdateTime(new Date());
                            cSrmAssessRecordRMapper.updateByPrimaryKeySelective(srmAssessRecordR);
                        }
                    }
                }

                return Rjson.success("更新成功", cSrmAssessRecordHReq);
            } else if (("3").equals(cSrmAssessRecordHReq.getOperationSign())) {
                // 更新供应商升降级申请头表
                cSrmAssessRecordH = new CSrmAssessRecordH();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setUpdateTime(new Date());
                cSrmAssessRecordHMapper.updateByPrimaryKeySelective(cSrmAssessRecordH);
                return Rjson.success("更新成功", cSrmAssessRecordHReq);
            } else {
                return Rjson.error("操作标识错误");
            }
        }
    }

    @Override
    public Rjson updatePerformanceEvaluationRecord(CSrmAssessRecordHReq cSrmAssessRecordHReq) {
        // 档案编号、操作标记、指标、指标值
        // 更新评估档案头行表，操作标记为提交评分，状态更新为评分中，操作标记为执行评分，状态更新为待发布，操作为发布，状态更新为已完成
        // 校验档案编号是否在R表中存在
        CSrmAssessRecordH cSrmAssessRecordH = new CSrmAssessRecordH();
        CSrmAssessRecordR cSrmAssessRecordR = null;
        cSrmAssessRecordH.setFileNumber(cSrmAssessRecordHReq.getFileNumber());
        cSrmAssessRecordH = cSrmAssessRecordHMapper.selectByPrimaryKey(cSrmAssessRecordH);
        if (null != cSrmAssessRecordH) {
            if (("1").equals(cSrmAssessRecordHReq.getOperationSign())) {
                // 更新评估档案头表
                cSrmAssessRecordH = new CSrmAssessRecordH();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setUpdateTime(new Date());
                cSrmAssessRecordHMapper.updateByPrimaryKeySelective(cSrmAssessRecordH);
                // 更新评估档案行表
                cSrmAssessRecordR = new CSrmAssessRecordR();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordR);
                cSrmAssessRecordR.setUpdateTime(new Date());
                cSrmAssessRecordRMapper.updateByPrimaryKeySelective(cSrmAssessRecordR);
            } else if (("2").equals(cSrmAssessRecordHReq.getOperationSign())) {
                // 更新评估档案头表
                cSrmAssessRecordH = new CSrmAssessRecordH();
                BeanUtils.copyProperties(cSrmAssessRecordHReq, cSrmAssessRecordH);
                cSrmAssessRecordH.setUpdateTime(new Date());
                cSrmAssessRecordHMapper.updateByPrimaryKeySelective(cSrmAssessRecordH);
            } else {
                return Rjson.error("操作标识错误");
            }
        } else {
            return Rjson.error("档案编号在绩效评估档案记录中不存在");
        }
        return Rjson.success("更新成功", null);
    }

    @Override
    public Rjson findReceivedAssess(CSrmAssessRecordHReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmAssessRecordHMapper.findReceivedAssess(req)));
    }

    @Override
    public Rjson findPerformanceEvaluationRecordHR(CSrmAssessRecordHReq req) {
        CSrmAssessRecordHRRsp rsp = new CSrmAssessRecordHRRsp();
        // 获取头档案数据
        if (StringUtil.eqNu(req.getFileNumber())) {
            List<CSrmAssessRecordHRRsp> rspList = cSrmAssessRecordHMapper.selectByPrimaryKeyListH(req);
            if (rspList.size() == 1) {
                rspList.forEach(item -> {
                    BeanUtils.copyProperties(item, rsp);
                });
            }
            // 获取行档案数据
            rsp.setReqList(cSrmAssessRecordRMapper.selectByPrimaryKeyListR(req));
            return Rjson.success(rsp);
        } else {
            return Rjson.success();
        }
    }

    @Override
    public Rjson findPerformanceEvaluationRecordH(CSrmAssessRecordHReq req) {
        // 获取头数据
        return Rjson.success(cSrmAssessRecordHMapper.selectByPrimaryKeyListH(req));
    }
}



