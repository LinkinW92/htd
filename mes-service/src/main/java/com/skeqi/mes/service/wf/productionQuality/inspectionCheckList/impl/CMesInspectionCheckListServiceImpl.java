package com.skeqi.mes.service.wf.productionQuality.inspectionCheckList.impl;

import com.skeqi.mes.mapper.wf.productionQuality.inspectionCheckList.CMesInspectionCheckListDao;
import com.skeqi.mes.service.wf.productionQuality.inspectionCheckList.CMesInspectionCheckListService;
import com.skeqi.mes.util.Rjson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CMesInspectionCheckListServiceImpl implements CMesInspectionCheckListService {
    @Resource
    private CMesInspectionCheckListDao dao;
    @Override
    public List<Map<String,Object>> findCheckList(Map<String,Object> map) {
        return dao.findCheckList(map);
    }

    @Override
    public List<Map<String, Object>> findCheckListDetail(String checkListCode) {
        return dao.findCheckListDetail(checkListCode);
    }

    @Override
    public Integer getNextCheckListCode() {
        return dao.getNextCheckListCode();
    }

    @Override
    public Rjson addCheckList(Map<String, Object> map) throws Exception {
        //验证清单编号唯一
        Integer code = dao.selectCheckListByCode(map.get("code").toString());
        if (code>0){
            throw new Exception("清单编号以存在");
        }
        //1.新增清单表
        Integer checkList = dao.addCheckList(map);
        if (checkList<1){
            throw new Exception("新增清单失败");
        }
        //2.新增清单详情
        Integer checkListDetail = dao.addCheckListDetail(map);
        if (checkListDetail<1){
            throw new Exception("新增清单明细失败");
        }

        return Rjson.success();
    }

    @Override
    public Rjson addCheckListDetail(Map<String, Object> map) throws Exception {
        //验证版本号唯一
        List<Map<String, Object>> versions = dao.selectCheckListDetailByVersions(map);
        if (versions.size()>0){
            throw new Exception("当前清单已存在该版本号");
        }
        //判断当前状态是否是开启
        if (map.get("start").equals("1")){
            //修改其他版本的开启状态
            List<Map<String, Object>> codeList = dao.findCheckListDetail(map.get("code").toString());
            if (codeList.size()>0) {
                //移除当前版本
                codeList.removeIf(s -> s.get("VERSIONS").equals(map.get("versions")));
                //收集其他版本的版本号
                List<String> versionList = new ArrayList<>();
                codeList.forEach(objectMap -> versionList.add((String) objectMap.get("VERSIONS")));
                //根据版本号修改版本状态
                if (versionList.size()>0){
                    Integer editStart = dao.editCheckListDetailByVersions(versionList,map.get("code").toString());
                    if (editStart<1){
                        throw new Exception("添加清单详情失败");
                    }
                }
            }
        }
        Integer checkListDetail = dao.addCheckListDetail(map);
        if (checkListDetail<1){
            throw new Exception("添加清单详情失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson editCheckListDetail(Map<String, Object> map) throws Exception {
        //新增清单版本重复覆盖版本标记
        if (map.get("flag")==null||map.get("flag").equals("")){
            //验证版本号唯一
            List<Map<String, Object>> versions = dao.selectCheckListDetailByVersions(map);
            if (versions.size()>0){
                throw new Exception("当前清单已存在该版本号");
            }
        }

        //判断当前状态是否是开启
        if (map.get("start").equals("1")){
            //修改其他版本的开启状态
            List<Map<String, Object>> codeList = dao.findCheckListDetail(map.get("code").toString());
            if (codeList.size()>0) {
                //移除当前版本
                codeList.removeIf(s -> s.get("VERSIONS").equals(map.get("versions")));
                //收集其他版本的版本号
                List<String> versionList = new ArrayList<>();
                codeList.forEach(objectMap -> versionList.add((String) objectMap.get("VERSIONS")));
                //根据版本号修改版本状态
                if (versionList.size()>0){
                    Integer editStart = dao.editCheckListDetailByVersions(versionList,map.get("code").toString());
                    if (editStart<1){
                        throw new Exception("修改清单详情失败");
                    }
                }
            }
        }
        Integer checkListDetail = dao.editCheckListDetailById(map);
        if (checkListDetail<1){
            throw new Exception("修改清单详情失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson addCheckListBatch(List<Map<String, Object>> data) throws Exception {
        Integer integer = dao.addCheckListBatch(data);
        if (integer<1){
            throw new Exception("导入清单失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson addCheckListDetailBatch(List<Map<String, Object>> data) throws Exception {
        Integer integer = dao.addCheckListDetailBatch(data);
        if (integer<1){
            throw new Exception("导入清单详情失败");
        }
        return Rjson.success();
    }

    @Override
    public List<Map<String, Object>> findCheckListDetailAll(List<String> codeArray) {
        return dao.findCheckListDetailAll(codeArray);
    }

    @Override
    public Integer findCheckListDetailByCodeAndVersions(Map<String, Object> objectMap) {
        return dao.findCheckListDetailByCodeAndVersions(objectMap);
    }

    @Override
    public Integer findCheckListByCodeAndType(Map<String, Object> objectMap) {
        return dao.findCheckListByCodeAndType(objectMap);
    }

    @Override
    public List<Map<String,Object>> findCheckListByProduceType(Map<String, Object> map) {
        return dao.findCheckListByProduceType(map);
    }

    @Override
    public List<Map<String, Object>> selectCheckListDetailByVersions(Map<String, Object> hashMap) {
        return dao.selectCheckListDetailByVersions(hashMap);
    }

    @Override
    public Rjson editCheckList(Map<String, Object> map) throws Exception {
        //验证添加还是修改
        List<Map<String, Object>> versions = dao.selectCheckListDetailByVersions(map);
        if (versions.size()>0){
            //修改
            //修改其他版本的开启状态
            List<Map<String, Object>> codeList = dao.findCheckListDetail(map.get("code").toString());
            if (codeList.size()>0) {
                //移除当前版本
                codeList.removeIf(s -> s.get("VERSIONS").equals(map.get("versions")));
                //收集其他版本的版本号
                List<String> versionList = new ArrayList<>();
                codeList.forEach(objectMap -> versionList.add((String) objectMap.get("VERSIONS")));
                //根据版本号修改版本状态
                if (versionList.size()>0){
                    Integer editStart = dao.editCheckListDetailByVersions(versionList,map.get("code").toString());
                    if (editStart<1){
                        throw new Exception("编辑清单失败");
                    }
                }
            }
            //修改本身版本信息
            Integer checkListDetail = dao.editCheckListDetail(map);
            if (checkListDetail<1){
                throw new Exception("编辑清单失败");
            }
        }else {
            //新增
            Integer checkListDetail = dao.addCheckListDetail(map);
            if (checkListDetail<1){
                throw new Exception("编辑清单失败");
            }
        }
        //3.修改清单
        Integer integer = dao.editCheckList(map);
        if (integer<1){
            throw new Exception("编辑清单失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson editCheckListDetailByStart(Map<String, Object> map) throws Exception {
        //修改其他版本的开启状态
        List<Map<String, Object>> codeList = dao.findCheckListDetail(map.get("code").toString());
        if (codeList.size()>0) {
            //移除当前版本
            codeList.removeIf(s -> s.get("VERSIONS").equals(map.get("versions")));
            //收集其他版本的版本号
            List<String> versionList = new ArrayList<>();
            codeList.forEach(objectMap -> versionList.add((String) objectMap.get("VERSIONS")));
            //根据版本号修改版本状态
            if (versionList.size()>0){
                Integer editStart = dao.editCheckListDetailByVersions(versionList,map.get("code").toString());
                if (editStart<1){
                    throw new Exception("启用清单详情失败");
                }
            }
        }
        Integer integer = dao.editCheckListDetailByStart(map);
        if (integer<1){
            throw new Exception("启用清单详情失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson deleteCheckList(Map<String, Object> map) throws Exception {
        Integer checkList = dao.deleteCheckList(map);
        if (checkList<1) {
            throw  new Exception("删除清单失败");
        }
        List<Map<String, Object>> codeList = dao.findCheckListDetail(map.get("code").toString());
        if (codeList.size()>0){
            Integer checkListDetail = dao.updateCheckListDetail(map);
            if (checkListDetail<1) {
                throw  new Exception("删除清单明细失败");
            }
        }
        return Rjson.success();
    }

    @Override
    public Rjson deleteCheckListDetail(Map<String, Object> map) throws Exception {
        Integer checkListDetail = dao.deleteCheckListDetail(map);
        if (checkListDetail<1) {
            throw  new Exception("删除清单明细失败");
        }
        return Rjson.success();
    }
}
