package com.skeqi.mes.mapper.wf;

import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductionPageDao {
    List<Map<String, Object>> findProduction();

    List<Map<String, Object>> findOnlineWorkOrder();

    List<Map<String, Object>> findEvent();


    List<Map<String, Object>> findOnlineShiftTeam();

    List<Map<String, Object>> finishedProduct();
}
