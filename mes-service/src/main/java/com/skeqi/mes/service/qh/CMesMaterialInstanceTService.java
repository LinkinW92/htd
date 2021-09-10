package com.skeqi.mes.service.qh;

import java.util.List;
import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import com.skeqi.mes.util.Rjson;

public interface CMesMaterialInstanceTService{

    List<CMesMaterialInstanceT> findAllMaterialInstance(CMesMaterialInstanceT materialInstanceT);

    Rjson addMaterialInstance(CMesMaterialInstanceT c,String userName) throws Exception;

    Rjson updateMaterialInstance(CMesMaterialInstanceT c, String userName) throws Exception;

    Rjson deleteMaterialInstance(CMesMaterialInstanceT id, String userName) throws Exception;

    List<CMesMaterialInstanceT> findMaterialInstanceByCodeOrId(CMesMaterialInstanceT cMesMaterialInstanceT);

    List<CMesMaterialInstanceT> findMaterialByProductIDAndWorkOrderId(Integer productId, String workOrderId);

    List<CMesMaterialInstanceT> findMaterialById(Integer id);

    Integer freezeInventory(CMesMaterialInstanceT c);
}
