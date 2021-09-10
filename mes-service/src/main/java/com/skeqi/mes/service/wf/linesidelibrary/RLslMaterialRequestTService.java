package com.skeqi.mes.service.wf.linesidelibrary;

import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.wf.linesidelibrary.CLslRockConfigT;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;
import com.skeqi.mes.util.Rjson;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
public interface RLslMaterialRequestTService{

    List<RLslMaterialRequestT> findMaterialRequest(List<CLslRockConfigT> resultList);

    List<RLslMaterialRequestT> addMaterialRequest(List<CLslRockConfigT> resultList) throws Exception;

    List<RLslMaterialRequestT> findMaterialRequestByStatus(List<Integer> statusList);

    Integer materialRequestConfirmReceipt(Map<String, Object> map) throws Exception;

    List<RLslMaterialRequestT> findMaterialRequestByBillNoList(List<RLslMaterialRequestT> result);

    Integer updateMaterialRequestCancelTask(RLslMaterialRequestT r) throws Exception;

    List<RLslMaterialRequestT> findMaterialRequestAll(RLslMaterialRequestT requestT);

    Integer rejectMaterialRequest(RLslMaterialRequestT rLslMaterialRequestT) throws Exception;
}
