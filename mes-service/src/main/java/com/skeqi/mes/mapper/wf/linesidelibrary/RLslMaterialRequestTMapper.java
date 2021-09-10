package com.skeqi.mes.mapper.wf.linesidelibrary;

import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.wf.linesidelibrary.CLslRockConfigT;import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface RLslMaterialRequestTMapper {
    List<RLslMaterialRequestT> findMaterialRequest(List<CLslRockConfigT> resultList);

    Integer addMaterialRequest(List<RLslMaterialRequestT> rLslMaterialRequestTS);

    List<RLslMaterialRequestT> findMaterialRequestByStatus(@Param("statusList") List<Integer> statusList);

    Integer findMaterialRequestByBillNo(@Param("billNo") String billNo,@Param("statusList")List<Integer> statusList);

    Integer updateByIdsAndStatus(RLslMaterialRequestT rLslMaterialRequestT);

    RLslMaterialRequestT selectMaterialRequest();

    List<RLslMaterialRequestT> findMaterialRequestByBillNoList(List<RLslMaterialRequestT> rLslMaterialRequestTS);

    Integer updateMaterialRequestCancelTask(RLslMaterialRequestT r);

    List<RLslMaterialRequestT> findMaterialRequestAll(RLslMaterialRequestT requestT);

    Integer rejectMaterialRequest(RLslMaterialRequestT r);
}
