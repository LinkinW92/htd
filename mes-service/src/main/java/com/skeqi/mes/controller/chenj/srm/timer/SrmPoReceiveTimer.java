package com.skeqi.mes.controller.chenj.srm.timer;

import com.skeqi.mes.mapper.chenj.srm.CSrmKThreePurchaseAbuttingMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmSendCommodityHMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.service.wf.timer.CMesTimerConfigTService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 送货单定时任务类
 */
@Component
@EnableScheduling
public class SrmPoReceiveTimer implements Runnable {

    /**
     * 定时器配置类
     */
    @Resource
    private CMesTimerConfigTService timerConfigTService;


    @Resource
    private CSrmKThreePurchaseAbuttingMapper cSrmKThreePurchaseAbuttingMapper;

    @Resource
    private CSrmSendCommodityHMapper cSrmSendCommodityHMapper;

    @Override
    public void run() {
        System.err.println("--------------------送货单定时任务类--------------------");

        // 获取是否有卡状态的订单 有则进行处理
        List<CSrmKThreePurchaseAbutting> purchaseAbuttingList = cSrmKThreePurchaseAbuttingMapper.selectByPrimaryKeyListStatus();
        if (purchaseAbuttingList.size() > 0) {
            // 更新状态为已变更
            cSrmKThreePurchaseAbuttingMapper.updateByPrimaryKeyListStatus(purchaseAbuttingList);
            // 停止本次执行任务
            System.err.println("已停止任务");
            return;
        }

        List<CSrmKThreePurchaseAbutting> alterRecordList = cSrmKThreePurchaseAbuttingMapper.findAlterRecordList("3");
        if (alterRecordList.size() > 0) {
            // 批量更新送货单状态为 5:已完成
            cSrmSendCommodityHMapper.updateBatchSelectiveSuccessStatus(alterRecordList);
            // 更新为已处理状态
            cSrmKThreePurchaseAbuttingMapper.updateBatchAlterStatus(alterRecordList);
        }
    }


}
