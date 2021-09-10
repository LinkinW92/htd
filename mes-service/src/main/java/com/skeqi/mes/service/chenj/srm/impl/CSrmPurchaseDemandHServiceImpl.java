package com.skeqi.mes.service.chenj.srm.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.chenj.srm.CSrmFileUploadingMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmPurchaseDemandHMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmPurchaseDemandRMapper;
import com.skeqi.mes.mapper.gmg.UserDao;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseDemandH;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseDemandR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseDemandHRListReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseDemandHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmFileUploadingRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseDemandHRListRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseDemandHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseDemandRReq;
import com.skeqi.mes.service.chenj.srm.CSrmPurchaseDemandHService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmPurchaseDemandHServiceImpl implements CSrmPurchaseDemandHService {

    @Resource
    private CSrmPurchaseDemandHMapper cSrmPurchaseDemandHMapper;

    @Resource
    private CSrmPurchaseDemandRMapper cSrmPurchaseDemandRMapper;

    @Resource
    private UserDao userDao;

    @Resource
    private CSrmFileUploadingMapper cSrmFileUploadingMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmPurchaseDemandHMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmPurchaseDemandH record) {
        return cSrmPurchaseDemandHMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmPurchaseDemandH record) {
        return cSrmPurchaseDemandHMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmPurchaseDemandH record) {
        return cSrmPurchaseDemandHMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmPurchaseDemandH record) {
        return cSrmPurchaseDemandHMapper.insertSelective(record);
    }

    @Override
    public CSrmPurchaseDemandH selectByPrimaryKey(CSrmPurchaseDemandH record) {
        return cSrmPurchaseDemandHMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmPurchaseDemandH record) {
        return cSrmPurchaseDemandHMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmPurchaseDemandH record) {
        return cSrmPurchaseDemandHMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmPurchaseDemandH> list) {
        return cSrmPurchaseDemandHMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmPurchaseDemandH> list) {
        return cSrmPurchaseDemandHMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmPurchaseDemandH> list) {
        return cSrmPurchaseDemandHMapper.batchInsert(list);
    }

    @Override
    public Rjson createPurchaseDemand(CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq) throws Exception {

        if (("1").equals(cSrmPurchaseDemandHReq.getOperationSign())) {

            /**
             * 申请单号、状态、申请人、申请日期、项目号、项目名称、申请部门、采购员、
             * 行项目号、物料编码、物料名称、数量、单位、需求日期、收货地点
             *
             * 生成申请单号（以PR开头+年月日+6位流水号）、更新采购申请头行表（如果是ERP同步过来的状态初始为已分配）
             */
            CSrmPurchaseDemandH cSrmPurchaseDemandH = cSrmPurchaseDemandHMapper.selectFinallyData();
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (cSrmPurchaseDemandH == null) {
                // 未找到数据，从最新一条开始
                cSrmPurchaseDemandHReq.setRequestCode("PR" + yyyyMMdd + 100000);
            } else {
                int requestCode = Integer.parseInt(cSrmPurchaseDemandH.getRequestCode().substring(10)) + 1;
                cSrmPurchaseDemandHReq.setRequestCode("PR" + yyyyMMdd + requestCode);
            }

            // 新增采购需求头表
            cSrmPurchaseDemandH = new CSrmPurchaseDemandH();
            BeanUtils.copyProperties(cSrmPurchaseDemandHReq, cSrmPurchaseDemandH);
            cSrmPurchaseDemandH.setApplicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmPurchaseDemandHReq.getApplicationDate()));
            cSrmPurchaseDemandH.setCreateTime(new Date());
            cSrmPurchaseDemandHMapper.insertSelective(cSrmPurchaseDemandH);
            if (!CollectionUtils.isEmpty(cSrmPurchaseDemandHReq.getPurList())) {
                // 新增采购需求行表
                for (CSrmPurchaseDemandRReq item : cSrmPurchaseDemandHReq.getPurList()) {
                    // 生成行号
                    CSrmPurchaseDemandR cSrmPurchaseDemandR = cSrmPurchaseDemandRMapper.selectFinallyData(cSrmPurchaseDemandHReq.getRequestCode());
                    if (cSrmPurchaseDemandR == null) {
                        item.setRowProjectNumber("1");
                    } else {
                        int lineItemNo = Integer.parseInt(cSrmPurchaseDemandR.getRowProjectNumber()) + 1;
                        item.setRowProjectNumber(String.valueOf(lineItemNo));
                    }
                    cSrmPurchaseDemandR = new CSrmPurchaseDemandR();
                    BeanUtils.copyProperties(item, cSrmPurchaseDemandR);
                    if (StringUtil.eqNu(item.getRequiredDate())) {
                        cSrmPurchaseDemandR.setRequiredDate(new SimpleDateFormat("yyyy-MM-dd").parse(item.getRequiredDate()));
                    }
                    if (StringUtil.eqNu(item.getDeliveryDate())) {
                        cSrmPurchaseDemandR.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse(item.getDeliveryDate()));
                    }
                    // 设置申请单号
                    cSrmPurchaseDemandR.setRequestCode(cSrmPurchaseDemandHReq.getRequestCode());
                    cSrmPurchaseDemandR.setCreateTime(new Date());
                    cSrmPurchaseDemandRMapper.insertSelective(cSrmPurchaseDemandR);
                }
            }
            return Rjson.success("创建成功", cSrmPurchaseDemandHReq);
        } else if (("2").equals(cSrmPurchaseDemandHReq.getOperationSign())) {

            CSrmPurchaseDemandHReq req = new CSrmPurchaseDemandHReq();
            req.setRequestCode(cSrmPurchaseDemandHReq.getRequestCode());
            // 新增采购需求头表
            List<CSrmPurchaseDemandHRsp> purchaseDemandH = cSrmPurchaseDemandHMapper.findPurchaseDemandH(req);
            if (purchaseDemandH.size() > 0) {
                // 更新采购需求头表
                CSrmPurchaseDemandH cSrmPurchaseDemandH = new CSrmPurchaseDemandH();
                BeanUtils.copyProperties(cSrmPurchaseDemandHReq, cSrmPurchaseDemandH);
                if (StringUtil.eqNu(cSrmPurchaseDemandHReq.getApplicationDate())) {
                    cSrmPurchaseDemandH.setApplicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmPurchaseDemandHReq.getApplicationDate()));
                }
                cSrmPurchaseDemandH.setUpdateTime(new Date());
                cSrmPurchaseDemandHMapper.updateByPrimaryKeySelective(cSrmPurchaseDemandH);
                if (!CollectionUtils.isEmpty(cSrmPurchaseDemandHReq.getPurList())) {
                    // 删除旧行数据
                    cSrmPurchaseDemandRMapper.delData(cSrmPurchaseDemandHReq.getRequestCode());
                    // 新增采购需求行表
                    for (CSrmPurchaseDemandRReq item : cSrmPurchaseDemandHReq.getPurList()) {
                        // 生成行号
                        CSrmPurchaseDemandR cSrmPurchaseDemandR = cSrmPurchaseDemandRMapper.selectFinallyData(cSrmPurchaseDemandHReq.getRequestCode());
                        if (cSrmPurchaseDemandR == null) {
                            item.setRowProjectNumber("1");
                        } else {
                            int lineItemNo = Integer.parseInt(cSrmPurchaseDemandR.getRowProjectNumber()) + 1;
                            item.setRowProjectNumber(String.valueOf(lineItemNo));
                        }
                        cSrmPurchaseDemandR = new CSrmPurchaseDemandR();
                        BeanUtils.copyProperties(item, cSrmPurchaseDemandR);
                        if (StringUtil.eqNu(item.getRequiredDate())) {
                            cSrmPurchaseDemandR.setRequiredDate(new SimpleDateFormat("yyyy-MM-dd").parse(item.getRequiredDate()));
                        }
                        if (StringUtil.eqNu(item.getDeliveryDate())) {
                            cSrmPurchaseDemandR.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse(item.getDeliveryDate()));
                        }
                        // 设置申请单号
                        cSrmPurchaseDemandR.setRequestCode(cSrmPurchaseDemandHReq.getRequestCode());
                        cSrmPurchaseDemandR.setCreateTime(new Date());
                        cSrmPurchaseDemandRMapper.insertSelective(cSrmPurchaseDemandR);
                    }
                }

            } else {
                return Rjson.error("单号不存在");
            }
            return Rjson.success("更新成功", cSrmPurchaseDemandHReq);
        } else if (("3").equals(cSrmPurchaseDemandHReq.getOperationSign())) {
            CSrmPurchaseDemandHReq req = new CSrmPurchaseDemandHReq();
            req.setRequestCode(cSrmPurchaseDemandHReq.getRequestCode());
            // 新增采购需求头表
            List<CSrmPurchaseDemandHRsp> purchaseDemandH = cSrmPurchaseDemandHMapper.findPurchaseDemandH(req);
            if (purchaseDemandH.size() > 0) {
                // 更新采购需求头表
                CSrmPurchaseDemandH cSrmPurchaseDemandH = new CSrmPurchaseDemandH();
                BeanUtils.copyProperties(cSrmPurchaseDemandHReq, cSrmPurchaseDemandH);
                if (StringUtil.eqNu(cSrmPurchaseDemandHReq.getApplicationDate())) {
                    cSrmPurchaseDemandH.setApplicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmPurchaseDemandHReq.getApplicationDate()));
                }
                cSrmPurchaseDemandH.setUpdateTime(new Date());
                cSrmPurchaseDemandHMapper.updateByPrimaryKeySelective(cSrmPurchaseDemandH);
            }

            return Rjson.success("更新成功", cSrmPurchaseDemandHReq);
        } else {
            return Rjson.error("操作标识错误");
        }
    }


    @Override
    public Rjson findPurchaseDemandH(CSrmPurchaseDemandHReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<CSrmPurchaseDemandHRsp> purchaseDemandH = cSrmPurchaseDemandHMapper.findPurchaseDemandH(req);
        purchaseDemandH.forEach(itemH -> {
            // 根据申请单号获取摘要信息 数据格式((物料名称,规格),(物料名称,规格)) 举例： (气源安装钣金,SSY0620-40-04-05-00-13A),(气源安装钣金,SSY0620-40-04-05-00-13A),(气源安装钣金,SSY0620-40-04-05-00-13A)
            CSrmPurchaseDemandR cSrmPurchaseDemandR = new CSrmPurchaseDemandR();
            cSrmPurchaseDemandR.setRequestCode(itemH.getRequestCode());
            List<CSrmPurchaseDemandR> srmPurchaseDemandR = cSrmPurchaseDemandRMapper.selectByPrimaryKey(cSrmPurchaseDemandR);
            StringBuilder sb = new StringBuilder();
            srmPurchaseDemandR.forEach(itemR -> {
                sb.append("(").append(itemR.getMaterialName()).append(",").append(itemR.getSpecification()).append(")").append(",");
            });
            String digest = "";
            if (sb.length() > 0) {
                digest = sb.substring(0,sb.lastIndexOf(","));
                itemH.setDigest(digest);
            }
        });

        return Rjson.success(new PageInfo<>(purchaseDemandH));
    }


    @Override
    public Rjson findPurchaseDemandHRList(CSrmPurchaseDemandHRListReq req) {
        if (CollectionUtils.isEmpty(req.getReqList())) {
            return Rjson.success();
        }

        // 校验当前单号中是否有重复文件名
        List<CSrmFileUploadingRsp> repetitionFileNameList = cSrmFileUploadingMapper.findRepetitionFileNameList(req);
        if (!CollectionUtils.isEmpty(repetitionFileNameList)) {
            String flagName = "";
            // 存储重复订单号
            String reOrderNumber = "";
            StringBuilder sb = new StringBuilder();
            for (CSrmFileUploadingRsp item : repetitionFileNameList) {
                if (item.getCount() > 1) {
//                    if(1==item.getFlag()){
//                        flagName="采购文件";
//                    }else if(2==item.getFlag()){
//                        flagName="供应商文件";
//                    }
                    // 根据文件名称获取订单号
                    req.setFileName(item.getFileName());
                    List<CSrmFileUploadingRsp> cSrmFileUploadingRsps = cSrmFileUploadingMapper.selectByPrimaryOrderNumberList(req);
                    cSrmFileUploadingRsps.parallelStream().forEach(orderNumberItem -> {
                        sb.append(orderNumberItem.getOrderNumber()).append("，");
                    });
                    reOrderNumber = sb.substring(0, sb.lastIndexOf("，"));
                    /**
                     * 重复文件：申请单号:POREQ052574，文件类型:采购文件，文件名:excel-list (1).xlsx。
                     * 重复申请单号：POREQ052574，POREQ052574，POREQ052574，POREQ052574。
                     */
                    return Rjson.error("重复文件：申请单号:" + item.getOrderNumber() + "，文件类型:" + flagName + "，文件名:" + item.getFileName() + "。" + "重复文件的申请单号：" + reOrderNumber + "。");
                } else {
                    break;
                }
            }
        }
        List<CSrmPurchaseDemandHRListRsp> purchaseDemandHRList = cSrmPurchaseDemandHMapper.findPurchaseDemandHRList(req);
        boolean anyMatch = purchaseDemandHRList
                .stream()
                .anyMatch((item) -> !StringUtil.eqNu(item.getBuyerName()));
        if (anyMatch) {
            return Rjson.error("申请单号未分配采购员");
        }
        // 根据采购员去重
        ArrayList<CSrmPurchaseDemandHRListRsp> collect = purchaseDemandHRList.parallelStream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                Comparator.comparing(CSrmPurchaseDemandHRListRsp::getBuyerName)
        )), ArrayList::new));
        StringBuilder sb = new StringBuilder();
        collect.parallelStream().forEach(item -> {
            sb.append(item.getBuyerName()).append(",");
        });
        JSONObject json = new JSONObject();
        json.put("principal", sb.substring(0, sb.lastIndexOf(",")));
        json.put("list", purchaseDemandHRList);
        return Rjson.success(json);
    }


    @Override
    public Rjson findPurchaseDemandR(CSrmPurchaseDemandHReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmPurchaseDemandHMapper.findPurchaseDemandR(req)));
    }

    @Override
    public Rjson allocationPurchaseDemand(CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq) {
        // 校验采购员是否存在
//        CMesUserTReq req = new CMesUserTReq();
//        req.setId(Integer.parseInt(cSrmPurchaseDemandHReq.getBuyer()));
//        if (CollectionUtils.isEmpty(userDao.findUserList(req))) {
//            return Rjson.error("采购员不存在");
//        }
        cSrmPurchaseDemandHMapper.allocationPurchaseDemand(cSrmPurchaseDemandHReq);
        return Rjson.success();
    }

    @Override
    public Rjson delPurchaseDemand(CSrmPurchaseDemandHRListReq req) {
        // 删除头表记录
        CSrmPurchaseDemandH srmPurchaseDemandH = new CSrmPurchaseDemandH();
        srmPurchaseDemandH.setRequestCode(req.getRequestCode());
        srmPurchaseDemandH.setDelete(true);
        cSrmPurchaseDemandHMapper.updateByPrimaryKeySelective(srmPurchaseDemandH);
        // 删除行表记录
        CSrmPurchaseDemandR cSrmPurchaseDemandR = new CSrmPurchaseDemandR();
        cSrmPurchaseDemandR.setRequestCode(req.getRequestCode());
        cSrmPurchaseDemandR.setDelete(true);
        cSrmPurchaseDemandRMapper.updateByPrimaryKeySelective(cSrmPurchaseDemandR);
        return Rjson.success();
    }
}

