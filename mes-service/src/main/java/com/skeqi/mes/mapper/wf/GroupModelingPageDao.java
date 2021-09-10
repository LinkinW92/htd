package com.skeqi.mes.mapper.wf;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GroupModelingPageDao {

    @Select("select * from c_mes_line_t")
    List<Map<String, Object>> findProductionLine();

    @Select("select * from c_mes_station_t")
    List<Map<String, Object>> findStation();

}
