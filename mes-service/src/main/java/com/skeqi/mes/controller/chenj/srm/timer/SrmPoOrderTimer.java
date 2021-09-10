package com.skeqi.mes.controller.chenj.srm.timer;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.common.utils.redis.RedisCache;
import com.skeqi.mes.finals.SrmFinal;
import com.skeqi.mes.mapper.chenj.srm.CSrmKThreePurchaseAbuttingMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmPurchaseOrderHMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmPurchaseOrderRMapper;
import com.skeqi.mes.mapper.wf.linesidelibrary.CLslDictionaryTMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOOrder;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOOrderResult;
import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;
import com.skeqi.mes.service.wf.timer.CMesTimerConfigTService;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.chenj.CommonUtils;
import com.skeqi.mes.util.wf.timer.TimerConfigConstant;
import com.skeqi.mes.util.wf.timer.log.TimePerformLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 采购订单定时任务类
 */
@Component
@EnableScheduling
@Slf4j
public class SrmPoOrderTimer implements Runnable {

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
    private CSrmPurchaseOrderRMapper cSrmPurchaseOrderRMapper;


    @Override
    public void run() {
        System.err.println("--------------------采购订单定时任务类--------------------");
        //1.查询SRM采购订单定时器配置
        List<CMesTimerConfigT> timerConfig = timerConfigTService.selectByCode(TimerConfigConstant.SRM_PO_REQUEST);
        if (timerConfig.size() > 0) {
            this.KThreePoOrderResult(timerConfig);
        }

    }

    // 存储新增单号ID
    List<CSrmKThreePurchaseAbutting> alterRecordListAdd = null;
    // 存储删除单号ID
    List<CSrmKThreePurchaseAbutting> alterRecordListDel = null;

    /**
     * SRM采购订单定时任务
     */
    public void KThreePoOrderResult(List<CMesTimerConfigT> timerConfig) {


        // 获取是否有卡状态的订单 有则进行处理
        List<CSrmKThreePurchaseAbutting> purchaseAbuttingList = cSrmKThreePurchaseAbuttingMapper.selectByPrimaryKeyListStatus();
        if (purchaseAbuttingList.size() > 0) {
            // 更新状态为已变更
            cSrmKThreePurchaseAbuttingMapper.updateByPrimaryKeyListStatus(purchaseAbuttingList);
            // 停止本次执行任务
			log.error("SRM采购订单定时任务已停止任务");
            return;
        }


        //  doType 单据类型((1.采购申请、2.采购订单、3.送货单))
        // 获取doType="1" 的数据
        List<CSrmKThreePurchaseAbutting> alterRecordList = cSrmKThreePurchaseAbuttingMapper.findAlterRecordList("2");
        if (alterRecordList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            // -------------------------------------------------查询条件拼接-------------------------------------------------------------
            // 初始化存储新增单号ID
            alterRecordListAdd = new ArrayList<>();
            // 初始化存储删除单号ID
            alterRecordListDel = new ArrayList<>();
            // 新增
            alterRecordList.stream().filter(item -> item.getAlterType().equals("1")).forEach(item -> {
                sb.append("ID=").append("'").append(item.getRequestCode()).append("'").append(" ").append("or").append(" ");
                CSrmKThreePurchaseAbutting purchaseAbutting = new CSrmKThreePurchaseAbutting();
                purchaseAbutting.setId(item.getId());
                alterRecordListAdd.add(purchaseAbutting);
            });
            String addItem = sb.toString();
            sb.setLength(0);
            if (StringUtil.eqNu(addItem)) {
                // 新增
                String createData = addItem.substring(0, addItem.lastIndexOf("or") - 1);
                // 执行新增处理逻辑
                executeSwitch(1, timerConfig, createData, alterRecordList);
            }


            // 删除
            alterRecordList.stream().filter(item -> item.getAlterType().equals("3")).forEach(item -> {
                CSrmKThreePurchaseAbutting purchaseAbutting = new CSrmKThreePurchaseAbutting();
                purchaseAbutting.setId(item.getId());
                purchaseAbutting.setOrderNumber(item.getOrderNumber());
                alterRecordListDel.add(purchaseAbutting);
            });

            if (!CollectionUtils.isEmpty(alterRecordListDel)) {
                // 执行删除处理逻辑
                executeSwitch(3, timerConfig, "", alterRecordList);
            }

        }

    }


    /**
     * 执行switch
     *
     * @param num
     * @param timerConfig
     */
    private void executeSwitch(int num, List<CMesTimerConfigT> timerConfig, String data, List<CSrmKThreePurchaseAbutting> alterRecordList) {
        KThreePOOrderResult orderResultRsp = null;
        LocalDateTime dataTime = LocalDateTime.now();
        // 变更类型(1.创建、2.修改、3.删除、4.入库(送货单))
        switch (num) {
            case 1:
                // 发送请求
                orderResultRsp = checkResult(sendPost(data), timerConfig, dataTime);
                if (null != orderResultRsp) {
                    System.err.println("----------------------------------------------新增数据----------------------------------------------");
                    // 根据ID去重
                    ArrayList<KThreePOOrder> collect = orderResultRsp.getData().stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                            Comparator.comparing(KThreePOOrder::getID)
                    )), ArrayList::new));
                    // 批量新增头数据
                    cSrmPurchaseOrderHMapper.batchInsertKThree(collect);
                    // 批量新增行数据
                    cSrmPurchaseOrderRMapper.batchInsertKThree(orderResultRsp.getData());
                    // 更新为已处理状态
                    cSrmKThreePurchaseAbuttingMapper.updateBatchAlterStatus(alterRecordListAdd);
                }
                break;
            case 2:
                // 修改
//                orderResultRsp = checkResult(sendPost(data), timerConfig, dataTime);
//                if (null != orderResultRsp) {
//                    System.err.println("----------------------------------------------修改数据----------------------------------------------");
//                    // 根据ID去重
//                    ArrayList<KThreePOOrder> collect = orderResultRsp.getData().parallelStream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
//                            Comparator.comparing(KThreePOOrder::getID)
//                    )), ArrayList::new));
//                    // 批量修改头数据
//                    cSrmPurchaseOrderHMapper.updateBatchSelectiveKThree(collect);
//                    // 修改行数据
//                    orderResultRsp.getData().parallelStream().forEach(item -> {
//                        cSrmPurchaseOrderRMapper.updateSelectiveKThree(item);
//                    });
//                    // 更新为已处理状态
//                    cSrmKThreePurchaseAbuttingMapper.updateBatchAlterStatus(alterRecordList);
//
//                }
                break;
            case 3:
                // 删除
                System.err.println("----------------------------------------------删除数据----------------------------------------------");
                // 批量删除头数据
                cSrmPurchaseOrderHMapper.delKThreeData(alterRecordListDel);
                // 批量删除行数据
                cSrmPurchaseOrderRMapper.delKThreeData(alterRecordListDel);
                // 更新为已处理状态
                cSrmKThreePurchaseAbuttingMapper.updateBatchAlterStatus(alterRecordListDel);
                break;
        }
    }


    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private CLslDictionaryTMapper cLslDictionaryTMapper;

	@Autowired
	private RedisCache redisCache;
    /**
     * 发送请求
     *
     * @param params
     * @return
     */
    public String sendPost(String params) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("jktype", "POOrder");
        map.put("method", "view");
        map.put("filter", params);
        System.err.println("----------------------------------------------发送请求----------------------------------------------");
        // 将编码改为utf-8
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 获取请求路径
        // kThreeUrl K3接口访问地址
        String requestUrl = CommonUtils.getRedisValue(redisCache,SrmFinal.K_THREE_URL,"K3请求url");
        ResponseEntity<String> forEntity = restTemplate.postForEntity(requestUrl, map, String.class);
        // 防止返回值乱码
        String body=forEntity.getBody().substring(forEntity.getBody().indexOf("{"));
		log.info("【采购订单入参】[{}]", JSONUtil.toJsonStr(map.toString()));
		log.info("【采购订单出参】[{}]", JSONUtil.toJsonStr(body));
        return forEntity.getBody();
    }

    /**
     * 校验参数
     *
     * @param body        响应体
     * @param timerConfig 定时器配置信息
     * @param dataTime    时间
     */
    private KThreePOOrderResult checkResult(String body, List<CMesTimerConfigT> timerConfig, LocalDateTime dataTime) {
        KThreePOOrderResult requestResultRsp = JSONObject.parseObject(body, KThreePOOrderResult.class);
        if (!CollectionUtils.isEmpty(requestResultRsp.getData())) {
            // 返回请求参数
            return requestResultRsp;
        } else {
            //记录定时任务日志
            TimePerformLogUtil.addCustomLog(timerConfig.get(0).getCode(), requestResultRsp.getMessage(), dataTime);
            // 通知邮箱
            //////////
            return null;
        }

    }


}
