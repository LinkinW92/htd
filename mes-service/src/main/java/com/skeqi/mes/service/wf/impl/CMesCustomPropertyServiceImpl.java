package com.skeqi.mes.service.wf.impl;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.mapper.all.CMesLineTDAO;
import com.skeqi.mes.mapper.all.CMesMaterialTDAO;
import com.skeqi.mes.mapper.wf.CMesAreaTMapper;
import com.skeqi.mes.mapper.wf.CMesFactoryTMapper;
import com.skeqi.mes.mapper.wf.CMesPlantTMapper;
import com.skeqi.mes.mapper.zch.OrderDao;
import com.skeqi.mes.mapper.zch.PurchaseOrderDao;
import com.skeqi.mes.mapper.zch.WorkorderDao;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.qh.*;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.wf.CustomAttributesConstant;
import org.apache.poi.hpsf.CustomProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CMesCustomPropertyServiceImpl implements CMesCustomPropertyService{

    @Autowired
    private CustomPropertyDao cMesCustomPropertyMapper;

    @Autowired
    private CMesMaterialTDAO cMesMaterialTDAO;

    @Autowired
    OrderDao orderDao;

    @Autowired
    WorkorderDao workorderDao;

    @Resource
    CMesFactoryTMapper factoryTMapper;

    @Resource
    CMesAreaTMapper areaTMapper;
    @Resource
    CMesPlantTMapper plantTMapper;

    @Autowired
    private CMesLineTDAO lineTDAO;

    @Autowired
    PurchaseOrderDao purchaseOrderDao;



    @Override
    public List<CMesCustomProperty> findCustomProperty(CMesCustomProperty customProperty) {
        return cMesCustomPropertyMapper.findCustomProperty(customProperty);
    }

    @Override
    public Rjson addCustomProperty(CMesCustomProperty customProperty) throws Exception {
        Integer nameCount = cMesCustomPropertyMapper.selectByName(customProperty);
        if (nameCount>0){
            return Rjson.error("???????????????");
        }

//        Integer englishNameCount = cMesCustomPropertyMapper.selectByEnglishName(customProperty);
//        if (englishNameCount>0){
//            return Rjson.error("?????????????????????");
//        }



        Integer addCustomProperty = cMesCustomPropertyMapper.addCustomProperty(customProperty);
        if (addCustomProperty<1){
            throw new Exception("???????????????????????????");
        }

        List<String> ids = new ArrayList<>();
        //????????????
        //????????????????????????????????????
        String prompt = "";
            switch (customProperty.getObjectType()){
                case CustomAttributesConstant.materialManager:
                    prompt = CustomAttributesConstant.materialManagerPrompt;
                    if ("0".equals(customProperty.getBindScopeKey())){
                        //1.???????????????????????????????????????
                        List<CMesJlMaterialT> materialCodeList = cMesMaterialTDAO.findMaterialCodeList();
                        materialCodeList.forEach(cMesJlMaterialT -> {
                            ids.add(cMesJlMaterialT.getBomId());
                        });
                    }else {
                        //1.???????????????????????????????????????
                        ids.add(customProperty.getBindScopeValue());
                    }
                    break;
                case CustomAttributesConstant.orderManagement:
                    prompt = CustomAttributesConstant.orderManagementPrompt;
                    if ("0".equals(customProperty.getBindScopeKey())){
                        //1.???????????????????????????????????????
                        List<Map<String, Object>> orderList = orderDao.findListAll();
                        orderList.forEach(objectMap -> {
                            ids.add((String) objectMap.get("CODE"));
                        });
                    }else {
                        //1.???????????????????????????????????????
                        ids.add(customProperty.getBindScopeValue());
                    }
                    break;
                case CustomAttributesConstant.workOrderManagement:
                    prompt = CustomAttributesConstant.workOrderManagementPrompt;
                    if ("0".equals(customProperty.getBindScopeKey())){
                        //1.???????????????????????????????????????
                        List<Map<String, Object>> workOrderList = workorderDao.findListAll();
                        workOrderList.forEach(objectMap -> {
                            ids.add((String) objectMap.get("WORKORDER_ID"));
                        });
                    }else {
                        //1.???????????????????????????????????????
                        ids.add(customProperty.getBindScopeValue());
                    }
                    break;
                case CustomAttributesConstant.factory:
                    prompt = CustomAttributesConstant.factoryPrompt;
                    if ("0".equals(customProperty.getBindScopeKey())){
                        //1.???????????????????????????????????????
                        List<CMesFactoryT> factoryTList = factoryTMapper.findFactoryAll(null,null,null);
                        factoryTList.forEach(objectMap -> {
                            ids.add(objectMap.getFactoryCode());
                        });
                    }else {
                        //1.???????????????????????????????????????
                        ids.add(customProperty.getBindScopeValue());
                    }
                    break;
                case CustomAttributesConstant.area:
                    prompt = CustomAttributesConstant.areaPrompt;
                    if ("0".equals(customProperty.getBindScopeKey())){
                        //1.???????????????????????????????????????
                        List<CMesAreaT> areaTList = areaTMapper.findAreaAll(new CMesAreaT());
                        areaTList.forEach(objectMap -> {
                            ids.add(objectMap.getAreaCode());
                        });
                    }else {
                        //1.???????????????????????????????????????
                        ids.add(customProperty.getBindScopeValue());
                    }
                    break;
                case CustomAttributesConstant.plant:
                    prompt = CustomAttributesConstant.plantPrompt;
                    if ("0".equals(customProperty.getBindScopeKey())){
                        //1.???????????????????????????????????????
                        List<CMesPlantT> plantAll = plantTMapper.findPlantAll(new CMesPlantT());
                        plantAll.forEach(objectMap -> {
                            ids.add(objectMap.getPlantCode());
                        });
                    }else {
                        //1.???????????????????????????????????????
                        ids.add(customProperty.getBindScopeValue());
                    }
                    break;
                case CustomAttributesConstant.lineManager:
                    prompt = CustomAttributesConstant.lineManagerPrompt;
                    if ("0".equals(customProperty.getBindScopeKey())){
                        //1.???????????????????????????????????????
                        List<CMesLineT> lineTList = lineTDAO.findAllLine(null);
                        lineTList.forEach(objectMap -> {
                            ids.add(objectMap.getCode());
                        });
                    }else {
                        //1.???????????????????????????????????????
                        ids.add(customProperty.getBindScopeValue());
                    }
                    break;
                case CustomAttributesConstant.purchaseManagement:
                    prompt = CustomAttributesConstant.purchaseManagementPrompt;
                    if ("0".equals(customProperty.getBindScopeKey())){
                        //1.??????????????????????????????????????????
                        List<Map<String, Object>> purchaseList = purchaseOrderDao.findPurchaseList(new HashMap<>());
                        purchaseList.forEach(objectMap -> {
                            ids.add((String) objectMap.get("PURCHASE_NO"));
                        });
                    }else {
                        //1.???????????????????????????????????????
                        ids.add(customProperty.getBindScopeValue());
                    }
                    break;
                default:
                    break;
            }


        Map<String, Object> stringObjectHashMap = new HashMap<>(16);

        stringObjectHashMap.put("value",customProperty.getDefaults());
        stringObjectHashMap.put("propertyId",customProperty.getId());
        stringObjectHashMap.put("objectType",customProperty.getObjectType());
        stringObjectHashMap.put("bindScopeKey",customProperty.getBindScopeKey());
        if (ids.size()<1) {
            throw new Exception("????????????"+prompt);
        }
        stringObjectHashMap.put("objectCode",ids);
        Integer integer = cMesCustomPropertyMapper.addCustomPropertyValueByObjectCode(stringObjectHashMap);
        if (integer<1){
            throw new Exception("???????????????????????????");
        }
        return Rjson.success();
    }
    @Override
    public Rjson editCustomProperty(CMesCustomProperty customProperty) throws Exception{
        List<Integer> integers = cMesCustomPropertyMapper.selectCustomPropertyAllId(customProperty);
        for (Integer item: integers) {
            if (!item.equals(customProperty.getId())){
                return Rjson.error("????????????????????????????????????");
            }
        }

        //?????????????????? ??????????????????
        CMesCustomProperty cMesCP = cMesCustomPropertyMapper.selectById(customProperty);
        if (!StringUtils.isEmpty(cMesCP)) {
            //????????????????????????????????????????????????????????????
            boolean bindScopeKey = cMesCP.getBindScopeKey().equals(customProperty.getBindScopeKey());
            //???????????????????????????????????????
            if (!bindScopeKey){
                //?????????????????????????????????
                CMesCustomPropertyValue cp = new CMesCustomPropertyValue();
                if (!"0".equals(customProperty.getBindScopeKey())){
                //?????????????????????????????????????????????????????????
                    //1.?????????????????????????????????????????????
                    cp.setPropertyId(customProperty.getId());
                    cp.setObjectType(customProperty.getObjectType());
                    cp.setObjectCode(customProperty.getBindScopeValue());
                    cp.setBindScopeKey(cMesCP.getBindScopeKey());
                    Integer delCustomPropertyValueAll = cMesCustomPropertyMapper.delCustomPropertyValueAll(cp);
                    if (delCustomPropertyValueAll<1){
                        throw new Exception("???????????????????????????");
                    }
                }else {
                //????????????????????????????????????????????????????????????
                    //1.???????????????????????????????????????
                    List<String> ids = new ArrayList<>();

                    switch (customProperty.getObjectType()){
                        case CustomAttributesConstant.materialManager:
                            List<CMesJlMaterialT> materialCodeList = cMesMaterialTDAO.findMaterialCodeList();
                            materialCodeList.forEach(cMesJlMaterialT -> {
                                //???????????????????????????
                                if (!cMesJlMaterialT.getBomId().equals(cMesCP.getBindScopeValue())){
                                    ids.add(cMesJlMaterialT.getBomId());
                                }
                            });
                            break;
                        case CustomAttributesConstant.orderManagement:
                            List<Map<String, Object>> orderList = orderDao.findListAll();
                            orderList.forEach(objectMap -> {
                                //???????????????????????????
                                if (!objectMap.get("CODE").equals(cMesCP.getBindScopeValue())){
                                    ids.add((String) objectMap.get("CODE"));
                                }
                            });
                            break;
                        case CustomAttributesConstant.workOrderManagement:
                            List<Map<String, Object>> workOrderList = workorderDao.findListAll();
                            workOrderList.forEach(objectMap -> {
                                //???????????????????????????
                                if (!objectMap.get("WORKORDER_ID").equals(cMesCP.getBindScopeValue())){
                                    ids.add((String) objectMap.get("WORKORDER_ID"));
                                }
                            });
                            break;
                        case CustomAttributesConstant.factory:
                            List<CMesFactoryT> factoryTList = factoryTMapper.findFactoryAll(null,null,null);
                            factoryTList.forEach(objectMap -> {
                                //???????????????????????????
                                if (!objectMap.getFactoryCode().equals(cMesCP.getBindScopeValue())){
                                    ids.add( objectMap.getFactoryCode());
                                }
                            });
                            break;
                        case CustomAttributesConstant.area:
                                List<CMesAreaT> areaTList = areaTMapper.findAreaAll(new CMesAreaT());
                                areaTList.forEach(objectMap -> {
                                    //???????????????????????????
                                    if (!objectMap.getAreaCode().equals(cMesCP.getBindScopeValue())) {
                                        ids.add(objectMap.getAreaCode());
                                    }
                                });
                            break;
                        case CustomAttributesConstant.plant:
                            List<CMesPlantT> plantAll = plantTMapper.findPlantAll(new CMesPlantT());
                            plantAll.forEach(objectMap -> {
                                //???????????????????????????
                                if (!objectMap.getPlantCode().equals(cMesCP.getBindScopeValue())) {
                                    ids.add(objectMap.getPlantCode());
                                }
                            });
                            break;

                        case CustomAttributesConstant.lineManager:
                                List<CMesLineT> lineTList = lineTDAO.findAllLine(null);
                                lineTList.forEach(objectMap -> {
                                    //???????????????????????????
                                    if (!objectMap.getCode().equals(cMesCP.getBindScopeValue())) {
                                        ids.add(objectMap.getCode());
                                    }
                                });
                            break;
                        case CustomAttributesConstant.purchaseManagement:
                                List<Map<String, Object>> purchaseList = purchaseOrderDao.findPurchaseList(new HashMap<>());
                                    purchaseList.forEach(objectMap -> {
                                        //???????????????????????????
                                        if (!objectMap.get("PURCHASE_NO").equals(cMesCP.getBindScopeValue())) {
                                            ids.add((String) objectMap.get("PURCHASE_NO"));
                                        }
                                    });
                            break;
                        default:
                            break;
                    }

                    Map<String, Object> stringObjectHashMap = new HashMap<>(16);
                    stringObjectHashMap.put("value",customProperty.getDefaults());
                    stringObjectHashMap.put("propertyId",customProperty.getId());
                    stringObjectHashMap.put("objectType",customProperty.getObjectType());
                    stringObjectHashMap.put("bindScopeKey",customProperty.getBindScopeKey());
                    stringObjectHashMap.put("objectCode",ids);
                    Integer integer = cMesCustomPropertyMapper.addCustomPropertyValueByObjectCode(stringObjectHashMap);
                    if (integer<1){
                        throw new Exception("???????????????????????????");
                    }
                }
                //2.?????????????????????????????????"????????????"
                cp.setBindScopeKey(customProperty.getBindScopeKey());
                Integer byBindScopeKey = cMesCustomPropertyMapper.editCustomPropertyValueByBindScopeKey(cp);
                if (byBindScopeKey<1){
                    throw new Exception("???????????????????????????");
                }

            }else {
                //???????????????
                if (!"0".equals(customProperty.getBindScopeKey())){
                    Map<String, Object> objectMap = new HashMap<>(16);
                    objectMap.put("propertyId",customProperty.getId());
                    objectMap.put("objectType",customProperty.getObjectType());
                    objectMap.put("objectCode",customProperty.getBindScopeValue());
                    Integer editCustomPropertyValue = cMesCustomPropertyMapper.editCustomPropertyValueByObjectCode(objectMap);
                    if (editCustomPropertyValue<1){
                        throw new Exception("?????????????????????????????????");
                    }
                }
            }
        }

        Integer editCustomProperty = cMesCustomPropertyMapper.editCustomProperty(customProperty);
        if (editCustomProperty<1){
            throw new Exception("???????????????????????????");
        }



        return Rjson.success();
    }

    @Override
    public Rjson delCustomProperty(Integer id) throws Exception {
        Map<String,Object> map = new HashMap<>();
        List<Integer> idList = new ArrayList<>();
        idList.add(id);
        map.put("idMap",idList);
        Integer del = cMesCustomPropertyMapper.delCustomProperty(map);
        if (del<1){
            throw new Exception("???????????????????????????");
        }
        Integer delCustomPropertyValue = cMesCustomPropertyMapper.delCustomPropertyValue(map);
        if (delCustomPropertyValue<1){
            throw new Exception("?????????????????????????????????");
        }
        return Rjson.success();
    }

    @Override
    public List<CMesCustomProperty> findCustomPropertyAll(CMesCustomProperty cMesCustomProperty) {
        return cMesCustomPropertyMapper.findCustomPropertyAll(cMesCustomProperty);
    }

    @Override
    public Integer delCustomPropertyByBindScopeValueAndObjectType(CMesCustomProperty customProperty) throws Exception {
        Map<String,Object> map1 = new HashMap<>();
        List<Integer> idList = new ArrayList<>();
        //????????????????????????????????????????????????????????????ID
        List<Integer> customPropertyAllId = cMesCustomPropertyMapper.selectCustomPropertyAllId(customProperty);
        if (customPropertyAllId.size()>0){
            //??????
            idList.addAll(customPropertyAllId);
            map1.put("idMap",idList);
            Integer del = cMesCustomPropertyMapper.delCustomProperty(map1);
            if (del<1){
                throw new Exception("???????????????????????????");
            }
        }
        //??????????????????????????????????????????????????????????????????ID
        List<Integer> customPropertyValueAll = cMesCustomPropertyMapper.selectCustomPropertyValueAll(customProperty);
        if (customPropertyValueAll.size()>0) {
            //??????
            idList.addAll(customPropertyValueAll);
            map1.put("idMap",idList);
            map1.put("bindScopeValue",customProperty.getBindScopeValue());
            map1.put("objectType",customProperty.getObjectType());
            Integer delCustomPropertyValue = cMesCustomPropertyMapper.delCustomPropertyValue(map1);
            if (delCustomPropertyValue < 1) {
                throw new Exception("?????????????????????????????????");
            }
        }
        return 1;
    }

}
