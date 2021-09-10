package com.skeqi.mes.controller.wf.timer.instantiationTimer;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wf.linesidelibrary.*;
import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;
import com.skeqi.mes.service.wf.linesidelibrary.CLslDictionaryTService;
import com.skeqi.mes.service.wf.linesidelibrary.CLslRockConfigTService;
import com.skeqi.mes.service.wf.linesidelibrary.RLslMaterialRequestTService;
import com.skeqi.mes.service.wf.timer.CMesTimerConfigTService;
import com.skeqi.mes.util.UDPUtils;
import com.skeqi.mes.util.wf.timer.TimerConfigConstant;
import com.skeqi.mes.util.wf.timer.log.TimePerformLogUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 线边库定时要料任务类
 * @author Lenovo
 */
@Component
@EnableScheduling
public class MarginLibraryTimer implements Runnable {
    @Override
    public void run() {
        //1.查询告警通知定时器配置
        List<CMesTimerConfigT> timerConfig = timerConfigTService.selectByCode(TimerConfigConstant.PRODUCE_LINE);
        if (timerConfig.size()>0){
            this.missingAlarm(timerConfig);
        }
    }

    /**
     * 线边库配置类
     */
    @Resource
    private CLslRockConfigTService rockConfigTService;

    /**
     * 要料请求类
     */
    @Resource
    private RLslMaterialRequestTService requestTService;

    /**
     * 喇叭配置
     */
    @Resource
    private CLslDictionaryTService dictionaryTService;

    /**
     * 定时器配置类
     */
    @Resource
    private CMesTimerConfigTService timerConfigTService;


    /**
     * 要料轮询任务
     */
    public void missingAlarm(List<CMesTimerConfigT> timerConfig){
        LocalDateTime dataTime = LocalDateTime.now();
        Exception exception = null;
        try {
                List<CLslRockConfigT> resultList = new ArrayList<>();
                //1.执行查询线边库正在使用的配置信息
                List<CLslRockConfigT> list = rockConfigTService.findAllWorkplaceConfig();
                if (list.size()>0){
                    //2.遍历线边库告警
                    for (CLslRockConfigT cLslRockConfigT : list) {
                        //3.确认线边库要料数量
                        List<Map<String, Object>> list1 = rockConfigTService.findWorkOrderRecipeByLineId(cLslRockConfigT);
                        if (list1.size() > 0) {
                            //工单余量
                            Integer remaining = StringUtils.isEmpty(list1.get(0).get("NUMBER_REMAINING")) ? 0 : Integer.parseInt(list1.get(0).get("NUMBER_REMAINING").toString());
                            //工序所需数量
                            Integer numbers = StringUtils.isEmpty(list1.get(0).get("NUMBERS")) ? 0 : Integer.parseInt(list1.get(0).get("NUMBERS").toString());
                            //料架容量
                            Integer capacity = cLslRockConfigT.getCapacity();
                            //料架余量
                            Integer quantity = cLslRockConfigT.getQuantity();
                            //能完成多少个和工单对应
                            if (remaining <= (capacity / numbers)) {
                                //工单对应工序使用物料数量
                                Integer workNumber = numbers;
                                if (remaining > 1) {
                                    workNumber = (remaining * numbers);
                                }
                                //工单余量减料架余量
                                cLslRockConfigT.setRequiredQuantity(workNumber - quantity);
                            } else {
                                //料架容量减料架余量
                                cLslRockConfigT.setRequiredQuantity(capacity - quantity);
                            }
                            //返回列表
                            if (cLslRockConfigT.getRequiredQuantity() > 0) {
                                resultList.add(cLslRockConfigT);
                            }
                        }
                    }
                    //4.存储请求记录到记录表
                    if (resultList.size()>0){
                        List<CLslRockConfigT> distinctClass =  resultList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(o -> o.getProductId() + ";" + o.getLineId()+";"+o.getStationId())
                        )), ArrayList::new));
                        //4.1验证当前告警记录是否存在并且处于未完成状态
                        List<RLslMaterialRequestT> requestTS =  requestTService.findMaterialRequest(distinctClass);
                        if (requestTS.size()>0){
                            //4.2筛选出未处理的要料记录
                                //4.2.1遍历查出来的未处理的请求记录
                            for (RLslMaterialRequestT rLslMaterialRequestT : requestTS) {
                                //4.2.2移除已存在但未处理的记录
                                resultList.removeIf(
                                        s ->
                                                s.getProductId().equals(rLslMaterialRequestT.getProductId())
                                                        && s.getLineId().equals(rLslMaterialRequestT.getLineId())
                                );
                            }
                        }
                        //4.2.3添加请求记录数据到请求记录表中
                        if (resultList.size()>0){
                            //存入请求记录表
                            List<RLslMaterialRequestT> result = requestTService.addMaterialRequest(resultList);
                            //调用安灯告警
                            alarm(result);
                        }
                    }
                }
        } catch (Exception e) {
            exception =e;
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            //记录定时任务日志
            TimePerformLogUtil.addLog(timerConfig.get(0).getCode(),exception,dataTime);
        }
    }

    /**
     * 安灯告警
     * @param result
     * @throws IOException
     */
    public void alarm(List<RLslMaterialRequestT> result) throws IOException {
        if (result.size()>0){
            List<Map<String, String>> maps = new ArrayList<>();
            List<RLslMaterialRequestT> lists = requestTService.findMaterialRequestByBillNoList(result);
            //1.去重
            List<RLslMaterialRequestT> list1 =  lists.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getBillNo()))), ArrayList::new));
            for (RLslMaterialRequestT r1 : list1) {
                //安灯喇叭告警信息
                StringBuilder msg = new StringBuilder();
                Map<String, String> stringStringHashMap = new HashMap<>(16);
                stringStringHashMap.put("billNo",r1.getBillNo());
                stringStringHashMap.put("line",r1.getLineName());
                stringStringHashMap.put("station",r1.getStationName());
                stringStringHashMap.put("trumpet",r1.getTrumpet()+"|");
                stringStringHashMap.put("loopSum",r1.getLoopSum());
                msg.append(r1.getLineName()).append("产线,").append(r1.getStationName()).append("工位,");
                for (RLslMaterialRequestT r2 : lists) {
                    //安灯喇叭告警信息
                    msg.append("缺少").append(r2.getMaterialName()+",").append(r2.getRequiredQuantity()+"个。");
                }
                //安灯喇叭告警信息
                stringStringHashMap.put("message",msg.toString());
                maps.add(stringStringHashMap);
            }

            //给客户端发送喇叭信息
            for (Map<String, String> stringStringMap : maps) {
                udpOpen(stringStringMap);
            }
        }
    }

    /**
     * 安灯告警开启喇叭
     * @throws IOException
     */
    public void udpOpen(Map<String, String> map) throws IOException {
        List<CLslDictionaryT> cLslDictionaryTS = dictionaryTService.selectAll();

        JSONObject json = new JSONObject();
        // 0:开始播放，1:停止播放
        json.put("type", 0);
        // 产线
        json.put("line", map.get("line"));
        // 工位
        json.put("station", map.get("station"));
        // 播放内容
        json.put("message", map.get("message"));
        // 喇叭设备号
        json.put("deviceID", map.get("trumpet"));
        //单据号
        json.put("billNo",map.get("billNo"));
        // 播放次数
        json.put("loopSum", map.get("loopSum"));

        //获取喇叭配置
        cLslDictionaryTS.forEach(cLslDictionaryT -> {
            if (cLslDictionaryT.getKey().equals("loopSum")) {
                if (map.get("loopSum")==null||map.get("loopSum").equals("")){
                    json.put("loopSum", cLslDictionaryT.getValue());
                }
            }
            if (cLslDictionaryT.getKey().equals("ip")) {
                json.put("ip", cLslDictionaryT.getValue());
            }
            if (cLslDictionaryT.getKey().equals("portNumber")) {
                json.put("portNumber", cLslDictionaryT.getValue());
            }
        });

        // 创建数据包
        UDPUtils.sendUDP(json.toString(),json.get("ip").toString(), Integer.valueOf(json.get("portNumber").toString()));
    }


    /**
     * 安灯告警喇叭停止
     * @param billNo
     * @throws IOException
     */
    public void udpStop(String billNo) throws IOException {
        List<CLslDictionaryT> cLslDictionaryTS = dictionaryTService.selectAll();
        // 创建发端Socket对象
        //DatagramSocket ds = new DatagramSocket();
        List<RLslMaterialRequestT> params = new ArrayList<>();
        RLslMaterialRequestT rLslMaterialRequestT = new RLslMaterialRequestT();
        rLslMaterialRequestT.setBillNo(billNo);
        params.add(rLslMaterialRequestT);
        List<RLslMaterialRequestT> lists = requestTService.findMaterialRequestByBillNoList(params);
        for (RLslMaterialRequestT r1 : lists) {
            JSONObject json = new JSONObject();
            // 0:开始播放，1:停止播放
            json.put("type", 1);
            // 产线
            json.put("line", "");
            // 工位
            json.put("station", "");
            // 播放内容
            json.put("message", "");
            // 喇叭设备号
            json.put("deviceID", r1.getTrumpet());
            //单据号
            json.put("billNo",r1.getBillNo());
            // 播放次数
            json.put("loopSum", r1.getLoopSum());
            //获取喇叭配置
            cLslDictionaryTS.forEach(cLslDictionaryT -> {
                if (cLslDictionaryT.getKey().equals("loopSum")) {
                    if (r1.getLoopSum()==null||r1.getLoopSum().equals("")){
                        json.put("loopSum", cLslDictionaryT.getValue());
                    }
                }
                if (cLslDictionaryT.getKey().equals("ip")) {
                    json.put("ip", cLslDictionaryT.getValue());
                }
                if (cLslDictionaryT.getKey().equals("portNumber")) {
                    json.put("portNumber", cLslDictionaryT.getValue());
                }
            });
            // 创建数据包
            UDPUtils.sendUDP(json.toString(),json.get("ip").toString(), Integer.valueOf(json.get("portNumber").toString()));
        }

//        byte[] bys = json.toString().getBytes("GB2312");
//        DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("127.0.0.1"), 4000);
//
//        // 调用DatagramPacket发送数据
//        ds.send(dp);
//        // 关闭发送端
//        ds.close();
    }



}
