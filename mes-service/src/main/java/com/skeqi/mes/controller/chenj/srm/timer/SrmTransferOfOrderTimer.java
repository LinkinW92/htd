package com.skeqi.mes.controller.chenj.srm.timer;

import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseDemandR;
import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;
import com.skeqi.mes.service.wf.timer.CMesTimerConfigTService;
import com.skeqi.mes.util.wf.timer.TimerConfigConstant;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 采购申请转订单定时任务类
 */
@Component
@EnableScheduling
public class SrmTransferOfOrderTimer implements Runnable {

    /**
     * 定时器配置类
     */
    @Resource
    private CMesTimerConfigTService timerConfigTService;


    @Resource
    private CSrmKThreePurchaseAbuttingMapper cSrmKThreePurchaseAbuttingMapper;

    @Resource
    private CSrmPurchaseOrderHMapper cSrmPurchaseOrderHMapper;

    @Resource
    private CSrmPurchaseDemandHMapper cSrmPurchaseDemandHMapper;

    @Resource
    private CSrmPurchaseDemandRMapper cSrmPurchaseDemandRMapper;

    @Resource
    private CSrmPurchaseOrderRMapper cSrmPurchaseOrderRMapper;


    @Override
    public void run() {
        System.err.println("--------------------采购申请转订单定时任务类--------------------");
        //1.查询RM采购申请定时器配置
        List<CMesTimerConfigT> timerConfig = timerConfigTService.selectByCode(TimerConfigConstant.SRM_PO_REQUEST);
        if (timerConfig.size() > 0) {
            this.transferOfOrderTimer(timerConfig);
        }

    }


    /**
     * SRM采购申请转订单定时任务
     */
    public void transferOfOrderTimer(List<CMesTimerConfigT> timerConfig) {
        System.err.println("SRM采购申请转订单定时任务");
        // 获取采购申请行中采购申请单和物料编码
        List<CSrmPurchaseDemandR> cSrmPurchaseDemandRS = cSrmPurchaseDemandRMapper.selectByPrimaryKeyTransferOfOrder();
        // 根据物料编码去重
        List<CSrmPurchaseDemandR> cSrmPurchaseDemandRSS = cSrmPurchaseDemandRS.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                Comparator.comparing(CSrmPurchaseDemandR::getMaterialCode)
        )), ArrayList::new));
//        System.err.println("采购申请数量" + cSrmPurchaseDemandRSS.size());
        // 获取采购订单行中采购申请单和物料编码
        List<CSrmPurchaseDemandR> cSrmPurchaseOrderRS = cSrmPurchaseOrderRMapper.selectByPrimaryKeyTransferOfOrder();
        // 根据物料编码去重
        List<CSrmPurchaseDemandR> cSrmPurchaseOrderRSS = cSrmPurchaseOrderRS.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                Comparator.comparing(CSrmPurchaseDemandR::getMaterialCode)
        )), ArrayList::new));
//        System.err.println("采购订单数量" + cSrmPurchaseOrderRSS.size());
//        System.err.println("===============采购申请与采购订单的申请单号  合并============");
        List<CSrmPurchaseDemandR> collectH = cSrmPurchaseDemandRSS.stream().filter(itemDr -> cSrmPurchaseOrderRSS.stream().map(CSrmPurchaseDemandR::getRequestCode).collect(Collectors.toList()).contains(itemDr.getRequestCode())).collect(Collectors.toList());
//        collectH.forEach(System.err::println);
        System.err.println("总需处理数量:\t" + collectH.size());
        // 全部转单需求单号
        List<String> cSrmPurchaseDemandRSAll = new ArrayList<>();
        // 部门转单需求单号
        List<String> cSrmPurchaseDemandRSPortion = new ArrayList<>();


        // 采购需求   itemOr // 采购订单;  itemD // 采购需求;
        // 用合并后的采购订单到采购需求单里匹配单号中物料编码是否都存在
        cSrmPurchaseDemandRSS.forEach(itemD -> {
            // 根据单号筛选  采购申请单
            List<CSrmPurchaseDemandR> collect = cSrmPurchaseDemandRSS.stream().filter(itemRs -> itemRs.getRequestCode().equals(itemD.getRequestCode())).collect(Collectors.toList());
            // 根据单号筛选  采购订单
            List<CSrmPurchaseDemandR> collect1 = cSrmPurchaseOrderRSS.stream().filter(itemRs -> itemRs.getRequestCode().equals(itemD.getRequestCode())).collect(Collectors.toList());
            if (collect1.size() == collect.size()) {
                // 全部转单
                cSrmPurchaseDemandRSAll.add(itemD.getRequestCode());
            } else {
                // 部分转单
                cSrmPurchaseDemandRSPortion.add(itemD.getRequestCode());
            }
        });

        List<String> listAll = cSrmPurchaseDemandRSAll.stream().distinct().collect(Collectors.toList());
        List<String> listPortion = cSrmPurchaseDemandRSPortion.stream().distinct().collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(listAll)) {
            listAll.removeAll(listPortion);
            System.err.println("全部转单:");
            listAll.forEach(System.err::println);
            // 更新采购需求表状态  全部转单
            cSrmPurchaseDemandHMapper.updateBatchCSrmPurchaseDemandRSAll(listAll);
        }
        if (!CollectionUtils.isEmpty(listPortion)) {
            System.err.println("部分转单:");
            listPortion.forEach(System.err::println);
            // 更新采购需求表状态  部分转单
            cSrmPurchaseDemandHMapper.updateBatchCSrmPurchaseDemandRSPortion(listPortion);
        }


    }


}
