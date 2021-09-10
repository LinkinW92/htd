package com.skeqi.mes.service.wf.impl;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import com.skeqi.mes.pojo.qh.CMesAreaT;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.pojo.qh.CMesFactoryT;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.wf.CustomAttributesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.qh.CMesPlantT;
import com.skeqi.mes.mapper.wf.CMesPlantTMapper;
import com.skeqi.mes.service.wf.CMesPlantTService;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CMesPlantTServiceImpl implements CMesPlantTService{

    @Resource
    private CMesPlantTMapper cMesPlantTMapper;

    @Autowired
    private CustomPropertyDao cMesCustomPropertyMapper;

    @Autowired
    CMesCustomPropertyService customPropertyService;

    @Override
    public List<CMesPlantT> findPlantAll(CMesPlantT cMesPlantT) {
        return cMesPlantTMapper.findPlantAll(cMesPlantT);
    }

    /**
     * 操作自定义属性值（内容）
     * @param plantT
     * @throws Exception
     */
    private void handleCustomValue(CMesPlantT plantT) throws Exception {
        List<Map<String, Object>> editList = new ArrayList<>();
        List<Map<String, Object>> addList = new ArrayList<>();
        List<Map<String, Object>> list = (List<Map<String, Object>>) plantT.getCustom();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> object : list) {
                if (!StringUtils.isEmpty(object)) {
                    System.out.println(object);
                    object.put("objectCode", plantT.getPlantCode());
                    if (object.get("bindScopeKey").equals("0")) {
                        object.put("propertyId", object.get("id"));
                        if (object.get("value") == null || "".equals(object.get("value"))) {
                            object.put("value", object.get("defaults"));
                        }
                    }
                }
                CMesCustomProperty cMesCustomProperty = new CMesCustomProperty();
                cMesCustomProperty.setObjectType(object.get("objectType").toString());
                cMesCustomProperty.setBindScopeValue(object.get("objectCode").toString());
                List<Integer> customPropertyValueAll = cMesCustomPropertyMapper.selectCustomPropertyValueAll(cMesCustomProperty);
                if (customPropertyValueAll.size()>0){
                    //编辑
                    editList.add(object);
                }else {
                    //新增
                    addList.add(object);
                }
            }

            //新增操作
            if (addList.size()>0){
                Map<String, Object> map1 = new HashMap<>();
                map1.put("list", addList);
                Integer editCustomPropertyValue = cMesCustomPropertyMapper.addCustomPropertyValue(map1);
                if (editCustomPropertyValue < 1) {
                    throw new Exception("添加自定义属性内容失败");
                }
            }

            //编辑操作
            if (editList.size()>0){
                for (Object object : editList) {
                    if (!StringUtils.isEmpty(object)){
                        Map<String, Object> hashMap = (Map<String, Object>) object;
                        if (hashMap.get("bindScopeKey").equals("0")) {
                            if (StringUtils.isEmpty(hashMap.get("bindScopeValue"))) {
                                hashMap.put("bindScopeValue",plantT.getPlantCode());
                            }
                        }
                        Integer integer = cMesCustomPropertyMapper.editCustomPropertyValue(hashMap);
                        if (integer < 1) {
                            throw new Exception("编辑自定义属性内容失败");
                        }
                    }
                }
            }
        }
    }

    @Override
    public Rjson addPlant(CMesPlantT plantT) throws Exception {
        //查询编号是否存在
        List<CMesPlantT> plantTList = cMesPlantTMapper.selectPlantByCode(plantT.getPlantCode());
        if (plantTList.size()>0){
            throw new Exception("车间编号已存在");
        }

        //新增自定义属性值（内容）
        handleCustomValue(plantT);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        plantT.setDateTime(date);
        plantT.setAlterDt(date);
        Integer area = cMesPlantTMapper.addPlant(plantT);
        if (area<1){
            throw new Exception("添加车间失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson editPlant(CMesPlantT plantT) throws Exception {
        //修改自定义属性值（内容）
        handleCustomValue(plantT);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        plantT.setAlterDt(date);
        Integer editFactory = cMesPlantTMapper.editPlant(plantT);
        if(editFactory<1){
            throw new Exception("修改车间失败");
        }

        return Rjson.success();
    }

    @Override
    public Rjson delPlantByIdAndCode(CMesPlantT plantT) throws Exception {
        CMesCustomProperty customProperty = new CMesCustomProperty();
        customProperty.setBindScopeValue(plantT.getPlantCode());
        //删除类型
        customProperty.setObjectType(CustomAttributesConstant.plant);
        Integer integer = customPropertyService.delCustomPropertyByBindScopeValueAndObjectType(customProperty);
        if (integer<1){
            throw new Exception("删除自定义属性失败");
        }

        Integer byIdAndCode = cMesPlantTMapper.delPlantByIdAndCode(plantT);
        if (byIdAndCode<1){
            throw new Exception("删除车间失败");
        }
        return Rjson.success();
    }
}
