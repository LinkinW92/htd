package com.skeqi.mes.service.wf;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author Lenovo
 */
public interface CMesCustomPropertyService{
    List<CMesCustomProperty> findCustomProperty(CMesCustomProperty customProperty);

    Rjson addCustomProperty(CMesCustomProperty customProperty) throws Exception;

    Rjson editCustomProperty(CMesCustomProperty customProperty) throws Exception;

    Rjson delCustomProperty(Integer id) throws Exception;

    List<CMesCustomProperty> findCustomPropertyAll(CMesCustomProperty cMesCustomProperty);

    /**
     * 按对象删除
     * @param cMesCustomProperty
     * @return
     */
    Integer delCustomPropertyByBindScopeValueAndObjectType(CMesCustomProperty cMesCustomProperty) throws Exception;
}
