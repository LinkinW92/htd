package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/11 22:10
 * @Description TODO
 */
public interface RetrospectiveDao {

    List<Map> listParam(@Param("whereList") Map whereList);
    List<Map> listMaterial(@Param("whereList") Map whereList);
    List<Map> listParent(@Param("sn") String sn);
    List<Map> listChildren(@Param("sn") String sn);
}
