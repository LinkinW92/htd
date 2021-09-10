package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmKThreePurchaseAbuttingMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmKThreePurchaseAbuttingReq;
import com.skeqi.mes.service.chenj.srm.CSrmKThreePurchaseAbuttingService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmKThreePurchaseAbuttingServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmKThreePurchaseAbuttingServiceImpl implements CSrmKThreePurchaseAbuttingService {

    @Resource
    private CSrmKThreePurchaseAbuttingMapper cSrmKThreePurchaseAbuttingMapper;

    @Override
    public int insertSelective(CSrmKThreePurchaseAbutting record) {
        return cSrmKThreePurchaseAbuttingMapper.insertSelective(record);
    }

    @Override
    public Rjson addAlterRecord(CSrmKThreePurchaseAbuttingReq cSrmKThreePurchaseAbuttingReq) {
        /**
         * 校验当前单据号是否存在，不存在则新增，存在则更新  更新对应时间字段(方便做页面查询时作为时间段条件查询)
         */
        // 校验当前单据号是否存在
        CSrmKThreePurchaseAbutting srmKThreePurchaseAbutting = new CSrmKThreePurchaseAbutting();
        srmKThreePurchaseAbutting.setDoNumber(cSrmKThreePurchaseAbuttingReq.getDoNumber());
        srmKThreePurchaseAbutting = cSrmKThreePurchaseAbuttingMapper.selectByPrimaryKey(srmKThreePurchaseAbutting);
        if (!StringUtil.eqNu(srmKThreePurchaseAbutting)) {
            // 不存在，新增
            srmKThreePurchaseAbutting = new CSrmKThreePurchaseAbutting();
            BeanUtils.copyProperties(cSrmKThreePurchaseAbuttingReq, srmKThreePurchaseAbutting);
            srmKThreePurchaseAbutting.setCreateTime(new Date());
            cSrmKThreePurchaseAbuttingMapper.insertSelective(srmKThreePurchaseAbutting);
        } else {
            // 存在，更新
            Integer id = srmKThreePurchaseAbutting.getId();
            srmKThreePurchaseAbutting = new CSrmKThreePurchaseAbutting();
            BeanUtils.copyProperties(cSrmKThreePurchaseAbuttingReq, srmKThreePurchaseAbutting);
            srmKThreePurchaseAbutting.setId(id);
            srmKThreePurchaseAbutting.setAlterStatus("1");
            srmKThreePurchaseAbutting.setUpdateTime(new Date());
            cSrmKThreePurchaseAbuttingMapper.updateByPrimaryKeySelective(srmKThreePurchaseAbutting);
        }
        return Rjson.success();
    }


    // 总数量
    int count;
    // 单据类型
    String doType;
    // 成功数量
    int successCount;
    // 采购申请成功数量
    int purSuccessCount;
    // 采购订单成功数量
    int purOrderSuccessCount;
    // 送货单成功数量
    int sendCargoOrderSuccessCount;

    /**
     * 定时 获取K3采购对接日志表数据
     */
    public Rjson findAlterRecord(CSrmKThreePurchaseAbuttingReq req) {
        count = 0;
        doType = "";
        successCount = 0;
        purSuccessCount = 0;
        purOrderSuccessCount = 0;
        sendCargoOrderSuccessCount = 0;
        /**
         *    (外层校验) 查询变更状态为1的数据
         *    (内层一级校验) 单据类型匹配对应模块
         *    (内层二级校验) 变更类型匹配并更新对应单据状态，最后更新状态为已变更并更新当前时间为更新时间
         */
        // 获取K3采购对接日志表数据
        List<CSrmKThreePurchaseAbutting> alterRecord = cSrmKThreePurchaseAbuttingMapper.findAlterRecord(req);
        StringBuilder sb=null;
        if (alterRecord.size() > 0) {
            // 记录总数量
            count = alterRecord.size();
            // 单据类型((1.采购申请、2.采购订单、3.送货单))     字段： doType
            // 变更类型(1.创建、2.修改、3.删除、4.入库(送货单)) 字段： alterType

            List<CSrmKThreePurchaseAbutting> purApplicationCollect = alterRecord.stream().filter(item -> item.getDoType().equals("1")).collect(Collectors.toList());
            purSuccessCount= purApplicationCollect.size();
            // 批量更新 采购申请数据  根据单据号 doNumber
            // 执行批量更新sql
            List<CSrmKThreePurchaseAbutting> purOrderCollect = alterRecord.stream().filter(item -> item.getDoType().equals("2")).collect(Collectors.toList());
            purOrderSuccessCount=purOrderCollect.size();
            // 批量更新 采购订单数据  根据单据号 doNumber
            // 执行批量更新sql
            List<CSrmKThreePurchaseAbutting> sendCargoOrderCollect = alterRecord.stream().filter(item -> item.getDoType().equals("3")).collect(Collectors.toList());
            sendCargoOrderSuccessCount=sendCargoOrderCollect.size();
            // 批量更新 送货单数据  根据单据号 doNumber
            // 执行批量更新sql
             sb = new StringBuilder().append("操作成功\n").append("总数量:").append(count).append("\n总成功数量:")
                    .append(successCount).append("\n采购申请成功数量\n").append(purSuccessCount)
                    .append("\n采购订单成功数量\n").append(purOrderSuccessCount).append("\n送货单成功数量\n").append(sendCargoOrderSuccessCount);
            System.err.println(sb);
        } else {
            System.err.println("暂无待处理数据");
        }

        return Rjson.success(sb);

    }


    @Override
    public  List<CSrmKThreePurchaseAbutting> findAlterRecordList(String doType) {
        return cSrmKThreePurchaseAbuttingMapper.findAlterRecordList(doType);
    }

    @Override
    public CSrmKThreePurchaseAbutting selectByPrimaryKey(CSrmKThreePurchaseAbutting record) {
        return cSrmKThreePurchaseAbuttingMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmKThreePurchaseAbutting> list) {
        return cSrmKThreePurchaseAbuttingMapper.updateBatchSelective(list);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmKThreePurchaseAbutting record) {
        return cSrmKThreePurchaseAbuttingMapper.updateByPrimaryKeySelective(record);
    }
}

