package com.skeqi.mes.service.wf.impl;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.mapper.wf.CMesPlantTMapper;
import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import com.skeqi.mes.pojo.qh.*;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.wf.CustomAttributesConstant;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.xml.rpc.handler.HandlerChain;

import com.skeqi.mes.mapper.wf.CMesAreaTMapper;
import com.skeqi.mes.service.wf.CMesAreaTService;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CMesAreaTServiceImpl implements CMesAreaTService{

    @Resource
    private CMesAreaTMapper cMesAreaTMapper;

    @Resource
    private CMesPlantTMapper cMesPlantTMapper;

    @Autowired
    private CustomPropertyDao cMesCustomPropertyMapper;

    @Autowired
    CMesCustomPropertyService customPropertyService;

    @Override
    public List<CMesAreaT> findAreaAll(CMesAreaT cMesAreaT) {
        return cMesAreaTMapper.findAreaAll(cMesAreaT);
    }

    @Override
    public Rjson addArea(CMesAreaT areaT) throws Exception {
        //查询编号是否存在
        List<CMesAreaT> areaTList = cMesAreaTMapper.selectAreaByCode(areaT.getAreaCode());
        if (areaTList.size()>0){
            throw new Exception("区域编号已存在");
        }

        //操作自定义属性值（内容）
        handleCustomValue(areaT);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        areaT.setDateTime(date);
        areaT.setAlterDt(date);
        Integer area = cMesAreaTMapper.addArea(areaT);
        if (area<1){
            throw new Exception("添加区域失败");
        }
        return Rjson.success();
    }

    /**
     * 操作自定义属性值（内容）
     * @param areaT
     * @throws Exception
     */
    private void handleCustomValue(CMesAreaT areaT) throws Exception {
        List<Map<String, Object>> editList = new ArrayList<>();
        List<Map<String, Object>> addList = new ArrayList<>();
        List<Map<String, Object>> list = (List<Map<String, Object>>) areaT.getCustom();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> object : list) {
                if (!StringUtils.isEmpty(object)) {
                    System.out.println(object);
                    object.put("objectCode", areaT.getAreaCode());
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
                                hashMap.put("bindScopeValue",areaT.getAreaCode());
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
    public Rjson editArea(CMesAreaT areaT) throws Exception {
        //按id查询区域信息
        List<CMesAreaT> areaTList = cMesAreaTMapper.selectAreaById(areaT.getId());

        //操作自定义属性值（内容）
        handleCustomValue(areaT);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        areaT.setAlterDt(date);
        Integer editFactory = cMesAreaTMapper.editArea(areaT);
        if(editFactory<1){
            throw new Exception("修改区域失败");
        }

        //车间同时需要修改绑定工厂信息
        Map<String, Object> map = new HashMap<>();
        map.put("beforeCompanyCode",areaTList.get(0).getCompanyCode());
        map.put("afterCompanyCode",areaT.getCompanyCode());
        map.put("beforeFactoryCode",areaTList.get(0).getFactoryCode());
        map.put("afterFactoryCode",areaT.getFactoryCode());
        List<String> areaCodeList = new ArrayList<>();
        areaCodeList.add(areaT.getAreaCode());
        map.put("areaCodeList",areaCodeList);
        if (!map.get("beforeCompanyCode").equals(map.get("afterCompanyCode"))&&!map.get("beforeFactoryCode").equals(map.get("afterFactoryCode"))){
            Integer editPlantByAreaCode= cMesPlantTMapper.editPlantByAreaCode(map);
            if (editPlantByAreaCode<1){
                throw new Exception("修改区域失败");
            }
        }
        return Rjson.success();
    }


    @Override
    public Rjson delAreaByIdAndCode(CMesAreaT areaT) throws Exception {
        Integer areaDel = cMesAreaTMapper.delAreaByIdAndCode(areaT);
        if (areaDel<1){
            throw new Exception("删除区域失败");
        }

        CMesCustomProperty customProperty = new CMesCustomProperty();
        customProperty.setBindScopeValue(areaT.getAreaCode());
        //删除类型
        customProperty.setObjectType(CustomAttributesConstant.area);
        Integer integer = customPropertyService.delCustomPropertyByBindScopeValueAndObjectType(customProperty);
        if (integer<1){
            throw new Exception("删除自定义属性失败");
        }

        //先查一遍是否有车间
        List<CMesPlantT> cMesPlantTList = cMesPlantTMapper.selectPlantByAreaCode(areaT.getAreaCode());
        if (cMesPlantTList.size()>0){
            //同时把车间的对应数据删除
            CMesPlantT cMesPlantT = new CMesPlantT();
            cMesPlantT.setAreaCode(areaT.getAreaCode());
            Integer plantDel = cMesPlantTMapper.delPlantByIdAndCode(cMesPlantT);
            if (plantDel<1){
                throw new Exception("删除区域失败");
            }
        }

        return Rjson.success();
    }
}
