package com.skeqi.mes.service.wf.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.wf.CMesAreaTMapper;
import com.skeqi.mes.mapper.wf.CMesPlantTMapper;
import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import com.skeqi.mes.pojo.chenj.srm.req.CMesFactoryTReq;
import com.skeqi.mes.pojo.chenj.srm.req.CWmsDepartmentTReq;
import com.skeqi.mes.pojo.qh.CMesAreaT;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.pojo.qh.CMesPlantT;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.wf.CustomAttributesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.CMesFactoryTMapper;
import com.skeqi.mes.pojo.qh.CMesFactoryT;
import com.skeqi.mes.service.wf.CMesFactoryTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class CMesFactoryTServiceImpl implements CMesFactoryTService{

    @Resource
    private CMesFactoryTMapper cMesFactoryTMapper;

    @Resource
    private CMesAreaTMapper cMesAreaTMapper;

    @Resource
    private CMesPlantTMapper cMesPlantTMapper;

    @Autowired
    private CustomPropertyDao cMesCustomPropertyMapper;

    @Autowired
    CMesCustomPropertyService customPropertyService;

    @Override
    public List<CMesFactoryT> findFactoryAll(String companyName,String factoryCode,String factoryName) {
        return cMesFactoryTMapper.findFactoryAll(companyName,factoryCode,factoryName);
    }
    /**
     * 操作自定义属性值（内容）
     * @param factoryT
     * @throws Exception
     */
    private void handleCustomValue(CMesFactoryT factoryT) throws Exception {
        List<Map<String, Object>> editList = new ArrayList<>();
        List<Map<String, Object>> addList = new ArrayList<>();
        List<Map<String, Object>> list = (List<Map<String, Object>>) factoryT.getCustom();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> object : list) {
                if (!StringUtils.isEmpty(object)) {
                    System.out.println(object);
                    object.put("objectCode", factoryT.getFactoryCode());
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
                                hashMap.put("bindScopeValue",factoryT.getFactoryCode());
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
    public Rjson addFactory(CMesFactoryT factoryT) throws Exception {
        //查询编号是否存在
        List<CMesFactoryT> factoryTList = cMesFactoryTMapper.selectFactoryByCode(factoryT.getFactoryCode());
        if (factoryTList.size()>0){
            throw new Exception("工厂编号已存在");
        }

        //新增自定义属性值（内容）
        handleCustomValue(factoryT);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        factoryT.setDateTime(date);
        factoryT.setAlterDt(date);
       Integer factory = cMesFactoryTMapper.addFactory(factoryT);
       if (factory<1){
           throw new Exception("添加工厂失败");
       }
        return Rjson.success();
    }

    @Override
    public Rjson editFactory(CMesFactoryT factoryT) throws Exception {
        //修改自定义属性值（内容）
        handleCustomValue(factoryT);

        //按id查询修改之前的工厂
        List<CMesFactoryT> factoryTList = cMesFactoryTMapper.selectFactoryById(factoryT.getId());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        factoryT.setAlterDt(date);
        Integer editFactory = cMesFactoryTMapper.editFactory(factoryT);
        if(editFactory<1){
            throw new Exception("修改工厂失败");
        }

        //修改区域
        //修改所有属于这个工厂的区域的公司

        //1.判断是否有修改公司内容
        Map<String, Object> map = new HashMap<>(16);
        map.put("beforeCompanyCode",factoryTList.get(0).getCompanyCode());
        map.put("afterCompanyCode",factoryT.getCompanyCode());
        if (!map.get("beforeCompanyCode").equals(map.get("afterCompanyCode"))){
            // 2.得到修改之前的工厂信息找到区域
            List<CMesAreaT> areaTList = cMesAreaTMapper.selectAreaByFactoryCode(factoryTList.get(0).getFactoryCode());
            if (areaTList.size()>0){
                List<String> areaCodeList = new ArrayList<>();
                List<Integer> areaIdList = new ArrayList<>();
                //3.遍历得到所有区域id
                areaTList.forEach(areaT -> {
                    areaIdList.add(areaT.getId());
                    areaCodeList.add(areaT.getAreaCode());
                });
                //4.得到修改之后的公司和区域id一起装入map
                map.put("areaIdList",areaIdList);
                //5.执行区域修改公司
                Integer editAreaById = cMesAreaTMapper.editAreaById(map);
                if (editAreaById<1){
                    throw new Exception("修改工厂失败");
                }

                //修改所有属于这个工厂的所有区域的所有车间的公司
                //6.得到修改之后的公司和区域编号一起装入map
                map.put("areaCodeList",areaCodeList);
                Integer editPlantByAreaCode = cMesPlantTMapper.editPlantByAreaCode(map);
                if (editPlantByAreaCode<1){
                    throw new Exception("修改工厂失败");
                }
            }

        }
        return Rjson.success();
    }

    @Override
    public Rjson delFactoryByIdAndCode(CMesFactoryT factoryT) throws Exception {
        CMesCustomProperty customProperty = new CMesCustomProperty();
        customProperty.setBindScopeValue(factoryT.getFactoryCode());
        //删除类型
        customProperty.setObjectType(CustomAttributesConstant.factory);

        Integer integer = customPropertyService.delCustomPropertyByBindScopeValueAndObjectType(customProperty);
        if (integer<1){
            throw new Exception("删除自定义属性失败");
        }

        Integer factory = cMesFactoryTMapper.delFactoryByIdAndCode(factoryT);
        if (factory<1){
            throw new Exception("删除工厂失败");
        }

        //先查一遍是否有区域
        List<CMesAreaT> areaTList = cMesAreaTMapper.selectAreaByFactoryCode(factoryT.getFactoryCode());
        if(areaTList.size()>0){
            for (CMesAreaT areaT : areaTList) {
                //1.获取属于当前工厂的区域表中的工厂编号
                CMesAreaT cMesAreaT = new CMesAreaT();
                cMesAreaT.setFactoryCode(areaT.getFactoryCode());
                //2.删除区域表中的属于当前工厂的记录
                Integer delArea = cMesAreaTMapper.delAreaByIdAndCode(cMesAreaT);
                if (delArea<1){
                    throw new Exception("删除工厂失败");
                }
                //3.删除车间表中属于当前工厂的记录
                //先查一遍是否有车间
                List<CMesPlantT> cMesPlantTList = cMesPlantTMapper.selectPlantByAreaCode(areaT.getAreaCode());
                if (cMesPlantTList.size()>0){
                    //同时把车间的对应数据删除
                    CMesPlantT cMesPlantT = new CMesPlantT();
                    cMesPlantT.setAreaCode(areaT.getAreaCode());
                    Integer plantDel = cMesPlantTMapper.delPlantByIdAndCode(cMesPlantT);
                    if (plantDel<1){
                        throw new Exception("删除工厂失败");
                    }
                }
            }

        }
        return Rjson.success();
    }
    @Override
    public Rjson findFactoryList(CMesFactoryTReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cMesFactoryTMapper.findFactoryList(req)));
    }

	@Override
	public Rjson findDepartmentData(CWmsDepartmentTReq req) {
		return Rjson.success(new PageInfo<>(cMesFactoryTMapper.findDepartmentData(req)));
	}
}
