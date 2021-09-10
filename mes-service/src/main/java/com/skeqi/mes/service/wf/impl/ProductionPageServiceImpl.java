package com.skeqi.mes.service.wf.impl;

import com.skeqi.mes.mapper.wf.ProductionPageDao;
import com.skeqi.mes.service.wf.ProductionPageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ProductionPageServiceImpl implements ProductionPageService {
    @Resource
    ProductionPageDao productionPageDao;
    @Override
    public List<Map<String, Object>> findProduction() {
        List<Map<String, Object>> production = productionPageDao.findProduction();
        for (Map<String, Object> objectMap : production) {
            //当日总产量
            Long num = Long.valueOf(String.valueOf(objectMap.get("num")));
            //合格数量
            Long percentage = Long.valueOf(String.valueOf(objectMap.get("percentage")));
            Double passRate = 0.0;
            if (num!=0){
                passRate = Double.valueOf ((num / percentage)* 100) ;
            }
            objectMap.put("passRate", passRate.toString() + '%');
        }

        return production;
    }

    @Override
    public List<Map<String, Object>> findOnlineWorkOrder() {
        return productionPageDao.findOnlineWorkOrder();
    }

    @Override
    public List<Map<String, Object>> findEvent() {
        return productionPageDao.findEvent();
    }

    @Override
    public List<Map<String, Object>> findProductionYield() {
        List<Map<String, Object>> productList = new ArrayList<>();

        List<Map<String, Object>> onlineWorkOrder = productionPageDao.findOnlineWorkOrder();
        List<String> productId = new ArrayList<>();
        onlineWorkOrder.forEach(objectMap->{
            productId.add(objectMap.get("PRODUCT_ID").toString());
        });
        List<String> collect = productId.stream().distinct().collect(Collectors.toList());
        for (String s : collect) {
            Integer okNumber = 0;
            Integer deffectNumber = 0;
            Integer offLineNumber = 0;
            String productionName = "";
            for (Map<String, Object> objectMap : onlineWorkOrder) {
                if (objectMap.get("PRODUCT_ID").toString().equals(s)) {
                    productionName = objectMap.get("PRODUCTION_NAME").toString();
                    //合格数量
                    okNumber += (Integer) objectMap.get("OK_NUMBER");
                    //缺陷数量
                    deffectNumber +=(Integer) objectMap.get("DEFFECT_NUMBER");
                    //下线数量
                    offLineNumber += (Integer) objectMap.get("OFFLINE_NUMBER");
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("PRODUCTION_NAME",productionName);
            map.put("okNumber",okNumber);
            map.put("deffectNumber",deffectNumber);
            map.put("offLineNumber",offLineNumber);
            productList.add(map);
        }

        return productList;
    }

    @Override
    public List<Map<String, Object>> findOnlineShiftTeam() {
        return productionPageDao.findOnlineShiftTeam();
    }

    @Override
    public List<Map<String, Object>> finishedProduct() {
        return productionPageDao.finishedProduct();
    }
}
