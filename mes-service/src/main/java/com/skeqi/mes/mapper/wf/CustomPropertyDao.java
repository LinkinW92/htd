package com.skeqi.mes.mapper.wf;

import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.pojo.qh.CMesCustomPropertyValue;

import java.util.List;
import java.util.Map;

public interface CustomPropertyDao {

    List<CMesCustomProperty> findCustomProperty(CMesCustomProperty customProperty);

    Integer addCustomProperty(CMesCustomProperty customProperty);

    Integer selectByName(CMesCustomProperty customProperty);

    Integer editCustomProperty(CMesCustomProperty customProperty);

    Integer editCustomPropertyValue(Map<String, Object> objectMap);

    Integer delCustomProperty(Map<String, Object> idMap);

    Integer delCustomPropertyValue(Map<String, Object> idMap);

    List<Integer> selectCustomPropertyAllId(CMesCustomProperty customProperty);

    CMesCustomProperty selectById(CMesCustomProperty customProperty);

    Integer delCustomPropertyValueAll(CMesCustomPropertyValue customProperty);

    Integer editCustomPropertyValueByBindScopeKey(CMesCustomPropertyValue cp);

    Integer addCustomPropertyValueByObjectCode(Map<String, Object> stringObjectHashMap);

    Integer editCustomPropertyValueByObjectCode(Map<String, Object> objectMap);

    Integer addCustomPropertyValue(Map<String, Object> map);

    List<Integer> selectCustomPropertyValueAll(CMesCustomProperty customProperty);

    List<CMesCustomProperty> findCustomPropertyAll(CMesCustomProperty cMesCustomProperty);

    Integer selectByEnglishName(CMesCustomProperty customProperty);
}
