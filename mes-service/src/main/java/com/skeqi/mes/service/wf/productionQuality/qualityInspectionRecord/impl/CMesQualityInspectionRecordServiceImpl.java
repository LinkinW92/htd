package com.skeqi.mes.service.wf.productionQuality.qualityInspectionRecord.impl;

import com.skeqi.mes.mapper.qh.PMesTrackingTMapper;
import com.skeqi.mes.mapper.wf.productionQuality.qualityInspectionRecord.CMesQualityInspectionRecordDao;
import com.skeqi.mes.mapper.zch.EventDao;
import com.skeqi.mes.service.wf.productionQuality.qualityInspectionRecord.CMesQualityInspectionRecordService;
import com.skeqi.mes.util.Rjson;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CMesQualityInspectionRecordServiceImpl implements CMesQualityInspectionRecordService {
    @Resource
    private CMesQualityInspectionRecordDao dao;

    @Resource
    private PMesTrackingTMapper pMesTrackingTMapper;

    @Resource
    private EventDao eventDao;
    @Override
    public List<Map<String, Object>> findQuality(Map<String, Object> map) {
        return dao.findQuality(map);
    }

    @Override
    public List<Map<String, Object>> findCheckListDetailBySN(String sn, String type) {
        List<Map<String, Object>> checkListDetailBySN = dao.findCheckListDetailBySN(sn, type);
        return checkListDetailBySN;
    }

    @Override
    public Rjson addQuality(Map<String, Object> map) throws Exception {
        //1.验证质检编号是否重复
        Integer code = dao.findQualityByCode(map.get("code").toString());
        if (code>0){
            throw new Exception("质检编号重复");
        }

        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("contentList");

        if (list.size()>0){
            //2.添加质检记录检查内容表
            Integer checkContent = dao.addCheckContent(map);
            if (checkContent<1){
                throw new Exception("质检记录检查内容添加失败");
            }
        }

        //3.下线表产品NG数据标记
        if (map.get("start").equals("NG")){
            Integer integer = pMesTrackingTMapper.editPMesTrackingBySN(map);
            if (integer<1){
                throw new Exception("产品切换状态失败");
            }
        }

        //4.添加质检记录表
        //执行新增质检记录
        Integer addQuality = dao.addQuality(map);
        if (addQuality<1) {
            throw new Exception("质检记录添加失败");
        }

        //5.添加事件记录
        String js = JSONArray.fromObject(list).toString();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("EVENT_DIS","质检"+map.get("start"));
        objectMap.put("OBJECT_TYPE","物料");
        objectMap.put("OBJECT_ID",map.get("sn"));
        objectMap.put("EVENT","质检");
        objectMap.put("OPERATOR",map.get("qcPersonnel"));
        objectMap.put("PARAMETER1",map.get("type"));
        objectMap.put("PARAMETER2",js);
        Integer integer = eventDao.addEvent(objectMap);
        if (integer<1) {
            return Rjson.success("质检事件记录添加失败");
        }
        return Rjson.success();
    }

    @Override
    public List<Map<String, Object>> findCheckContent(Map<String, Object> map) {
        return dao.findCheckContent(map);
    }

    @Override
    public Rjson auditQuality(Map<String, Object> map) throws Exception {
        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("contentList");
        if (list.size()>0){
            //1.修改质检记录检查内容表
            for (Map<String, Object> objectMap : list) {
                Integer checkContent = dao.editCheckContent(objectMap);
                if (checkContent < 1) {
                    throw new Exception("质检记录检查内容审核失败");
                }
            }
        }

        //2.修改质检记录表
        //执行修改质检记录
        Integer auditQuality = dao.auditQuality(map);
        if (auditQuality<1) {
            throw new Exception("质检记录审核失败");
        }

        //3.下线表产品NG数据标记
        if (map.get("resultLead").equals("OK")){
            map.put("start","OK");
            Integer integer = pMesTrackingTMapper.editPMesTrackingBySN(map);
            if (integer<1){
                throw new Exception("产品切换状态失败");
            }
        }

        //4.添加审核事件记录
        String js = JSONArray.fromObject(list).toString();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("EVENT_DIS","质检审核"+map.get("start"));
        objectMap.put("OBJECT_TYPE","物料");
        objectMap.put("OBJECT_ID",map.get("sn"));
        objectMap.put("EVENT","质检审核");
        objectMap.put("OPERATOR",map.get("qcLead"));
        objectMap.put("PARAMETER1",map.get("resultLead"));
        objectMap.put("PARAMETER2",js);
        Integer integer = eventDao.addEvent(objectMap);
        if (integer<1) {
            return Rjson.success("质检事件记录添加失败");
        }

        return Rjson.success();
    }

    @Override
    public List<Map<String, Object>> findQualityAll(Map<String, Object> map) {
        return dao.findQualityAll(map);
    }

    @Override
    public List<Map<String, Object>> findDisposeQuality(Map<String, Object> map) {
        return dao.findDisposeQuality(map);
    }
}
