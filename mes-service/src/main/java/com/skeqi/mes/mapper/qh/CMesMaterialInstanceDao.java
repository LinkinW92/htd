package com.skeqi.mes.mapper.qh;

import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import java.util.List;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialResponseT;
import org.apache.ibatis.annotations.Param;

public interface CMesMaterialInstanceDao {
    List<CMesMaterialInstanceT> findAllMaterialInstanceList(CMesMaterialInstanceT materialInstanceT);

    Integer addMaterialInstance(CMesMaterialInstanceT cMesMaterialInstanceT);

    Integer updateMaterialInstance(CMesMaterialInstanceT c);

    Integer deleteMaterialInstance(Integer id);

    List<CMesMaterialInstanceT> findMaterialInstanceByCodeOrId(CMesMaterialInstanceT cMesMaterialInstanceT);

    List<CMesMaterialInstanceT> findMaterialByProductIDAndWorkOrderId(@Param("productId") Integer productId, @Param("workOrderId") String workOrderId);

    List<CMesMaterialInstanceT> findMaterialById(@Param("id") Integer id);

    List<CMesMaterialInstanceT> findMaterialInstanceByCodeAndBatchAndSn(List<RLslMaterialResponseT> rLslMaterialResponseTS);

    Integer updateMaterialInstanceByRLslMaterialResponseTS(@Param("list") List<RLslMaterialResponseT> rLslMaterialResponseTS);

    Integer freezeInventory(CMesMaterialInstanceT c);
}
