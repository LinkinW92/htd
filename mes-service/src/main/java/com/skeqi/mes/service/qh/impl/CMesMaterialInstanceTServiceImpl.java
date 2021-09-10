package com.skeqi.mes.service.qh.impl;

import com.skeqi.mes.mapper.zch.EventDao;
import com.skeqi.mes.util.Rjson;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import com.skeqi.mes.mapper.qh.CMesMaterialInstanceDao;
import com.skeqi.mes.service.qh.CMesMaterialInstanceTService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CMesMaterialInstanceTServiceImpl implements CMesMaterialInstanceTService{

    @Resource
    private CMesMaterialInstanceDao cMesMaterialInstanceDao;

    @Resource
    private EventDao eventDao;

    @Override
    public List<CMesMaterialInstanceT> findAllMaterialInstance(CMesMaterialInstanceT materialInstanceT) {
        return cMesMaterialInstanceDao.findAllMaterialInstanceList(materialInstanceT);
    }

    @Override
    public Rjson addMaterialInstance(CMesMaterialInstanceT c,String userName) throws Exception {
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("DT",new Date());
        obj.put("OBJECT_TYPE","物料");
        obj.put(
                "OBJECT_ID",
                String.valueOf(
                        (c.getMaterialCode()==null?"":c.getMaterialCode())+
                        (c.getMaterialBatch()==null?"":c.getMaterialBatch())+
                        (c.getMaterialSn()==null?"":c.getMaterialSn())
                )
        );
        obj.put("EVENT","创建");
        obj.put("PARAMETER1","");
        obj.put("PARAMETER2","");
        obj.put("PARAMETER3","");
        obj.put("OPERATOR",userName);
        obj.put("EVENT_DIS","物料实例创建json");
        Integer integer = eventDao.addEvent(obj);
        if (integer <1) {
            throw new Exception("实例添加失败");
        }
        Integer integer1 = cMesMaterialInstanceDao.addMaterialInstance(c);
        if (integer1 <1) {
            throw new Exception("实例添加失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson updateMaterialInstance(CMesMaterialInstanceT c, String userName) throws Exception {
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("DT",new Date());
        obj.put("OBJECT_TYPE","物料");
        obj.put(
                "OBJECT_ID",
                String.valueOf(
                        (c.getMaterialCode()==null?"":c.getMaterialCode())+
                                (c.getMaterialBatch()==null?"":c.getMaterialBatch())+
                                (c.getMaterialSn()==null?"":c.getMaterialSn())
                )
        );
        obj.put("EVENT","修改");
        obj.put("PARAMETER1","");
        obj.put("PARAMETER2","");
        obj.put("PARAMETER3","");
        obj.put("OPERATOR",userName);
        obj.put("EVENT_DIS","物料实例修改json");
        Integer integer = eventDao.addEvent(obj);
        if (integer <1) {
            throw new Exception("实例修改失败");
        }
        Integer integer1 = cMesMaterialInstanceDao.updateMaterialInstance(c);
        if (integer1 <1) {
            throw new Exception("实例修改失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson deleteMaterialInstance(CMesMaterialInstanceT c, String userName) throws Exception {
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("DT",new Date());
        obj.put("OBJECT_TYPE","物料");
        obj.put(
                "OBJECT_ID",
                String.valueOf(
                        (c.getMaterialCode()==null?"":c.getMaterialCode())+
                                (c.getMaterialBatch()==null?"":c.getMaterialBatch())+
                                (c.getMaterialSn()==null?"":c.getMaterialSn())
                )
        );
        obj.put("EVENT","删除");
        obj.put("PARAMETER1","");
        obj.put("PARAMETER2","");
        obj.put("PARAMETER3","");
        obj.put("OPERATOR",userName);
        obj.put("EVENT_DIS","物料实例删除json");
        Integer integer = eventDao.addEvent(obj);
        if (integer <1) {
            throw new Exception("实例删除失败");
        }
        Integer integer1 = cMesMaterialInstanceDao.deleteMaterialInstance(c.getId());
        if (integer1 <1) {
            throw new Exception("实例删除失败");
        }
        return Rjson.success();
    }

    @Override
    public List<CMesMaterialInstanceT> findMaterialInstanceByCodeOrId(CMesMaterialInstanceT cMesMaterialInstanceT) {
        return cMesMaterialInstanceDao.findMaterialInstanceByCodeOrId(cMesMaterialInstanceT);
    }

    @Override
    public List<CMesMaterialInstanceT> findMaterialByProductIDAndWorkOrderId(Integer productId, String workOrderId) {
        return cMesMaterialInstanceDao.findMaterialByProductIDAndWorkOrderId(productId,workOrderId);
    }

    @Override
    public List<CMesMaterialInstanceT> findMaterialById(Integer id) {
        return cMesMaterialInstanceDao.findMaterialById(id);
    }

    @Override
    public Integer freezeInventory(CMesMaterialInstanceT c) {
        return cMesMaterialInstanceDao.freezeInventory(c);
    }
}
