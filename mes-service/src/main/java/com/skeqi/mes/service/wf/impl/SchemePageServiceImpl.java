package com.skeqi.mes.service.wf.impl;

import com.skeqi.mes.mapper.wf.SchemePageDao;
import com.skeqi.mes.mapper.yin.MaterialDao;
import com.skeqi.mes.service.wf.SchemePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lenovo
 */
@Service
public class SchemePageServiceImpl implements SchemePageService {
    @Resource
    SchemePageDao schemePageDao;
    @Override
    public List<Map<String, Object>> findOrder() {
        //订单整体情况（订单目标）
        List<Map<String, Object>> orderTarget = schemePageDao.findOrderTarget();
        //订单整体情况（已生产）
        List<Map<String, Object>> alreadyProduce = schemePageDao.findOrderAlreadyProduce();
        //返回值
        List<Map<String,Object>> list = new ArrayList<>();
        if (orderTarget.size()>0){
            //1.遍历订单整体目标
            orderTarget.forEach(objectMap -> {
                //默认值
                HashMap<String, Object> map = new HashMap<>();
                map.put("produce",objectMap.get("name"));
                map.put("produceType",objectMap.get("type"));
                map.put("alreadyProduce",0);
                map.put("waitingProduce",objectMap.get("num"));
                map.put("target",objectMap.get("num"));
                if (alreadyProduce.size()>0){
                    //2.遍历已生产
                    alreadyProduce.forEach(already ->{
                        //3.订单目标和订单已生产 产品型号 相同则减去已生产
                        if (objectMap.get("type").equals(already.get("tyep"))){
                            //已生产
                            map.put("alreadyProduce",already.get("num"));
                            //待生产
                            Integer waitingProduce = (Integer) objectMap.get("num") - ((Integer) already.get("num"));
                            map.put("waitingProduce",waitingProduce);
                        }
                    });
                }
                //返回值赋值
                list.add(map);
            });
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findBuyingSituation() throws ParseException {
        //1.获取数据
        List<Map<String, Object>> buyingSituation = schemePageDao.findBuyingSituation();
        //2.得到日期
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        //3.创建返回值
        List<Map<String, Object>> list = new ArrayList<>();
        //4.遍历查询结果
        for (Map<String, Object> objectMap : buyingSituation) {
            //4-1.得到当前查询结果的日期并转化为年-月-日格式
            String dt = simpleDateFormat1.format(objectMap.get("DT"));
            //标记默认为true
            boolean flag = true;
            //遍历返回结果（如果第一次执行会先执行新增不会执行遍历）
            for (Map<String, Object> objectMap1 : list) {
                //4-2.判断查询结果的物料是否已经存在
                if (objectMap1.get("materialCode").equals(objectMap.get("MATERIAL_NO"))) {
                    //4-3.存在就不需要执行新增（修改对应日期的数值）设置标记为假
                    flag = false;
                    //4-4.遍历当前物料的日期
                    for (Map<String, Object> purchaseTime : (List<Map<String, Object>>) objectMap1.get("purchase")) {
                        if (purchaseTime.get("time").equals(dt)) {
                            //对应日期的值修改
                            //将数据库数据格式转换为Integer
                            Integer num = Integer.parseInt(((BigDecimal) objectMap.get("num")).toString());
                            //做运算叠加
                            Integer purchaseQuantity = num + (Integer)purchaseTime.get("purchaseQuantity");
                            purchaseTime.put("purchaseQuantity", purchaseQuantity);
                        }
                    }
                }
            }
            //返回结果新增物料操作
            if (flag){
                //1.创建返回结果对象
                Map<String, Object> map = new HashMap<>();
                map.put("materialName", objectMap.get("MATERIAL_NAME"));
                map.put("materialCode", objectMap.get("MATERIAL_NO"));
                //2.同时创建物料的日期集合
                List<Map<String, Object>> purchase = new ArrayList<>();
                for (int i = 0; i < 14; i++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + i);
                    Date today = calendar.getTime();
                    //3.生产的日期
                    String result = simpleDateFormat1.format(today);
                    //4.创建日期集合下的对象
                    Map<String, Object> map1 = new HashMap<>();
                    //5.存下当前生成的日期
                    map1.put("time", result);
                    //6.采购数量默认值为0
                    map1.put("purchaseQuantity", 0);
                    //7.判断查询出的数据中日期是否和生成的日期一致
                    if (dt.equals(result)) {
                        //8.修改采购数量
                        map1.put("purchaseQuantity", objectMap.get("num"));
                    }
                    //9.存下日期对象
                    purchase.add(map1);
                }
                //10.存入map
                map.put("purchase", purchase);
                //11.返回结果赋值
                list.add(map);
            }
        }

        return list;

    }

    @Override
    public List<Map<String, Object>> findProduction() {

        List<Map<String, Object>> production = schemePageDao.findProduction();
        //2.得到日期
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        //3.创建返回值
        List<Map<String, Object>> list = new ArrayList<>();
        //4.遍历查询结果
        for (Map<String, Object> objectMap : production) {
            //4-1.得到当前查询结果的日期并转化为年-月-日格式
            String dt = simpleDateFormat1.format(objectMap.get("PLAN_START_TIME"));
            //标记默认为true
            boolean flag = true;
            //遍历返回结果（如果第一次执行会先执行新增不会执行遍历）
            for (Map<String, Object> objectMap1 : list) {
                //4-2.判断查询结果的产品是否已经存在
                if (objectMap1.get("productionId").equals(objectMap.get("PRODUCT_ID"))) {
                    //4-3.存在就不需要执行新增（修改对应日期的数值）设置标记为假
                    flag = false;
                    //4-4.遍历当前产品的日期
                    for (Map<String, Object> produce : (List<Map<String, Object>>) objectMap1.get("produce")) {
                        if (produce.get("time").equals(dt)) {
                            //对应日期的值修改
                            //将数据库数据格式转换为Integer
                            Integer num = Integer.parseInt(((BigDecimal) objectMap.get("num")).toString());
                            //做运算叠加
                            Integer productionOutput = num + (Integer)produce.get("productionOutput");
                            produce.put("productionOutput", productionOutput);
                        }
                    }
                }
            }
            //返回结果新增产品操作
            if (flag){
                //1.创建返回结果对象
                Map<String, Object> map = new HashMap<>();
                map.put("productionName", objectMap.get("productName"));
                map.put("productionId", objectMap.get("PRODUCT_ID"));
                //2.同时创建产品的日期集合
                List<Map<String, Object>> produce = new ArrayList<>();
                for (int i = 0; i < 14; i++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + i);
                    Date today = calendar.getTime();
                    //3.生产的日期
                    String result = simpleDateFormat1.format(today);
                    //4.创建日期集合下的对象
                    Map<String, Object> map1 = new HashMap<>();
                    //5.存下当前生成的日期
                    map1.put("time", result);
                    //6.产品数量默认值为0
                    map1.put("productionOutput", 0);
                    //7.判断查询出的数据中日期是否和生成的日期一致
                    if (dt.equals(result)) {
                        //8.修改产品数量
                        map1.put("productionOutput", objectMap.get("num"));
                    }
                    //9.存下日期对象
                    produce.add(map1);
                }
                //10.存入map
                map.put("produce", produce);
                //11.返回结果赋值
                list.add(map);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findEvent() {
        Map<String,Object> map = new HashMap<>();
        List<Map<String, Object>> list = schemePageDao.findEvent();
        return list;
    }

    /**
     * 在线订单
     * @return
     */
    @Override
    public List<Map<String, Object>> findOnlineOrder() {
        return schemePageDao.findOnlineOrder();
    }
    /**
     * 在线工单
     * @return
     */
    @Override
    public List<Map<String, Object>> findOnlineWorkOrder() {
        return schemePageDao.findOnlineWorkOrder();
    }

    /**
     * 在线产线
     * @return
     */
    @Override
    public List<Map<String, Object>> findOnlineLine() {
        return schemePageDao.findOnlineLine();
    }
    /**
     * 产线优率
     * @return
     */
    @Override
    public List<Map<String, Object>> findLineOptimalRate() {
        return schemePageDao.findLineOptimalRate();
    }
}
